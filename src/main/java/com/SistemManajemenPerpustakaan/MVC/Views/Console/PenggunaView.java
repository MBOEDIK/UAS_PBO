package com.SistemManajemenPerpustakaan.MVC.Views.Console;

import com.SistemManajemenPerpustakaan.DTOs.PenggunaDTO;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;
import com.SistemManajemenPerpustakaan.Utils.InformationPrinter;

import java.util.List;
import java.util.Scanner;

public class PenggunaView {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Menampilkan menu utama untuk manajemen pengguna dan mengembalikan pilihan mentah pengguna.
     * @return Pilihan integer dari pengguna.
     */
    public int tampilkanMenuPengguna() {
        System.out.print("\n-- Manajemen Pengguna --" +
                "\n1. Tambah Pengguna" +
                "\n2. Tampilkan Detail Pengguna" +
                "\n3. Update Pengguna" +
                "\n4. Hapus Pengguna" +
                "\n5. Kembali ke Halaman Admin" +
                "\nMasukkan Pilihan: ");
        int pilihan = input.nextInt();
        input.nextLine(); // Membersihkan buffer
        return pilihan;
    }

    /**
     * Menampilkan header untuk form tambah pengguna.
     */
    public void tampilkanHeaderTambahPengguna() {
        System.out.println("\n--- Tambah Pengguna Baru ---");
    }

    /**
     * Meminta pengguna untuk memilih jenis pengguna (Admin atau Anggota).
     * @return Pilihan integer dari pengguna (1 untuk Admin, 2 untuk Anggota).
     */
    public int mintaJenisPengguna() {
        System.out.print("\nPilih jenis pengguna:" +
                "\n1. Admin" +
                "\n2. Anggota" +
                "\nMasukkan pilihan: ");
        int pilihan = input.nextInt();
        input.nextLine(); // Membersihkan buffer
        return pilihan;
    }

    /**
     * Menggunakan InformationPrinter untuk meminta input atribut dari pengguna dan mengisinya ke DTO.
     * Metode ini tetap di View karena tugas utamanya adalah interaksi I/O (menampilkan prompt dan membaca input).
     * @param penggunaDTO DTO untuk diisi.
     * @param atribut Atribut yang akan diminta dari pengguna.
     */
    public void mintaInputAtributPengguna(PenggunaDTO penggunaDTO, String... atribut) {
        InformationPrinter.tampilkanAtributDenganInput(penggunaDTO, "", 1, atribut);
    }

    /**
     * Menampilkan daftar pengguna yang tersedia dalam format bernomor.
     * @param daftarPengguna List objek Pengguna yang akan ditampilkan.
     * @param judul Judul untuk daftar.
     */
    public void tampilkanDaftarPengguna(List<Pengguna> daftarPengguna, String judul) {
        InformationPrinter.tampilkanObjek(daftarPengguna, judul, "nama");
    }

    /**
     * Meminta pengguna untuk memilih nomor dari daftar yang ditampilkan.
     * @param aksi Teks aksi untuk ditampilkan di prompt (misal: "update", "hapus").
     * @return Nomor (indeks + 1) yang dipilih oleh pengguna.
     */
    public int mintaPilihanPengguna(String aksi) {
        System.out.print("\nPilih nomor pengguna yang ingin di-" + aksi + ": ");
        int pilihan = input.nextInt();
        input.nextLine(); // Membersihkan buffer
        return pilihan;
    }

    /**
     * Menampilkan semua atribut dari satu objek Pengguna.
     * @param pengguna Objek Pengguna yang detailnya akan ditampilkan.
     */
    public void tampilkanDetailPengguna(Pengguna pengguna) {
        System.out.println("\n--- Detail Pengguna ---");
        InformationPrinter.tampilkanAtributDenganNilai(pengguna, "", 0, "id");
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

    /**
     * Menampilkan prompt konfirmasi spesifik sebelum menghapus pengguna.
     * @param namaPengguna Nama pengguna yang akan dihapus.
     * @return String "Y" atau "N" (dalam huruf besar).
     */
    public String mintaKonfirmasiHapus(String namaPengguna) {
        return mintaKonfirmasi("Yakin ingin menghapus pengguna bernama \"" + namaPengguna + "\"?");
    }

    /**
     * Menampilkan pesan umum ke konsol (misalnya, pesan error, sukses, atau informasi).
     * @param pesan Teks pesan yang akan ditampilkan.
     */
    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }
}
