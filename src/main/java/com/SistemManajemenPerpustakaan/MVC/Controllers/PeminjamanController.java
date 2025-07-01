// INI JUAN YANG KASI KOMEN
// CONTROLLER UNTUK LOGIKA PINJAM DAN KEMBALIKAN BUKU

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

    // HANDLE LOGIKA UNTUK PINJAM BUKU
    // VALIDASI USER, KUOTA, DAN STATUS BUKU
    public static void handleTambahPeminjaman() {
        Anggota penggunaSaatIni = (Anggota) LoginController.getPenggunaSaatIni();
        if (penggunaSaatIni == null) {
            view.tampilkanPesan("Error: Tidak ada pengguna yang login.");
            return;
        }

        // CEK APAKAH ADA BUKU DAN ADA YANG TERSEDIA
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

        // CEK APAKAH USER SUDAH MAKSIMAL MEMINJAM
        if (penggunaSaatIni.getJumlahPinjam() >= penggunaSaatIni.getMaksimalPinjam()) {
            String pesanLimit = "Peminjaman ditolak! Anda telah mencapai batas maksimal.";
            if (penggunaSaatIni.getMaksimalPinjam() < 3) {
                pesanLimit += " Batas Anda dikurangi karena keterlambatan.";
            }
            view.tampilkanPesan(pesanLimit);
            return;
        }

        // TAMPILKAN BUKU YANG BISA DIPINJAM
        view.tampilkanBukuTersedia(bukuTersedia);
        int pilihan = view.mintaPilihanBuku("pinjam");

        // VALIDASI INPUT USER
        if (pilihan <= 0 || pilihan > bukuTersedia.size()) {
            view.tampilkanPesan("Pilihan tidak valid.");
            return;
        }

        Buku bukuDipilih = bukuTersedia.get(pilihan - 1);

        // BUAT DTO PEMINJAMAN UNTUK DISIMPAN
        PeminjamanDTO peminjamanDTO = new PeminjamanDTO();
        peminjamanDTO.id = IdGenerator.generateUniqueId(ambilSemuaPeminjaman(), Peminjaman::getId);
        peminjamanDTO.idAnggota = penggunaSaatIni.getId();
        peminjamanDTO.kodeBuku = bukuDipilih.getKode();
        peminjamanDTO.tanggalPinjam = DateTimeTools.getTanggalHariIni();
        peminjamanDTO.deadline = DateTimeTools.buatDeadline(7);
        peminjamanDTO.tanggalKembali = "-";
        peminjamanDTO.status = "Belum Dikembalikan";

        // SIMPAN TRANSAKSI DAN UPDATE SEMUA ATRIBUT TERKAIT
        tambahPeminjaman(peminjamanDTO);
        BukuController.updateAtribut(bukuDipilih.getKode(), "tersedia", false);
        PenggunaController.updateAtribut(penggunaSaatIni.getId(), "jumlahPinjam", penggunaSaatIni.getJumlahPinjam() + 1);
        LoginController.refreshPenggunaSaatIni(PenggunaController.ambilPengguna(penggunaSaatIni.getId()));

        // KASIH PESAN SUKSES + DEADLINE
        view.tampilkanPesan("Peminjaman Buku Berhasil! Deadline: " + peminjamanDTO.deadline);
    }

    // HANDLE LOGIKA UNTUK KEMBALIKAN BUKU
    // CEK STATUS, UPDATE ATRIBUT, BERES
    public static void handleKembalikanBuku() {
        Anggota penggunaSaatIni = (Anggota) LoginController.getPenggunaSaatIni();
        if (penggunaSaatIni == null) {
            view.tampilkanPesan("Error: Tidak ada pengguna yang login.");
            return;
        }

        // FILTER BUKU YANG MASIH BELUM DIKEMBALIKAN
        List<Peminjaman> pinjamanAktif = ambilSemuaPeminjaman().stream()
                .filter(p -> p.getIdAnggota().equals(penggunaSaatIni.getId()) && p.getStatus().equals("Belum Dikembalikan"))
                .collect(Collectors.toList());

        if (pinjamanAktif.isEmpty()) {
            view.tampilkanPesan("Anda tidak memiliki buku yang sedang dipinjam.");
            return;
        }

        // SIAPKAN TAMPILAN DETAIL PINJAMAN
        List<PeminjamanView.PeminjamanDetailDisplay> daftarTampilan = new ArrayList<>();
        for (Peminjaman p : pinjamanAktif) {
            Buku b = BukuController.ambilBuku(p.getKodebuku());
            String judul = (b != null) ? b.getJudul() : "Buku Tidak Ditemukan";
            long sisaHari = DateTimeTools.sisaHariMenujuDeadline(p.getDeadline());
            String statusDeadline = (sisaHari < 0)
                    ? "(Terlambat " + (-sisaHari) + " hari)"
                    : "(Sisa " + sisaHari + " hari)";
            daftarTampilan.add(new PeminjamanView.PeminjamanDetailDisplay(judul, statusDeadline));
        }

        // PILIH BUKU YANG INGIN DIKEMBALIKAN
        view.tampilkanDaftarPinjamanPengguna(daftarTampilan);
        int pilihan = view.mintaPilihanBuku("kembalikan");

        if (pilihan <= 0 || pilihan > pinjamanAktif.size()) {
            view.tampilkanPesan("Pilihan tidak valid.");
            return;
        }

        Peminjaman peminjamanDipilih = pinjamanAktif.get(pilihan - 1);
        long sisaHari = DateTimeTools.sisaHariMenujuDeadline(peminjamanDipilih.getDeadline());

        // JIKA TERLAMBAT, MAKA DIKURANGI BATAS PINJAM
        if (sisaHari < 0) {
            PenggunaController.updateAtribut(penggunaSaatIni.getId(), "maksimalPinjam", penggunaSaatIni.getMaksimalPinjam() - 1);
            PenggunaController.updateAtribut(penggunaSaatIni.getId(), "terlambat", true);
            view.tampilkanPesan("Anda terlambat! Batas maksimal dipotong.");
        } else {
            view.tampilkanPesan("Terima kasih! Pengembalian tepat waktu.");
        }

        // UPDATE STATUS DAN DATA LAINNYA
        updateAtribut(peminjamanDipilih.getId(), "tanggalKembali", DateTimeTools.getTanggalHariIni());
        updateAtribut(peminjamanDipilih.getId(), "status", "Sudah dikembalikan");
        BukuController.updateAtribut(peminjamanDipilih.getKodebuku(), "tersedia", true);
        PenggunaController.updateAtribut(penggunaSaatIni.getId(), "jumlahPinjam", penggunaSaatIni.getJumlahPinjam() - 1);
        LoginController.refreshPenggunaSaatIni(penggunaSaatIni);
    }

    // DIGUNAKAN ADMIN UNTUK MELIHAT SEMUA RIWAYAT PINJAMAN
    public static void handleDetailPeminjaman() {
        while (true) {
            if (ambilSemuaPeminjaman().isEmpty()) {
                view.tampilkanPesan("Belum ada data peminjaman di sistem.");
                return;
            }

            List<Peminjaman> semuaPeminjaman = ambilSemuaPeminjaman();
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

    // ======================== CRUD SECTION ========================

    // TAMBAH DATA PEMINJAMAN KE REPOSITORY
    public static void tambahPeminjaman(PeminjamanDTO dto){
        Peminjaman pengguna = DTOtoModel.toPeminjaman(dto);
        PeminjamanRepository.tambah(pengguna);
    }

    // AMBIL DATA PEMINJAMAN BERDASARKAN ID
    public static Peminjaman ambilPeminjaman(String kodePeminjaman){
        return PeminjamanRepository.ambilPeminjamanById(kodePeminjaman);
    }

    // AMBIL SEMUA DATA PEMINJAMAN YANG ADA
    public static List<Peminjaman> ambilSemuaPeminjaman(){
        return PeminjamanRepository.ambilSemua();
    }

    // UPDATE ATRIBUT SPESIFIK DI DATA PEMINJAMAN
    public static boolean updateAtribut(String kodePeminjaman, String atribut, Object nilaiBaru) {
        return PeminjamanRepository.updateAtribut(kodePeminjaman, atribut, nilaiBaru);
    }

    // UPDATE KESELURUHAN OBJEK PEMINJAMAN
    public static void updatePeminjaman(String kodePeminjaman, PeminjamanDTO dto){
        Peminjaman pengguna = DTOtoModel.toPeminjaman(dto);
        PeminjamanRepository.updatePeminjaman(kodePeminjaman, pengguna);
    }

    // HAPUS DATA PEMINJAMAN DARI SISTEM
    public static void hapusPeminjaman(String kodePeminjaman) {
        PeminjamanRepository.hapus(kodePeminjaman);
    }
}
