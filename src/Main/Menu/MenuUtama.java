package Main.Menu;

import Main.Menu.SubMenuAdmin.SubMenuDetailPeminjaman;

import java.util.Scanner;

public class MenuUtama {

//ATTRIBUTES
    private static int inputInt, subInputInt, subSubInputInt;
    private static String inputStr;
    private static Boolean inputBool;

//OBJECTS
        static Scanner input = new Scanner(System.in);

//METHODS
    //============================================================================================================================================================================================================

    /*
    * Nampilin semua akses admin
    * */
    public static void menuAdmin(){
        loop : while (true){
            System.out.print("" +
                    "\n-- HALAMAN ADMIN --" +
                    "\n1. Manajemen Buku" +
                    "\n2. Manajemen Anggota" +
                    "\n3. Detail Peminjaman" +
                    "\n4. Log out" +
                    "\nPilih menu: ");
            inputInt = input.nextInt();
            input.nextLine();

            switch (inputInt){
                case 1:
                    MenuAdmin.menuManajemenBuku();
                    continue ;
                case 2:
                    MenuAdmin.menuManajemenPengguna();
                    continue;
                case 3:
                    MenuAdmin.menuDetailPeminjaman();
                    continue;
                case 4:
                    break loop;
                default:
                    System.out.print("Pilihan tidak ada!\n");
                    continue;
            }
        }
    }

    //============================================================================================================================================================================================================

    /*
    * Nampilin semua akses anggota
    * */
    public static void menuAnggota(){
        loop : while (true){
            System.out.print("" +
                    "\n-- HALAMAN Anggota --" +
                    "\n1. Peminjaman" +
                    "\n2. Pengembalian" +
                    "\n3. Log out" +
                    "\nPilih menu: ");
            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan){
                case 1:
                    MenuAnggota.peminjaman();
                    continue;
                case 2:
                    MenuAnggota.pengembalian();
                    continue loop;
                case 3: break loop;
                default:
                    System.out.print("Pilihan tidak ada!\n");
            }
        }
    }

    //============================================================================================================================================================================================================
}
