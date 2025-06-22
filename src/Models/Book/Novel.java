package Models.Book;

public class Novel extends Buku {
    //ATTRIBUTE
    private String genreBuku;

    //CONSTRUCTOR
    public Novel(String kodeBuku, String judulBuku, String kategoriBuku, String pengarangBuku, String tahunTerbitBuku, Boolean ketersediaanBuku) {
        super(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku);
    }

    //GETTER
    public String getGenreBuku(){ return genreBuku; }

    //SETTER
    public void setGenreBuku(String genreBuku){ this.genreBuku = genreBuku; }
}
