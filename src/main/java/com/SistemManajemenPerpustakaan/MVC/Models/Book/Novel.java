package com.SistemManajemenPerpustakaan.MVC.Models.Book;

public class Novel extends Buku {
    //ATTRIBUTE
    private String genre;

    //CONSTRUCTOR
    public Novel(String kode, String judul, String pengarang, String tahunTerbit, Boolean tersedia, String jenis, String genre) {
        super(kode, judul, pengarang, tahunTerbit, tersedia, jenis);
        this.genre = genre;
    }

    //GETTER
    public String getGenre(){ return genre; }

    //SETTER
    public void setGenre(String genre){ this.genre = genre; }
}
