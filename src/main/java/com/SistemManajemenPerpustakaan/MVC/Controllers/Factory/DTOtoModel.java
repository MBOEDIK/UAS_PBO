package com.SistemManajemenPerpustakaan.MVC.Controllers.Factory;

import com.SistemManajemenPerpustakaan.DTOs.Books.*;
import com.SistemManajemenPerpustakaan.DTOs.PeminjamanDTO;
import com.SistemManajemenPerpustakaan.DTOs.Users.AdminDTO;
import com.SistemManajemenPerpustakaan.DTOs.Users.AnggotaDTO;
import com.SistemManajemenPerpustakaan.DTOs.Users.PenggunaDTO;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.*;
import com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Admin;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Anggota;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;

//CLASS UNTUK KONVERSI DTO KE MODEL
public class DTOtoModel {

    //KONVERSI DTO KE OBJEK BUKU
    public static Buku toBuku(BukuDTO dto){
        switch (dto.jenis){
            case JURNAL:
                JurnalDTO a = (JurnalDTO) dto;
                return new Jurnal(
                        a.kode, a.judul, a.pengarang,
                        a.tahunTerbit, a.tersedia, "Jurnal Ilmiah",
                        a.institusi, a.terindeksSinta
                );
            case MAJALAH:
                MajalahDTO b = (MajalahDTO) dto;
                return new Majalah(
                        b.kode, b.judul, b.pengarang,
                        b.tahunTerbit, b.tersedia, "Majalah",
                        b.topik
                );
            case NOVEL:
                NovelDTO c = (NovelDTO) dto;
                return new Novel(
                        c.kode, c.judul, c.pengarang,
                        c.tahunTerbit, c.tersedia, "Novel",
                        c.genre
                );
            case TEXTBOOK:
                TextbookDTO d = (TextbookDTO) dto;
                return new TextBook(
                        d.kode, d.judul, d.pengarang,
                        d.tahunTerbit, d.tersedia, "Textbook",
                        d.bidangIlmu
                );
        }
        return null;
    }

    //KONVERSI DTO KE OBJEK PENGGUNA
    public static Pengguna toPengguna(PenggunaDTO dto){
        switch (dto.jenis){
            case ADMIN:
                AdminDTO a = (AdminDTO) dto;
                return new Admin(
                        a.id, "Admin", a.nama,
                        a.alamat, a.nomorHP, a.username,
                        a.password, a.nip
                );
            case ANGGOTA:
                AnggotaDTO b = (AnggotaDTO) dto;
                return new Anggota(
                        b.id, "Anggota", b.nama,
                        b.alamat, b.nomorHP, b.username,
                        b.password, b.terlambatMengembalikan,
                        b.maksimalPinjamBuku, b.jumlahPinjamBuku
                );
        }
        return null;
    }

    //KONVERSI DTO KE OBJEK PEMINJAMAN
    public static Peminjaman toPeminjaman(PeminjamanDTO dto){
        PeminjamanDTO a = (PeminjamanDTO) dto;
        return new Peminjaman(
                a.id, a.idAnggota, a.kodeBuku,
                a.tanggalPinjam, a.tanggalKembali,
                a.deadline, a.status
        );
    }
}
