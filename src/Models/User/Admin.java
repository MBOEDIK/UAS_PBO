package Models.User;

public class Admin extends Pengguna {
    String nipAdmin ;

    //menerapkan super dari class parent
    Admin(String idUser, String nama, String alamatUser, int nomorHpUser, String nipAdmin) {
        super(idUser, nama, alamatUser, nomorHpUser);
    }

    //getter
    public String getNipAdmin () {
        return nipAdmin ;
    }

    //setter
    public void setNipAdmin(String nipAdmin) {
        this.nipAdmin = nipAdmin;
    }
}