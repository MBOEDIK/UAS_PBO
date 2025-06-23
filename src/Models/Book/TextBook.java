package Models.Book;

public class TextBook extends Buku {
    //ATTRIBUTE
    private String bidangIlmu;

    //CONSTRUCTOR
    public TextBook(String kodeBuku, String judulBuku, String kategoriBuku, String pengarangBuku, String tahunTerbitBuku, Boolean ketersediaanBuku, String bidangIlmu) {
        super(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku);
        this.bidangIlmu = bidangIlmu;
    }

    //GETTER
    public String getBidangIlmu(){ return bidangIlmu; }

    //SETTER
    public void setBidangIlmu(String bidangIlmu){ this.bidangIlmu = bidangIlmu; }
}
