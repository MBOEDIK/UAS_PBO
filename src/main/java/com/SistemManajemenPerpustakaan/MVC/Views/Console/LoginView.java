// INI JUAN YANG KASI KOMEN
// CLASS VIEW UNTUK MENAMPILKAN LOGIN DI KONSOL

package com.SistemManajemenPerpustakaan.MVC.Views.Console;

import com.SistemManajemenPerpustakaan.Enums.JenisPengguna;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginView {

    // ARRAY UNTUK MENYIMPAN USERNAME DAN PASSWORD
    private static String[] usernameNpassword = new String[2];
    private static String tempStr = null;

    // OBJEK SCANNER UNTUK INPUT KONSOL
    private static Scanner input = new Scanner(System.in);

    // MENAMPILKAN MENU PILIHAN PENGGUNA (ADMIN/ANGGOTA)
    public static JenisPengguna menuInputJenisPengguna(){
        JenisPengguna y;
        int x;
        System.out.print("" +
                "\n=== PILIH JENIS PENGGUNA ===" +
                "\n1. Admin" +
                "\n2. Anggota" +
                "\nPilih login sebagai apa (1/2): ");

        try {
            x = input.nextInt();
            input.nextLine(); // BUAT NGILANGIN \n SISA DARI nextInt()
            switch (x){
                case 1 -> y = JenisPengguna.ADMIN;
                case 2 -> y = JenisPengguna.ANGGOTA;
                default -> y = null; // NILAI INVALID = NULL
            }
        } catch (InputMismatchException e) {
            // ERROR KALO INPUTNYA BUKAN ANGKA
            throw new InputMismatchException("Input harus berupa angka!\n");
        }
        return y;
    }

    // MINTA USER UNTUK ISI USERNAME DAN PASSWORD
    public static String[] inputLogin(String jenisPengguna){
        System.out.print("\n=== LOGIN SEBAGAI "+jenisPengguna+" ===");
        System.out.print("\nMasukkan Username: "); usernameNpassword[0] = input.nextLine();
        System.out.print("Masukkan Password: "); usernameNpassword[1] = input.nextLine();
        return usernameNpassword;
    }

    // MENAMPILKAN PESAN KALO LOGIN GAGAL
    public static void usernameOrPasswordSalah(){
        System.out.print("Username/Password salah!\n");
    }
}
