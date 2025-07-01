// INI JUAN YANG KASI KOMEN
// BIAR KODE CONTROLLER INI GAMPANG DIPELAJARI

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

    // OBJEK VIEW UTAMA UNTUK INTERAKSI USER DI MENU BUKU
    private static final BukuView bukuView = new BukuView();

    // MENU UTAMA UNTUK KELOLA BUKU
    // MENANGANI NAVIGASI DAN PILIHAN USER
    public static void kelolaMenuBuku() {
        boolean berjalan = true;
        while (berjalan) {
            int pilihan = bukuView.tampilkanMenuBuku();
            switch (pilihan) {
                case 1:
                    handleTambahBuku(); break;
                case 2:
                    handleDetailBuku(); break;
                case 3:
                    handleUpdateBuku(); break;
                case 4:
                    handleHapusBuku(); break;
                case 5:
                    berjalan = false; break;
                default:
                    bukuView.tampilkanPesan("Pilihan tidak valid! Masukkan angka antara 1 dan 5.");
                    break;
            }
        }
    }

    // MENANGANI PROSES PENAMBAHAN BUKU BARU
    // VALIDASI INPUT DAN SET NILAI DEFAULT
    private static void handleTambahBuku() {
        BukuDTO bukuDTO = bukuView.mintaInputBukuBaru();

        if (bukuDTO == null) {
            bukuView.tampilkanPesan("Tambah buku dibatalkan atau input tidak valid.");
            return;
        }

        // GENERATE KODE UNIK DAN SET TERSEDIA = TRUE
        bukuDTO.kode = IdGenerator.generateUniqueId(ambilSemuaBuku(), Buku::getKode);
        bukuDTO.tersedia = true;

        tambahBuku(bukuDTO);
        bukuView.tampilkanPesan("Buku berhasil ditambahkan!");
    }

    // MENANGANI PROSES MELIHAT DETAIL BUKU
    // BISA LIHAT BEBERAPA KALI SAMPAI USER KELUAR
    private static void handleDetailBuku() {
        while (true) {
            List<Buku> semuaBuku = ambilSemuaBuku();
            if (IsObjekKosong.check(semuaBuku, "Buku")) return;

            bukuView.tampilkanDaftarBuku(semuaBuku);

            String kodeBuku = ObjekIdGetter.get(
                    semuaBuku,
                    "\nPilih nomor buku yang ingin dilihat detailnya: ",
                    Buku::getKode);

            if (kodeBuku == null) return;

            Buku buku = ambilBuku(kodeBuku);
            bukuView.tampilkanDetailBuku(buku);

            if (!bukuView.mintaKonfirmasi("\nApakah anda ingin melihat detail buku lainnya?")) {
                break;
            }
        }
    }

    // MENANGANI PROSES UPDATE ATRIBUT BUKU
    // MENGGUNAKAN LAMBDA UNTUK LOGIKA UPDATE
    private static void handleUpdateBuku() {
        while (true) {
            List<Buku> semuaBuku = ambilSemuaBuku();
            if (IsObjekKosong.check(semuaBuku, "Buku")) return;

            bukuView.tampilkanDaftarBuku(semuaBuku);

            String kodeBuku = ObjekIdGetter.get(
                    semuaBuku,
                    "Pilih nomor buku yang ingin diupdate: ",
                    Buku::getKode);

            if (kodeBuku == null) return;

            Buku bukuTarget = ambilBuku(kodeBuku);

            InformationPrinter.tampilkanUpdateAtribut(
                    bukuTarget,
                    " ",
                    kodeBuku,
                    data -> BukuController.updateAtribut(data.id, data.namaAtribut, data.nilaiBaru),
                    0, "kode", "jenis");

            bukuView.tampilkanPesan("Atribut berhasil diupdate.");

            if (!bukuView.mintaKonfirmasi("\nApakah anda ingin meng-update buku lainnya?")) {
                break;
            }
        }
    }

    // MENANGANI PROSES PENGHAPUSAN BUKU
    // CEK KONFIRMASI DARI USER DULU
    private static void handleHapusBuku() {
        List<Buku> semuaBuku = ambilSemuaBuku();
        if (IsObjekKosong.check(semuaBuku, "Buku")) return;

        bukuView.tampilkanDaftarBuku(semuaBuku);

        Strin
