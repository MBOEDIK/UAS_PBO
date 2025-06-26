package com.SistemManajemenPerpustakaan.MVC.Models.Book;

public class TextBook extends Buku {
    //ATTRIBUTE
    private String bidangIlmu;

    //CONSTRUCTOR
    public TextBook(String kode, String judul, String pengarang, String tahunTerbit, Boolean tersedia, String jenis, String bidangIlmu) {
        super(kode, judul, pengarang, tahunTerbit, tersedia, jenis);
        this.bidangIlmu = bidangIlmu;
    }

    //GETTER
    public String getBidangIlmu(){ return bidangIlmu; }

    //SETTER
    public void setBidangIlmu(String bidangIlmu){ this.bidangIlmu = bidangIlmu; }
}
