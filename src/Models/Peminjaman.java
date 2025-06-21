package Models;

import Models.Book.Book;
import Models.User.User;
import service.Reportable;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;



public class Peminjaman implements Reportable {
    public enum Status {
        AKTIF, SELESAI
    }

    private final String idPeminjaman;
    private final User peminjam;
    private final Book buku;
    private final LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;
    private Status status;

    public Peminjaman(String idPeminjaman, User peminjam, Book buku, LocalDate tanggalPinjam) {
        if (idPeminjaman == null || peminjam == null || buku == null || tanggalPinjam == null)
            throw new IllegalArgumentException("Semua data wajib diisi.");
        this.idPeminjaman = idPeminjaman;
        this.peminjam = peminjam;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.status = Status.AKTIF;
    }

    public void selesaikan(LocalDate tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
        this.status = Status.SELESAI;
    }

    public boolean isTerlambat(LocalDate batas) {
        return status == Status.AKTIF && LocalDate.now().isAfter(batas);
    }

    public String getIdPeminjaman() { return idPeminjaman; }
    public User getPeminjam() { return peminjam; }
    public Book getBuku() { return buku; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public Status getStatus() { return status; }

    @Override
    public String generateReport() {
        return String.format("Peminjaman[%s] oleh %s\nBuku: %s (%s)\nTanggal Pinjam: %s\nStatus: %s",
                idPeminjaman,
                peminjam.getNama(),
                buku.getJudul(),
                buku.getCategory(),
                tanggalPinjam,
                status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Peminjaman)) return false;
        Peminjaman that = (Peminjaman) o;
        return Objects.equals(idPeminjaman, that.idPeminjaman);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPeminjaman);
    }
}