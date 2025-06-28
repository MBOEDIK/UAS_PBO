package com.SistemManajemenPerpustakaan.MVC.Models.Book;

public class Novel extends Buku {
    //ATTRIBUTE
    private String genre;

    //CONSTRUCTOR
    public Novel(String kode, String judul, String pengarang, String tahunTerbit, Boolean tersedia, String jenis, String genre) {
        super(kode, judul, pengarang, tahunTerbit, tersedia, jenis);
        this.genre = genre;
    }

    @Override
    public void updateAttribute(String atribut, Object nilaiBaru) {
        switch (atribut.toUpperCase()) {
            case "JUDUL": setJudul((String) nilaiBaru); break;
            case "PENGARANG": setPengarang((String) nilaiBaru); break;
            case "TAHUN TERBIT": setTahunTerbit((String) nilaiBaru); break;
            case "TERSEDIA": setTersedia((Boolean) nilaiBaru); break;
            case "JENIS": setJenis((String) nilaiBaru); break;
            case "GENRE": setGenre((String) nilaiBaru); break;
            default: System.out.println("Atribut tidak dikenali");
        }
    }

    //GETTER
    public String getGenre(){ return genre; }

    //SETTER
    public void setGenre(String genre){ this.genre = genre; }
}
