package Models;

import java.time.LocalDate;

public class Peminjaman {
    private String idPeminjaman;
    private Anggota anggota;
    private Buku buku;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;

    public Peminjaman(String idPeminjaman, Anggota anggota, Buku buku, LocalDate tanggalPinjam, LocalDate tanggalKembali) {
        this.idPeminjaman = idPeminjaman;
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
    }

    public String getIdPeminjaman() {
        return idPeminjaman;
    }

    public void setIdPeminjaman(String idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(LocalDate tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(LocalDate tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    @Override
    public String toString() {
        return "Peminjaman{" +
                "id='" + idPeminjaman + '\'' +
                ", anggota=" + anggota.getNama() +
                ", buku=" + buku.getJudul() +
                ", pinjam=" + tanggalPinjam +
                ", kembali=" + tanggalKembali +
                '}';
    }
}
