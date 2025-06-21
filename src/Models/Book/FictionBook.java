package Models.Book;

public class FictionBook extends Book {
    private String genre;

    public FictionBook(String kodeBuku, String judul, String genre) {
        super(kodeBuku, judul);
        this.genre = genre;
    }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    @Override
    public String getCategory() {
        return "Fiksi";
    }
}