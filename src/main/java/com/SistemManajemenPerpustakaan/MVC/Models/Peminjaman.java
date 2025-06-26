package com.SistemManajemenPerpustakaan.MVC.Models;

public class Peminjaman {
    //ATTRIBUTE
    private String id;
    private String idAnggota ;
    private String kodeBuku;
    private String tanggalPinjam;
    private String tanggalKembali;
    private String deadline;
    private String status;

    //CONSTRUCTOR
    public Peminjaman (String id, String idAnggota, String idKodeBuku,  String tanggalPinjam, String tanggalKembali, String deadline, String status) {
        this.id = id ;
        this.idAnggota = idAnggota;
        this.kodeBuku = idKodeBuku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.deadline = deadline;
        this.status = status;
    }
    //GETTER
    public String getId() {
        return id;
    }
    public String getIdAnggota() {
        return idAnggota;
    }
    public String getKodebuku() {
        return kodeBuku;
    }
    public String getTanggalPinjam() {
        return tanggalPinjam;
    }
    public String getTanggalKembali() {
        return tanggalKembali;
    }
    public String getDeadline() {
        return deadline;
    }
    public String getStatus() {
        return status;
    }

    //SETTER
    public void setId(String id) {
        this.id = id;
    }
    public void setIdAnggota(String idAnggota) {
        this.idAnggota = idAnggota;
    }
    public void setKodeBuku(String kodeBuku) {
        this.kodeBuku = kodeBuku;
    }
    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }
    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }
    public void setDeadline(String deadline) {
        this.tanggalKembali = deadline;
    }
    public void setStatus(String statusPengembalian) {
        this.status = statusPengembalian;
    }
}