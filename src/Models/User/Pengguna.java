package Models.User;

public abstract class Pengguna {
    protected String idUser;    // ID unik pengguna
    protected String nama;// Nama pengguna
    protected String alamatUser; // alamar user
    protected int nomorHpUser;// kosong delapan berapa mass


    Pengguna (String idUser, String nama, String alamatUser, int nomorHpUser) {
        this.idUser = idUser;
        this.nama = nama ;
        this.alamatUser = alamatUser ;
        this.nomorHpUser = nomorHpUser ;
    }
    // Getter methods
    public String getIdPengguna() {
        return idUser;
    }

    public String getNamaPengguna() {
        return nama;
    }

    public String getAlamatPengguna() {
        return alamatUser;
    }

    public int getNomorHPPengguna() {
        return nomorHpUser;
    }

    // Setter methods
    public void setIdPengguna(String idPengguna) {
        this.idUser = idPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.nama = namaPengguna;
    }

    public void setAlamatPengguna(String alamatPengguna) {
        this.alamatUser = alamatPengguna;
    }

    public void setNomorHPPengguna(int nomorHPPengguna) {
        this.nomorHpUser = nomorHPPengguna;
    }

}