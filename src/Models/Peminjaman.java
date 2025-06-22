package Models;

import Models.Book.Buku;
import Models.User.Pengguna;
import service.Reporttable;


import java.time.LocalDate;
import java.util.Objects;

/**
 * Kelas Peminjaman merepresentasikan data transaksi peminjaman buku oleh user.
 */

public class Peminjaman implements Reporttable {
    /**
     * Status dari peminjaman buku.
     */
    public enum Status {
        AKTIF, SELESAI
    }

    private final String idPeminjaman;
    private final Pengguna peminjam;
    private final Buku buku;
    private final LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;
    private Status status;

    /**
     * Konstruktor peminjaman dengan validasi.
     */
    public Peminjaman(String idPeminjaman, Pengguna peminjam, Buku buku, LocalDate tanggalPinjam) {
        if (idPeminjaman == null || peminjam == null || buku == null || tanggalPinjam == null)
            throw new IllegalArgumentException("Semua data wajib diisi.");
        this.idPeminjaman = idPeminjaman;
        this.peminjam = peminjam;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.status = Status.AKTIF;
    }

    /**
     * Menandai peminjaman sebagai selesai.
     */
    public void selesaikan(LocalDate tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
        this.status = Status.SELESAI;
    }

    /**
     * Mengecek apakah peminjaman melewati batas.
     */
    public boolean isTerlambat(LocalDate batas) {
        return status == Status.AKTIF && LocalDate.now().isAfter(batas);
    }

    public String getIdPeminjaman() { return idPeminjaman; }
    public Pengguna getPeminjam() { return peminjam; }
    public Buku getBuku() { return buku; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public Status getStatus() { return status; }

    /**
     * Menghasilkan laporan ringkas dari transaksi peminjaman.
     */
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
