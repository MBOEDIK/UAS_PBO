package com.SistemManajemenPerpustakaan.MVC.Views.Console;

import com.SistemManajemenPerpustakaan.Enums.JenisPengguna;

import java.util.InputMismatchException;
import java.util.Scanner;

//CLASS YG MENAMPILKAN TAMPILAN LOGIN
public class LoginView {

    //ATTRIBUTE
    private static String[] usernameNpassword = new String[2];
    private static String tempStr = null;

    //OBJECT
    private static Scanner input = new Scanner(System.in);

    //MENAMPILKAN PILIHAN JENIS PENGGUNA
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
            input.nextLine();
            switch (x){
                case 1 -> y = JenisPengguna.ADMIN;
                case 2 -> y = JenisPengguna.ANGGOTA;
                default -> y = null;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Input harus berupa angka!\n");

        }
        return y;
    }

    //MENAMPILKAN FORM INPUT USERNAME DAN PASSOWRD
    public static String[] inputLogin(String jenisPengguna){
        System.out.print("\n=== LOGIN SEBAGAI "+jenisPengguna+" ===");
        System.out.print("\nMasukkan Username: "); usernameNpassword[0] = input.nextLine();
        System.out.print("Masukkan Password: "); usernameNpassword[1] = input.nextLine();
        return usernameNpassword;
    }

    //MEMBERITAHU BAHWA USERNAME/PASSWORD SALAH
    public static void usernameOrPasswordSalah(){
        System.out.print("Username/Password salah!\n");
    }
}
