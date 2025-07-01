// INI JUAN YANG KASI KOMEN
// CLASS ANGGOTA TURUNAN PENGGUNA UNTUK USER BIASA / PEMINJAM

package com.SistemManajemenPerpustakaan.MVC.Models.User;

public class Anggota extends Pengguna {

    // ATRIBUT KHUSUS ANGGOTA: STATUS TERLAMBAT, MAKS PINJAM, DAN JUMLAH PINJAM
    private Boolean terlambat;
    private int maksimalPinjam, jumlahPinjam;

    // CONSTRUCTOR UNTUK MENGISI DATA ANGGOTA LENGKAP
    public Anggota(String id, String jenis, String nama, String alamat, String nomorHP, String username, String password, Boolean terlambat, int maksimalPinjam, int jumlahPinjam) {
        super(id, jenis, nama, alamat, nomorHP, username, password);
        this.terlambat = terlambat;
        this.maksimalPinjam = maksimalPinjam;
        this.jumlahPinjam = jumlahPinjam;
    }

    // METHOD UNTUK UPDATE ATRIBUT ANGGOTA SECARA DINAMIS
    // DIPAKAI SAAT EDIT DATA DI CONTROLLER
    @Override
    public void updateAttribute(String atribut, Object nilaiBaru) {
        switch (atribut.toUpperCase()) {
            case "ID": setId((String) nilaiBaru); break;
            case "JENIS": setJenis((String) nilaiBaru); break; // ✅ SUDAH DIPERBAIKI DARI "PENGARANG"
            case "NAMA": setNama((String) nilaiBaru); break;
            case "ALAMAT": setAlamat((String) nilaiBaru); break;
            case "NOMORHP": setNomorHP((String) nilaiBaru); break;
            case "USERNAME": setUsername((String) nilaiBaru); break;
            case "PASSWORD": setPassword((String) nilaiBaru); break;
            case "TERLAMBAT": setTerlambat((Boolean) nilaiBaru); break;
            case "MAKSIMALPINJAM": setMaksimalPinjam((int) nilaiBaru); break;
            case "JUMLAHPINJAM": setJumlahPinjam((int) nilaiBaru); break;
            default: System.out.println("Atribut tidak dikenali");
        }
    }

    // GETTER UNTUK AMBIL DATA KHUSUS ANGGOTA
    public boolean getTerlambat() { return terlambat; }
    public int getMaksimalPinjam() { return maksimalPinjam; }
    public int getJumlahPinjam() { return jumlahPinjam; }

    // SETTER UNTUK UBAH DATA KHUSUS ANGGOTA
    public void setTerlambat(boolean terlambat) { this.terlambat = terlambat; }
    public void setMaksimalPinjam(int maksimalPinjam) { this.maksimalPinjam = maksimalPinjam; }

    // ✅ PERBAIKAN: LANGSUNG SET NILAI, BUKAN DITAMBAHIN
    public void setJumlahPinjam(int jumlahPinjam) { this.jumlahPinjam = jumlahPinjam; }
}
