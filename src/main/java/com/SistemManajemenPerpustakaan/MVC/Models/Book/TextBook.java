package com.SistemManajemenPerpustakaan.MVC.Models.Book;

public class TextBook extends Buku {
    //ATTRIBUTE
    private String bidangIlmu;

    //CONSTRUCTOR
    public TextBook(String kode, String judul, String pengarang, String tahunTerbit, Boolean tersedia, String jenis, String bidangIlmu) {
        super(kode, judul, pengarang, tahunTerbit, tersedia, jenis);
        this.bidangIlmu = bidangIlmu;
    }

    @Override
    public void updateAttribute(String atribut, Object nilaiBaru) {
        switch (atribut.toUpperCase()) {
            case "JUDUL": setJudul((String) nilaiBaru); break;
            case "PENGARANG": setPengarang((String) nilaiBaru); break;
            case "TAHUN TERBIT": setTahunTerbit((String) nilaiBaru); break;
            case "TERSEDIA": setTersedia((Boolean) nilaiBaru); break;
            case "JENIS": setJenis((String) nilaiBaru); break;
            case "BIDANGILMU": setBidangIlmu((String) nilaiBaru); break;
            default: System.out.println("Atribut tidak dikenali");
        }
    }

    //GETTER
    public String getBidangIlmu(){ return bidangIlmu; }

    //SETTER
    public void setBidangIlmu(String bidangIlmu){ this.bidangIlmu = bidangIlmu; }
}
