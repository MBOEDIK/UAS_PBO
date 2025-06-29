package com.SistemManajemenPerpustakaan.MVC.Controllers;

import com.SistemManajemenPerpustakaan.DTOs.BukuDTO;
import com.SistemManajemenPerpustakaan.MVC.Controllers.Factory.DTOtoModel;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Views.Console.BukuView;
import com.SistemManajemenPerpustakaan.Repositories.BukuRepository;
import com.SistemManajemenPerpustakaan.Utils.IsObjekKosong;

import java.util.List;

public class BukuController {

    //RUN
    public static void jalankanBukuView(){
        loop : while (true){
            int pilihan = BukuView.menuBuku();
            switch (pilihan){
                case 1: BukuView.tambahBuku(); break;
                case 2: if (!IsObjekKosong.check(BukuController.ambilSemuaBuku(), "Buku")) BukuView.detailBuku(); break;
                case 3: if (!IsObjekKosong.check(BukuController.ambilSemuaBuku(), "Buku")) BukuView.updateBuku(); break;
                case 4: if (!IsObjekKosong.check(BukuController.ambilSemuaBuku(), "Buku")) BukuView.hapusBuku(); break;
                case 5: break loop;
            }
        }
    }

    //CREATE
    public static void tambahBuku(BukuDTO dto){
        Buku buku = DTOtoModel.toBuku(dto);
        BukuRepository.tambah(buku);
    }

    //READ
    public static Buku ambilBuku(String kodeBuku){
        return  BukuRepository.ambilBukuById(kodeBuku);
    }

    public static List<Buku> ambilSemuaBuku(){
        return BukuRepository.ambilSemua();
    }

    //UPDATE
    public static boolean updateAtribut(String kodeBuku, String atribut, Object nilaiBaru) {
        return BukuRepository.updateAtribut(kodeBuku, atribut, nilaiBaru);
    }

    public static void updateBuku(String kodeBuku, BukuDTO dto){
        Buku buku = DTOtoModel.toBuku(dto);
        BukuRepository.updateBuku(kodeBuku, buku);
    }

    //DELETE
    public static void hapusBuku(String kodeBuku) {
        BukuRepository.hapusBuku(kodeBuku);
    }
}
