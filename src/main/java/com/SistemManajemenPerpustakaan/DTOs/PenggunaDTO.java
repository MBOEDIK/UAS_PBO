package com.SistemManajemenPerpustakaan.DTOs;

import com.SistemManajemenPerpustakaan.Enums.JenisPengguna;

public class PenggunaDTO {

    //ATTRIBUTE UMUM
    public String id;
    public JenisPengguna jenis;
    public String nama;
    public String alamat;
    public String nomorHP;
    public String username;
    public String password;

    //ATTRIBUTE ADMIN
    public String nipADMIN;

    //ATTRIBUTE ANGGOTA
    public Boolean terlambatANGGOTA;
    public int maksimalPinjamANGGOTA, jumlahPinjamANGGOTA;
}
