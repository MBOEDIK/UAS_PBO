// INI JUAN YANG KASI KOMEN
// CLASS VIEW UNTUK MENAMPILKAN MENU ADMIN & ANGGOTA DI TERMINAL

package com.SistemManajemenPerpustakaan.MVC.Views.Console;

import java.util.Scanner;

public class MenuView {

    // VARIABEL SEMENTARA UNTUK MENYIMPAN NILAI INPUT
    private static String tempStr = null;
    private static int tempInt = 0;
    private static int menuUtama = 0;

    // SCANNER UNTUK BACA INPUT USER DARI TERMINAL
    private static Scanner input = new Scanner(System.in);

    // MENAMPILKAN MENU UTAMA UNTUK ADMIN
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
            x = input.nextInt(); // BACA PILIHAN USER
            input.nextLine(); // BUANG ENTER SISA INPUT

            if (x < 1 || x > 4){
                System.out.print("Pilihan tidak ada!\n"); // VALIDASI INPUT
                continue;
            }
            return x; // KEMBALIKAN PILIHAN YANG VALID
        }
    }

    // MENAMPILKAN MENU UTAMA UNTUK ANGGOTA
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
                System.out.print("Pilihan tidak ada!\n"); // CEK PILIHAN VALID
                continue;
            }
            return x; // RETURN PILIHAN USER
        }
    }
}
