package com.SistemManajemenPerpustakaan.MVC.Models;

import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;

import java.util.ArrayList;

public class PenyimpananData {
    //ATTRIBUTE
    private static ArrayList<Pengguna> dataPengguna = new ArrayList<>();
    private static ArrayList<Buku> dataBuku = new ArrayList<>();
    private static ArrayList<Peminjaman> dataPeminjaman = new ArrayList<>();

    //GETTER
    public static ArrayList<Pengguna> getPengguna(){ return dataPengguna; }
    public static ArrayList<Buku> getBuku(){ return dataBuku; }
    public static ArrayList<Peminjaman> getDataPeminjaman(){ return dataPeminjaman; }

    //SETTER
    public void setDataPengguna(ArrayList<Pengguna> pengguna) { this.dataPengguna = pengguna; }
    public void setDataBuku(ArrayList<Buku> buku) { this.dataBuku = buku; }
    public void setDataPeminjaman(ArrayList<Peminjaman> peminjaman) { this.dataPeminjaman = peminjaman; }

}
