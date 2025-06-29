package com.SistemManajemenPerpustakaan.MVC.Controllers;

import com.SistemManajemenPerpustakaan.DTOs.PenggunaDTO;
import com.SistemManajemenPerpustakaan.MVC.Controllers.Factory.DTOtoModel;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;
import com.SistemManajemenPerpustakaan.MVC.Views.Console.PenggunaView;
import com.SistemManajemenPerpustakaan.Repositories.PenggunaRepository;

import java.util.List;

public class PenggunaController {

    //RUN
    public static void jalankanPenggunaView(){
        loop : while (true){
            int pilihan = PenggunaView.menuPengguna();
            switch (pilihan){
                case 1: PenggunaView.tambahPengguna(); break;
                case 2: if (!PenggunaView.isPenggunaKosong()) PenggunaView.detailPengguna(); break;
                case 3: if (!PenggunaView.isPenggunaKosong()) PenggunaView.updatePengguna(); break;
                case 4: if (!PenggunaView.isPenggunaKosong()) PenggunaView.hapusPengguna(); break;
                case 5: break loop;
            }
        }
    }

    //CREATE
    public static void tambahPengguna(PenggunaDTO dto){
        Pengguna pengguna = DTOtoModel.toPengguna(dto);
        PenggunaRepository.tambah(pengguna);
    }

    //READ
    public static Pengguna ambilPengguna(String kodePengguna){
        return  PenggunaRepository.ambilPenggunaById(kodePengguna);
    }

    public static List<Pengguna> ambilSemuaPengguna(){
        return PenggunaRepository.ambilSemua();
    }

    //UPDATE
    public static boolean updateAtribut(String kodePengguna, String atribut, Object nilaiBaru) {
        return PenggunaRepository.updateAtribut(kodePengguna, atribut, nilaiBaru);
    }

    public static void updatePengguna(String kodePengguna, PenggunaDTO dto){
        Pengguna pengguna = DTOtoModel.toPengguna(dto);
        PenggunaRepository.updatePengguna(kodePengguna, pengguna);
    }

    //DELETE
    public static void hapusPengguna(String kodePengguna) {
        PenggunaRepository.hapus(kodePengguna);
    }
}
