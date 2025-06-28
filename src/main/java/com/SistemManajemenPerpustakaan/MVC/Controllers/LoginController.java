package com.SistemManajemenPerpustakaan.MVC.Controllers;

import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;
import com.SistemManajemenPerpustakaan.MVC.Views.Console.LoginView;
import com.SistemManajemenPerpustakaan.Repositories.PenggunaRepository;
import com.SistemManajemenPerpustakaan.Utils.IdValidator;

import java.util.ArrayList;
import java.util.List;

public class LoginController {

    //ATTRIBUTE
    private static Pengguna penggunaSaatIni;

    //OBJECT
    private static List<Pengguna> penggunas = PenggunaRepository.ambilSemua();

    //MENGONTROL LOGIKA LOGIN
    public static void login(){
        //MENAMPILKAN MENU LOGIN
        String jenisPengguna = LoginView.menuInputJenisPengguna();

        while (true){
            //VALIDASI USERNAME DAN PASSWORD
            String[] tempStrArr = LoginView.inputLogin(jenisPengguna);
            int i = 0;
            for (Pengguna pengguna : penggunas){
                if (
                        IdValidator.validasiStr(tempStrArr[0], pengguna.getUsername()) &&
                                IdValidator.validasiStr(tempStrArr[1], pengguna.getPassword()) &&
                                jenisPengguna.equals("ADMIN")){
                    MenuController.menuAdmin();
                }
                if (
                        IdValidator.validasiStr(tempStrArr[0], pengguna.getUsername()) &&
                                IdValidator.validasiStr(tempStrArr[1], pengguna.getPassword()) &&
                                jenisPengguna.equals("ANGGOTA")){

                }

                //MEMANGGIL TAMPILAN USERNAME/PASSOWRD SALAH
                if (i == penggunas.size() - 1){
                    LoginView.usernameOrPasswordSalah();
                }
                i++;
            }
        }
    }

    public static Pengguna getPenggunaSaatIni() { return penggunaSaatIni; }
}
