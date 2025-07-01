package com.SistemManajemenPerpustakaan.MVC.Controllers;

import com.SistemManajemenPerpustakaan.DTOs.PeminjamanDTO;
import com.SistemManajemenPerpustakaan.MVC.Controllers.Factory.DTOtoModel;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Anggota;
import com.SistemManajemenPerpustakaan.MVC.Views.Console.PeminjamanView;
import com.SistemManajemenPerpustakaan.MVC.Views.GUI.subController.PenggunaListController;
import com.SistemManajemenPerpustakaan.Repositories.PeminjamanRepository;
import com.SistemManajemenPerpustakaan.Utils.DateTimeTools;
import com.SistemManajemenPerpustakaan.Utils.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PeminjamanController {

    private static PeminjamanView view = new PeminjamanView();

    /**
     * Menangani seluruh alur logika untuk menambahkan peminjaman baru.
     */
    public static void handleTambahPeminjaman() {
        Anggota penggunaSaatIni = (Anggota) LoginController.getPenggunaSaatIni();
        if (penggunaSaatIni == null) {
            view.tampilkanPesan("Error: Tidak ada pengguna yang login.");
            return;
        }

        // 1. Validasi dan Pra-kondisi
        if (BukuController.ambilSemuaBuku().isEmpty()) {
            view.tampilkanPesan("Maaf, tidak ada buku di perpustakaan saat ini.");
            return;
        }

        List<Buku> bukuTersedia = BukuController.ambilSemuaBuku().stream()
                .filter(Buku::getTersedia)
                .collect(Collectors.toList());

        if (bukuTersedia.isEmpty()) {
            view.tampilkanPesan("Maaf, semua buku sedang dipinjam.");
            return;
        }

        if (penggunaSaatIni.getJumlahPinjam() >= penggunaSaatIni.getMaksimalPinjam()) {
            String pesanLimit = "Peminjaman ditolak! Anda telah mencapai batas maksimal peminjaman buku (" +
                    penggunaSaatIni.getMaksimalPinjam() + " buku).";
            if (penggunaSaatIni.getMaksimalPinjam() < 3) { // Asumsi batas normal adalah 3 atau lebih
                pesanLimit += " Batas Anda dikurangi karena ada keterlambatan pengembalian sebelumnya.";
            }
            view.tampilkanPesan(pesanLimit);
            return;
        }

        // 2. Interaksi dengan View
        view.tampilkanBukuTersedia(bukuTersedia);
        int pilihan = view.mintaPilihanBuku("pinjam");

        // 3. Proses Input dan Eksekusi Logika
        if (pilihan <= 0 || pilihan > bukuTersedia.size()) {
            view.tampilkanPesan("Pilihan tidak valid.");
            return;
        }

        Buku bukuDipilih = bukuTersedia.get(pilihan - 1);

        // 4. Persiapan DTO dan Update Data
        PeminjamanDTO peminjamanDTO = new PeminjamanDTO();
        peminjamanDTO.id = IdGenerator.generateUniqueId(PeminjamanController.ambilSemuaPeminjaman(), Peminjaman::getId);
        peminjamanDTO.idAnggota = penggunaSaatIni.getId();
        peminjamanDTO.kodeBuku = bukuDipilih.getKode();
        peminjamanDTO.tanggalPinjam = DateTimeTools.getTanggalHariIni();
        peminjamanDTO.deadline = DateTimeTools.buatDeadline(7);
        peminjamanDTO.tanggalKembali = "-";
        peminjamanDTO.status = "Belum Dikembalikan";

        // 5. Lakukan Transaksi ke Controller Lain
        PeminjamanController.tambahPeminjaman(peminjamanDTO);
        BukuController.updateAtribut(bukuDipilih.getKode(), "tersedia", false);
        PenggunaController.updateAtribut(penggunaSaatIni.getId(), "jumlahPinjam", (penggunaSaatIni.getJumlahPinjam() + 1));

        // Refresh data pengguna saat ini setelah update
        LoginController.refreshPenggunaSaatIni(PenggunaController.ambilPengguna(penggunaSaatIni.getId()));

        view.tampilkanPesan("Peminjaman Buku Berhasil! Deadline pengembalian adalah " + peminjamanDTO.deadline + ".");
    }

    /**
     * Menangani seluruh alur logika untuk mengembalikan buku.
     */
    public static void handleKembalikanBuku() {
        Anggota penggunaSaatIni = (Anggota) LoginController.getPenggunaSaatIni();
        if (penggunaSaatIni == null) {
            view.tampilkanPesan("Error: Tidak ada pengguna yang login.");
            return;
        }

        // 1. Persiapan Data untuk Ditampilkan
        List<Peminjaman> pinjamanAktif = PeminjamanController.ambilSemuaPeminjaman().stream()
                .filter(p -> p.getIdAnggota().equals(penggunaSaatIni.getId()) && p.getStatus().equals("Belum Dikembalikan"))
                .collect(Collectors.toList());

        if (pinjamanAktif.isEmpty()) {
            view.tampilkanPesan("Anda tidak memiliki buku yang sedang dipinjam.");
            return;
        }

        // Membuat list data yang siap ditampilkan oleh View
        List<PeminjamanView.PeminjamanDetailDisplay> daftarTampilan = new ArrayList<>();
        for (Peminjaman p : pinjamanAktif) {
            Buku b = BukuController.ambilBuku(p.getKodebuku());
            String judul = (b != null) ? b.getJudul() : "Buku Tidak Ditemukan";
            long sisaHari = DateTimeTools.sisaHariMenujuDeadline(p.getDeadline());
            String statusDeadline = (sisaHari < 0)
                    ? String.format("(Terlambat %d hari)", -sisaHari)
                    : String.format("(Sisa %d hari)", sisaHari);
            daftarTampilan.add(new PeminjamanView.PeminjamanDetailDisplay(judul, statusDeadline));
        }

        // 2. Interaksi dengan View
        view.tampilkanDaftarPinjamanPengguna(daftarTampilan);
        int pilihan = view.mintaPilihanBuku("kembalikan");

        // 3. Proses Input dan Eksekusi Logika
        if (pilihan <= 0 || pilihan > pinjamanAktif.size()) {
            view.tampilkanPesan("Pilihan tidak valid.");
            return;
        }

        Peminjaman peminjamanDipilih = pinjamanAktif.get(pilihan - 1);
        long sisaHari = DateTimeTools.sisaHariMenujuDeadline(peminjamanDipilih.getDeadline());

        // 4. Lakukan Transaksi berdasarkan Kondisi
        if (sisaHari < 0) {
            // Logika jika terlambat
            PenggunaController.updateAtribut(penggunaSaatIni.getId(), "maksimalPinjam", (penggunaSaatIni.getMaksimalPinjam() - 1));
            PenggunaController.updateAtribut(penggunaSaatIni.getId(), "terlambat", true);
            view.tampilkanPesan("Anda terlambat mengembalikan buku. Batas maksimal peminjaman Anda dikurangi 1.");
        } else {
            // Logika jika tepat waktu
            view.tampilkanPesan("Terima kasih telah mengembalikan buku tepat waktu.");
        }

        // Logika yang sama untuk kedua kondisi
        PeminjamanController.updateAtribut(peminjamanDipilih.getId(), "tanggalKembali", DateTimeTools.getTanggalHariIni());
        PeminjamanController.updateAtribut(peminjamanDipilih.getId(), "status", "Sudah dikembalikan");
        BukuController.updateAtribut(peminjamanDipilih.getKodebuku(), "tersedia", true);
        PenggunaController.updateAtribut(penggunaSaatIni.getId(), "jumlahPinjam", (penggunaSaatIni.getJumlahPinjam() - 1));

        // Refresh data pengguna saat ini setelah update
        LoginController.refreshPenggunaSaatIni(penggunaSaatIni);
    }

    /**
     * Menangani alur untuk melihat detail peminjaman (biasanya untuk Admin).
     */
    public static void handleDetailPeminjaman() {
        while (true) {
            if (PeminjamanController.ambilSemuaPeminjaman().isEmpty()) {
                view.tampilkanPesan("Belum ada data peminjaman di sistem.");
                return;
            }

            List<Peminjaman> semuaPeminjaman = PeminjamanController.ambilSemuaPeminjaman();
            view.tampilkanSemuaPeminjaman(semuaPeminjaman);
            int pilihan = view.mintaPilihanPeminjaman("lihat detailnya");

            if (pilihan > 0 && pilihan <= semuaPeminjaman.size()) {
                Peminjaman peminjamanDipilih = semuaPeminjaman.get(pilihan - 1);
                view.tampilkanDetailPeminjaman(peminjamanDipilih);
            } else {
                view.tampilkanPesan("Pilihan tidak valid.");
            }

            if (!view.mintaKonfirmasi("\nApakah anda ingin melihat detail peminjaman lainnya?").equals("Y")) {
                break;
            }
        }
    }

//    //RUN
//    public static void jalankanPeminjamanView(){
//        PeminjamanView.detailPeminjaman();
//    }
//    public static void jalankanPeminjamanView(int x){
//        switch (x){
//            case 1 -> PeminjamanView.tambahPeminjaman();
//            case 2 -> PeminjamanView.kembalikanBukuPeminjaman();
//        }
//    }

    //CREATE
    public static void tambahPeminjaman(PeminjamanDTO dto){
        Peminjaman pengguna = DTOtoModel.toPeminjaman(dto);
        PeminjamanRepository.tambah(pengguna);
    }

    //READ
    public static Peminjaman ambilPeminjaman(String kodePeminjaman){
        return  PeminjamanRepository.ambilPeminjamanById(kodePeminjaman);
    }

    public static List<Peminjaman> ambilSemuaPeminjaman(){
        return PeminjamanRepository.ambilSemua();
    }

    //UPDATE
    public static boolean updateAtribut(String kodePeminjaman, String atribut, Object nilaiBaru) {
        return PeminjamanRepository.updateAtribut(kodePeminjaman, atribut, nilaiBaru);
    }

    public static void updatePeminjaman(String kodePeminjaman, PeminjamanDTO dto){
        Peminjaman pengguna = DTOtoModel.toPeminjaman(dto);
        PeminjamanRepository.updatePeminjaman(kodePeminjaman, pengguna);
    }

    //DELETE
    public static void hapusPeminjaman(String kodePeminjaman) {
        PeminjamanRepository.hapus(kodePeminjaman);
    }
}
