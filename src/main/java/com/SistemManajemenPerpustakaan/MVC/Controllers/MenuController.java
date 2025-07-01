// INI JUAN YANG KASI KOMEN
// NGATUR MENU ADMIN & ANGGOTA DI APLIKASI

package com.SistemManajemenPerpustakaan.MVC.Controllers;

import com.SistemManajemenPerpustakaan.MVC.Views.Console.MenuView;

public class MenuController {

    // METHOD UNTUK MENU ADMIN
    // HANDLE INPUT ADMIN UNTUK KELOLA FITUR
    public static void menuAdmin() {
        while (true) {
            int x = MenuView.menuAdmin();

            // PILIHAN 4 UNTUK KELUAR DARI MENU ADMIN
            if (x == 4) return;

            // JALANKAN FITUR SESUAI PILIHAN ADMIN
            switch (x) {
                case 1 -> BukuController.kelolaMenuBuku();          // KELOLA DATA BUKU
                case 2 -> PenggunaController.kelolaMenuPengguna();  // KELOLA DATA PENGGUNA
                case 3 -> PeminjamanController.handleDetailPeminjaman(); // LIHAT DETAIL PEMINJAMAN
            }
        }
    }

    // METHOD UNTUK MENU ANGGOTA
    // USER ANGGOTA BISA PINJAM & KEMBALIKAN BUKU
    public static void menuAnggota() {
        while (true) {
            int x = MenuView.menuAnggota();

            // PILIHAN 3 UNTUK KELUAR DARI MENU ANGGOTA
            if (x == 3) return;

            // EKSEKUSI FITUR UNTUK ANGGOTA
            switch (x) {
                case 1 -> PeminjamanController.handleTambahPeminjaman(); // PINJAM BUKU
                case 2 -> PeminjamanController.handleKembalikanBuku();   // KEMBALIKAN BUKU
            }
        }
    }
}
