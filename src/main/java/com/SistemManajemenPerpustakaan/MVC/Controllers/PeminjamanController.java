package com.SistemManajemenPerpustakaan.MVC.Controllers;

import com.SistemManajemenPerpustakaan.DTOs.PeminjamanDTO;
import com.SistemManajemenPerpustakaan.MVC.Controllers.Factory.DTOtoModel;
import com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman;
import com.SistemManajemenPerpustakaan.MVC.Views.Console.PeminjamanView;
import com.SistemManajemenPerpustakaan.Repositories.PeminjamanRepository;

import java.util.List;

public class PeminjamanController {

    //RUN
    public static void jalankanPeminjamanView(){
        PeminjamanView.detailPeminjaman();
    }

    //CREATE
    public static void tambahPeminjaman(PeminjamanDTO dto){
        Peminjaman pengguna = DTOtoModel.toPeminjaman(dto);
        PeminjamanRepository.tambah(pengguna);
    }

    //READ
    public static Peminjaman ambilPeminjaman(String kodePeminjaman){
        return  PeminjamanRepository.ambilPeminjamanById(kodePeminjaman);
    }

    public static List<Peminjaman> AmbilSemuaPeminjaman(){
        return PeminjamanRepository.ambilSemua();
    }

    //UPDATE
    public static boolean updateAtribut(String kodePeminjaman, String atribut, Object nilaiBaru) {
        return PeminjamanRepository.updateAtribut(kodePeminjaman, atribut, nilaiBaru);
    }

    public static void updatePeminjaman(String kodePeminjaman, PeminjamanDTO dto){
        Peminjaman pengguna = DTOtoModel.toPeminjaman(dto);
        PeminjamanRepository.updatePeminjaman(kodePeminjaman, pengguna);
    }

    //DELETE
    public static void hapusPeminjaman(String kodePeminjaman) {
        PeminjamanRepository.hapus(kodePeminjaman);
    }
}
