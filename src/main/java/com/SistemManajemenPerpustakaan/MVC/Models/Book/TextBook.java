// INI JUAN YANG KASI KOMEN
// CLASS TEXTBOOK TURUNAN DARI BUKU UNTUK JENIS BUKU PELAJARAN

package com.SistemManajemenPerpustakaan.MVC.Models.Book;

public class TextBook extends Buku {

    // ATRIBUT KHUSUS TEXTBOOK: BIDANG ILMU YANG DIBAHAS
    private String bidangIlmu;

    // CONSTRUCTOR BUAT INISIALISASI SEMUA DATA TEXTBOOK
    public TextBook(String kode, String judul, String pengarang, String tahunTerbit, Boolean tersedia, String jenis, String bidangIlmu) {
        super(kode, judul, pengarang, tahunTerbit, tersedia, jenis);
        this.bidangIlmu = bidangIlmu;
    }

    // METHOD UNTUK UPDATE ATRIBUT TEXTBOOK SECARA DINAMIS
    // DIPANGGIL SAAT PROSES UPDATE DI CONTROLLER
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

    // GETTER BUAT AMBIL NILAI BIDANG ILMU
    public String getBidangIlmu(){ return bidangIlmu; }

    // SETTER BUAT UBAH NILAI BIDANG ILMU
    public void setBidangIlmu(String bidangIlmu){ this.bidangIlmu = bidangIlmu; }
}
