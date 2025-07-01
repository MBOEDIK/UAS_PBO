// INI JUAN YANG KASI KOMEN
// CLASS NOVEL INI TURUNAN DARI BUKU UNTUK JENIS NOVEL

package com.SistemManajemenPerpustakaan.MVC.Models.Book;

public class Novel extends Buku {

    // ATRIBUT TAMBAHAN UNTUK NOVEL: GENRE NOVELNYA
    private String genre;

    // CONSTRUCTOR UNTUK MENGISI SEMUA DATA NOVEL
    public Novel(String kode, String judul, String pengarang, String tahunTerbit, Boolean tersedia, String jenis, String genre) {
        super(kode, judul, pengarang, tahunTerbit, tersedia, jenis);
        this.genre = genre;
    }

    // METHOD UNTUK UPDATE ATRIBUT NOVEL SECARA DINAMIS
    // DIPANGGIL SAAT UPDATE DARI CONTROLLER
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

    // GETTER UNTUK MENGAMBIL NILAI GENRE NOVEL
    public String getGenre(){ return genre; }

    // SETTER UNTUK MENGUBAH NILAI GENRE NOVEL
    public void setGenre(String genre){ this.genre = genre; }
}
