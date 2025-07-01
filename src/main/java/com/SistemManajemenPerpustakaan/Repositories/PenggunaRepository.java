// INI JUAN YANG KOMEN
package com.SistemManajemenPerpustakaan.Repositories;

import com.SistemManajemenPerpustakaan.Enums.JenisPengguna;
import com.SistemManajemenPerpustakaan.MVC.Models.User.*;
import com.SistemManajemenPerpustakaan.Utils.CsvUtil;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;

public class PenggunaRepository {

    private static final String FILE_PATH = "pengguna.csv";
    private static final String[] HEADERS = {
            "id", "jenis", "nama", "alamat", "nomorHP", "username", "password",
            "nipADMIN", "terlambatANGGOTA", "maksimalPinjamANGGOTA", "jumlahPinjamANGGOTA"
    };

    static {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
                CsvUtil.writeData(FILE_PATH, HEADERS, new ArrayList<>());
            }
        } catch (IOException e) {
            throw new RuntimeException("Gagal menginisialisasi file CSV Pengguna: " + e.getMessage(), e);
        }
    }

    // FUNGSI UNTUK MENYIMPAN SEMUA DATA PENGGUNA KE FILE CSV.
    // MENGKONVERSI DAFTAR OBJEK PENGGUNA MENJADI BARIS DATA SESUAI JENIS PENGGUNA.
    private static void simpanSemua(List<Pengguna> penggunas) {
        List<List<Object>> dataRows = new ArrayList<>();
        for (Pengguna p : penggunas) {
            List<Object> row = new ArrayList<>();
            if (p instanceof Admin) {
                Admin admin = (Admin) p;
                row.addAll(List.of(
                        admin.getId(), admin.getJenis(), admin.getNama(), admin.getAlamat(), admin.getNomorHP(),
                        admin.getUsername(), admin.getPassword(), admin.getNip(),
                        "", "", ""
                ));
            } else if (p instanceof Anggota) {
                Anggota anggota = (Anggota) p;
                row.addAll(List.of(
                        anggota.getId(), anggota.getJenis(), anggota.getNama(), anggota.getAlamat(), anggota.getNomorHP(),
                        anggota.getUsername(), anggota.getPassword(),
                        "",
                        anggota.getTerlambat(), anggota.getMaksimalPinjam(), anggota.getJumlahPinjam()
                ));
            }
            dataRows.add(row);
        }

        try {
            CsvUtil.writeData(FILE_PATH, HEADERS, dataRows);
        } catch (FileSystemException e) {
            // ### PERBAIKAN DI SINI ###
            System.err.println("\n[GAGAL] Gagal menyimpan data pengguna ke CSV: " + FILE_PATH + ". File ini sedang digunakan oleh program lain (misalnya Excel).");
            System.err.println("Harap tutup program tersebut dan coba lagi.");
        } catch (IOException e) {
            throw new RuntimeException("Gagal menyimpan data pengguna ke CSV karena error I/O tak terduga: " + e.getMessage(), e);
        }
    }

    // FUNGSI UNTUK MENGAMBIL SEMUA DATA PENGGUNA DARI FILE CSV.
    // MEMBACA DAN MENGUBAH CATATAN CSV MENJADI OBJEK PENGGUNA SESUAI JENISNYA.
    private static List<Pengguna> ambilSemuaMutable() {
        try {
            List<CSVRecord> records = CsvUtil.readData(FILE_PATH, HEADERS);
            List<Pengguna> penggunas = new ArrayList<>();
            for (CSVRecord record : records) {
                JenisPengguna jenis = JenisPengguna.valueOf(record.get("jenis").toUpperCase());
                String id = record.get("id");
                String nama = record.get("nama");
                String alamat = record.get("alamat");
                String nomorHP = record.get("nomorHP");
                String username = record.get("username");
                String password = record.get("password");

                if (jenis == JenisPengguna.ADMIN) {
                    String nip = record.get("nipADMIN");
                    Admin admin = new Admin(id, jenis.toString(), nama, alamat, nomorHP, username, password, nip);
                    penggunas.add(admin);
                } else if (jenis == JenisPengguna.ANGGOTA) {
                    boolean terlambat = Boolean.parseBoolean(record.get("terlambatANGGOTA"));
                    int maksimalPinjam = Integer.parseInt(record.get("maksimalPinjamANGGOTA"));
                    int jumlahPinjam = Integer.parseInt(record.get("jumlahPinjamANGGOTA"));
                    Anggota anggota = new Anggota(id, jenis.toString(), nama, alamat, nomorHP, username, password, terlambat, maksimalPinjam, jumlahPinjam);
                    penggunas.add(anggota);
                }
            }
            return penggunas;
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Gagal membaca atau mem-parsing data dari CSV, mengembalikan list kosong: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // FUNGSI UNTUK MENAMBAHKAN PENGGUNA BARU KE REPOSITORI.
    // MENAMBAHKAN OBJEK PENGGUNA KE DAFTAR DAN MENYIMPAN PERUBAHAN.
    public static void tambah(Pengguna pengguna) {
        List<Pengguna> penggunas = ambilSemuaMutable();
        penggunas.add(pengguna);
        simpanSemua(penggunas);
    }

    // FUNGSI UNTUK MENGAMBIL PENGGUNA BERDASARKAN ID.
    // MENCARI OBJEK PENGGUNA DALAM DAFTAR BERDASARKAN ID UNIKNYA.
    public static Pengguna ambilPenggunaById(String idPengguna) {
        return ambilSemuaMutable().stream()
                .filter(p -> p.getId().equals(idPengguna))
                .findFirst()
                .orElse(null);
    }

    // FUNGSI UNTUK MENGAMBIL SEMUA PENGGUNA SEBAGAI DAFTAR YANG TIDAK DAPAT DIUBAH.
    // MENGEMBALIKAN SALINAN DAFTAR PENGGUNA UNTUK MENCEGAH MODIFIKASI LANGSUNG.
    public static List<Pengguna> ambilSemua() {
        return List.copyOf(ambilSemuaMutable());
    }

    // FUNGSI UNTUK MEMPERBARUI DATA PENGGUNA YANG ADA.
    // MENGGANTI OBJEK PENGGUNA DENGAN ID TERTENTU DENGAN DATA BARU.
    public static void updatePengguna(String idPengguna, Pengguna penggunaBaru) {
        List<Pengguna> penggunas = ambilSemuaMutable();
        boolean updated = false;
        for (int i = 0; i < penggunas.size(); i++) {
            if (penggunas.get(i).getId().equals(idPengguna)) {
                penggunaBaru.setId(idPengguna);
                penggunas.set(i, penggunaBaru);
                updated = true;
                break;
            }
        }
        if (updated) {
            simpanSemua(penggunas);
        } else {
            throw new IllegalArgumentException("Pengguna dengan ID " + idPengguna + " tidak ditemukan!");
        }
    }

    // FUNGSI UNTUK MENGHAPUS PENGGUNA DARI REPOSITORI.
    // MENGHAPUS OBJEK PENGGUNA BERDASARKAN KODE DAN MENYIMPAN PERUBAHAN.
    public static void hapus(String kodePengguna) {
        List<Pengguna> penggunas = ambilSemuaMutable();
        boolean removed = penggunas.removeIf(pengguna -> pengguna.getId().equals(kodePengguna));
        if (removed) {
            simpanSemua(penggunas);
        }
    }

    // FUNGSI UNTUK MEMPERBARUI ATRIBUT SPESIFIK DARI PENGGUNA.
    // MENEMUKAN PENGGUNA BERDASARKAN ID DAN MEMPERBARUI ATRIBUT YANG DIMINTA.
    public static boolean updateAtribut(String id, String atribut, Object nilaiBaru) {
        Pengguna pengguna = ambilPenggunaById(id);
        if (pengguna != null) {
            pengguna.updateAttribute(atribut, nilaiBaru);
            updatePengguna(id, pengguna);
            return true;
        }
        return false;
    }
}