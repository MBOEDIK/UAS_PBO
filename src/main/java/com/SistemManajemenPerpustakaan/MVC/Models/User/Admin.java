package com.SistemManajemenPerpustakaan.MVC.Models.User;

public class Admin extends Pengguna {
    //ATTRIBUTE
    private String nip;

    //CONSTRUCTOR
    public Admin(String id, String jenis, String nama, String alamat, String nomorHP, String username, String password, String nip) {
        super(id, jenis, nama, alamat, nomorHP, username, password);
        this.nip = nip;
    }

    @Override
    public void updateAttribute(String atribut, Object nilaiBaru) {
        switch (atribut.toUpperCase()) {
            case "ID": setId((String) nilaiBaru); break;
            case "PENGARANG": setJenis((String) nilaiBaru); break;
            case "NAMA": setNama((String) nilaiBaru); break;
            case "ALAMAT": setAlamat((String) nilaiBaru); break;
            case "NOMORHP": setNomorHP((String) nilaiBaru); break;
            case "USERNAME": setUsername((String) nilaiBaru); break;
            case "PASSWORD": setPassword((String) nilaiBaru); break;
            case "NIP": setNip((String) nilaiBaru); break;
            default: System.out.println("Atribut tidak dikenali");
        }
    }

    //GETTER
    public String getNip() { return nip; }

    //SETTER
    public void setNip(String nip) { this.nip = nip; }
}