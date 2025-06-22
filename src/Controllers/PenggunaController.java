package Controllers;

import Models.PenyimpananData;
import Models.User.Pengguna;
import java.util.Scanner;

// CLASS PENGGUNACONTROLLER UNTUK MENGELOLA DATA USER
public class PenggunaController {
    // ATTRIBUTE
    private PenyimpananData data;
    private Scanner scanner = new Scanner(System.in);

    // CONSTRUCTOR
    public PenggunaController(PenyimpananData data) {
        this.data = data;
    }

    // METHOD TAMBAH PENGGUNA
    public void tambahPengguna(Pengguna pengguna) {
        data.getPengguna().add(pengguna);
        System.out.println("PENGGUNA BERHASIL DITAMBAHKAN");
    }

    // METHOD HAPUS PENGGUNA
    public void hapusPengguna(String id) {
        data.getPengguna().removeIf(p -> p.getIdPengguna().equals(id));
        System.out.println("PENGGUNA BERHASIL DIHAPUS JIKA ADA");
    }

    // METHOD TAMPILKAN SEMUA PENGGUNA
    public void tampilkanPengguna() {
        for (Pengguna p : data.getPengguna()) {
            System.out.println(p.getNamaPengguna() + " | " + p.getIdPengguna());
        }
    }
}
