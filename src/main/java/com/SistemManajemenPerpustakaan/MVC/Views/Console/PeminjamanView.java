package com.SistemManajemenPerpustakaan.MVC.Views.Console;

import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman;
import com.SistemManajemenPerpustakaan.Utils.InformationPrinter;

import java.util.List;
import java.util.Scanner;

public class PeminjamanView {
    private static final Scanner input = new Scanner(System.in);

    // Sebuah record sederhana untuk membungkus data yang akan ditampilkan di daftar pengembalian.
    // Ini adalah pola ViewModel sederhana untuk mempermudah View.
    public record PeminjamanDetailDisplay(String judulBuku, String statusDeadline) {}

    /**
     * Menampilkan pesan umum ke konsol (misalnya, pesan error, sukses, atau informasi).
     * @param pesan Teks pesan yang akan ditampilkan.
     */
    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }

    /**
     * Menampilkan daftar buku yang tersedia untuk dipinjam.
     * @param daftarBuku List objek Buku yang tersedia.
     */
    public void tampilkanBukuTersedia(List<Buku> daftarBuku) {
        System.out.println("\n--- Buku yang Tersedia untuk Dipinjam ---");
        InformationPrinter.tampilkanObjek(daftarBuku, "", "judul");
    }

    /**
     * Menampilkan daftar buku yang sedang dipinjam oleh pengguna saat ini.
     * @param daftarPinjaman List berisi objek PeminjamanDetailDisplay yang sudah disiapkan Controller.
     */
    public void tampilkanDaftarPinjamanPengguna(List<PeminjamanDetailDisplay> daftarPinjaman) {
        System.out.println("\n--- Daftar Buku yang Anda Pinjam ---");
        if (daftarPinjaman.isEmpty()) {
            System.out.println("Anda tidak sedang meminjam buku.");
            return;
        }

        for (int i = 0; i < daftarPinjaman.size(); i++) {
            PeminjamanDetailDisplay item = daftarPinjaman.get(i);
            System.out.printf("      %d. %s %s%n", (i + 1), item.judulBuku(), item.statusDeadline());
        }
    }

    /**
     * Menampilkan daftar semua peminjaman yang ada di sistem (untuk Admin).
     * @param daftarPeminjaman List semua objek Peminjaman.
     */
    public void tampilkanSemuaPeminjaman(List<Peminjaman> daftarPeminjaman) {
        System.out.println("\n--- Semua Data Peminjaman ---");
        InformationPrinter.tampilkanObjek(daftarPeminjaman, "", "tanggalPinjam");
    }

    /**
     * Meminta pengguna untuk memilih nomor dari daftar yang ditampilkan.
     * @param aksi Teks aksi untuk ditampilkan di prompt (misal: "pinjam", "kembalikan").
     * @return Nomor (indeks + 1) yang dipilih oleh pengguna.
     */
    public int mintaPilihanBuku(String aksi) {
        System.out.print("\nMasukkan nomor buku yang ingin di-" + aksi + ": ");
        int pilihan = input.nextInt();
        input.nextLine(); // Membersihkan buffer
        return pilihan;
    }

    /**
     * Meminta pengguna untuk memilih nomor dari daftar peminjaman.
     * @param aksi Teks aksi untuk ditampilkan di prompt.
     * @return Nomor (indeks + 1) yang dipilih oleh pengguna.
     */
    public int mintaPilihanPeminjaman(String aksi) {
        System.out.print("\nPilih nomor peminjaman yang ingin di-" + aksi + ": ");
        int pilihan = input.nextInt();
        input.nextLine(); // Membersihkan buffer
        return pilihan;
    }

    /**
     * Menampilkan semua atribut dari satu objek Peminjaman.
     * @param peminjaman Objek Peminjaman yang detailnya akan ditampilkan.
     */
    public void tampilkanDetailPeminjaman(Peminjaman peminjaman) {
        System.out.println("\n--- Detail Peminjaman ---");
        InformationPrinter.tampilkanAtributDenganNilai(peminjaman, "", 0, "id");
    }

    /**
     * Meminta konfirmasi dari pengguna dengan pertanyaan ya/tidak.
     * @param pesan Pesan pertanyaan yang akan ditampilkan.
     * @return String "Y" atau "N" (dalam huruf besar) berdasarkan input pengguna.
     */
    public String mintaKonfirmasi(String pesan) {
        System.out.print(pesan + " (y/n): ");
        return input.nextLine().toUpperCase();
    }
}
