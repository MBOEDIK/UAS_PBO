package com.SistemManajemenPerpustakaan.Repositories;

import com.SistemManajemenPerpustakaan.MVC.Models.Book.*;
import java.util.ArrayList;
import java.util.List;

public class BukuRepository {

    //ATTRIBUTE
    private static ArrayList<Buku> bukus = new ArrayList<>();

    //CREATE
    public static void tambah(Buku buku){
        bukus.add(buku);
    }

    //READ
    public static Buku ambilBukuById(String kodeBuku) {
        for (Buku buku : bukus) {
            if (buku.getKode().equals(kodeBuku)) {
                return buku;
            }
        }
        return null;
    }


    //AMBIL SEMUA BUKU (IMMUTABLE)
    public static List<Buku> ambilSemua() {
        return List.copyOf(bukus); // Tidak bisa diubah sama sekali
    }

    //UPDATE
    public static void updateBuku(String kodeBuku, Buku buku){
        for (int i = 0; i < bukus.size(); i++) {
            if (bukus.get(i).getKode().equals(kodeBuku)){
                bukus.set(i, buku);
                return;
            }
        }
        throw new IllegalArgumentException("Buku tidak ditemukan!");
    }

    public static boolean updateAtribut(String id, String atribut, Object nilaiBaru) {
        Buku buku = ambilBukuById(id);
        if (buku != null) {
            buku.updateAttribute(atribut, nilaiBaru);
            return true;
        }
        return false;
    }

    //DELETE
    public static void hapus(String kodeBuku) {
        bukus.removeIf(buku -> buku.getKode().equals(kodeBuku));
    }

    //MEMBERIKAN INFORMASI JUMLAH BUKU
    public static int jumlahBuku(){
        return bukus.size();
    }
}
