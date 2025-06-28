package com.SistemManajemenPerpustakaan.MVC.Controllers;

import com.SistemManajemenPerpustakaan.MVC.Controllers.Books.BukuController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.Users.PenggunaController;
import com.SistemManajemenPerpustakaan.MVC.Views.Console.Books.BukuView;
import com.SistemManajemenPerpustakaan.MVC.Views.Console.MenuView;

public class MenuController{
    //ATTRIBUTE
    private static int pilihanMenuAdmin;
    private static int pilihanMenuAnggota;

    //OBJECT

    //METHOD
    //MENU ADMIN
    public static void menuAdmin(){
        while (true){
            pilihanMenuAdmin = MenuView.menuAdmin();
            switch (pilihanMenuAdmin){
                case 1 -> BukuController.jalankanBukuView();
                case 2 -> PenggunaController.jalankanPenggunaView();
                case 3 -> PeminjamanController.jalankanPeminjamanView();
            }
        }
    }

    //MENU ANGGOTA
    public static void menuAnggota(){
        while (true){
            pilihanMenuAnggota = MenuView.menuAdmin();
            switch (pilihanMenuAnggota){
                case 1 -> BukuController.jalankanBukuView();
                case 2 -> PenggunaController.jalankanPenggunaView();
                case 3 -> PeminjamanController.jalankanPeminjamanView();
            }
        }
    }
}