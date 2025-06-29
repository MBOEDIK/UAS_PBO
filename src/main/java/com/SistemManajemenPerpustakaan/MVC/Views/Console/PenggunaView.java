package com.SistemManajemenPerpustakaan.MVC.Views.Console;

import com.SistemManajemenPerpustakaan.DTOs.PenggunaDTO;
import com.SistemManajemenPerpustakaan.Enums.JenisPengguna;
import com.SistemManajemenPerpustakaan.MVC.Controllers.BukuController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.PenggunaController;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;
import com.SistemManajemenPerpustakaan.Utils.InformationPrinter;
import com.SistemManajemenPerpustakaan.Utils.IdGenerator;

import java.util.Scanner;

public class PenggunaView {
    //OBJECT
    private static Scanner input = new Scanner(System.in);

    //METHOD
    //PILIHAN AKSI KE BUKU
    public static int menuPengguna(){
        while (true){
            int x;
            System.out.print("" +
                    "\n-- Manajemen Pengguna --" +
                    "\n1. Tambah Pengguna" +
                    "\n2. Tampilkan Detail Pengguna" +
                    "\n3. Update Pengguna" +
                    "\n4. Hapus Pengguna" +
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
    public static void tambahPengguna(){
        PenggunaDTO penggunaDTO = new PenggunaDTO();
        System.out.print("\nTAMBAH BUKU ->");
        System.out.print("" +
                "\nPilih jenis pengguna:" +
                "\n" +
                "\n      1. Admin" +
                "\n      2. Anggota" +
                "\n" +
                "\nMasukkan pilihan: ");
        int x = input.nextInt();
        input.nextLine();

        switch (x){
            case 1:
                InformationPrinter.tampilkanAtributDenganInput(penggunaDTO, "", 1, "nama", "alamat", "nomorHP", "username", "password", "nipADMIN");
                penggunaDTO.jenis = JenisPengguna.ADMIN;
                break;
            case 2:
                InformationPrinter.tampilkanAtributDenganInput(penggunaDTO, "", 1, "nama", "alamat", "nomorHP", "username", "password", "terlambatANGGOTA", "maksimalPinjamANGGOTA", "jumlahPinjamANGGOTA");
                penggunaDTO.jenis = JenisPengguna.ANGGOTA;
                break;

        }

        penggunaDTO.id = IdGenerator.generateUniqueId(PenggunaController.ambilSemuaPengguna(), Pengguna::getId);
        penggunaDTO.terlambatANGGOTA = false;
        PenggunaController.tambahPengguna(penggunaDTO);
    }

    //READ
    public static void detailPengguna(){
        while (true){
            if (isPenggunaKosong()) return;

            InformationPrinter.tampilkanObjek(PenggunaController.ambilSemuaPengguna(), "Pengguna yang tersedia", "nama");
            String idPengguna = AmbilIdPengguna("tampilkan detailnya");

            if (idPengguna == null) return;

            InformationPrinter.tampilkanAtributDenganNilai(PenggunaController.ambilPengguna(idPengguna),
                    "", 0, "id");

            System.out.print("\nApakah anda ingin melihat detail pengguna lainnya? (y/n): ");
            String x = input.nextLine();
            if (!(x.toUpperCase().equals("Y"))) break;
        }
    }

    //UPDATE
    public static void updatePengguna(){
        while (true){
            if (isPenggunaKosong()) return;

            InformationPrinter.tampilkanObjek(PenggunaController.ambilSemuaPengguna(), "Pengguna yang tersedia", "nama");
            String idPengguna = AmbilIdPengguna("update");

            if (idPengguna == null) return;

            InformationPrinter.tampilkanUpdateAtribut(
                    PenggunaController.ambilPengguna(idPengguna),
                    " ", idPengguna,
                    data -> PenggunaController.updateAtribut(data.id, data.namaAtribut, data.nilaiBaru),
                    0, "kode", "jenis");

            System.out.print("\nApakah anda ingin meng-update atribut pengguna lainnya? (y/n): ");
            String x = input.nextLine();
            if (!(x.toUpperCase().equals("Y"))) break;
        }
    }

    //DELETE
    public static void hapusPengguna () {
        if (isPenggunaKosong()) return;

        InformationPrinter.tampilkanObjek(PenggunaController.ambilSemuaPengguna(), "Pengguna yang tersedia", "nama");
        String idPengguna = AmbilIdPengguna("hapus");

        System.out.print("Yakin ingin menghapus pengguna bernama \""+ PenggunaController.ambilPengguna(idPengguna).getNama()+"\"? (y/n): ");
        String x = input.nextLine();

        if ((x.toUpperCase().equals("Y"))) {
            PenggunaController.hapusPengguna(idPengguna);
            System.out.print("Hapus pengguna berhasil!\n");
        }
        else {
            System.out.print("Hapus pengguna dibatalkan.\n");
        }
    }


    //===TOOLS===
    public static String AmbilIdPengguna(String aksi){
        String y;
        int x;
        System.out.print("\nPilih nomor pengguna yang ingin di"+aksi+": "); x = input.nextInt() - 1; input.nextLine();
        if (x >= PenggunaController.ambilSemuaPengguna().size() || x < 0) {
            System.out.print("pilihan tidak ada!\n");
            y = null;
        }
        else {
            y = PenggunaController.ambilSemuaPengguna().get(x).getId();
        }
        return y;
    }

    public static Boolean isPenggunaKosong(){
        if (PenggunaController.ambilSemuaPengguna().isEmpty()){
            System.out.print("Pengguna kosong!\n");
            return true;
        }else {
            return false;
        }
    }
}
