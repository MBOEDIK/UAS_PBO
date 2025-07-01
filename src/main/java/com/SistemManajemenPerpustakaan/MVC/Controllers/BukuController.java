package com.SistemManajemenPerpustakaan.MVC.Controllers;

import com.SistemManajemenPerpustakaan.DTOs.BukuDTO;
import com.SistemManajemenPerpustakaan.MVC.Controllers.Factory.DTOtoModel;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Views.Console.*;
import com.SistemManajemenPerpustakaan.Repositories.BukuRepository;
import com.SistemManajemenPerpustakaan.Utils.IdGenerator;
import com.SistemManajemenPerpustakaan.Utils.InformationPrinter;
import com.SistemManajemenPerpustakaan.Utils.IsObjekKosong;
import com.SistemManajemenPerpustakaan.Utils.ObjekIdGetter;

import java.util.List;

public class BukuController {
    private static final BukuView bukuView = new BukuView();
    /**
     * Metode utama yang menjadi "router" untuk semua aksi terkait buku.
     */
    public static void kelolaMenuBuku() {
        boolean berjalan = true;
        while (berjalan) {
            int pilihan = bukuView.tampilkanMenuBuku();
            switch (pilihan) {
                case 1:
                    handleTambahBuku();
                    break;
                case 2:
                    handleDetailBuku();
                    break;
                case 3:
                    handleUpdateBuku();
                    break;
                case 4:
                    handleHapusBuku();
                    break;
                case 5:
                    berjalan = false;
                    break;
                default:
                    bukuView.tampilkanPesan("Pilihan tidak valid! Masukkan angka antara 1 dan 5.");
                    break;
            }
        }
    }

    /**
     * Menangani alur penambahan buku.
     */
    private static void handleTambahBuku() {
        BukuDTO bukuDTO = bukuView.mintaInputBukuBaru();

        if (bukuDTO == null) {
            bukuView.tampilkanPesan("Tambah buku dibatalkan atau input tidak valid.");
            return;
        }

        // Logika bisnis murni: generate ID dan set status default.
        bukuDTO.kode = IdGenerator.generateUniqueId(ambilSemuaBuku(), Buku::getKode);
        bukuDTO.tersedia = true;

        tambahBuku(bukuDTO);
        bukuView.tampilkanPesan("Buku berhasil ditambahkan!");
    }

    /**
     * Menangani alur penampilan detail buku.
     */
    private static void handleDetailBuku() {
        while (true) {
            List<Buku> semuaBuku = ambilSemuaBuku();
            // Pengecekan data kosong dilakukan oleh Controller.
            if (IsObjekKosong.check(semuaBuku, "Buku")) return;

            bukuView.tampilkanDaftarBuku(semuaBuku);

            // Controller memanggil ObjekIdGetter karena utilitas ini memproses input
            // (pilihan nomor) dan mengembalikannya sebagai data (kode buku).
            String kodeBuku = ObjekIdGetter.get(
                    semuaBuku,
                    "\nPilih nomor buku yang ingin dilihat detailnya: ",
                    Buku::getKode);

            if (kodeBuku == null) return; // Pengguna membatalkan

            Buku buku = ambilBuku(kodeBuku);
            bukuView.tampilkanDetailBuku(buku);

            if (!bukuView.mintaKonfirmasi("\nApakah anda ingin melihat detail buku lainnya?")) {
                break;
            }
        }
    }

    /**
     * Menangani alur pembaruan buku.
     */
    private static void handleUpdateBuku() {
        while (true) {
            List<Buku> semuaBuku = ambilSemuaBuku();
            if (IsObjekKosong.check(semuaBuku, "Buku")) return;

            bukuView.tampilkanDaftarBuku(semuaBuku);

            String kodeBuku = ObjekIdGetter.get(
                    semuaBuku,
                    "Pilih nomor buku yang ingin diupdate: ",
                    Buku::getKode);

            if (kodeBuku == null) return; // Pengguna membatalkan

            Buku bukuTarget = ambilBuku(kodeBuku);

            // Controller memanggil InformationPrinter.tampilkanUpdateAtribut karena
            // method ini membutuhkan lambda yang berisi LOGIKA BISNIS untuk update.
            // Lambda ini tidak boleh ada di View.
            InformationPrinter.tampilkanUpdateAtribut(
                    bukuTarget,
                    " ",
                    kodeBuku,
                    // Lambda ini adalah inti dari logika update, harus di Controller.
                    data -> BukuController.updateAtribut(data.id, data.namaAtribut, data.nilaiBaru),
                    0, "kode", "jenis");

            bukuView.tampilkanPesan("Atribut berhasil diupdate.");

            if (!bukuView.mintaKonfirmasi("\nApakah anda ingin meng-update buku lainnya?")) {
                break;
            }
        }
    }

    /**
     * Menangani alur penghapusan buku.
     */
    private static void handleHapusBuku() {
        List<Buku> semuaBuku = ambilSemuaBuku();
        if (IsObjekKosong.check(semuaBuku, "Buku")) return;

        bukuView.tampilkanDaftarBuku(semuaBuku);

        String kodeBuku = ObjekIdGetter.get(
                semuaBuku,
                "Pilih nomor buku yang ingin dihapus: ",
                Buku::getKode);

        if (kodeBuku == null) { // Pengguna membatalkan
            bukuView.tampilkanPesan("Hapus buku dibatalkan.");
            return;
        }

        Buku bukuTarget = ambilBuku(kodeBuku);

        // Pengambilan keputusan (if) dilakukan di Controller berdasarkan hasil dari View.
        boolean dikonfirmasi = bukuView.mintaKonfirmasi("Yakin ingin menghapus buku berjudul \"" + bukuTarget.getJudul() + "\"?");

        if (dikonfirmasi) {
            hapusBuku(kodeBuku);
            bukuView.tampilkanPesan("Hapus buku berhasil!");
        } else {
            bukuView.tampilkanPesan("Hapus buku dibatalkan.");
        }
    }

    //RUN
//    public static void jalankanBukuView(){
//        loop : while (true){
//            int pilihan = BukuView.menuBuku();
//            switch (pilihan){
//                case 1: BukuView.tambahBuku(); break;
//                case 2: if (!IsObjekKosong.check(BukuController.ambilSemuaBuku(), "Buku")) BukuView.detailBuku(); break;
//                case 3: if (!IsObjekKosong.check(BukuController.ambilSemuaBuku(), "Buku")) BukuView.updateBuku(); break;
//                case 4: if (!IsObjekKosong.check(BukuController.ambilSemuaBuku(), "Buku")) BukuView.hapusBuku(); break;
//                case 5: break loop;
//            }
//        }
//    }

    //================================================================================

    //CREATE
    public static void tambahBuku(BukuDTO dto){
        Buku buku = DTOtoModel.toBuku(dto);
        BukuRepository.tambah(buku);
    }

    //READ
    public static Buku ambilBuku(String kodeBuku){
        return  BukuRepository.ambilBukuById(kodeBuku);
    }

    public static List<Buku> ambilSemuaBuku(){
        return BukuRepository.ambilSemua();
    }

    //UPDATE
    public static boolean updateAtribut(String kodeBuku, String atribut, Object nilaiBaru) {
        return BukuRepository.updateAtribut(kodeBuku, atribut, nilaiBaru);
    }

    public static void updateBuku(String kodeBuku, BukuDTO dto){
        Buku buku = DTOtoModel.toBuku(dto);
        BukuRepository.updateBuku(kodeBuku, buku);
    }

    //DELETE
    public static void hapusBuku(String kodeBuku) {
        BukuRepository.hapusBuku(kodeBuku);
    }
}
