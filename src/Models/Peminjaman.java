package Models;

public class Peminjaman {
    //ini atribut dari class peminjaman
    private String idPeminjaman ;
    private String idAnggota ;
    private String idKodeBuku ;
    private String tglPinjam ;
    private String tglKembali ;
    private String statusPengembalian ;

    //ini adalah constructor
    Peminjaman (String idPeminjaman, String idAnggota, String idKodeBuku, String tglKembali, String tglPinjam, String statusPengembalian ) {
        this.idPeminjaman = idPeminjaman ;
        this.idAnggota = idAnggota;
        this.idKodeBuku = idKodeBuku;
        this.tglPinjam = tglPinjam;
        this.tglKembali = tglKembali;
        this.statusPengembalian = statusPengembalian;
    }
    // Getter Methodd
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

    // Setter Methodd
    public void setIdPeminjaman(String idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public void setStatusKeterlambatanPengembalian(String idAnggota) {
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

    // Method untuk mereport
    public String generateReport() {
        return "ID Peminjaman : " + idPeminjaman + "\n" +
                "ID Anggota    : " + idAnggota + "\n" +
                "Kode Buku     : " + idKodeBuku + "\n" +
                "Tanggal Pinjam: " + tglPinjam + "\n" +
                "Tanggal Kembali: " + tglKembali + "\n" +
                "Status        : " + statusPengembalian;
    }
}