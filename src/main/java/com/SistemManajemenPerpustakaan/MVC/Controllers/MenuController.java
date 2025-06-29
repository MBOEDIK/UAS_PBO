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
                case 1 -> BukuController.jalankanBukuView();
                case 2 -> PenggunaController.jalankanPenggunaView();
                case 3 -> PeminjamanController.jalankanPeminjamanView();
            }
        }
    }

    //MENU ANGGOTA
    public static void menuAnggota(){
        while (true){
            int x = MenuView.menuAnggota();
            if (x == 3) return;
            switch (x){
                case 1 -> PeminjamanController.jalankanPeminjamanView(1);
                case 2 -> PeminjamanController.jalankanPeminjamanView(2);
            }
        }
    }
}