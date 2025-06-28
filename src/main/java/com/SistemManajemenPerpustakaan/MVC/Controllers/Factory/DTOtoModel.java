package com.SistemManajemenPerpustakaan.MVC.Controllers.Factory;

import com.SistemManajemenPerpustakaan.DTOs.BukuDTO;
import com.SistemManajemenPerpustakaan.DTOs.PeminjamanDTO;
import com.SistemManajemenPerpustakaan.DTOs.PenggunaDTO;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.*;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Admin;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Anggota;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;

//CLASS UNTUK KONVERSI DTO KE MODEL
public class DTOtoModel {

    //KONVERSI DTO KE OBJEK BUKU
    public static Buku toBuku(BukuDTO dto){
        switch (dto.jenis){
            case JURNAL:
                return new Jurnal(
                        dto.kode, dto.judul, dto.pengarang,
                        dto.tahunTerbit, dto.tersedia, "Jurnal",
                        dto.institusiJURNAL, dto.terindeksSintaJURNAL
                );
            case MAJALAH:
                return new Majalah(
                        dto.kode, dto.judul, dto.pengarang,
                        dto.tahunTerbit, dto.tersedia, "Majalah",
                        dto.topikMAJALAH
                );
            case NOVEL:
                return new Novel(
                        dto.kode, dto.judul, dto.pengarang,
                        dto.tahunTerbit, dto.tersedia, "Novel",
                        dto.genreNOVEL
                );
            case TEXTBOOK:
                return new TextBook(
                        dto.kode, dto.judul, dto.pengarang,
                        dto.tahunTerbit, dto.tersedia, "Textbook",
                        dto.bidangIlmuTEXTBOOK
                );
        }
        return null;
    }

    //KONVERSI DTO KE OBJEK PENGGUNA
    public static Pengguna toPengguna(PenggunaDTO dto){
        switch (dto.jenis){
            case ADMIN:
                return new Admin(
                        dto.id, "Admin", dto.nama,
                        dto.alamat, dto.nomorHP, dto.username,
                        dto.password, dto.nipADMIN
                );
            case ANGGOTA:
                return new Anggota(
                        dto.id, "Anggota", dto.nama,
                        dto.alamat, dto.nomorHP, dto.username,
                        dto.password, dto.terlambatANGGOTA,
                        dto.maksimalPinjamANGGOTA, dto.jumlahPinjamANGGOTA
                );
        }
        return null;
    }

    //KONVERSI DTO KE OBJEK PEMINJAMAN
    public static com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman toPeminjaman(PeminjamanDTO dto){
        PeminjamanDTO a = (PeminjamanDTO) dto;
        return new com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman(
                a.id, a.idAnggota, a.kodeBuku,
                a.tanggalPinjam, a.tanggalKembali,
                a.deadline, a.status
        );
    }
}
