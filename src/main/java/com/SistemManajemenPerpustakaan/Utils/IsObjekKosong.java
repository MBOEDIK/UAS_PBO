package com.SistemManajemenPerpustakaan.Utils;

import java.util.List;

public class IsObjekKosong {
    // MEMERIKSA APAKAH DAFTAR KOSONG.
    // MENCETAK PESAN JIKA DAFTAR KOSONG DAN MENGEMBALIKAN TRUE.
    public static <T> boolean check(List<T> list, String namaObjek) {
        if (list == null || list.isEmpty()) {
            System.out.println(namaObjek + " KOSONG!");
            return true;
        }
        return false;
    }

    // MEMERIKSA APAKAH DAFTAR KOSONG.
    // MENGEMBALIKAN TRUE JIKA DAFTAR KOSONG.
    public static <T> boolean check(List<T> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }
}