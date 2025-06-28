package com.SistemManajemenPerpustakaan.MVC.Views.Console.Books;

import com.SistemManajemenPerpustakaan.DTOs.BukuDTO;
import com.SistemManajemenPerpustakaan.Enums.JenisBuku;
import com.SistemManajemenPerpustakaan.MVC.Controllers.Books.BukuController;
import com.SistemManajemenPerpustakaan.Utils.InformationPrinter;
import com.SistemManajemenPerpustakaan.Utils.IdGenerator;

import java.util.Scanner;

public class BukuView {

    //OBJECT
    private static Scanner input = new Scanner(System.in);
    
    //METHOD
    //PILIHAN AKSI KE BUKU
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
            if (isBukuKosong()) return;

            InformationPrinter.tampilkanObjek(BukuController.AmbilSemuaBuku(), "Buku yang tersedia", "judul");
            String kodeBuku = ambilKodeBuku("tampilkan detailnya");

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
            if (isBukuKosong()) return;

            InformationPrinter.tampilkanObjek(BukuController.AmbilSemuaBuku(), "Buku yang tersedia", "judul");
            String kodeBuku = ambilKodeBuku("update");

            if (kodeBuku == null) return;

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
        if (isBukuKosong()) return;

        InformationPrinter.tampilkanObjek(BukuController.AmbilSemuaBuku(), "Buku yang tersedia", "judul");
        String kodeBuku = ambilKodeBuku("hapus");

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


    //===TOOLS===
    public static String ambilKodeBuku(String aksi){
        String y;
        int x;
        System.out.print("\nPilih nomor buku yang ingin di"+aksi+": "); x = input.nextInt() - 1; input.nextLine();
        if (x >= BukuController.AmbilSemuaBuku().size() || x < 0) {
            System.out.print("pilihan tidak ada!\n");
            y = null;
        }
        else {
            y = BukuController.AmbilSemuaBuku().get(x).getKode();
        }
        return y;
    }

    public static Boolean isBukuKosong(){
        if (BukuController.AmbilSemuaBuku().isEmpty()){
            System.out.print("Buku kosong!\n");
            return true;
        }else {
            return false;
        }
    }

}
