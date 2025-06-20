package Main;

import Models.Anggota;
import Models.Buku;
import Models.Peminjaman;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Buat objek Buku
        Buku buku1 = new Buku("B01", "Java Dasar", "Nadif Beta", 2022, true);
        System.out.println(buku1);

        // Buat objek Anggota
        Anggota anggota1 = new Anggota("A01", "Budihihii", "Jl. Menuju Masa depan", "081234567890");
        System.out.println(anggota1);

        // Buat objek Peminjaman
        Peminjaman pinjam1 = new Peminjaman("P001", anggota1, buku1, LocalDate.now(), LocalDate.now().plusDays(7));
        System.out.println(pinjam1);
    }
}
