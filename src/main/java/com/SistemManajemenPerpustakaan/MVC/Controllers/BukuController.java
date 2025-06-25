package com.SistemManajemenPerpustakaan.MVC.Controllers;

import com.SistemManajemenPerpustakaan.MVC.Models.Book.*;
import com.SistemManajemenPerpustakaan.Tools.PersingkatPanggilData;

public class BukuController implements PersingkatPanggilData {
    
//METHODS
    //============================================================================================================================================================================================================
    
    public static void tambah(String kodeBuku, String judulBuku,String kategoriBuku,String pengarangBuku,String tahunTerbitBuku,
                              Boolean ketersediaanBuku, String institusi, String terindeksSinta){
        dataBuku.add(new JurnalIlmiah(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku, institusi, terindeksSinta));
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public static void tambah(String kodeBuku, String judulBuku, String kategoriBuku, String pengarangBuku,
                              String tahunTerbitBuku, Boolean ketersediaanBuku, String nilaiString){
        if(kategoriBuku.toLowerCase().equals("majalah")){
            dataBuku.add(new Majalah(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku, nilaiString));
        } else if (kategoriBuku.toLowerCase().equals("novel")) {
            dataBuku.add(new Novel(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku, nilaiString));
        } else if (kategoriBuku.toLowerCase().equals("textbook")) {
            dataBuku.add(new TextBook(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku, nilaiString));
        }
    }

    //============================================================================================================================================================================================================
    
    public static void edit(int indeksBuku, String attribute, String nilai){
        switch (attribute){
            case "kodeBuku": dataBuku.get(indeksBuku).setKodeBuku(nilai); break;
            case "judul": dataBuku.get(indeksBuku).setJudulBuku(nilai); break;
            case "kategori": dataBuku.get(indeksBuku).setKategoriBuku(nilai); break;
            case "pengarang": dataBuku.get(indeksBuku).setPengarangBuku(nilai); break;
            case "tahunTerbit": dataBuku.get(indeksBuku).setTahunTerbitBuku(nilai); break;
            case "institusi":
                JurnalIlmiah jurnalIlmiah = (JurnalIlmiah) dataBuku.get(indeksBuku);
                jurnalIlmiah.setInstitusi(nilai);
                break;
            case "topik":
                Majalah majalah = (Majalah) dataBuku.get(indeksBuku);
                majalah.setTopikBuku(nilai);
                break;
            case "genre":
                Novel novel = (Novel) dataBuku.get(indeksBuku);
                novel.setGenreBuku(nilai);
                break;
            case "bidangIlmu":
                TextBook textBook = (TextBook) dataBuku.get(indeksBuku);
                textBook.setBidangIlmu(nilai);
                break;
            case "terindeksSinta":
                JurnalIlmiah jurnalIlmiah1 = (JurnalIlmiah) dataBuku.get(indeksBuku);
                jurnalIlmiah1.setTerindeksSinta(nilai);
                break;

        }
    }
    
    public static void edit(int indeksBuku, String attribute, Boolean nilai){
        dataBuku.get(indeksBuku).setKetersediaanBuku(nilai);
    }

    //============================================================================================================================================================================================================
    
    public static void hapus(int indeks){
        dataBuku.remove(indeks);
    }

    //============================================================================================================================================================================================================

    public static void tampilkan(){

    }

    //============================================================================================================================================================================================================
}
