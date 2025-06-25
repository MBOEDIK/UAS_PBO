package com.SistemManajemenPerpustakaan.MVC.Controllers;

import com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman;
import com.SistemManajemenPerpustakaan.Tools.PersingkatPanggilData;

//Ini class yang handle logika Peminjaman
public class PeminjamanController implements PersingkatPanggilData {
    //============================================================================================================================================================================================================

    public static void tambah(String idPeminjaman, String idAnggota, String idKodeBuku, String tglPinjam, String tglKembali, String deadlinePeminjaman, String statusPengembalian){
        dataPeminjaman.add(new Peminjaman(idPeminjaman, idAnggota, idKodeBuku, tglPinjam, tglKembali, deadlinePeminjaman, statusPengembalian));
    }

    //============================================================================================================================================================================================================

    public static void edit(int indeksPeminjaman, String attribute, String nilai){
        switch (attribute){
            case "idPeminjaman": dataPeminjaman.get(indeksPeminjaman).setIdPeminjaman(nilai); break;
            case "idAnggota": dataPeminjaman.get(indeksPeminjaman).setIdAnggota(nilai); break;
            case "kodeBuku": dataPeminjaman.get(indeksPeminjaman).setKodeBuku(nilai); break;
            case "tanggalPinjam": dataPeminjaman.get(indeksPeminjaman).setTanggalPinjam(nilai); break;
            case "tanggalKembali": dataPeminjaman.get(indeksPeminjaman).setTanggalKembali(nilai); break;
            case "deadlinePeminjaman": dataPeminjaman.get(indeksPeminjaman).setDeadlinePeminjaman(nilai); break;
            case "status": dataPeminjaman.get(indeksPeminjaman).setStatusKeterlambatan(nilai); break;
        }
    }

    //============================================================================================================================================================================================================

    public static void hapus(int indeks){
        dataPeminjaman.remove(indeks);
    }

    //============================================================================================================================================================================================================

    public static void tampilkan(){

    }
}
