package com.SistemManajemenPerpustakaan.MVC.Views.Console;

import java.util.Scanner;

public class MenuView {

    //ATTRIBUTE
    private static String tempStr = null;
    private static int tempInt = 0;
    private static int menuUtama = 0;

    //OBJECT
    private static Scanner input = new Scanner(System.in);

    //METHOD
    public static int menuAdmin(){
        while (true){
            int x;
            System.out.print("" +
                    "\n-- HALAMAN ADMIN --" +
                    "\n1. Manajemen Buku" +
                    "\n2. Manajemen Pengguna" +
                    "\n3. Detail Peminjaman" +
                    "\n4. Log out" +
                    "\nPilih menu: ");
            x = input.nextInt();
            input.nextLine();

            if (x < 1 || x > 4){
                System.out.print("Pilihan tidak ada!\n");
                continue;
            }
            return x;
        }
    }

    public static int menuAnggota(){
        int x;
        while (true){
            System.out.print("" +
                    "\n-- HALAMAN ANGGOTA --" +
                    "\n1. Peminjaman" +
                    "\n2. Pengembalian" +
                    "\n3. Log out" +
                    "\nPilih menu: ");
            x = input.nextInt();
            input.nextLine();

            if (x < 1 || x > 4){
                System.out.print("Pilihan tidak ada!\n");
                continue;
            }
            return x;
        }
    }
}