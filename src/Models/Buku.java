package Models;

public class Buku {
    private String kodeBuku;
    private String judul;
    private String pengarang;
    private int tahunTerbit;
    private boolean tersedia;

    public Buku(String kodeBuku, String judul, String pengarang, int tahunTerbit, boolean tersedia) {
        this.kodeBuku = kodeBuku;
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.tersedia = tersedia;
    }

    public String getKodeBuku() {
        return kodeBuku;
    }

    public void setKodeBuku(String kodeBuku) {
        this.kodeBuku = kodeBuku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    @Override
    public String toString() {
        return "Buku{" +
                "kode='" + kodeBuku + '\'' +
                ", judul='" + judul + '\'' +
                ", pengarang='" + pengarang + '\'' +
                ", tahun=" + tahunTerbit +
                ", tersedia=" + tersedia +
                '}';
    }
}
