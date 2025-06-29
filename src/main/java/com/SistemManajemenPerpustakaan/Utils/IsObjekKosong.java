package com.SistemManajemenPerpustakaan.Utils;

import java.util.List;

public class IsObjekKosong {
    public static <T> boolean check(List<T> list, String namaObjek) {
        if (list == null || list.isEmpty()) {
            System.out.println(namaObjek + " kosong!");
            return true;
        }
        return false;
    }

    public static <T> boolean check(List<T> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }
}
