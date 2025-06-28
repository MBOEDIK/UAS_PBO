package com.SistemManajemenPerpustakaan.MVC.Controllers.Books;

import com.SistemManajemenPerpustakaan.DTOs.BukuDTO;
import com.SistemManajemenPerpustakaan.MVC.Controllers.Factory.DTOtoModel;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Views.Console.Books.BukuView;
import com.SistemManajemenPerpustakaan.Repositories.BukuRepository;

import java.util.List;

public class BukuController {

    //RUN
    public static void jalankanBukuView(){
        loop : while (true){
            int pilihan = BukuView.menuBuku();
            switch (pilihan){
                case 1: BukuView.tambahBuku(); break;
                case 2: if (!BukuView.isBukuKosong()) BukuView.detailBuku(); break;
                case 3: if (!BukuView.isBukuKosong()) BukuView.updateBuku(); break;
                case 4: if (!BukuView.isBukuKosong()) BukuView.hapusBuku(); break;
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

    public static List<Buku> AmbilSemuaBuku(){
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
        // ### PERUBAHAN DI SINI ###
        // Memanggil method yang sudah benar di Repository
        BukuRepository.hapusBuku(kodeBuku);
    }
}