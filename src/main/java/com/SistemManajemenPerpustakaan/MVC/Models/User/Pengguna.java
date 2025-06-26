package com.SistemManajemenPerpustakaan.MVC.Models.User;

import com.SistemManajemenPerpustakaan.Enums.JenisPengguna;

public abstract class Pengguna {
    //ATTRIBUTE
    private String id;
    private String jenis;
    private String nama;
    private String alamat;
    private String nomorHP;
    private String username;
    private String password;


    //CONSTRUCTOR
    public Pengguna (String id, String jenis,String nama, String alamat, String nomorHP, String username, String password) {
        this.id = id;
        this.jenis = jenis;
        this.nama = nama ;
        this.alamat = alamat ;
        this.nomorHP = nomorHP ;
        this.username = username;
        this.password = password;
    }

    //GETTER
    public String getId() {
        return id;
    }
    public String getJenis() { return jenis; }
    public String getNama() {
        return nama;
    }
    public String getAlamat() {
        return alamat;
    }
    public String getNomorHP() {
        return nomorHP;
    }
    public String getUsername(){ return username; }
    public String getPassword(){ return password; }

    //SETTER
    public void setId(String id) {
        this.id = id;
    }
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public void setNomorHP(String nomorHPPengguna) {
        this.nomorHP = nomorHPPengguna;
    }
    public void setUsername(String username){ this.username = username; }
    public void setPassword(String password){ this.password = password; }

}