// INI JUAN YANG KASI KOMEN
// CLASS VIEW UNTUK INTERAKSI PENGGUNA DI TERMINAL

package com.SistemManajemenPerpustakaan.MVC.Views.Console;

import com.SistemManajemenPerpustakaan.DTOs.PenggunaDTO;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;
import com.SistemManajemenPerpustakaan.Utils.InformationPrinter;

import java.util.List;
import java.util.Scanner;

public class PenggunaView {
    private static final Scanner input = new Scanner(System.in);

    // TAMPILKAN MENU PENGGUNA DAN KEMBALIKAN PILIHAN USER
    public int tampilkanMenuPengguna() {
        System.out.print("\n-- Manajemen Pengguna --" +
                "\n1. Tambah Pengguna" +
                "\n2. Tampilkan Detail Pengguna" +
                "\n3. Update Pengguna" +
                "\n4. Hapus Pengguna" +
                "\n5. Kembali ke Halaman Admin" +
                "\nMasukkan Pilihan: ");
        int pilihan = input.nextInt();
        input.nextLine(); // BUANG ENTER SISA INPUT
        return pilihan;
    }

    // TAMPILKAN HEADER TAMBAH PENGGUNA BARU
    public void tampilkanHeaderTambahPengguna() {
        System.out.println("\n--- Tambah Pengguna Baru ---");
    }

    // MINTA USER PILIH JENIS PENGGUNA (ADMIN/ANGGOTA)
    public int mintaJenisPengguna() {
        System.out.print("\nPilih jenis pengguna:" +
                "\n1. Admin" +
                "\n2. Anggota" +
                "\nMasukkan pilihan: ");
        int pilihan = input.nextInt();
        input.nextLine(); // BUANG ENTER SISA INPUT
        return pilihan;
    }

    // MINTA INPUT ATRIBUT DARI USER DAN ISI KE DTO
    public void mintaInputAtributPengguna(PenggunaDTO penggunaDTO, String... atribut) {
        InformationPrinter.tampilkanAtributDenganInput(penggunaDTO, "", 1, atribut);
    }

    // TAMPILKAN DAFTAR PENGGUNA DENGAN NAMA
    public void tampilkanDaftarPengguna(List<Pengguna> daftarPengguna, String judul) {
        InformationPrinter.tampilkanObjek(daftarPengguna, judul, "nama");
    }

    // MINTA USER PILIH NOMOR DARI DAFTAR PENGGUNA
    public int mintaPilihanPengguna(String aksi) {
        System.out.print("\nPilih nomor pengguna yang ingin di-" + aksi + ": ");
        int pilihan = input.nextInt();
        input.nextLine(); // BUANG ENTER
        return pilihan;
    }

    // TAMPILKAN DETAIL LENGKAP DARI 1 PENGGUNA
    public void tampilkanDetailPengguna(Pengguna pengguna) {
        System.out.println("\n--- Detail Pengguna ---");
        InformationPrinter.tampilkanAtributDenganNilai(pengguna, "", 0, "id");
    }

    // MINTA KONFIRMASI UMUM (Y/N)
    public String mintaKonfirmasi(String pesan) {
        System.out.print(pesan + " (y/n): ");
        return input.nextLine().toUpperCase(); // UBAH KE HURUF BESAR
    }

    // MINTA KONFIRMASI SEBELUM HAPUS PENGGUNA
    public String mintaKonfirmasiHapus(String namaPengguna) {
        return mintaKonfirmasi("Yakin ingin menghapus pengguna bernama \"" + namaPengguna + "\"?");
    }

    // TAMPILKAN PESAN STRING UMUM KE TERMINAL
    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }
}
