package Main.Menu;

import Main.Menu.SubMenuAdmin.SubMenuDetailPeminjaman;

import java.util.Scanner;

/*
 * Kelas MenuUtama merupakan pusat navigasi sistem yang mengatur alur menu utama untuk admin dan anggota.
 * Bertanggung jawab untuk menampilkan opsi menu dan mengarahkan pengguna ke fungsi yang sesuai berdasarkan peran (admin/anggota).
 * Berinteraksi dengan Scanner untuk input pengguna dan kelas MenuAdmin untuk fungsi administratif.
 */
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
     * Menyediakan antarmuka menu utama untuk pengguna dengan role admin.
     * Method ini menampilkan pilihan menu admin dan mengarahkan ke fungsi yang sesuai.
     * Menggunakan loop while untuk menjaga menu tetap aktif sampai admin memilih logout.
     * Tidak menerima parameter dan tidak mengembalikan nilai.
     * Catatan: Case 2 dan 3 masih perlu diimplementasikan untuk manajemen anggota dan peminjaman.
     */
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
     * Menyediakan antarmuka menu utama untuk pengguna dengan role anggota.
     * Menampilkan opsi peminjaman, pengembalian, dan logout bagi anggota.
     * TODO: Implementasikan logika untuk case 1 (peminjaman) dan case 2 (pengembalian).
     * Perlu ditambahkan integrasi dengan sistem peminjaman dan validasi input.
     */
    public static void menuAnggota(){
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
