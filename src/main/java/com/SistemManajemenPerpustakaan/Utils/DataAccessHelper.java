package com.SistemManajemenPerpustakaan.Utils;

import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Models.PenyimpananData;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;

import java.util.ArrayList;

public interface DataAccessHelper {
    static ArrayList<Pengguna> dataPengguna = PenyimpananData.getPengguna();
    static ArrayList<Buku> dataBuku = PenyimpananData.getBuku();
    static ArrayList<com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman> dataPeminjaman = PenyimpananData.getDataPeminjaman();
}
