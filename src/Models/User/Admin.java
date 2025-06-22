package Models.User;


public class Admin extends Pengguna {
    String nipAdmin ;

    Admin(String idUser, String nama, String alamatUser, int nomorHpUser, String nipAdmin) {
        super(idUser, nama, alamatUser, nomorHpUser);
    }

    public String getNipAdmin () {
        return nipAdmin ;
    }

    public void setNipAdmin(String nipAdmin) {
        this.nipAdmin = nipAdmin;
    }
}