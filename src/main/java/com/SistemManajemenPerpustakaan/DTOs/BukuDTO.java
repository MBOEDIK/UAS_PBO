package com.SistemManajemenPerpustakaan.DTOs;

import com.SistemManajemenPerpustakaan.Enums.JenisBuku;

public class BukuDTO {
    //ATTRIBUTE UMUM
    public String kode;
    public String judul;
    public String pengarang;
    public String tahunTerbit;
    public Boolean tersedia;
    public JenisBuku jenis;

    //ATTRIBUTE SPESIFIK DARI JURNAL
    public String institusiJURNAL;
    public String terindeksSintaJURNAL;

    //ATTRIBUTE SPESIFIK DARI MAJALAH
    public String topikMAJALAH;

    //ATTRIBUTE SPESIFIK DARI NOVEL
    public String genreNOVEL;

    //ATTRIBUTE SPESIFIK DARI TEXTBOOK
    public String bidangIlmuTEXTBOOK;
}


