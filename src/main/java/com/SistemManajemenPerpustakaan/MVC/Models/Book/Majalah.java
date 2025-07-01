// INI JUAN YANG KASI KOMEN
// CLASS MAJALAH TURUNAN DARI BUKU, KHUSUS UNTUK JENIS MAJALAH

package com.SistemManajemenPerpustakaan.MVC.Models.Book;

public class Majalah extends Buku {

    // ATRIBUT TAMBAHAN UNTUK MAJALAH: TOPIK UTAMA
    private String topik;

    // CONSTRUCTOR UNTUK SET SEMUA DATA MAJALAH
    public Majalah(String kode, String judul, String pengarang, String tahunTerbit, Boolean tersedia, String jenis, String topik) {
        super(kode, judul, pengarang, tahunTerbit, tersedia, jenis);
        this.topik = topik;
    }

    // METHOD UNTUK UPDATE DATA MAJALAH BERDASARKAN ATRIBUT
    // DIPAKAI SAAT EDIT DI MENU UPDATE
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

    // GETTER UNTUK AMBIL NILAI TOPIK MAJALAH
    public String getTopik(){ return topik; }

    // SETTER UNTUK UBAH NILAI TOPIK MAJALAH
    public void setTopik(String topik){ this.topik = topik; }
}
