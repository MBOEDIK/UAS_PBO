package com.SistemManajemenPerpustakaan.MVC.Models.Book;

public abstract class Buku {
    //INI ATTRIBUTE
    private String kode;
    private String judul;
    private String pengarang;
    private String tahunTerbit;
    private Boolean tersedia;
    private String jenis;

    //INI CONSTRUCTOR
    protected Buku(String kode, String judul, String pengarang, String tahunTerbit, Boolean tersedia, String jenis) {
        this.kode = kode;
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.tersedia = tersedia;
        this.jenis = jenis;
    }

    //UPDATE ATTRIBUT SPESIFIK
    public abstract void updateAttribute(String atribut, Object nilaiBaru);

    //INI GETTER
    public String getKode() { return kode; }
    public String getJudul() { return judul; }
    public String getPengarang() { return pengarang; }
    public String getTahunTerbit() { return tahunTerbit; }
    public Boolean getTersedia() { return tersedia; }
    public String getJenis() { return jenis; }

    //INI SETTER
    public void setKode(String kode){ this.kode = kode; }
    public void setJudul(String judul){ this.judul = judul; }
    public void setPengarang(String pengarang){  this.pengarang = pengarang; }
    public void setTahunTerbit(String tahunTerbit){ this.tahunTerbit = tahunTerbit; }
    public void setTersedia(Boolean tersedia){ this.tersedia = tersedia; }
    public void setJenis(String jenis){ this.jenis = jenis; }
}
