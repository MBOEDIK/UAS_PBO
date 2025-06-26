package com.SistemManajemenPerpustakaan.MVC.Models.Book;

public class Jurnal extends Buku {
    //ATTRIBUTE
    private String institusi;
    private String terindeksSinta;

    //CONSTRUCTOR
    public Jurnal(String kode, String judul, String pengarang, String tahunTerbit, Boolean tersedia, String jenis, String institusi, String terindeksSinta) {
        super(kode, judul, pengarang, tahunTerbit, tersedia, jenis);
        this.institusi = institusi;
        this.terindeksSinta = terindeksSinta;
    }

    //GETTER
    public String getInstitusi(){ return institusi; }
    public String getTerindeksSinta(){ return terindeksSinta; }

    //SETTER
    public void setInstitusi(String institusi){ this.institusi = institusi; }
    public void setTerindeksSinta(String terindeksSinta){ this.terindeksSinta = terindeksSinta; }
}
