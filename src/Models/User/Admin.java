package Models.User;

public abstract class Admin extends Pengguna {
    //ATTRIBUTE
    private String nipAdmin ;

    //CONSTRUCTOR
    Admin(String idPengguna, String namaPengguna, String alamatPengguna, String nomorHpPengguna, String nipAdmin) {
        super(idPengguna, namaPengguna, alamatPengguna, nomorHpPengguna);
        this.nipAdmin = nipAdmin;
    }

    //GETTER
    public String getNipAdmin () { return nipAdmin ; }

    //SETTER
    public void setNipAdmin(String nipAdmin) { this.nipAdmin = nipAdmin; }
}