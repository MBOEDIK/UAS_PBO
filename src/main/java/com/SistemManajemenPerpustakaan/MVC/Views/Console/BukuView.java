package com.SistemManajemenPerpustakaan.MVC.Views.Console;

import com.SistemManajemenPerpustakaan.DTOs.BukuDTO;
import com.SistemManajemenPerpustakaan.Enums.JenisBuku;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.Utils.InformationPrinter;

import java.util.List;
import java.util.Scanner;

public class BukuView {

    private final Scanner input = new Scanner(System.in);

    /**
     * HANYA menampilkan menu dan mengembalikan pilihan mentah pengguna.
     * Tugasnya murni presentasi dan input.
     */
    public int tampilkanMenuBuku() {
        System.out.print("\n-- Manajemen Buku --" +
                "\n1. Tambah Buku" +
                "\n2. Tampilkan Detail Buku" +
                "\n3. Update Buku" +
                "\n4. Hapus Buku" +
                "\n5. Kembali ke Halaman Admin" +
                "\nMasukkan Pilihan: ");
        try {
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Mengembalikan nilai tidak valid untuk ditangani Controller
        }
    }

    /**
     * HANYA mengelola urutan prompt untuk membuat buku baru.
     * Metode ini memanggil InformationPrinter karena tugas utamanya adalah mengelola
     * presentasi input (menampilkan "masukkan judul:", "masukkan pengarang:", dst.).
     * Mengembalikan DTO yang terisi data mentah.
     */
    public BukuDTO mintaInputBukuBaru() {
        BukuDTO bukuDTO = new BukuDTO();
        System.out.println("\n--- TAMBAH BUKU BARU ---");
        System.out.print(
                "Pilih jenis buku:\n" +
                        "  1. Jurnal\n  2. Majalah\n  3. Novel\n  4. Textbook\n" +
                        "Masukkan pilihan: ");
        int pilihanJenis;
        try {
            pilihanJenis = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            return null; // Input tidak valid
        }

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
                return null; // Pilihan tidak valid
        }
        return bukuDTO;
    }

    /**
     * HANYA menampilkan daftar objek yang diberikan oleh Controller.
     * Menggunakan InformationPrinter untuk formatting.
     */
    public void tampilkanDaftarBuku(List<Buku> daftarBuku) {
        InformationPrinter.tampilkanObjek(daftarBuku, "Buku yang tersedia", "judul");
    }

    /**
     * HANYA menampilkan detail satu buku yang diberikan oleh Controller.
     */
    public void tampilkanDetailBuku(Buku buku) {
        InformationPrinter.tampilkanAtributDenganNilai(buku, "", 0, "kode");
    }

    /**
     * HANYA menanyakan konfirmasi (y/n) dan mengembalikan boolean.
     */
    public boolean mintaKonfirmasi(String pesan) {
        System.out.print(pesan + " (y/n): ");
        return input.nextLine().equalsIgnoreCase("Y");
    }

    /**
     * HANYA menampilkan pesan string generik.
     */
    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }
}