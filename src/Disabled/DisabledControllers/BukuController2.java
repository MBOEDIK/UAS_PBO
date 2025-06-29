package com.SistemManajemenPerpustakaan.MVC.Controllers.DisabledControllers;

import com.SistemManajemenPerpustakaan.MVC.Models.Book.*;

public class BukuController2 implements DataAccessHelper {
    
METHODS
    ============================================================================================================================================================================================================
    
    public static void tambah(String kodeBuku, String judulBuku,String kategoriBuku,String pengarangBuku,String tahunTerbitBuku,
                              Boolean ketersediaanBuku, String institusi, String terindeksSinta){
        dataBuku.add(new Jurnal(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku, institusi, terindeksSinta));
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
            case "kodeBuku": dataBuku.get(indeksBuku).setKode(nilai); break;
            case "judul": dataBuku.get(indeksBuku).setJudul(nilai); break;
            case "kategori": dataBuku.get(indeksBuku).setJenis(nilai); break;
            case "pengarang": dataBuku.get(indeksBuku).setPengarang(nilai); break;
            case "tahunTerbit": dataBuku.get(indeksBuku).setTahunTerbit(nilai); break;
            case "institusi":
                Jurnal jurnalIlmiah = (Jurnal) dataBuku.get(indeksBuku);
                jurnalIlmiah.setInstitusi(nilai);
                break;
            case "topik":
                Majalah majalah = (Majalah) dataBuku.get(indeksBuku);
                majalah.setTopik(nilai);
                break;
            case "genre":
                Novel novel = (Novel) dataBuku.get(indeksBuku);
                novel.setGenre(nilai);
                break;
            case "bidangIlmu":
                TextBook textBook = (TextBook) dataBuku.get(indeksBuku);
                textBook.setBidangIlmu(nilai);
                break;
            case "terindeksSinta":
                Jurnal jurnalIlmiah1 = (Jurnal) dataBuku.get(indeksBuku);
                jurnalIlmiah1.setTerindeksSinta(nilai);
                break;

        }
    }
    
    public static void edit(int indeksBuku, String attribute, Boolean nilai){
        dataBuku.get(indeksBuku).setTersedia(nilai);
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
