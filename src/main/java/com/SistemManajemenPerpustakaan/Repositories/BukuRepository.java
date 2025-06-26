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
    public static Buku ambil(String kodeBuku) {
        return bukus.stream()
                .filter(b -> b.getKode().equals(kodeBuku))
                .findFirst()
                .orElse(null);
    }

    public static List<Buku> findAll() {
        return new ArrayList<>(bukus);  // Return copy untuk hindari modifikasi langsung
    }


    //UPDATE
    public static void update(Buku buku){
        for (int i = 0; i < bukus.size(); i++) {
            if (bukus.get(i).getKode().equals(buku.getKode())) {
                bukus.set(i, buku);
                return;
            }
        }
        throw new IllegalArgumentException("Buku tidak ditemukan!");
    }

    //DELETE
    public static void hapus(String kodeBuku) {
        bukus.removeIf(buku -> buku.getKode().equals(kodeBuku));
    }
}
