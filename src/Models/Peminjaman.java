package Models;

public class Peminjaman {
    //ATTRIBUTE
    private String idPeminjaman ;
    private String idAnggota ;
    private String idKodeBuku ;
    private String tglPinjam ;
    private String tglKembali ;
    private String statusPengembalian ;

    //CONSTRUCTOR
    public Peminjaman (String idPeminjaman, String idAnggota, String idKodeBuku, String tglKembali, String tglPinjam, String statusPengembalian) {
        this.idPeminjaman = idPeminjaman ;
        this.idAnggota = idAnggota;
        this.idKodeBuku = idKodeBuku;
        this.tglPinjam = tglPinjam;
        this.tglKembali = tglKembali;
        this.statusPengembalian = statusPengembalian;
    }
    //GETTER
    public String getIdPeminjaman() {
        return idPeminjaman;
    }
    public String getIdAnggota() {
        return idAnggota;
    }
    public String getKodebuku() {
        return idKodeBuku;
    }
    public String getTanggalPinjam() {
        return tglPinjam;
    }
    public String getTanggalKembali() {
        return tglKembali;
    }
    public String getStatusKeterlambatanPengembalian() {
        return statusPengembalian;
    }

    //SETTER
    public void setIdPeminjaman(String idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }
    public void setIdAnggota(String idAnggota) {
        this.idAnggota = idAnggota;
    }
    public void setKodeBuku(String kodeBuku) {
        this.idKodeBuku = kodeBuku;
    }
    public void setTanggalPinjam(String tanggalPinjam) {
        this.tglPinjam = tanggalPinjam;
    }
    public void setTanggalKembali(String tanggalKembali) {
        this.tglKembali = tanggalKembali;
    }
    public void setStatusKeterlambatan(String statusPengembalian) {
        this.statusPengembalian = statusPengembalian;
    }

    //Method untuk nampilin data peminjaman
    public String generateReport() {
        return "ID Peminjaman : " + idPeminjaman + "\n" +
                "ID Anggota    : " + idAnggota + "\n" +
                "Kode Buku     : " + idKodeBuku + "\n" +
                "Tanggal Pinjam: " + tglPinjam + "\n" +
                "Tanggal Kembali: " + tglKembali + "\n" +
                "Status        : " + statusPengembalian;
    }
}