package Models.Book;

import java.util.Objects;

public abstract class Book {
    private String kodeBuku;
    private String judul;

    protected Book(String kodeBuku, String judul) {
        if (kodeBuku == null || !kodeBuku.matches("BK\\d{3,}"))
            throw new IllegalArgumentException("Kode buku harus diawali 'BK' dan diikuti minimal 3 digit.");
        if (judul == null || judul.isBlank())
            throw new IllegalArgumentException("Judul tidak boleh kosong.");
        this.kodeBuku = kodeBuku;
        this.judul = judul;
    }

    public String getKodeBuku() { return kodeBuku; }
    public String getJudul() { return judul; }
    public void setJudul(String judul) {
        if (judul == null || judul.isBlank())
            throw new IllegalArgumentException("Judul tidak boleh kosong.");
        this.judul = judul;
    }

    public abstract String getCategory();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return kodeBuku.equals(book.kodeBuku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kodeBuku);
    }
}
