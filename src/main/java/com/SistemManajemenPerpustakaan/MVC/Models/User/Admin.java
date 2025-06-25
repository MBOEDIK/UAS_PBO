package com.SistemManajemenPerpustakaan.MVC.Models.User;

public class Admin extends Pengguna {
    //ATTRIBUTE
    protected String nipAdmin ;

    //CONSTRUCTOR
    public Admin(String idPengguna, String jenisPengguna, String namaPengguna, String alamatPengguna, String nomorHpPengguna, String username, String password, String nipAdmin) {
        super(idPengguna, jenisPengguna, namaPengguna, alamatPengguna, nomorHpPengguna, username, password);
        this.nipAdmin = nipAdmin;
    }

    //GETTER
    public String getNipAdmin () { return nipAdmin ; }

    //SETTER
    public void setNipAdmin(String nipAdmin) { this.nipAdmin = nipAdmin; }
}