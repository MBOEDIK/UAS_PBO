// INI JUAN YANG KASI KOMEN
// KODE CONTROLLER LOGIN DENGAN KOMEN LEBIH JELAS BRO

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

    // MENYIMPAN OBJEK USER YANG SEDANG LOGIN
    public static Pengguna anggotaSaatIni;

    // MENYIMPAN LIST SEMUA PENGGUNA DARI DATABASE
    private static List<Pengguna> penggunas = PenggunaRepository.ambilSemua();

    // METHOD UTAMA UNTUK MENJALANKAN LOGIKA LOGIN
    public static void login() {
        loop : while (true) {
            // USER MEMILIH JENIS PENGGUNA (ADMIN / ANGGOTA)
            JenisPengguna jenisPengguna = LoginView.menuInputJenisPengguna();

            // USER INPUT USERNAME DAN PASSWORD
            String[] tempStrArr = LoginView.inputLogin(jenisPengguna.toString());

            int i = 0;
            for (Pengguna pengguna : penggunas) {

                // CEK JIKA DATA COCOK DENGAN ADMIN
                if (
                        tempStrArr[0].equals(pengguna.getUsername()) &&
                                tempStrArr[1].equals(pengguna.getPassword()) &&
                                jenisPengguna == JenisPengguna.ADMIN &&
                                pengguna instanceof Admin
                ) {
                    // MASUK KE MENU ADMIN
                    MenuController.menuAdmin();
                    continue loop;
                }

                // CEK JIKA DATA COCOK DENGAN ANGGOTA
                if (
                        tempStrArr[0].equals(pengguna.getUsername()) &&
                                tempStrArr[1].equals(pengguna.getPassword()) &&
                                jenisPengguna == JenisPengguna.ANGGOTA &&
                                pengguna instanceof Anggota
                ) {
                    // SIMPAN ANGGOTA SAAT INI DAN MASUK MENU ANGGOTA
                    anggotaSaatIni = pengguna;
                    MenuController.menuAnggota();
                    continue loop;
                }

                // JIKA SEMUA PENGGUNA DILOOP DAN TIDAK ADA YANG COCOK
                if (i == penggunas.size() - 1) {
                    LoginView.usernameOrPasswordSalah();
                }

                i++;
            }
        }
    }

    // MENGAMBIL OBJEK USER YANG LAGI LOGIN SAAT INI
    public static Pengguna getPenggunaSaatIni() {
        return anggotaSaatIni;
    }

    // MENGATUR OBJEK USER YANG SEDANG LOGIN
    public static void setPenggunaSaatIni(Pengguna pengguna) {
        anggotaSaatIni = pengguna;
    }

    // MENGUPDATE OBJEK LOGIN DENGAN DATA TERBARU
    public static void refreshPenggunaSaatIni(Pengguna penggunaSaatIni) {
        for (Pengguna pengguna : PenggunaController.ambilSemuaPengguna()) {
            if (pengguna.getId().equals(penggunaSaatIni.getId())) {
                anggotaSaatIni = pengguna;
            }
        }
    }
}
