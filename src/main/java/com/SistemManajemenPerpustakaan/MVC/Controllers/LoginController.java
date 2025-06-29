package com.SistemManajemenPerpustakaan.MVC.Controllers;

import com.SistemManajemenPerpustakaan.Enums.JenisPengguna;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Admin;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Anggota;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;
import com.SistemManajemenPerpustakaan.MVC.Views.Console.LoginView;
import com.SistemManajemenPerpustakaan.Repositories.PenggunaRepository;
import com.SistemManajemenPerpustakaan.Utils.IsTwoSameChecker;

import java.util.List;

public class LoginController {

    //ATTRIBUTE
    private static Pengguna anggotaSaatIni;

    //OBJECT
    private static List<Pengguna> penggunas = PenggunaRepository.ambilSemua();

    //MENGONTROL LOGIKA LOGIN
    public static void login(){
        loop : while (true){
            //MENAMPILKAN MENU LOGIN
            JenisPengguna jenisPengguna = LoginView.menuInputJenisPengguna();

            //VALIDASI USERNAME DAN PASSWORD
            String[] tempStrArr = LoginView.inputLogin(jenisPengguna.toString());
            int i = 0;
            for (Pengguna pengguna : penggunas){
                if (
                        tempStrArr[0].equals(pengguna.getUsername()) &&
                                tempStrArr[1].equals(pengguna.getPassword()) &&
                                jenisPengguna == JenisPengguna.ADMIN && pengguna instanceof Admin){
                    anggotaSaatIni = pengguna;
                    MenuController.menuAdmin();
                    continue loop;
                }
                if (
                        tempStrArr[0].equals(pengguna.getUsername()) &&
                                tempStrArr[1].equals(pengguna.getPassword()) &&
                                jenisPengguna == JenisPengguna.ANGGOTA && pengguna instanceof Anggota){
                    anggotaSaatIni = pengguna;
                    MenuController.menuAnggota();
                    continue loop;
                }

                //MEMANGGIL TAMPILAN USERNAME/PASSOWRD SALAH
                if (i == penggunas.size() - 1){
                    LoginView.usernameOrPasswordSalah();
                }
                i++;
            }
        }
    }

    public static Pengguna getPenggunaSaatIni() { return anggotaSaatIni; }
}
