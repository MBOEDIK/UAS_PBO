package Models.Book;

public class TextBuku extends Buku {
    //ATTRIBUTE
    private String bidangIlmu;

    //CONSTRUCTOR
    public TextBuku(String kodeBuku, String judulBuku, String kategoriBuku, String pengarangBuku, String tahunTerbitBuku, Boolean ketersediaanBuku) {
        super(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku);
    }

    //GETTER
    public String getBidangIlmu(){ return bidangIlmu; }

    //SETTER
    public void setBidangIlmu(String bidangIlmu){ this.bidangIlmu = bidangIlmu; }
}
