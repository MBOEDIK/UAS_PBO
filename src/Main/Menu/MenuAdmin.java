package Main.Menu;

import Main.Menu.SubMenuAdmin.SubMenuManajemenBuku;

import java.util.Scanner;

public class MenuAdmin {

//ATTRIBUTES
    private static int inputInt, subInputInt, subSubInputInt;
    private static String inputStr;
    private static Boolean inputBool;

//OBJECTS
    static Scanner input = new Scanner(System.in);

//METHODS
    //============================================================================================================================================================================================================

    public static void menuManajemenBuku(){
        loop : while (true){
            System.out.print("" +
                    "\n-- Manajemen Buku --" +
                    "\n1. Tambah Buku" +
                    "\n2. Edit Buku" +
                    "\n3. Hapus Buku" +
                    "\n4. Tampilkan Buku" +
                    "\n5. Kembali ke Halaman Admin" +
                    "\nMasukkan Pilihan: ");
            subInputInt = input.nextInt();
            input.nextLine();

            switch (subInputInt){
                case 1:
                    SubMenuManajemenBuku.menuTambahBuku();
                    continue;
                case 2:
                    SubMenuManajemenBuku.menuEditBuku();
                    continue;
                case 3:
                    SubMenuManajemenBuku.menuHapusBuku();
                    continue;
                case 4:
                    SubMenuManajemenBuku.menuDetailBuku();
                    continue;
                case 5: break loop;
            }
        }
    }

    //============================================================================================================================================================================================================

    public static void menuManajemenAnggota(){

    }

    //============================================================================================================================================================================================================

    public static void menuManajemenPeminjaman(){

    }

    //============================================================================================================================================================================================================
}
