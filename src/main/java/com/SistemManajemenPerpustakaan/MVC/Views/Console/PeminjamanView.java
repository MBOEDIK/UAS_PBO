// INI JUAN YANG KASI KOMEN
// CLASS VIEW BUAT TAMPILAN DAN INPUT PEMINJAMAN DI TERMINAL

package com.SistemManajemenPerpustakaan.MVC.Views.Console;

import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman;
import com.SistemManajemenPerpustakaan.Utils.InformationPrinter;

import java.util.List;
import java.util.Scanner;

public class PeminjamanView {
    private static final Scanner input = new Scanner(System.in);

    // RECORD KHUSUS UNTUK TAMPILAN DAFTAR PINJAMAN
    public record PeminjamanDetailDisplay(String judulBuku, String statusDeadline) {}

    // TAMPILIN PESAN UMUM KE TERMINAL
    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }

    // TAMPILIN SEMUA BUKU YANG MASIH TERSEDIA DIPINJAM
    public void tampilkanBukuTersedia(List<Buku> daftarBuku) {
        System.out.println("\n--- Buku yang Tersedia untuk Dipinjam ---");
        InformationPrinter.tampilkanObjek(daftarBuku, "", "judul");
    }

    // TAMPILIN DAFTAR BUKU YANG DIPINJAM OLEH USER SAAT INI
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

    // TAMPILIN SEMUA DATA PEMINJAMAN YANG ADA (KHUSUS ADMIN)
    public void tampilkanSemuaPeminjaman(List<Peminjaman> daftarPeminjaman) {
        System.out.println("\n--- Semua Data Peminjaman ---");
        InformationPrinter.tampilkanObjek(daftarPeminjaman, "", "tanggalPinjam");
    }

    // MINTA INPUT USER UNTUK PILIH NOMOR BUKU YANG AKAN DIPINJAM/KEMBALI
    public int mintaPilihanBuku(String aksi) {
        System.out.print("\nMasukkan nomor buku yang ingin di-" + aksi + ": ");
        int pilihan = input.nextInt();
        input.nextLine(); // BERSIHKAN BUFFER ENTER
        return pilihan;
    }

    // MINTA USER PILIH NOMOR PEMINJAMAN YANG MAU DI PROSES
    public int mintaPilihanPeminjaman(String aksi) {
        System.out.print("\nPilih nomor peminjaman yang ingin di-" + aksi + ": ");
        int pilihan = input.nextInt();
        input.nextLine();
        return pilihan;
    }

    // TAMPILKAN DETAIL LENGKAP DARI SATU OBJEK PEMINJAMAN
    public void tampilkanDetailPeminjaman(Peminjaman peminjaman) {
        System.out.println("\n--- Detail Peminjaman ---");
        InformationPrinter.tampilkanAtributDenganNilai(peminjaman, "", 0, "id");
    }

    // MINTA KONFIRMASI USER UNTUK MELAKUKAN TINDAKAN (Y/N)
    public String mintaKonfirmasi(String pesan) {
        System.out.print(pesan + " (y/n): ");
        return input.nextLine().toUpperCase();
    }
}
