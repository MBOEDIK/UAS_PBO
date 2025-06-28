package com.SistemManajemenPerpustakaan.Utils;

import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;

public class AttributeCounter {
    public static int hitungAtribut(Class<?> clazz) {
        return clazz.getDeclaredFields().length;
    }
}
