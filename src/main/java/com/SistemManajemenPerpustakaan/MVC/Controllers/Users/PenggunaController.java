package com.SistemManajemenPerpustakaan.MVC.Controllers.Users;

import com.SistemManajemenPerpustakaan.MVC.Models.User.Admin;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Anggota;
import com.SistemManajemenPerpustakaan.Tools.DataAccessHelper;

//Ini class yang handle logika terkait pengguna
public class PenggunaController implements DataAccessHelper {

    //============================================================================================================================================================================================================

    public static void tambah(String idPengguna, String jenisPengguna, String namaPengguna, String alamatPengguna, String nomorHpPengguna, String username, String password, String nipAdmin){
        dataPengguna.add(new Admin(idPengguna, jenisPengguna, namaPengguna, alamatPengguna, nomorHpPengguna, username, password, nipAdmin));
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void tambah(String idPengguna, String jenisPengguna, String namaPengguna, String alamatPengguna, String nomorHpPengguna, String username, String password, Boolean terlambatMengembalikan,int maksimalPinjamBuku, int jumlahPinjamBuku){
        dataPengguna.add(new Anggota(idPengguna, jenisPengguna, namaPengguna, alamatPengguna, nomorHpPengguna, username, password, terlambatMengembalikan, maksimalPinjamBuku, jumlahPinjamBuku));
    }

    //============================================================================================================================================================================================================

    public static void edit(int indeksPengguna, String attribute, String nilai){
        switch (attribute){
            case "jenis": dataPengguna.get(indeksPengguna).setJenis(nilai); break;
            case "nama": dataPengguna.get(indeksPengguna).setNama(nilai); break;
            case "alamat": dataPengguna.get(indeksPengguna).setAlamat(nilai); break;
            case "nomorHP": dataPengguna.get(indeksPengguna).setNomorHPPengguna(nilai); break;
            case "username": dataPengguna.get(indeksPengguna).setUsername(nilai); break;
            case "password": dataPengguna.get(indeksPengguna).setPassword(nilai); break;
        }
    }

    //============================================================================================================================================================================================================

    public static void hapus(int indeks){
        dataPengguna.remove(indeks);
    }

    //============================================================================================================================================================================================================

    public static void tampilkan(){

    }

    //============================================================================================================================================================================================================
}
