package Models.User;

public abstract class Pengguna {
    //ATTRIBUTE
    private String idPengguna;
    private String jenisPengguna;
    private String namaPengguna;
    private String alamatPengguna;
    private String nomorHpPengguna;
    private String username;
    private String password;


    //CONSTRUCTOR
    public Pengguna (String idPengguna, String jenisPengguna,String namaPengguna, String alamatPengguna, String nomorHpPengguna, String username, String password) {
        this.idPengguna = idPengguna;
        this.jenisPengguna = jenisPengguna;
        this.namaPengguna = namaPengguna ;
        this.alamatPengguna = alamatPengguna ;
        this.nomorHpPengguna = nomorHpPengguna ;
        this.username = username;
        this.password = password;
    }

    //GETTER
    public String getIdPengguna() {
        return idPengguna;
    }
    public String getJenisPengguna() {
        return jenisPengguna;
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
    public String getUsername(){ return username; }
    public String getPassword(){ return password; }

    //SETTER
    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }
    public void setJenisPengguna(String jenisPengguna) {
        this.jenisPengguna = jenisPengguna;
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
    public void setUsername(String username){ this.username = username; }
    public void setPassword(String password){ this.password = password; }

}