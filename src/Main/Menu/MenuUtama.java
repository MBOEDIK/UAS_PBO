package Main.Menu;

import java.util.Scanner;

//CLASS UNTUK NGATUR LOGIKA MENU
public class MenuUtama {

//ATTRIBUTES
    private static int inputInt, subInputInt, subSubInputInt;
    private static String inputStr;
    private static Boolean inputBool;

//OBJECTS
        static Scanner input = new Scanner(System.in);

//METHODS
    //============================================================================================================================================================================================================

    //logika untuk menu admin
    public static void menuAdmin(){
        loop : while (true){
            System.out.print("" +
                    "\n-- HALAMAN ADMIN --" +
                    "\n1. Manajemen Buku" +
                    "\n2. Manajemen Anggota" +
                    "\n3. Manajemen Peminjaman" +
                    "\n4. Log out" +
                    "\nPilih menu: ");
            inputInt = input.nextInt();
            input.nextLine();

            switch (inputInt){
                case 1:
                    MenuAdmin.menuManajemenBuku();
                    continue ;
                case 2: continue;
                case 3: continue;
                case 4: continue;
                default:
                    System.out.print("Pilihan tidak ada!\n");
                    continue;
            }
        }
    }

    //============================================================================================================================================================================================================

    //logika untuk menu anggota
    public void menuAnggota(){
        System.out.print("" +
                "\n-- HALAMAN Anggota --" +
                "\n1. Peminjaman" +
                "\n2. Pengembalian" +
                "\n3. Log out" +
                "\nPilih menu: ");
        int pilihan = input.nextInt();
        input.nextLine();

        switch (pilihan){
            case 1: break;
            case 2: break;
            case 3: break;
            default:
                System.out.print("Pilihan tidak ada!\n");
        }
    }

    //============================================================================================================================================================================================================
}
