// INI JUAN YANG KASI KOMEN
// CLASS ADMIN TURUNAN DARI PENGGUNA, DITAMBAH NIP KHUSUS ADMIN

package com.SistemManajemenPerpustakaan.MVC.Models.User;

public class Admin extends Pengguna {

    // ATRIBUT KHUSUS ADMIN: NOMOR INDUK PEGAWAI (NIP)
    private String nip;

    // CONSTRUCTOR UNTUK INISIALISASI SEMUA DATA ADMIN
    public Admin(String id, String jenis, String nama, String alamat, String nomorHP, String username, String password, String nip) {
        super(id, jenis, nama, alamat, nomorHP, username, password);
        this.nip = nip;
    }

    // METHOD UNTUK UPDATE ATRIBUT SECARA DINAMIS SAAT EDIT DATA
    @Override
    public void updateAttribute(String atribut, Object nilaiBaru) {
        switch (atribut.toUpperCase()) {
            case "ID": setId((String) nilaiBaru); break;
            case "PENGARANG": setJenis((String) nilaiBaru); break; // NOTE: KAYANYA INI HARUSNYA "JENIS", BUKAN "PENGARANG"
            case "NAMA": setNama((String) nilaiBaru); break;
            case "ALAMAT": setAlamat((String) nilaiBaru); break;
            case "NOMORHP": setNomorHP((String) nilaiBaru); break;
            case "USERNAME": setUsername((String) nilaiBaru); break;
            case "PASSWORD": setPassword((String) nilaiBaru); break;
            case "NIP": setNip((String) nilaiBaru); break;
            default: System.out.println("Atribut tidak dikenali");
        }
    }

    // GETTER UNTUK AMBIL NIP ADMIN
    public String getNip() { return nip; }

    // SETTER UNTUK MENGUBAH NIP ADMIN
    public void setNip(String nip) { this.nip = nip; }
}
