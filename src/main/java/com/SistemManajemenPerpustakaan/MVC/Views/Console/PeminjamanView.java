package com.SistemManajemenPerpustakaan.MVC.Views.Console;

import com.SistemManajemenPerpustakaan.DTOs.PeminjamanDTO;
import com.SistemManajemenPerpustakaan.MVC.Controllers.BukuController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.LoginController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.PeminjamanController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.PenggunaController;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Anggota;
import com.SistemManajemenPerpustakaan.Utils.*;

import java.util.Scanner;

public class PeminjamanView {
    //OBJECT
    private static Scanner input = new Scanner(System.in);

    //METHOD
    //TAMBAH PEMINJAMANAN
    public static void tambahPeminjaman(){
        PeminjamanDTO peminjamanDTO = new PeminjamanDTO();


        //CEK APAKAH BUKU KOSONG, KALO KOSONG MAKA KELUAR DARI METHOD
        if (IsObjekKosong.check(BukuController.ambilSemuaBuku(), "Buku")) return;

        //CEK APAKAH ADA BUKU YAN TERSEDIA, JIKA TIDAK MAKA KELUAR DARI METHOD
        int bukuTidakTersedia = 0;
        for (Buku buku : BukuController.ambilSemuaBuku()){
            if (!buku.getTersedia()){
                bukuTidakTersedia++;
                if (bukuTidakTersedia == BukuController.ambilSemuaBuku().size()){
                    System.out.print("Buku yang tersedia tidak ada!\n");
                    return;
                }
            }
        }


        //MENAMPILKAN DAFTAR BUKU YANG TERSEDIA
        System.out.print("\nBuku yang Tersedia\n");
        InformationPrinter.tampilkanObjek(
                BukuController.ambilSemuaBuku(), "",
                "tersedia", true, "judul", null);

        //AMBIL KODE, BUKU, DAN PENGGUNA TERPILIH SAAT INI
        String kodeBuku = ObjekIdGetter.get(
                BukuController.ambilSemuaBuku(),
                "\nPilih buku yang ingin dipinjam: ", Buku::getKode);

        if (!IsIdExist.check(kodeBuku, BukuController.ambilSemuaBuku(), Buku::getKode)){
            System.out.print("Buku dengan judul tersebut tidak ada!\n");
            return;
        }

        Anggota penggunaSaatIni = (Anggota) LoginController.getPenggunaSaatIni();

        if (penggunaSaatIni.getJumlahPinjam() == penggunaSaatIni.getMaksimalPinjam() &&
                penggunaSaatIni.getMaksimalPinjam() < 3){
            System.out.print("Peminjaman ditolak! Anda telah meminjam " +
                    ""+penggunaSaatIni.getJumlahPinjam()+"" +
                    " buku, anda hanya boleh meminjam maksimal "+penggunaSaatIni.getMaksimalPinjam()+"" +
                    " buku saja dikarenakan maksimal peminjaman buku Anda dikurangi akibat keterlambatan " +
                    "Anda dalam mengumpulkan buku sebelumnya.\n");
            return;
        }
        else if (penggunaSaatIni.getJumlahPinjam() == penggunaSaatIni.getMaksimalPinjam()) {
            System.out.print("Peminjaman ditolak! Anda telah meminjam sebanyak jumlah maksimal peminjaman buku" +
                    " ("+penggunaSaatIni.getMaksimalPinjam()+" buku).\n");
            return;
        }

        peminjamanDTO.id = IdGenerator.generate();
        peminjamanDTO.idAnggota = LoginController.getPenggunaSaatIni().getId();
        peminjamanDTO.kodeBuku = BukuController.ambilBuku(kodeBuku).getKode();
        peminjamanDTO.tanggalKembali = "-";
        peminjamanDTO.tanggalPinjam = DateTimeTools.getTanggalHariIni();
        peminjamanDTO.deadline = DateTimeTools.buatDeadline(7);
        peminjamanDTO.status = "Belum Dikembalikan";

        PenggunaController.updateAtribut(penggunaSaatIni.getId(), "jumlahPinjam", (penggunaSaatIni.getJumlahPinjam() + 1));

        BukuController.updateAtribut(kodeBuku, "tersedia", false);

        PeminjamanController.tambahPeminjaman(peminjamanDTO);
        System.out.print("Peminjaman Buku Berhasil! Anda sekarang meminjam "+penggunaSaatIni.getJumlahPinjam()+" buku. Deadline peminjaman buku ini adalah "+peminjamanDTO.deadline+".\n");
    }

    //KEMBALIKAN BUKU/HAPUS PEMINJAMAN
    public static void kembalikanBukuPeminjaman(){

    }


    //LIAT DETAIL INFORMASI DARI PEMINJAMAN YANG DIPILIH
    public static void detailPeminjaman(){
        while (true){
            if (IsObjekKosong.check(BukuController.ambilSemuaBuku(), "dawd")) return;
            System.out.print("Peminjaman yang Tersedia");
            InformationPrinter.tampilkanObjek(PeminjamanController.ambilSemuaPeminjaman(), "", "tanggalPinjam");
            String idPeminjaman = ObjekIdGetter.get(PeminjamanController.ambilSemuaPeminjaman(), "\nPilih peminjaman yang ingin dilihat lebih detail: ", Peminjaman::getId);

            if (idPeminjaman == null) return;

            InformationPrinter.tampilkanAtributDenganNilai(PeminjamanController.ambilPeminjaman(idPeminjaman),
                    "", 0, "id");

            System.out.print("\nApakah anda ingin melihat detail peminjaman lainnya? (y/n): ");
            String x = input.nextLine();
            if (!(x.toUpperCase().equals("Y"))) break;
        }
    }
}
