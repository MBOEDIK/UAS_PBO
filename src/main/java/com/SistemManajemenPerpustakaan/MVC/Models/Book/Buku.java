package com.SistemManajemenPerpustakaan.MVC.Models.Book;

public abstract class Buku {
    //INI ATTRIBUTE
    private String kodeBuku;
    private String judulBuku;
    private String kategoriBuku;
    private String pengarangBuku;
    private String tahunTerbitBuku;
    private Boolean ketersediaanBuku;

    //INI CONSTRUCTOR
    protected Buku(String kodeBuku, String judulBuku, String kategoriBuku, String pengarangBuku, String tahunTerbitBuku, Boolean ketersediaanBuku) {
        this.judulBuku = judulBuku;
        this.kodeBuku = kodeBuku;
        this.kategoriBuku = kategoriBuku;
        this.pengarangBuku = pengarangBuku;
        this.tahunTerbitBuku = tahunTerbitBuku;
        this.ketersediaanBuku = ketersediaanBuku;
    }

    //INI GETTER
    public String getKodeBuku() { return kodeBuku; }
    public String getJudulBuku() { return judulBuku; }
    public String getKategoriBuku() { return kategoriBuku; }
    public String getPengarangBuku() { return pengarangBuku; }
    public String getTahunTerbitBuku() { return tahunTerbitBuku; }
    public Boolean getKetersediaanBuku() { return ketersediaanBuku; }

    //INI SETTER
    public void setKodeBuku(String kodeBuku){ this.kodeBuku = kodeBuku; }
    public void setJudulBuku(String judulBuku){ this.judulBuku = judulBuku; }
    public void setKategoriBuku(String kategoriBuku){ this.kategoriBuku = kategoriBuku; }
    public void setPengarangBuku(String pengarangBuku){  this.pengarangBuku = pengarangBuku; }
    public void setTahunTerbitBuku(String tahunTerbitBuku){ this.tahunTerbitBuku = tahunTerbitBuku; }
    public void setKetersediaanBuku(Boolean ketersediaanBuku){ this.ketersediaanBuku = ketersediaanBuku; }
}
