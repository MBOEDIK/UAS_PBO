package com.SistemManajemenPerpustakaan.Sementara;

import com.SistemManajemenPerpustakaan.MVC.Controllers.PeminjamanController;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Anggota;
import com.SistemManajemenPerpustakaan.Tools.DateTimeTools;
import com.SistemManajemenPerpustakaan.Tools.IdGenerator;
import com.SistemManajemenPerpustakaan.Tools.DataAccessHelper;

import java.util.Scanner;

public class MenuAnggota implements DataAccessHelper {

    //ATTRIBUTES
    private static String judulBuku = null;
    private static String kodeBuku = null, idAnggota = null;
    private static int jumlahBukuDiPinjam = 0;

    //OBJECTS
    private static Scanner input = new Scanner(System.in);

//METHODS
    //============================================================================================================================================================================================================

    public static void peminjaman(){

        loop : while (true){
            //Cek ketersediaan Buku
            for (int i = 0; i < dataBuku.size(); i++){
                if (dataBuku.get(i).getTersedia()){
                    break;
                }
                if (i == dataBuku.size() - 1){
                    System.out.print("Buku tidak tersedia.\n");
                    break loop;
                }
            }



            //Tampilkan buku yang attribute "ketersediaanBuku"-nya true
            int numIndex = 1;
            System.out.print("\nDaftar Buku Tersedia:");
            subLoop : for (Buku buku : dataBuku){
                if (!buku.getTersedia()){
                    continue subLoop;
                }
                System.out.print("\n      "+(numIndex)+". "+buku.getJudul());
                numIndex++;
            }



            //Input judul buku
            System.out.print("\nPeminjaman Buku ->");
            System.out.print("\nMasukkan judul buku yang ingin dipinjam: "); judulBuku = input.nextLine();



            //ngecek yg diinput itu String atau angka, jika angka maka loop utama diulang dari awal
            boolean hanyaNonAngka = false;
            for (char c : judulBuku.toCharArray()){
                if (!Character.isDigit(c)){
                    hanyaNonAngka = true;
                }
            }
            if (!hanyaNonAngka){
                System.out.print("Buku dengan judul tersebut sedang tidak tersedia.\n");
                continue loop;
            }



            //Ngecek apakah input cocok dengan semua judul dari semua buku yang ada, kalo ga ada maka loop utama diulang dari awal
            subLoop : for (int i = 0; i < dataBuku.size(); i++){
                if (dataBuku.get(i).getTersedia()){
                    if (dataBuku.get(i).getJudul().toLowerCase().equals(judulBuku.toLowerCase())){
                        kodeBuku = dataBuku.get(i).getKode();
                        dataBuku.get(i).setTersedia(false);
                        break subLoop;
                    }
                    if (i == dataBuku.size() - 1) {
                        System.out.print("Buku dengan judul tersebut sedang tidak tersedia.\n");
                        continue loop;
                    }
                }
            }



            //Ngecek apakah Anggota saat ini udh sampe batas maksimal peminjaman atau belum. Kalo udah, maka keluar dari loop utama.
            String deadline = "";
            subLoop : for (int i = 0; i < dataPengguna.size(); i++){
                if (dataPengguna.get(i).getNama().toLowerCase().equals(LoginSystem.getPenggunaSaatIni().getNama().toLowerCase())){
                    if (((Anggota) dataPengguna.get(i)).getJumlahPinjamBuku() == ((Anggota) dataPengguna.get(i)).getMaksimalPinjamBuku()){
                        if (((Anggota) dataPengguna.get(i)).getTerlambatMengembalikan()){
                            System.out.print("Peminjaman ditolak! Anda telah meminjam " +
                                    ""+((Anggota) dataPengguna.get(i)).getJumlahPinjamBuku()+"" +
                                    " buku, anda hanya boleh meminjam maksimal "+((Anggota) dataPengguna.get(i)).getMaksimalPinjamBuku()+"" +
                                    " buku saja dikarenakan maksimal peminjaman buku Anda dikurangi akibat keterlambatan Anda dalam mengumpulkan buku sebelumnya.\n");
                        }
                        else {
                            System.out.print("Peminjaman ditolak! Anda telah meminjam sebanyak jumlah maksimal peminjaman buku" +
                                    " ("+((Anggota) dataPengguna.get(i)).getJumlahPinjamBuku()+" buku).\n");
                        }
                        break loop;
                    }

                    //Nambah +1 jumlah buku yg dipinjam anggota ini
                    //Ngambil jumlah semua buku yg udah dipinjam anggota saat ini selama ini
                    //Netapin deadline
                    //Ngambil id anggota saat ini utk dimasukkin ke informasi peminjaman
                    ((Anggota) dataPengguna.get(i)).setJumlahPinjamBuku(1);
                    jumlahBukuDiPinjam = ((Anggota) dataPengguna.get(i)).getJumlahPinjamBuku();
                    deadline = DateTimeTools.buatDeadline(7);
                    idAnggota = dataPengguna.get(i).getId();
                    break subLoop;
                }
            }



            //Tambah peminjaman dengan masukkin semua informasi peminjaman yg dibutuhin
            PeminjamanController.tambah(IdGenerator.generate(), idAnggota, kodeBuku, DateTimeTools.getTanggalHariIni(),"-", DateTimeTools.buatDeadline(7), "Tepat Waktu" );
            System.out.print("Peminjaman Buku Berhasil! Anda sekarang meminjam "+jumlahBukuDiPinjam+" buku. Deadline peminjaman buku ini adalah "+deadline+".\n");
            break loop;
        }
    }

    //============================================================================================================================================================================================================

    public static void pengembalian(){
        loop : while (true){
            System.out.print("\nDaftar Buku yang Anda Pinjam: ");
            int numIndex = 1, indeksBuku = 0;
            subLoop : for (int i = 0; i < dataPeminjaman.size(); i++){
                boolean cocok1 = false, cocok2 = false;
                for (int j = 0; j < dataPengguna.size(); j++){
                    if (dataPeminjaman.get(i).getIdAnggota().equals(LoginSystem.getPenggunaSaatIni().getId())) cocok1 = true;
                }
                for (int j = 0; j < dataBuku.size(); j++){
                    if (dataPeminjaman.get(i).getKodebuku().equals(dataBuku.get(j).getKode())) {
                        indeksBuku = j;
                        cocok2 = true;
                    }
                }
                if (cocok1 == true && cocok2 == true){
                    System.out.print("\n      "+numIndex+". "+dataBuku.get(indeksBuku).getJudul()+" (Deadline "+DateTimeTools.sisaHariMenujuDeadline(dataPeminjaman.get(i).getDeadline())+" hari lagi)");
                    numIndex++;
                }
            }
            System.out.print("\nMasukkan judul buku yang ingin dikembalikan: "); judulBuku = input.nextLine();


            System.out.print("FITUR INI BELUM JADI!\n");
            break loop;
        }
    }

    //============================================================================================================================================================================================================

}
