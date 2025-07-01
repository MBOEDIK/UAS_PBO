// INI JUAN YANG KASI KOMEN
// CLASS JURNAL INI TURUNAN DARI BUKU, KHUSUS UNTUK JENIS JURNAL

package com.SistemManajemenPerpustakaan.MVC.Models.Book;

public class Jurnal extends Buku {

    // ATTRIBUTE TAMBAHAN KHUSUS JURNAL
    private String institusi;
    private String terindeksSinta;

    // CONSTRUCTOR UNTUK INISIALISASI DATA JURNAL
    public Jurnal(String kode, String judul, String pengarang, String tahunTerbit, Boolean tersedia, String jenis, String institusi, String terindeksSinta) {
        super(kode, judul, pengarang, tahunTerbit, tersedia, jenis);
        this.institusi = institusi;
        this.terindeksSinta = terindeksSinta;
    }

    // METHOD UNTUK UPDATE ATRIBUT SECARA DINAMIS
    // DIPANGGIL DARI CONTROLLER SAAT UPDATE
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

    // GETTER UNTUK AMBIL DATA KHUSUS JURNAL
    public String getInstitusi(){ return institusi; }
    public String getTerindeksSinta(){ return terindeksSinta; }

    // SETTER UNTUK UBAH DATA KHUSUS JURNAL
    public void setInstitusi(String institusi){ this.institusi = institusi; }
    public void setTerindeksSinta(String terindeksSinta){ this.terindeksSinta = terindeksSinta; }
}
