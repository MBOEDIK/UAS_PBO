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

    @Override
    public void updateAttribute(String atribut, Object nilaiBaru) {
        switch (atribut.toUpperCase()) {
            case "JUDUL": setJudul((String) nilaiBaru); break;
            case "PENGARANG": setPengarang((String) nilaiBaru); break;
            case "TAHUN TERBIT": setTahunTerbit((String) nilaiBaru); break;
            case "TERSEDIA": setTersedia((Boolean) nilaiBaru); break;
            case "JENIS": setJenis((String) nilaiBaru); break;
            case "INSTITUSI": setInstitusi((String) nilaiBaru); break;
            case "TERINDEKSSINTA": setTerindeksSinta((String) nilaiBaru); break;
            default: System.out.println("Atribut tidak dikenali");
        }
    }

    //GETTER
    public String getInstitusi(){ return institusi; }
    public String getTerindeksSinta(){ return terindeksSinta; }

    //SETTER
    public void setInstitusi(String institusi){ this.institusi = institusi; }
    public void setTerindeksSinta(String terindeksSinta){ this.terindeksSinta = terindeksSinta; }
}
