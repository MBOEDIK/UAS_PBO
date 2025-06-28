package com.SistemManajemenPerpustakaan.Repositories;

import com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman;

import java.util.ArrayList;
import java.util.List;

public class PeminjamanRepository {
    private static ArrayList<Peminjaman> peminjamans = new ArrayList<>();

    //CREATE
    public static void tambah(Peminjaman peminjaman){
        peminjamans.add(peminjaman);
    }

    //READ
    public static Peminjaman ambilPeminjamanById(String idPeminjaman) {
        for (Peminjaman peminjaman : peminjamans) {
            if (peminjaman.getId().equals(idPeminjaman)) {
                return peminjaman;
            }
        }
        return null;
    }


    //AMBIL SEMUA BUKU (IMMUTABLE)
    public static List<Peminjaman> ambilSemua() {
        return List.copyOf(peminjamans); // Tidak bisa diubah sama sekali
    }

    //UPDATE
    public static void updatePeminjaman(String idPeminjaman, Peminjaman peminjaman){
        for (int i = 0; i < peminjamans.size(); i++) {
            if (peminjamans.get(i).getId().equals(idPeminjaman)){
                peminjamans.set(i, peminjaman);
                return;
            }
        }
        throw new IllegalArgumentException("Peminjaman tidak ditemukan!");
    }

    public static boolean updateAtribut(String id, String atribut, Object nilaiBaru) {
        Peminjaman peminjaman = ambilPeminjamanById(id);
        if (peminjaman != null) {
            peminjaman.updateAttribute(atribut, nilaiBaru);
            return true;
        }
        return false;
    }

    //DELETE
    public static void hapus(String kodePeminjaman) {
        peminjamans.removeIf(peminjaman -> peminjaman.getId().equals(kodePeminjaman));
    }

    //MEMBERIKAN INFORMASI JUMLAH BUKU
    public static int jumlahPeminjaman(){
        return peminjamans.size();
    }
}
