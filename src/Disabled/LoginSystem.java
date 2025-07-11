package com.SistemManajemenPerpustakaan.Sementara;

import com.SistemManajemenPerpustakaan.MVC.Models.Book.Majalah;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Novel;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Admin;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Anggota;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;
import com.SistemManajemenPerpustakaan.Utils.IdGenerator;

import java.util.Scanner;

//INI CLASS YG HANDLE SEMUA URUSAN LOGIN LOGINAN
public class LoginSystem implements DataAccessHelper {

//ATTRIBUTE
    private static String username = "", password = "";
    private static String statusPengguna = "";

//OBJECT
    private static Scanner input = new Scanner(System.in);
    private static Pengguna penggunaSaatIni;


//DATA SEMENTARA (UNTUK UJI COBA VALIDASI AJA, NANTI INI DIHAPUS AJA KALO UDH ADA DATABSE)
     public static void dummyData(){
        dataPengguna.add(new Admin(IdGenerator.generate(), "Admin","Prabowo", "Tegalgondo, Malang, Jawa Timur, Indoneisia", "08123456789", "a", "a", "53244342522423425"));
        dataPengguna.add(new Anggota(IdGenerator.generate(), "Anggota", "Budi", "Ngade, Ternate, Maluku Utara, Indonesia", "082278925369", "a", "a", false, 3, 0));
        dataBuku.add(new Majalah(IdGenerator.generate(), "Buku Majalah A", "Majalah", "Cahyono", "2022", true, "Fisika Kuantum"));
        dataBuku.add(new Novel(IdGenerator.generate(), "Buku Novel C", "Novel", "Agus", "1999", true, "Misteri"));
     }

//METHODS
//====================================================================

    //logika menu login
    public static void tampilanLoginUtama(){
        //--------------------------------------------------------------------

        loop : while (true){
            //pilih status pengguna (admin atau anggota)
            while (true){
                System.out.print("" +
                        "\n=== PILIH STATUS PENGGUNA ===" +
                        "\n1. Admin" +
                        "\n2. Anggota" +
                        "\nPilih login sebagai apa (1/2): ");
                int pilihan = input.nextInt();
                input.nextLine();

                switch (pilihan){
                    case 1: statusPengguna = "ADMIN"; break;
                    case 2: statusPengguna = "ANGGOTA"; break;
                    default:
                        System.out.print("Pilihan tidak ada!\n");
                        continue loop;
                }
                break;
            }

            //--------------------------------------------------------------------

            //input login
            subLoop : while (true){
                System.out.print("\n=== LOGIN SEBAGAI "+statusPengguna+" ===");
                System.out.print("\nMasukkan Username: "); username = input.nextLine();
                System.out.print("Masukkan Password: "); password = input.nextLine();

                int i = 0;

                //validasi login
                subSubLoop : for(Pengguna pengguna : dataPengguna){
                    if(username.equals(pengguna.getUsername()) && password.equals(pengguna.getPassword())){
                        if(statusPengguna.equals("ADMIN") && pengguna instanceof Admin){
                            penggunaSaatIni = pengguna;
                            MenuUtama.menuAdmin();
                            continue loop;
                        }
                        if(statusPengguna.equals("ANGGOTA") && pengguna instanceof Anggota){
                            penggunaSaatIni = pengguna;
                            MenuUtama.menuAnggota();
                            continue loop;
                        }
                    }
                    if (i == dataPengguna.size() - 1){
                        System.out.print("Username/Password salah!\n");
                        continue subLoop;
                    }
                    i++;
                }
            }
        }
    }

    //====================================================================

    //getter
    public static Pengguna getPenggunaSaatIni(){ return penggunaSaatIni; }

}
