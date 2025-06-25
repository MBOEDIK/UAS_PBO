package Models.Book;

public class JurnalIlmiah extends Buku {
    //ATTRIBUTE
    private String institusi;
    private String terindeksSinta;

    //CONSTRUCTOR
    public JurnalIlmiah(String kodeBuku, String judulBuku, String kategoriBuku, String pengarangBuku, String tahunTerbitBuku, Boolean ketersediaanBuku, String institusi, String terindeksSinta) {
        super(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku);
        this.institusi = institusi;
        this.terindeksSinta = terindeksSinta;
    }

    //GETTER
    public String getInstitusi(){ return institusi; }
    public String getTerindeksSinta(){ return terindeksSinta; }

    //SETTER
    public void setInstitusi(String institusi){ this.institusi = institusi; }
    public void setTerindeksSinta(String terindeksSinta){ this.terindeksSinta = terindeksSinta; }
}
