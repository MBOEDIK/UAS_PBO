package com.SistemManajemenPerpustakaan.MVC.Models.Book;

public class Majalah extends Buku {
    //ATTRIBUTE
    private String topik;

    //CONSTRUCTOR

    public Majalah(String kode, String judul, String pengarang, String tahunTerbit, Boolean tersedia, String jenis, String topik) {
        super(kode, judul, pengarang, tahunTerbit, tersedia, jenis);
        this.topik = topik;
    }

    //GETTER
    public String getTopik(){ return topik; }

    //SETTER
    public void setTopik(String topik){ this.topik = topik; }
}
