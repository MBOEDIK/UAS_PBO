package Controllers;

import Models.Book.JurnalIlmiah;
import Models.Book.Majalah;
import Models.Book.Novel;
import Models.Book.TextBook;
import Models.PenyimpananData;
import Models.User.Admin;
import Models.User.Anggota;

//Ini class yang handle logika terkait pengguna
public class PenggunaController {
    //============================================================================================================================================================================================================

    public static void tambah(String idPengguna, String jenisPengguna, String namaPengguna, String alamatPengguna, String nomorHpPengguna, String username, String password, String nipAdmin){
        PenyimpananData.getPengguna().add(new Admin(idPengguna, jenisPengguna, namaPengguna, alamatPengguna, nomorHpPengguna, username, password, nipAdmin));
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void tambah(String idPengguna, String jenisPengguna, String namaPengguna, String alamatPengguna, String nomorHpPengguna, String username, String password, Boolean terlambatMengembalikan,int maksimalPinjamBuku, int jumlahPinjamBuku){
        PenyimpananData.getPengguna().add(new Anggota(idPengguna, jenisPengguna, namaPengguna, alamatPengguna, nomorHpPengguna, username, password, terlambatMengembalikan, maksimalPinjamBuku, jumlahPinjamBuku));
    }

    //============================================================================================================================================================================================================

    public static void edit(int indeksPengguna, String attribute, String nilai){
        switch (attribute){
            case "jenis": PenyimpananData.getPengguna().get(indeksPengguna).setJenisPengguna(nilai); break;
            case "nama": PenyimpananData.getPengguna().get(indeksPengguna).setNamaPengguna(nilai); break;
            case "alamat": PenyimpananData.getPengguna().get(indeksPengguna).setAlamatPengguna(nilai); break;
            case "nomorHP": PenyimpananData.getPengguna().get(indeksPengguna).setNomorHPPengguna(nilai); break;
            case "username": PenyimpananData.getPengguna().get(indeksPengguna).setUsername(nilai); break;
            case "password": PenyimpananData.getPengguna().get(indeksPengguna).setPassword(nilai); break;
        }
    }

    //============================================================================================================================================================================================================

    public static void hapus(int indeks){
        PenyimpananData.getPengguna().remove(indeks);
    }

    //============================================================================================================================================================================================================

    public static void tampilkan(){

    }

    //============================================================================================================================================================================================================
}
