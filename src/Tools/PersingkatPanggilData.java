package Tools;

import Models.Book.Buku;
import Models.Peminjaman;
import Models.PenyimpananData;
import Models.User.Pengguna;

import java.util.ArrayList;

public interface PersingkatPanggilData {
    static ArrayList<Pengguna> dataPengguna = PenyimpananData.getPengguna();
    static ArrayList<Buku> dataBuku = PenyimpananData.getBuku();
    static ArrayList<Peminjaman> dataPeminjaman = PenyimpananData.getDataPeminjaman();
}
