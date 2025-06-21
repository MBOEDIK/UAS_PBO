package Models.Book;

/**
 * Kelas turunan Book untuk buku non-fiksi.
 * Menambahkan atribut subjectArea.
 */
public class NonFictionBook extends Book {
    private String subjectArea;

    public NonFictionBook(String kodeBuku, String judul, String subjectArea) {
        super(kodeBuku, judul);
        this.subjectArea = subjectArea;
    }

    public String getSubjectArea() { return subjectArea; }
    public void setSubjectArea(String subjectArea) { this.subjectArea = subjectArea; }

    @Override
    public String getCategory() {
        return "Non-Fiksi";
    }
}
