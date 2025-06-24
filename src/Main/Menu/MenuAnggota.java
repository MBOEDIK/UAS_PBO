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
    private static int inputInt, subInputInt, subSubInputInt;
    private static String inputStr;
    private static Boolean inputBool;

    //OBJECTS
    static Scanner input = new Scanner(System.in);

//METHODS
    //============================================================================================================================================================================================================

    public static void peminjaman(){
        String alamatLengkap = "", nomorHPPengguna = "", judulBuku = "", username = "", password = "";
        String kodeBuku = "", idAnggota = "";

        loop : while (true){

            for (int i = 0; i < PenyimpananData.getBuku().size(); i++){
                if (PenyimpananData.getBuku().get(i).getKetersediaanBuku()){
                    break;
                }
                if (i == PenyimpananData.getBuku().size() - 1){
                    System.out.print("Buku tidak tersedia.\n");
                    break loop;
                }
            }

            ArrayList<Buku> bukuTersedia = new ArrayList<>();
            for (int i = 0; i < PenyimpananData.getBuku().size(); i++){
                if (PenyimpananData.getBuku().get(i).getKetersediaanBuku()){
                    bukuTersedia.add(PenyimpananData.getBuku().get(i));
                }
            }

            System.out.print("\nDaftar Buku Tersedia:");
            for (int i = 0; i < bukuTersedia.size(); i++){
                System.out.print("\n      "+(i + 1)+". "+bukuTersedia.get(i).getJudulBuku());
            }

            System.out.print("\nPeminjaman Buku ->");
            System.out.print("\nMasukkan judul buku yang ingin dipinjam: "); judulBuku = input.nextLine();

//            subLoop : for (int i = 0; i < PenyimpananData.getPengguna().size(); i++){
//                if (PenyimpananData.getPengguna().get(i).getNamaPengguna().toLowerCase().equals(namaLengkap.toLowerCase())){
//
//                    break subLoop;
//                }
//                if (i == PenyimpananData.getPengguna().size() - 1){
//                    System.out.print("Anda belum menjadi anggota, silahkan isi form di bawah ini terlebih dahulu ->");
//                    System.out.print("\nMasukkan alamat lengkap Anda: "); alamatLengkap = input.nextLine();
//                    System.out.print("Masukkan nomor handphone Anda: "); nomorHPPengguna = input.nextLine();
//                    System.out.print("*Buat Username: "); username = input.nextLine();
//                    System.out.print("*Buat Password: "); password = input.nextLine();
//                    System.out.print("Masukkan judul buku yang ingin dipinjam: "); judulBuku = input.nextLine();
//                    break subLoop;
//                }
//            }

            subLoop : for (int i = 0; i < PenyimpananData.getBuku().size(); i++){
                if (PenyimpananData.getBuku().get(i).getKetersediaanBuku()){
                    if (PenyimpananData.getBuku().get(i).getJudulBuku().toLowerCase().equals(judulBuku.toLowerCase())){
                        kodeBuku = PenyimpananData.getBuku().get(i).getKodeBuku();
                        PenyimpananData.getBuku().get(i).setKetersediaanBuku(false);
                        break subLoop;
                    }
                    if (i == PenyimpananData.getPengguna().size() - 1) {
                        System.out.print("Buku dengan judul tersebut sedang tidak tersedia.\n");
                        break loop;
                    }
                }
            }

            int jumlahBukuDiPinjam = 0;
            String deadline = "";

            subLoop : for (int i = 0; i < PenyimpananData.getPengguna().size(); i++){
                if (PenyimpananData.getPengguna().get(i).getNamaPengguna().toLowerCase().equals(LoginSystem.getPenggunaSaatIni().getNamaPengguna().toLowerCase())){
                    if (PenyimpananData.getPengguna().get(i) instanceof Admin){
                        System.out.print("Peminjaman ditolak! Admin tidak boleh meminjam buku melalui prosedur ini!\n");
                        break loop;
                    }
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

                    ((Anggota) PenyimpananData.getPengguna().get(i)).setJumlahPinjamBuku(1);
                    jumlahBukuDiPinjam = ((Anggota) PenyimpananData.getPengguna().get(i)).getJumlahPinjamBuku();
                    deadline = DateTimeTools.buatDeadline(7);

                    idAnggota = PenyimpananData.getPengguna().get(i).getIdPengguna();
                    break subLoop;
                }
            }

            PeminjamanController.tambah(IdGenerator.generate(), idAnggota, kodeBuku, DateTimeTools.getTanggalHariIni(),"-", DateTimeTools.buatDeadline(7), "Tepat Waktu" );
            System.out.print("Peminjaman Buku Berhasil! Anda sekarang meminjam "+jumlahBukuDiPinjam+" buku. Deadline peminjaman buku ini adalah "+deadline+".\n");
            break loop;
        }
    }

    //============================================================================================================================================================================================================

    public static void pengembalian(){
        loop : while (true){
            System.out.print("" +
                    "\n-- Manajemen Pengguna --" +
                    "\n1. Tambah Pengguna" +
                    "\n2. Edit Pengguna" +
                    "\n3. Hapus Pengguna" +
                    "\n4. Tampilkan Detail Pengguna" +
                    "\n5. Kembali ke Halaman Admin" +
                    "\nMasukkan Pilihan: ");
            subInputInt = input.nextInt();
            input.nextLine();

            switch (subInputInt){
                case 1:
                    SubMenuManajemenPengguna.menuTambahPengguna();
                    continue;
                case 2:
                    SubMenuManajemenPengguna.menuEditPengguna();
                    continue;
                case 3:
                    SubMenuManajemenPengguna.menuHapusPengguna();
                    continue;
                case 4:
                    SubMenuManajemenPengguna.menuDetailPengguna();
                    continue;
                case 5: break loop;
            }
        }
    }

    //============================================================================================================================================================================================================

}
