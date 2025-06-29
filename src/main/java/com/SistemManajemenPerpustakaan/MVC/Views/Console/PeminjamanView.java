package com.SistemManajemenPerpustakaan.MVC.Views.Console;

import com.SistemManajemenPerpustakaan.DTOs.PeminjamanDTO;
import com.SistemManajemenPerpustakaan.MVC.Controllers.BukuController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.LoginController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.PeminjamanController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.PenggunaController;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Anggota;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;
import com.SistemManajemenPerpustakaan.Utils.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

public class PeminjamanView {
    //OBJECT
    private static Scanner input = new Scanner(System.in);

    //METHOD
    //TAMBAH PEMINJAMANAN
    public static void tambahPeminjaman(){
        while (true){
            PeminjamanDTO peminjamanDTO = new PeminjamanDTO();

            //MENGAMBIL DATA PENGGUNA YANG LOGIN SAAT INI
            Anggota penggunaSaatIni = (Anggota) LoginController.getPenggunaSaatIni();

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

            //AMBIL KODE BUKU
            String kodeBuku = ObjekIdGetter.getBersyarat(
                    BukuController.ambilSemuaBuku(),
                    "\nPilih nomor buku yang ingin dipinjam: ", Buku::getKode, Buku::getTersedia, true);

            //CEK APAKAH BUKU YANG DIPILIH ADA, JIKA TIDAK MAKA ULANGI METHOD
            if (!IsIdExist.check(kodeBuku, BukuController.ambilSemuaBuku(), Buku::getKode)){
                System.out.print("Buku dengan judul tersebut tidak ada!\n");
                break;
            }

            //CEK APAKAH ANGGOTA INI MENCAPAI BATAS MAKSIMAL PEMINJAMAN
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

            //PERSIAPAN PENAMBAHAN DATA PEMINJAMAN
            peminjamanDTO.id = IdGenerator.generateUniqueId(PeminjamanController.ambilSemuaPeminjaman(), Peminjaman::getId);
            peminjamanDTO.idAnggota = LoginController.getPenggunaSaatIni().getId();
            peminjamanDTO.kodeBuku = BukuController.ambilBuku(kodeBuku).getKode();
            peminjamanDTO.tanggalKembali = "-";
            peminjamanDTO.tanggalPinjam = DateTimeTools.getTanggalHariIni();
            peminjamanDTO.deadline = DateTimeTools.buatDeadline(7);
            peminjamanDTO.status = "Belum Dikembalikan";

            //MENETAPKAN JUMLAH BUKU YG UDAH DIPINJAM OLEH PENGGUNA INI MENJADI +1
            PenggunaController.updateAtribut(penggunaSaatIni.getId(), "jumlahPinjam", (penggunaSaatIni.getJumlahPinjam() + 1));
            for (Pengguna pengguna : PenggunaController.ambilSemuaPengguna()){
                if (pengguna.getId().equals(penggunaSaatIni.getId())) {
                    penggunaSaatIni = (Anggota) pengguna;
                    LoginController.setPenggunaSaatIni(penggunaSaatIni);
                }
            }

            //MENETAPKAN BUKU TERPILIH MENJADI TIDAK TERSEDIA
            BukuController.updateAtribut(kodeBuku, "tersedia", false);

            PeminjamanController.tambahPeminjaman(peminjamanDTO);
            System.out.print("Peminjaman Buku Berhasil! Anda sekarang meminjam "+penggunaSaatIni.getJumlahPinjam()+" buku. Deadline peminjaman buku ini adalah "+peminjamanDTO.deadline+".\n");
            break;
        }
    }

