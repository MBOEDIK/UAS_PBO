package com.SistemManajemenPerpustakaan.MVC.Views.Console;

import com.SistemManajemenPerpustakaan.DTOs.BukuDTO;
import com.SistemManajemenPerpustakaan.Enums.JenisBuku;
import com.SistemManajemenPerpustakaan.MVC.Controllers.BukuController;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.Utils.InformationPrinter;
import com.SistemManajemenPerpustakaan.Utils.IdGenerator;
import com.SistemManajemenPerpustakaan.Utils.IsObjekKosong;
import com.SistemManajemenPerpustakaan.Utils.ObjekIdGetter;

import java.util.Scanner;

public class BukuView {

    //OBJECT
    private static Scanner input = new Scanner(System.in);
    
    //METHOD
    //PILIHAN AKSI KE BUKU

    public static void TESS(){
        BukuDTO bukuDTO = new BukuDTO();

        String judul = input.nextLine();
        String pengarang = input.nextLine();

        bukuDTO.judul = judul;
        bukuDTO.pengarang = pengarang;

        BukuController.tambahBuku(bukuDTO);
    }

    public static int menuBuku(){
        while (true){
            int x;
            System.out.print("" +
                    "\n-- Manajemen Buku --" +
                    "\n1. Tambah Buku" +
                    "\n2. Tampilkan Detail Buku" +
                    "\n3. Update Buku" +
                    "\n4. Hapus Buku" +
                    "\n5. Kembali ke Halaman Admin" +
                    "\nMasukkan Pilihan: ");
            x = input.nextInt();
            input.nextLine();
            if (x < 1 || x > 5) {
                System.out.print("Pilihan tidak ada!\n");
                continue;
            }
            return x;
        }
    }

    //CREATE
    public static void tambahBuku(){
        BukuDTO bukuDTO = new BukuDTO();
        System.out.print("\nTAMBAH BUKU ->");
        System.out.print("" +
                "\nPilih jenis buku:" +
                "\n" +
                "\n      1. Jurnal" +
                "\n      2. Majalah" +
                "\n      3. Novel" +
                "\n      4. Textbook" +
                "\n" +
                "\nMasukkan pilihan: ");
        int x = input.nextInt();
        input.nextLine();

        switch (x){
            case 1:
                InformationPrinter.tampilkanAtributDenganInput(bukuDTO, "", 1, "judul", "pengarang", "tahunTerbit", "institusiJURNAL", "terindeksSintaJURNAL");
                bukuDTO.jenis = JenisBuku.JURNAL;
                break;
            case 2:
                InformationPrinter.tampilkanAtributDenganInput(bukuDTO, "", 1, "judul", "pengarang", "tahunTerbit", "topikMAJALAH");
                bukuDTO.jenis = JenisBuku.MAJALAH;
                break;
            case 3:
                InformationPrinter.tampilkanAtributDenganInput(bukuDTO, "", 1, "judul", "pengarang", "tahunTerbit", "genreNOVEL");
                bukuDTO.jenis = JenisBuku.NOVEL;
                break;
            case 4:
                InformationPrinter.tampilkanAtributDenganInput(bukuDTO, "", 1, "judul", "pengarang", "tahunTerbit", "bidangIlmuTEXTBOOK");
                bukuDTO.jenis = JenisBuku.TEXTBOOK;
                break;

        }

        bukuDTO.kode = IdGenerator.generate();
        bukuDTO.tersedia = true;
        BukuController.tambahBuku(bukuDTO);
    }

    //READ
    public static void detailBuku(){
        while (true){
            //CEK APAKAH BUKU KOSONG, JIKA YA MAKA KELUAR DARI METHOD
            if (IsObjekKosong.check(BukuController.ambilSemuaBuku(), "Buku")) return;
            InformationPrinter.tampilkanObjek(
                    BukuController.ambilSemuaBuku(), "Buku yang tersedia", "judul");
            String kodeBuku = ObjekIdGetter.get(
                    BukuController.ambilSemuaBuku(),
                    "\nPilih nomor buku yang ingin dilihat detailnya: ", Buku::getKode);

            if (kodeBuku == null) return;

            InformationPrinter.tampilkanAtributDenganNilai(BukuController.ambilBuku(kodeBuku),
                    "", 0, "kode");

            System.out.print("\nApakah anda ingin melihat detail buku lainnya? (y/n): ");
            String x = input.nextLine();
            if (!(x.toUpperCase().equals("Y"))) break;
        }
    }

    //UPDATE
    public static void updateBuku(){
        while (true){
            //CEK APAKAH BUKU KOSONG, JIKA YA MAKA KELUAR DARI METHOD
            if (IsObjekKosong.check(BukuController.ambilSemuaBuku(), "Buku")) return;
            //TAMPILKAN SEMUA BUKU
            InformationPrinter.tampilkanObjek(
                    BukuController.ambilSemuaBuku(), "Buku yang tersedia", "judul");
            //MENGAMBIL KODE BUKU, JIKA TIDAK ADA MAKA KELUAR DARI METHOD
            String kodeBuku = ObjekIdGetter.get(
                    BukuController.ambilSemuaBuku(),
                    "Pilih nomor buku yang ingin diupdate: ", Buku::getKode);
            if (kodeBuku == null) return;

            //MEMILIH DAN MENGUPDATE ATRIBUTE YANG BISA DIUPDATE
            InformationPrinter.tampilkanUpdateAtribut(
                    BukuController.ambilBuku(kodeBuku),
                    " ", kodeBuku,
                    data -> BukuController.updateAtribut(data.id, data.namaAtribut, data.nilaiBaru),
                    0, "kode", "jenis");

            System.out.print("\nApakah anda ingin meng-update atribut buku lainnya? (y/n): ");
            String x = input.nextLine();
            if (!(x.toUpperCase().equals("Y"))) break;
        }
    }

    //DELETE
    public static void hapusBuku () {
        if (IsObjekKosong.check(BukuController.ambilSemuaBuku(), "Buku")) return;

        InformationPrinter.tampilkanObjek(BukuController.ambilSemuaBuku(), "Buku yang tersedia", "judul");
        String kodeBuku = ObjekIdGetter.get(
                BukuController.ambilSemuaBuku(),
                "Pilih nomor buku yang ingin dihapus: ", Buku::getKode);

        System.out.print("Yakin ingin menghapus buku berjudul \""+BukuController.ambilBuku(kodeBuku).getJudul()+"\"? (y/n): ");
        String x = input.nextLine();

        if ((x.toUpperCase().equals("Y"))) {
            BukuController.hapusBuku(kodeBuku);
            System.out.print("Hapus buku berhasil!\n");
        }
        else {
            System.out.print("Hapus buku dibatalkan.\n");
        }
    }
}
