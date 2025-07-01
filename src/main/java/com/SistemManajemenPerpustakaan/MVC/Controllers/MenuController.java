package com.SistemManajemenPerpustakaan.MVC.Controllers;

import com.SistemManajemenPerpustakaan.MVC.Views.Console.MenuView;

public class MenuController{
    //METHOD
    //MENU ADMIN
    public static void menuAdmin(){
        while (true){
            int x = MenuView.menuAdmin();
            if (x == 4) return;
            switch (x){
                case 1 -> BukuController.kelolaMenuBuku();
                case 2 -> PenggunaController.kelolaMenuPengguna();
                case 3 -> PeminjamanController.handleDetailPeminjaman();
            }
        }
    }

    //MENU ANGGOTA
    public static void menuAnggota(){
        while (true){
            int x = MenuView.menuAnggota();
            if (x == 3) return;
            switch (x){
                case 1 -> PeminjamanController.handleTambahPeminjaman();
                case 2 -> PeminjamanController.handleKembalikanBuku();
            }
        }
    }
}