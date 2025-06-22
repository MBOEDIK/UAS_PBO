package Models.Book;

public class Majalah extends Buku {
    //ATTRIBUTE
    private String topikBuku;

    //CONSTRUCTOR
    public Majalah(String kodeBuku, String judulBuku, String kategoriBuku, String pengarangBuku, String tahunTerbitBuku, Boolean ketersediaanBuku) {
        super(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku);
    }

    //GETTER
    public String getTopikBuku(){ return topikBuku; }

    //SETTER
    public void setTopikBuku(String topikBuku){ this.topikBuku = topikBuku; }
}
