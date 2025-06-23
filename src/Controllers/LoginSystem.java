package Controllers;

import Models.PenyimpananData;
import Models.User.Admin;
import Models.User.Anggota;
import Models.User.Pengguna;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

//INI CLASS YG HANDLE SEMUA URUSAN LOGIN LOGINAN
public class LoginSystem {

//ATTRIBUTE
    String username = "", password = "";
    String statusPengguna = "";
    Pengguna pengguna;

//OBJECT
    Scanner input = new Scanner(System.in);
    PenyimpananData data = new PenyimpananData();
    ArrayList<Pengguna> penggunaList = new ArrayList<>();

//DATA SEMENTARA (UNTUK UJI COBA VALIDASI AJA, NANTI INI DIHAPUS AJA KALO UDH ADA DATABSE)
    public void dummyData(){
        data.getPengguna().add(new Admin("024231", "Prabowo", "Tegalgondo, Malang, Jawa Timur, Indoneisia", "08123456789", "prabowo123", "123456789", "53244342522423425"));
        data.getPengguna().add(new Anggota("0242313", "Budi", "Ngade, Ternate, Maluku Utara, Indonesia", "082278925369", "MBOEDIK", "iniPassword123", false, 3));
    }

//METHODS
    //logika menu login
    public void tampilanLoginUtama(){
        //====================================================================

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
                    System.out.print("Pilihan tidak ada!");
                    continue;
            }
            break;
        }

        //====================================================================

        //input login
        loop : while (true){
            System.out.print("\n=== LOGIN SEBAGAI "+statusPengguna+" ===");
            System.out.print("\nMasukkan Username: "); username = input.nextLine();
            System.out.print("Masukkan Password: "); password = input.nextLine();

            //validasi login
            for(Pengguna pengguna : data.getPengguna()){
                if(username.equals(pengguna.getUsername()) && password.equals(pengguna.getPassword())){
                    if(statusPengguna.equals("ADMIN") && pengguna instanceof Admin){
                        System.out.println("Panggil MenuSystem Admin");
                        break loop; //"loop" ini maksudnya nge-break "while (true)"
                    }
                    else if(statusPengguna.equals("ANGGOTA") && pengguna instanceof Anggota){
                        System.out.print("Panggil MenuSystem Admin");
                        break loop;
                    }
                }
                else {
                    System.out.print("Username/Password salah!\n");
                    continue loop;
                }
            }

            //====================================================================
        }
    }

}
