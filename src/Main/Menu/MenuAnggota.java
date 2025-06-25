package Main.Menu;

import Controllers.PeminjamanController;
import Controllers.PenggunaController;
import Main.LoginSystem;
import Main.Menu.SubMenuAdmin.SubMenuManajemenPengguna;
import Models.Book.Buku;
import Models.PenyimpananData;
import Models.User.Admin;
import Models.User.Anggota;
import Models.User.Pengguna;
import Tools.DateTimeTools;
import Tools.IdGenerator;
import com.sun.jdi.ArrayReference;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuAnggota {

    //ATTRIBUTES
    private static String judulBuku = null;
    private static String kodeBuku = null, idAnggota = null;
    private static int jumlahBukuDiPinjam = 0;

    //OBJECTS
    static Scanner input = new Scanner(System.in);

//METHODS
    //============================================================================================================================================================================================================

    public static void peminjaman(){

        loop : while (true){
            //Cek ketersediaan Buku
            for (int i = 0; i < PenyimpananData.getBuku().size(); i++){
                if (PenyimpananData.getBuku().get(i).getKetersediaanBuku()){
                    break;
                }
                if (i == PenyimpananData.getBuku().size() - 1){
                    System.out.print("Buku tidak tersedia.\n");
                    break loop;
                }
            }



            //Tampilkan buku yang attribute "ketersediaanBuku"-nya true
            int numIndex = 1;
            System.out.print("\nDaftar Buku Tersedia:");
            subLoop : for (Buku buku : PenyimpananData.getBuku()){
                if (!buku.getKetersediaanBuku()){
                    continue subLoop;
                }
                System.out.print("\n      "+(numIndex)+". "+buku.getJudulBuku());
                numIndex++;
            }



            //Input judul buku
            System.out.print("\nPeminjaman Buku ->");
            System.out.print("\nMasukkan judul buku yang ingin dipinjam: "); judulBuku = input.nextLine();



            //ngecek yg diinput itu String atau angka, jika angka maka loop utama diulang dari awal
            if (judulBuku.matches(".*\\d.*")) {
                System.out.println("Input tidak boleh angka!");
                continue loop;
            }



            //Ngecek apakah input cocok dengan semua judul dari semua buku yang ada, kalo ga ada maka loop utama diulang dari awal
            subLoop : for (int i = 0; i < PenyimpananData.getBuku().size(); i++){
                if (PenyimpananData.getBuku().get(i).getKetersediaanBuku()){
                    if (PenyimpananData.getBuku().get(i).getJudulBuku().toLowerCase().equals(judulBuku.toLowerCase())){
                        kodeBuku = PenyimpananData.getBuku().get(i).getKodeBuku();
                        PenyimpananData.getBuku().get(i).setKetersediaanBuku(false);
                        break subLoop;
                    }
                    if (i == PenyimpananData.getPengguna().size() - 1) {
                        System.out.print("Buku dengan judul tersebut sedang tidak tersedia.\n");
                        continue loop;
                    }
                }
            }



            //Ngecek apakah Anggota saat ini udh sampe batas maksimal peminjaman atau belum. Kalo udah, maka keluar dari loop utama.
            String deadline = "";
            subLoop : for (int i = 0; i < PenyimpananData.getPengguna().size(); i++){
                if (PenyimpananData.getPengguna().get(i).getNamaPengguna().toLowerCase().equals(LoginSystem.getPenggunaSaatIni().getNamaPengguna().toLowerCase())){
                    if (((Anggota) PenyimpananData.getPengguna().get(i)).getJumlahPinjamBuku() == ((Anggota) PenyimpananData.getPengguna().get(i)).getMaksimalPinjamBuku()){
                        if (((Anggota) PenyimpananData.getPengguna().get(i)).getTerlambatMengembalikan()){
                            System.out.print("Peminjaman ditolak! Anda telah meminjam " +
                                    ""+((Anggota) PenyimpananData.getPengguna().get(i)).getJumlahPinjamBuku()+"" +
                                    " buku, anda hanya boleh meminjam maksimal "+((Anggota) PenyimpananData.getPengguna().get(i)).getMaksimalPinjamBuku()+"" +
                                    " buku saja dikarenakan maksimal peminjaman buku Anda dikurangi akibat keterlambatan Anda dalam mengumpulkan buku sebelumnya.\n");
                        }
                        else {
                            System.out.print("Peminjaman ditolak! Anda telah meminjam sebanyak jumlah maksimal peminjaman buku" +
                                    " ("+((Anggota) PenyimpananData.getPengguna().get(i)).getJumlahPinjamBuku()+" buku).\n");
                        }
                        break loop;
                    }

                    //Nambah +1 jumlah buku yg dipinjam anggota ini
                    //Ngambil jumlah semua buku yg udah dipinjam anggota saat ini selama ini
                    //Netapin deadline
                    //Ngambil id anggota saat ini utk dimasukkin ke informasi peminjaman
                    ((Anggota) PenyimpananData.getPengguna().get(i)).setJumlahPinjamBuku(1);
                    jumlahBukuDiPinjam = ((Anggota) PenyimpananData.getPengguna().get(i)).getJumlahPinjamBuku();
                    deadline = DateTimeTools.buatDeadline(7);
                    idAnggota = PenyimpananData.getPengguna().get(i).getIdPengguna();
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

        }
    }

    //============================================================================================================================================================================================================

}
