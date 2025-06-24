package Main.Menu;

import Main.Menu.SubMenuAdmin.SubMenuManajemenBuku;
import Main.Menu.SubMenuAdmin.SubMenuManajemenPengguna;

import java.util.Scanner;

/*
 * Kelas MenuAdmin berfungsi sebagai antarmuka utama untuk semua fitur administratif sistem.
 * Bertanggung jawab untuk menampilkan dan mengarahkan alur menu manajemen buku, anggota,
 * dan peminjaman. Berinteraksi dengan berbagai SubMenu untuk operasi spesifik dan menggunakan
 * Scanner untuk menerima input pengguna. Kelas ini menjadi penghubung antara pengguna admin
 * dengan fungsionalitas sistem yang lebih kompleks.
 */
public class MenuAdmin {

//ATTRIBUTES
    private static int inputInt, subInputInt, subSubInputInt;
    private static String inputStr;
    private static Boolean inputBool;

//OBJECTS
    static Scanner input = new Scanner(System.in);

//METHODS
    //============================================================================================================================================================================================================

    /*
     * Menyediakan antarmuka menu untuk manajemen buku lengkap dengan pilihan operasi CRUD.
     * Method ini menampilkan opsi menu dan mengarahkan ke submenu yang sesuai berdasarkan input.
     * Menggunakan loop while untuk menjaga menu tetap aktif sampai pengguna memilih keluar.
     * Tidak menerima parameter apapun dan tidak mengembalikan nilai.
     */
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

    public static void menuManajemenPengguna(){
        loop : while (true){
            System.out.print("" +
                    "\n-- Manajemen Pengguna --" +
                    "\n1. Tambah Pengguna" +
                    "\n2. Edit Pengguna" +
                    "\n3. Hapus Pengguna" +
                    "\n4. Tampilkan Pengguna" +
                    "\n5. Kembali ke Halaman Admin" +
                    "\nMasukkan Pilihan: ");
            subInputInt = input.nextInt();
            input.nextLine();

            switch (subInputInt){
                case 1:
                    SubMenuManajemenPengguna.menuTambahPengguna();
                    continue;
                case 2:
                    SubMenuManajemenPengguna.menuEditPengguna();
                    continue;
                case 3:
                    SubMenuManajemenPengguna.menuHapusPengguna();
                    continue;
                case 4:
                    SubMenuManajemenPengguna.menuDetailPengguna();
                    continue;
                case 5: break loop;
            }
        }
    }

    //============================================================================================================================================================================================================

    public static void menuManajemenPeminjaman(){

    }

    //============================================================================================================================================================================================================
}
