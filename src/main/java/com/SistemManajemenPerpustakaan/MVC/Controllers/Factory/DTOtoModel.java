package com.SistemManajemenPerpustakaan.MVC.Controllers.Factory;

import com.SistemManajemenPerpustakaan.DTOs.BukuDTO;
import com.SistemManajemenPerpustakaan.DTOs.PeminjamanDTO;
import com.SistemManajemenPerpustakaan.DTOs.PenggunaDTO;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.*;
import com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Admin;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Anggota;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;

public class DTOtoModel {

    public static Buku toBuku(BukuDTO dto) {
        // Menggunakan dto.jenis.toString() untuk konsistensi (menghasilkan string huruf besar)
        String jenisBukuStr = dto.jenis.toString();

        switch (dto.jenis) {
            case JURNAL:
                return new Jurnal(dto.kode, dto.judul, dto.pengarang, dto.tahunTerbit, dto.tersedia, jenisBukuStr, dto.institusiJURNAL, dto.terindeksSintaJURNAL);
            case MAJALAH:
                return new Majalah(dto.kode, dto.judul, dto.pengarang, dto.tahunTerbit, dto.tersedia, jenisBukuStr, dto.topikMAJALAH);
            case NOVEL:
                return new Novel(dto.kode, dto.judul, dto.pengarang, dto.tahunTerbit, dto.tersedia, jenisBukuStr, dto.genreNOVEL);
            case TEXTBOOK:
                return new TextBook(dto.kode, dto.judul, dto.pengarang, dto.tahunTerbit, dto.tersedia, jenisBukuStr, dto.bidangIlmuTEXTBOOK);
        }
        return null;
    }

    public static Pengguna toPengguna(PenggunaDTO dto) {
        // Menggunakan dto.jenis.toString() untuk konsistensi (menghasilkan string huruf besar)
        String jenisPenggunaStr = dto.jenis.toString();

        switch (dto.jenis) {
            case ADMIN:
                return new Admin(dto.id, jenisPenggunaStr, dto.nama, dto.alamat, dto.nomorHP, dto.username, dto.password, dto.nipADMIN);
            case ANGGOTA:
                return new Anggota(dto.id, jenisPenggunaStr, dto.nama, dto.alamat, dto.nomorHP, dto.username, dto.password, dto.terlambatANGGOTA, dto.maksimalPinjamANGGOTA, dto.jumlahPinjamANGGOTA);
        }
        return null;
    }

    public static Peminjaman toPeminjaman(PeminjamanDTO dto) {
        return new Peminjaman(dto.id, dto.idAnggota, dto.kodeBuku, dto.tanggalPinjam, dto.tanggalKembali, dto.deadline, dto.status);
    }
}