    //KEMBALIKAN BUKU/HAPUS PEMINJAMAN
    public static void kembalikanBukuPeminjaman(){
        //MENAMPILKAN DAFTAR BUKU YANG DIPINJAM OLEH PENGGUNA INI
        System.out.println("Daftar Buku yang Kamu Pinjam");
        ArrayList<String> kodeBuku = new ArrayList<>();
        ArrayList<String> idPeminjaman = new ArrayList<>();
        ArrayList<String> deadlinPeminjaman = new ArrayList<>();
        Anggota penggunaSaatIni = (Anggota) LoginController.getPenggunaSaatIni();
        for (Pengguna pengguna : PenggunaController.ambilSemuaPengguna()){
            if (pengguna.getId().equals(penggunaSaatIni.getId())) penggunaSaatIni = (Anggota) pengguna;
        }
        for (int i = 1; i <= penggunaSaatIni.getJumlahPinjam(); i++){
            for (Peminjaman peminjaman : PeminjamanController.ambilSemuaPeminjaman()){
                if (penggunaSaatIni.getId().equals(peminjaman.getIdAnggota()) && peminjaman.getStatus().equals("Belum Dikembalikan")){
                    for (Buku buku : BukuController.ambilSemuaBuku()){
                        if (peminjaman.getKodebuku().equals(buku.getKode())){
                            kodeBuku.add(buku.getKode());
                            deadlinPeminjaman.add(peminjaman.getDeadline());
                            idPeminjaman.add(peminjaman.getId());
                        }
                    }
                }
            }
        }

        System.out.println();
        for (int i = 0; i < kodeBuku.size(); i++){
            System.out.print("      "+(i+1)+". ");
            InformationPrinter.tampilkanObjekTanpaNomor(
                    BukuController.ambilSemuaBuku(),
                    "kode", kodeBuku.get(i), "judul");
            if (DateTimeTools.sisaHariMenujuDeadline(deadlinPeminjaman.get(i)) < 0){
                System.out.print(" ("+(DateTimeTools.sisaHariMenujuDeadline(deadlinPeminjaman.get(i)) * (-1))+" hari berlalu dari deadline)");
            }
            else {
                System.out.print(" (Deadline "+DateTimeTools.sisaHariMenujuDeadline(deadlinPeminjaman.get(i))+" hari lagi)");
            }
            System.out.println();
        }
        //MEMILIH BUKU YANG INGIN DIKEMBALIKAN
        System.out.print("\nMasukkan nomor buku yang ingin dikembalikan: ");
        int x = input.nextInt() - 1;
        input.nextLine();

        if (x >= kodeBuku.size()){
            System.out.print("Nomor buku yang Anda pilih tidak ada!\n");
            return;
        }

        if (DateTimeTools.sisaHariMenujuDeadline(deadlinPeminjaman.get(x)) < 0){
            PenggunaController.updateAtribut(penggunaSaatIni.getId(), "maksimalPinjam", (penggunaSaatIni.getMaksimalPinjam()-1));
            PenggunaController.updateAtribut(penggunaSaatIni.getId(), "terlambat",true);
            PenggunaController.updateAtribut(penggunaSaatIni.getId(), "jumlahPinjam", (penggunaSaatIni.getJumlahPinjam() - 1));
            for (Pengguna pengguna : PenggunaController.ambilSemuaPengguna()){
                if (pengguna.getId().equals(penggunaSaatIni.getId())) {
                    penggunaSaatIni = (Anggota) pengguna;
                    LoginController.setPenggunaSaatIni(penggunaSaatIni);
                }
            }
            PeminjamanController.updateAtribut(idPeminjaman.get(x), "tanggalKembali", DateTimeTools.getTanggalHariIni());
            PeminjamanController.updateAtribut(idPeminjaman.get(x), "status", "Sudah dikembalikan");
            BukuController.updateAtribut(kodeBuku.get(x), "tersedia", true);
            System.out.print("Anda terlambat mengembalikan buku, " +
                    "sebagai konsekuensi maka jumlah maksimal peminjaman buku Anda dikurangi 1. " +
                    "Jumlah maksimal buku yang bisa Anda pinjam saat ini adalah "+penggunaSaatIni.getMaksimalPinjam()+" buku.\n");
        }
        else {
            PenggunaController.updateAtribut(penggunaSaatIni.getId(), "jumlahPinjam", (penggunaSaatIni.getJumlahPinjam() - 1));
            for (Pengguna pengguna : PenggunaController.ambilSemuaPengguna()){
                if (pengguna.getId().equals(penggunaSaatIni.getId())) {
                    penggunaSaatIni = (Anggota) pengguna;
                    LoginController.setPenggunaSaatIni(penggunaSaatIni);
                }
            }
            PeminjamanController.updateAtribut(idPeminjaman.get(x), "tanggalKembali", DateTimeTools.getTanggalHariIni());
            PeminjamanController.updateAtribut(idPeminjaman.get(x), "status", "Sudah dikembalikan");
            BukuController.updateAtribut(kodeBuku.get(x), "tersedia", true);
            System.out.print("Terima kasih karena sudah mengembalikan buku tepat waktu.\n");
        }
    }


    //LIAT DETAIL INFORMASI DARI PEMINJAMAN YANG DIPILIH
    public static void detailPeminjaman(){
        while (true){
            if (IsObjekKosong.check(PeminjamanController.ambilSemuaPeminjaman(), "Peminjaman")) return;
            System.out.print("Peminjaman yang Tersedia\n");
            InformationPrinter.tampilkanObjek(PeminjamanController.ambilSemuaPeminjaman(), "", "tanggalPinjam");
            String idPeminjaman = ObjekIdGetter.get(PeminjamanController.ambilSemuaPeminjaman(),
                    "\nPilih peminjaman yang ingin dilihat lebih detail: ", Peminjaman::getId);

            if (idPeminjaman == null) return;

            InformationPrinter.tampilkanAtributDenganNilai(PeminjamanController.ambilPeminjaman(idPeminjaman),
                    "", 0, "id");

            System.out.print("\nApakah anda ingin melihat detail peminjaman lainnya? (y/n): ");
            String x = input.nextLine();
            if (!(x.toUpperCase().equals("Y"))) break;
        }
    }
}
