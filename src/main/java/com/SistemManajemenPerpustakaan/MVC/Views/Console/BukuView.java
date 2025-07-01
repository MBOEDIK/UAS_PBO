// INI JUAN YANG KASI KOMEN
// CLASS VIEW YANG NAMPILIN MENU DAN INPUT KONSOL UNTUK BUKU

package com.SistemManajemenPerpustakaan.MVC.Views.Console;

import com.SistemManajemenPerpustakaan.DTOs.BukuDTO;
import com.SistemManajemenPerpustakaan.Enums.JenisBuku;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.Utils.InformationPrinter;

import java.util.List;
import java.util.Scanner;

public class BukuView {

    // SCANNER UNTUK INPUT DARI USER MELALUI TERMINAL
    private final Scanner input = new Scanner(System.in);

    // MENAMPILKAN MENU UTAMA BUKU DAN MENGEMBALIKAN PILIHAN USER
    public int tampilkanMenuBuku() {
        System.out.print("\n-- Manajemen Buku --" +
                "\n1. Tambah Buku" +
                "\n2. Tampilkan Detail Buku" +
                "\n3. Update Buku" +
                "\n4. Hapus Buku" +
                "\n5. Kembali ke Halaman Admin" +
                "\nMasukkan Pilihan: ");
        try {
            return Integer.parseInt(input.nextLine()); // MENGUBAH INPUT JADI ANGKA
        } catch (NumberFormatException e) {
            return -1; // MENGEMBALIKAN -1 KALAU INPUT TIDAK VALID
        }
    }

    // MENGAMBIL INPUT USER UNTUK MEMBUAT BUKU BARU (DTO)
    public BukuDTO mintaInputBukuBaru() {
        BukuDTO bukuDTO = new BukuDTO();
        System.out.println("\n--- TAMBAH BUKU BARU ---");
        System.out.print(
                "Pilih jenis buku:\n" +
                        "  1. Jurnal\n  2. Majalah\n  3. Novel\n  4. Textbook\n" +
                        "Masukkan pilihan: ");
        int pilihanJenis;
        try {
            pilihanJenis = Integer.parseInt(input.nextLine()); // CEK ANGKA JENIS
        } catch (NumberFormatException e) {
            return null; // RETURN NULL KALAU BUKAN ANGKA
        }

        // MENENTUKAN JENIS BUKU & MINTA ATRIBUT KHUSUS SESUAI JENIS
        switch (pilihanJenis) {
            case 1:
                bukuDTO.jenis = JenisBuku.JURNAL;
                InformationPrinter.tampilkanAtributDenganInput(bukuDTO, "", 1, "judul", "pengarang", "tahunTerbit", "institusiJURNAL", "terindeksSintaJURNAL");
                break;
            case 2:
                bukuDTO.jenis = JenisBuku.MAJALAH;
                InformationPrinter.tampilkanAtributDenganInput(bukuDTO, "", 1, "judul", "pengarang", "tahunTerbit", "topikMAJALAH");
                break;
            case 3:
                bukuDTO.jenis = JenisBuku.NOVEL;
                InformationPrinter.tampilkanAtributDenganInput(bukuDTO, "", 1, "judul", "pengarang", "tahunTerbit", "genreNOVEL");
                break;
            case 4:
                bukuDTO.jenis = JenisBuku.TEXTBOOK;
                InformationPrinter.tampilkanAtributDenganInput(bukuDTO, "", 1, "judul", "pengarang", "tahunTerbit", "bidangIlmuTEXTBOOK");
                break;
            default:
                return null; // JENIS TIDAK VALID = BATAL INPUT
        }
        return bukuDTO;
    }

    // MENAMPILKAN SEMUA BUKU YANG DIKIRIM OLEH CONTROLLER
    public void tampilkanDaftarBuku(List<Buku> daftarBuku) {
        InformationPrinter.tampilkanObjek(daftarBuku, "Buku yang tersedia", "judul");
    }

    // MENAMPILKAN DETAIL 1 BUKU SAJA (DIAMBIL DARI CONTROLLER)
    public void tampilkanDetailBuku(Buku buku) {
        InformationPrinter.tampilkanAtributDenganNilai(buku, "", 0, "kode");
    }

    // MENAMPILKAN PROMPT Y/N DAN RETURN TRUE JIKA Y
    public boolean mintaKonfirmasi(String pesan) {
        System.out.print(pesan + " (y/n): ");
        return input.nextLine().equalsIgnoreCase("Y");
    }

    // MENAMPILKAN PESAN TEKS BIASA (ERROR, BERHASIL, DLL)
    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }
}
