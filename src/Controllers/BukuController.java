package Controllers;

import Models.Book.*;
import Models.PenyimpananData;

//CLASS UNTUK NGATUR LALU LINTAS DAN VALIDASI DATA BUKU
public class BukuController {

    //ATTRIBUTE


    //OBJECTS


    //METHODS
    //============================================================================================================================================================================================================

    //method overload utk nambah objek JURNAL ILMIAH
    public static void tambah(String kodeBuku,
                              String judulBuku,
                              String kategoriBuku,
                              String pengarangBuku,
                              String tahunTerbitBuku,
                              Boolean ketersediaanBuku,
                              String institusi,
                              String terindeksSinta){
        PenyimpananData.getBuku().add(new JurnalIlmiah(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku, institusi, terindeksSinta));
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //method overload utk nambah objek MAJALAH, NOVEL, DAN BUKU.
    public static void tambah(String kodeBuku,
                              String judulBuku,
                              String kategoriBuku,
                              String pengarangBuku,
                              String tahunTerbitBuku,
                              Boolean ketersediaanBuku,
                              String nilaiString){
        if(kategoriBuku.toLowerCase().equals("majalah")){
            PenyimpananData.getBuku().add(new Majalah(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku, nilaiString));
        } else if (kategoriBuku.toLowerCase().equals("novel")) {
            PenyimpananData.getBuku().add(new Novel(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku, nilaiString));
        } else if (kategoriBuku.toLowerCase().equals("textbook")) {
            PenyimpananData.getBuku().add(new TextBook(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku, nilaiString));
        }
    }

    //============================================================================================================================================================================================================

    public static void edit(int indeksBuku, int indeksPengeditan, String nilaiString){
        switch (indeksPengeditan){
            case 1: PenyimpananData.getBuku().get(indeksBuku).setKodeBuku(nilaiString); break;
            case 2: PenyimpananData.getBuku().get(indeksBuku).setJudulBuku(nilaiString); break;
            case 3: PenyimpananData.getBuku().get(indeksBuku).setKategoriBuku(nilaiString); break;
            case 4: PenyimpananData.getBuku().get(indeksBuku).setPengarangBuku(nilaiString); break;
            case 5: PenyimpananData.getBuku().get(indeksBuku).setTahunTerbitBuku(nilaiString); break;
            case 6:
                switch (PenyimpananData.getBuku().get(indeksBuku).getKategoriBuku().toLowerCase()){
                    case "jurnal ilmiah":
                        JurnalIlmiah jurnalIlmiah = (JurnalIlmiah) PenyimpananData.getBuku().get(indeksBuku);
                        jurnalIlmiah.setInstitusi(nilaiString);
                        break;
                    case "majalah":
                        Majalah majalah = (Majalah) PenyimpananData.getBuku().get(indeksBuku);
                        majalah.setTopikBuku(nilaiString);
                        break;
                    case "novel":
                        Novel novel = (Novel) PenyimpananData.getBuku().get(indeksBuku);
                        novel.setGenreBuku(nilaiString);
                        break;
                    case "textbook":
                        TextBook textbook = (TextBook) PenyimpananData.getBuku().get(indeksBuku);
                        textbook.setBidangIlmu(nilaiString);
                        break;
                }
                break;
            case 7:
                JurnalIlmiah jurnalIlmiah = (JurnalIlmiah) PenyimpananData.getBuku().get(indeksBuku);
                jurnalIlmiah.setTerindeksSinta(nilaiString);
                break;
        }
    }
    public static void edit(int indeksBuku, int indeksPengeditan, Boolean nilaiBoolean){
        switch (indeksPengeditan){
            case 7, 8:
                PenyimpananData.getBuku().get(indeksBuku).setKetersediaanBuku(nilaiBoolean);
                break;
        }
        PenyimpananData.getBuku().get(indeksBuku).setKetersediaanBuku(nilaiBoolean);
    }

    //============================================================================================================================================================================================================

    public static void hapus(int indeks){
        PenyimpananData.getBuku().remove(indeks);
    }

    //============================================================================================================================================================================================================

    public static void tampilkan(){

    }

    //============================================================================================================================================================================================================
}
