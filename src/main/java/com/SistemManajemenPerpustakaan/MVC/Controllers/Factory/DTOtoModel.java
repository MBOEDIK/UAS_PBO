// INI JUAN YANG KASI KOMEN
// BIAR NGERTI FUNGSI TIAP KONVERSI DTO KE MODEL

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

    // KONVERSI DARI BukuDTO KE OBJEK Buku ASLI
    // MEMBEDAKAN TIAP TIPE (JURNAL, MAJALAH, DLL)
    public static Buku toBuku(BukuDTO dto) {
        String jenisBukuStr = dto.jenis.toString();

        switch (dto.jenis) {
            case JURNAL:
                return new Jurnal(dto.kode, dto.judul, dto.pengarang, dto.tahunTerbit,
                        dto.tersedia, jenisBukuStr, dto.institusiJURNAL, dto.terindeksSintaJURNAL);
            case MAJALAH:
                return new Majalah(dto.kode, dto.judul, dto.pengarang, dto.tahunTerbit,
                        dto.tersedia, jenisBukuStr, dto.topikMAJALAH);
            case NOVEL:
                return new Novel(dto.kode, dto.judul, dto.pengarang, dto.tahunTerbit,
                        dto.tersedia, jenisBukuStr, dto.genreNOVEL);
            case TEXTBOOK:
                return new TextBook(dto.kode, dto.judul, dto.pengarang, dto.tahunTerbit,
                        dto.tersedia, jenisBukuStr, dto.bidangIlmuTEXTBOOK);
        }
        return null;
    }

    // KONVERSI PenggunaDTO KE OBJEK Pengguna
    // BISA JADI ADMIN ATAU ANGGOTA
    public static Pengguna toPengguna(PenggunaDTO dto) {
        String jenisPenggunaStr = dto.jenis.toString();

        switch (dto.jenis) {
            case ADMIN:
                return new Admin(dto.id, jenisPenggunaStr, dto.nama, dto.alamat,
                        dto.nomorHP, dto.username, dto.password, dto.nipADMIN);
            case ANGGOTA:
                return new Anggota(dto.id, jenisPenggunaStr, dto.nama, dto.alamat,
                        dto.nomorHP, dto.username, dto.password,
                        dto.terlambatANGGOTA, dto.maksimalPinjamANGGOTA, dto.jumlahPinjamANGGOTA);
        }
        return null;
    }

    // KONVERSI PeminjamanDTO KE OBJEK PEMINJAMAN
    // LANGSUNG RETURN OBJEK DARI DTO
    public static Peminjaman toPeminjaman(PeminjamanDTO dto) {
        return new Peminjaman(dto.id, dto.idAnggota, dto.kodeBuku,
                dto.tanggalPinjam, dto.tanggalKembali, dto.deadline, dto.status);
    }
}
