package com.SistemManajemenPerpustakaan.Utils;

import com.SistemManajemenPerpustakaan.DTOs.BukuDTO;
import com.SistemManajemenPerpustakaan.DTOs.PenggunaDTO;
import com.SistemManajemenPerpustakaan.Enums.JenisBuku;
import com.SistemManajemenPerpustakaan.Enums.JenisPengguna;
import com.SistemManajemenPerpustakaan.MVC.Controllers.BukuController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.PenggunaController;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;

public class DummyDataGenerator {
    // MENGHASILKAN DATA DUMMY.
    // MENAMBAHKAN PENGGUNA DAN BUKU UNTUK PENGUJIAN.
    public static void Generate(){
        PenggunaDTO penggunaDTO1 = new PenggunaDTO();
        penggunaDTO1.id = IdGenerator.generateUniqueId(PenggunaController.ambilSemuaPengguna(), Pengguna::getId);
        penggunaDTO1.nama = "Prabowo";
        penggunaDTO1.alamat = "Jakarta, Indonesia";
        penggunaDTO1.jenis = JenisPengguna.ADMIN;
        penggunaDTO1.nomorHP = "08123456789";
        penggunaDTO1.username = "a";
        penggunaDTO1.password = "a";
        penggunaDTO1.nipADMIN = "1201003011423";
        PenggunaController.tambahPengguna(penggunaDTO1);

        PenggunaDTO penggunaDTO2 = new PenggunaDTO();
        penggunaDTO2.id = IdGenerator.generateUniqueId(PenggunaController.ambilSemuaPengguna(), Pengguna::getId);
        penggunaDTO2.nama = "Budi";
        penggunaDTO2.alamat = "Tegalgondo, Malang, Jawa Timur, Indonesia";
        penggunaDTO2.jenis = JenisPengguna.ANGGOTA;
        penggunaDTO2.nomorHP = "082278925369";
        penggunaDTO2.username = "a";
        penggunaDTO2.password = "a";
        penggunaDTO2.terlambatANGGOTA = false;
        penggunaDTO2.maksimalPinjamANGGOTA = 3;
        penggunaDTO2.jumlahPinjamANGGOTA = 0;
        PenggunaController.tambahPengguna(penggunaDTO2);

        BukuDTO bukuDTO1 = new BukuDTO();
        bukuDTO1.kode = IdGenerator.generateUniqueId(BukuController.ambilSemuaBuku(), Buku::getKode);
        bukuDTO1.judul = "Majalah A";
        bukuDTO1.pengarang = "Cahyono";
        bukuDTO1.tahunTerbit = "2022";
        bukuDTO1.jenis = JenisBuku.MAJALAH;
        bukuDTO1.tersedia = true;
        bukuDTO1.topikMAJALAH = "Geopolitik";
        BukuController.tambahBuku(bukuDTO1);

        BukuDTO bukuDTO2 = new BukuDTO();
        bukuDTO2.kode = IdGenerator.generateUniqueId(BukuController.ambilSemuaBuku(), Buku::getKode);
        bukuDTO2.judul = "Novel C";
        bukuDTO2.pengarang = "Agus";
        bukuDTO2.tahunTerbit = "1999";
        bukuDTO2.jenis = JenisBuku.NOVEL;
        bukuDTO2.tersedia = true;
        bukuDTO2.genreNOVEL = "Romansa";
        BukuController.tambahBuku(bukuDTO2);
    }
}