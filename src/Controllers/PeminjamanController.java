package Controllers;

import Models.Peminjaman;
import Models.PenyimpananData;
import java.util.Scanner;

// CLASS PEMINJAMANCONTROLLER UNTUK MENGELOLA TRANSAKSI PEMINJAMAN DAN PENGEMBALIAN
public class PeminjamanController {
    // ATTRIBUTE
    private PenyimpananData data;
    private Scanner scanner = new Scanner(System.in);

    // CONSTRUCTOR
    public PeminjamanController(PenyimpananData data) {
        this.data = data;
    }

    // METHOD TAMBAH PEMINJAMAN
    public void tambahPeminjaman(Peminjaman peminjaman) {
        data.getDataPeminjaman().add(peminjaman);
        System.out.println("PEMINJAMAN BERHASIL DITAMBAHKAN");
    }

    // METHOD TAMPILKAN PEMINJAMAN
    public void tampilkanPeminjaman() {
        for (Peminjaman p : data.getDataPeminjaman()) {
            System.out.println(p.generateReport());
        }
    }
}
