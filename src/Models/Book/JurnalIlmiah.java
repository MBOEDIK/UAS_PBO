package Models.Book;

public class JurnalIlmiah extends Buku {
    //ATTRIBUTE
    private String institusi;
    private Boolean terindeksSinta;

    //CONSTRUCTOR
    public JurnalIlmiah(String kodeBuku, String judulBuku, String kategoriBuku, String pengarangBuku, String tahunTerbitBuku, Boolean ketersediaanBuku) {
        super(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku);
    }

    //GETTER
    public String getInstitusi(){ return institusi; }
    public Boolean getTerindeksSinta(){ return terindeksSinta; }

    //SETTER
    public void setInstitusi(String institusi){ this.institusi = institusi; }
    public void setTerindeksSinta(Boolean terindeksSinta){ this.terindeksSinta = terindeksSinta; }
}
