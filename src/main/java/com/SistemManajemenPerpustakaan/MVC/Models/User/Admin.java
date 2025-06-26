package com.SistemManajemenPerpustakaan.MVC.Models.User;

import com.SistemManajemenPerpustakaan.Enums.JenisPengguna;

public class Admin extends Pengguna {
    //ATTRIBUTE
    private String nip;

    //CONSTRUCTOR
    public Admin(String idPengguna, String String, String namaPengguna, String alamatPengguna, String nomorHpPengguna, String username, String password, String nip) {
        super(idPengguna, String, namaPengguna, alamatPengguna, nomorHpPengguna, username, password);
        this.nip = nip;
    }

    //GETTER
    public String getNip() { return nip; }

    //SETTER
    public void setNip(String nip) { this.nip = nip; }
}