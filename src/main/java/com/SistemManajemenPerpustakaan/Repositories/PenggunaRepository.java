package com.SistemManajemenPerpustakaan.Repositories;

import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;

import java.util.ArrayList;
import java.util.List;

public class PenggunaRepository {
    private static ArrayList<Pengguna> penggunas = new ArrayList<>();

    //CREATE
    public static void tambah(Pengguna pengguna){
        penggunas.add(pengguna);
    }

    //READ
    public static Pengguna ambilPenggunaById(String idPengguna) {
        for (Pengguna pengguna : penggunas) {
            if (pengguna.getId().equals(idPengguna)) {
                return pengguna;
            }
        }
        return null;
    }


    //AMBIL SEMUA BUKU (IMMUTABLE)
    public static List<Pengguna> ambilSemua() {
        return List.copyOf(penggunas); // Tidak bisa diubah sama sekali
    }

    //UPDATE
    public static void updatePengguna(String idPengguna, Pengguna pengguna){
        for (int i = 0; i < penggunas.size(); i++) {
            if (penggunas.get(i).getId().equals(idPengguna)){
                penggunas.set(i, pengguna);
                return;
            }
        }
        throw new IllegalArgumentException("Pengguna tidak ditemukan!");
    }

    public static boolean updateAtribut(String id, String atribut, Object nilaiBaru) {
        Pengguna pengguna = ambilPenggunaById(id);
        if (pengguna != null) {
            pengguna.updateAttribute(atribut, nilaiBaru);
            return true;
        }
        return false;
    }

    //DELETE
    public static void hapus(String kodePengguna) {
        penggunas.removeIf(pengguna -> pengguna.getId().equals(kodePengguna));
    }

    //MEMBERIKAN INFORMASI JUMLAH BUKU
    public static int jumlahPengguna(){
        return penggunas.size();
    }
}
