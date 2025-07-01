// INI JUAN YANG KASI KOMEN
// CLASS ABSTRAK UNTUK SEMUA JENIS PENGGUNA (ADMIN & ANGGOTA)

package com.SistemManajemenPerpustakaan.MVC.Models.User;

public abstract class Pengguna {

    // ATRIBUT DASAR PENGGUNA: IDENTITAS, LOGIN, DAN KONTAK
    private String id;
    private String jenis;
    private String nama;
    private String alamat;
    private String nomorHP;
    private String username;
    private String password;

    // CONSTRUCTOR UNTUK MENGISI SEMUA DATA PENGGUNA
    public Pengguna(String id, String jenis, String nama, String alamat, String nomorHP, String username, String password) {
        this.id = id;
        this.jenis = jenis;
        this.nama = nama ;
        this.alamat = alamat ;
        this.nomorHP = nomorHP ;
        this.username = username;
        this.password = password;
    }

    // METHOD ABSTRAK UNTUK UPDATE ATRIBUT SECARA DINAMIS
    // AKAN DIIMPLEMENTASI DI ADMIN / ANGGOTA
    public abstract void updateAttribute(String atribut, Object nilaiBaru);

    // GETTER UNTUK MENGAMBIL DATA PENGGUNA
    public String getId() { return id; }
    public String getJenis() { return jenis; }
    public String getNama() { return nama; }
    public String getAlamat() { return alamat; }
    public String getNomorHP() { return nomorHP; }
    public String getUsername(){ return username; }
    public String getPassword(){ return password; }

    // SETTER UNTUK MENGUBAH DATA PENGGUNA
    public void setId(String id) { this.id = id; }
    public void setJenis(String jenis) { this.jenis = jenis; }
    public void setNama(String nama) { this.nama = nama; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public void setNomorHP(String nomorHPPengguna) { this.nomorHP = nomorHPPengguna; }
    public void setUsername(String username){ this.username = username; }
    public void setPassword(String password){ this.password = password; }

}
