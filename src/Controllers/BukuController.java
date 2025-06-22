package Controllers;

import Models.Book.Buku;
import Models.PenyimpananData;
import java.util.Scanner;

// CLASS BUKUCONTROLLER UNTUK MENGELOLA CRUD BUKU
public class BukuController {
    // ATTRIBUTE
    private PenyimpananData data;
    private Scanner scanner = new Scanner(System.in);

    // CONSTRUCTOR
    public BukuController(PenyimpananData data) {
        this.data = data;
    }

    // METHOD TAMBAH BUKU
    public void tambahBuku(Buku buku) {
        data.getBuku().add(buku);
        System.out.println("BUKU BERHASIL DITAMBAHKAN");
    }

    // METHOD HAPUS BUKU
    public void hapusBuku(String kode) {
        data.getBuku().removeIf(b -> b.getKodeBuku().equals(kode));
        System.out.println("BUKU BERHASIL DIHAPUS JIKA ADA");
    }

    // METHOD TAMPILKAN SEMUA BUKU
    public void tampilkanBuku() {
        for (Buku b : data.getBuku()) {
            System.out.println(b.getJudulBuku() + " | " + b.getKodeBuku());
        }
    }
}
