package com.SistemManajemenPerpustakaan.MVC.Models.Book;

public class Majalah extends Buku {
    //ATTRIBUTE
    private String topik;

    //CONSTRUCTOR

    public Majalah(String kode, String judul, String pengarang, String tahunTerbit, Boolean tersedia, String jenis, String topik) {
        super(kode, judul, pengarang, tahunTerbit, tersedia, jenis);
        this.topik = topik;
    }

    @Override
    public void updateAttribute(String atribut, Object nilaiBaru) {
        switch (atribut.toUpperCase()) {
            case "JUDUL": setJudul((String) nilaiBaru); break;
            case "PENGARANG": setPengarang((String) nilaiBaru); break;
            case "TAHUN TERBIT": setTahunTerbit((String) nilaiBaru); break;
            case "TERSEDIA": setTersedia((Boolean) nilaiBaru); break;
            case "JENIS": setJenis((String) nilaiBaru); break;
            case "TOPIK": setTopik((String) nilaiBaru); break;
            default: System.out.println("Atribut tidak dikenali");
        }
    }

    //GETTER
    public String getTopik(){ return topik; }

    //SETTER
    public void setTopik(String topik){ this.topik = topik; }
}
