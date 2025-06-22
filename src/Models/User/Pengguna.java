package Models.User;

public abstract class Pengguna {
    //ATTRIBUTE
    private String idPengguna;    // ID unik pengguna
    private String namaPengguna;// Nama pengguna
    private String alamatPengguna; // alamar user
    private String nomorHpPengguna;// kosong delapan berapa mass

    //CONSTRUCTOR
    Pengguna (String idPengguna, String namaPengguna, String alamatPengguna, String nomorHpPengguna) {
        this.idPengguna = idPengguna;
        this.namaPengguna = namaPengguna ;
        this.alamatPengguna = alamatPengguna ;
        this.nomorHpPengguna = nomorHpPengguna ;
    }

    //GETTER
    public String getIdPengguna() {
        return idPengguna;
    }
    public String getNamaPengguna() {
        return namaPengguna;
    }
    public String getAlamatPengguna() {
        return alamatPengguna;
    }
    public String getNomorHPPengguna() {
        return nomorHpPengguna;
    }

    //SETTER
    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }
    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }
    public void setAlamatPengguna(String alamatPengguna) {
        this.alamatPengguna = alamatPengguna;
    }
    public void setNomorHPPengguna(String nomorHPPengguna) {
        this.nomorHpPengguna = nomorHPPengguna;
    }

}