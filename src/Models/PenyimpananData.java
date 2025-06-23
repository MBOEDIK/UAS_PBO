package Models;

import Models.Book.Buku;
import Models.User.Pengguna;

import java.util.ArrayList;

public class PenyimpananData {
    //ATTRIBUTE
    private static ArrayList<Pengguna> dataPengguna = new ArrayList<>();
    private static ArrayList<Buku> dataBuku = new ArrayList<>();
    private static ArrayList<Peminjaman> dataPeminjaman = new ArrayList<>();

    //GETTER
    public ArrayList<Pengguna> getPengguna(){ return dataPengguna; }
    public ArrayList<Buku> getBuku(){ return dataBuku; }
    public ArrayList<Peminjaman> getDataPeminjaman(){ return dataPeminjaman; }

    //SETTER
    public void setDataPengguna(ArrayList<Pengguna> pengguna) { this.dataPengguna = pengguna; }
    public void setDataBuku(ArrayList<Buku> buku) { this.dataBuku = buku; }
    public void setDataPeminjaman(ArrayList<Peminjaman> peminjaman) { this.dataPeminjaman = peminjaman; }

}
