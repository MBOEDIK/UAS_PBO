package Controllers;

import Models.Peminjaman;
import Models.PenyimpananData;
import Models.User.Admin;
import Models.User.Anggota;

//Ini class yang handle logika Peminjaman
public class PeminjamanController {
    //============================================================================================================================================================================================================

    public static void tambah(String idPeminjaman, String idAnggota, String idKodeBuku, String tglKembali, String tglPinjam, String statusPengembalian){
        PenyimpananData.getDataPeminjaman().add(new Peminjaman(idPeminjaman, idAnggota, idKodeBuku, tglKembali, tglPinjam, statusPengembalian));
    }

    //============================================================================================================================================================================================================

    public static void edit(int indeksPeminjaman, String attribute, String nilai){
        switch (attribute){
            case "idPeminjaman": PenyimpananData.getDataPeminjaman().get(indeksPeminjaman).setIdPeminjaman(nilai); break;
            case "idAnggota": PenyimpananData.getDataPeminjaman().get(indeksPeminjaman).setIdAnggota(nilai); break;
            case "kodeBuku": PenyimpananData.getDataPeminjaman().get(indeksPeminjaman).setKodeBuku(nilai); break;
            case "tanggalPinjam": PenyimpananData.getDataPeminjaman().get(indeksPeminjaman).setTanggalPinjam(nilai); break;
            case "tanggalKembali": PenyimpananData.getDataPeminjaman().get(indeksPeminjaman).setTanggalKembali(nilai); break;
            case "status": PenyimpananData.getDataPeminjaman().get(indeksPeminjaman).setStatusKeterlambatan(nilai); break;
        }
    }

    //============================================================================================================================================================================================================

    public static void hapus(int indeks){
        PenyimpananData.getDataPeminjaman().remove(indeks);
    }

    //============================================================================================================================================================================================================

    public static void tampilkan(){

    }
}
