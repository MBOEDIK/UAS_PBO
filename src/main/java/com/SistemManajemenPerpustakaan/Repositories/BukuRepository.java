// INI JUAN YANG KOMEN
package com.SistemManajemenPerpustakaan.Repositories;

import com.SistemManajemenPerpustakaan.Enums.JenisBuku;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.*;
import com.SistemManajemenPerpustakaan.Utils.CsvUtil;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BukuRepository {

    private static final String FILE_PATH = "buku.csv";
    private static final String[] HEADERS = {
            "kode", "judul", "pengarang", "tahunTerbit", "tersedia", "jenis",
            "institusiJURNAL", "terindeksSintaJURNAL", "topikMAJALAH", "genreNOVEL", "bidangIlmuTEXTBOOK"
    };

    static {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
                CsvUtil.writeData(FILE_PATH, HEADERS, new ArrayList<>());
            }
        } catch (IOException e) {
            throw new RuntimeException("Gagal menginisialisasi file CSV Buku: " + e.getMessage(), e);
        }
    }

    // FUNGSI UNTUK MENYIMPAN SEMUA DATA BUKU KE FILE CSV.
    // MENGUBAH DAFTAR OBJEK BUKU MENJADI BARIS DATA UNTUK DISIMPAN.
    private static void simpanSemua(List<Buku> bukus) {
        List<List<Object>> dataRows = new ArrayList<>();
        for (Buku b : bukus) {
            String institusi = (b instanceof Jurnal) ? ((Jurnal) b).getInstitusi() : "";
            String sinta = (b instanceof Jurnal) ? ((Jurnal) b).getTerindeksSinta() : "";
            String topik = (b instanceof Majalah) ? ((Majalah) b).getTopik() : "";
            String genre = (b instanceof Novel) ? ((Novel) b).getGenre() : "";
            String bidangIlmu = (b instanceof TextBook) ? ((TextBook) b).getBidangIlmu() : "";

            List<Object> row = List.of(
                    b.getKode(), b.getJudul(), b.getPengarang(), b.getTahunTerbit(), b.getTersedia(), b.getJenis(),
                    institusi, sinta, topik, genre, bidangIlmu
            );
            dataRows.add(row);
        }

        try {
            CsvUtil.writeData(FILE_PATH, HEADERS, dataRows);
        } catch (FileSystemException e) {
            // ### PERBAIKAN DI SINI ###
            // Menangkap error spesifik jika file sedang digunakan
            System.err.println("\n[GAGAL] Gagal menyimpan data buku ke CSV: " + FILE_PATH + ". File ini sedang digunakan oleh program lain (misalnya Excel).");
            System.err.println("Harap tutup program tersebut dan coba lagi.");
        } catch (IOException e) {
            // Menangkap error I/O lainnya
            throw new RuntimeException("Gagal menyimpan data buku ke CSV karena error I/O tak terduga: " + e.getMessage(), e);
        }
    }

    // FUNGSI UNTUK MENGAMBIL SEMUA DATA BUKU DARI FILE CSV.
    // MEMBACA DAN MENGUBAH CATATAN CSV MENJADI OBJEK BUKU YANG DAPAT DIMODIFIKASI.
    private static List<Buku> ambilSemuaMutable() {
        try {
            List<CSVRecord> records = CsvUtil.readData(FILE_PATH, HEADERS);
            return records.stream().map(record -> {
                        String kode = record.get("kode");
                        String judul = record.get("judul");
                        String pengarang = record.get("pengarang");
                        String tahunTerbit = record.get("tahunTerbit");
                        boolean tersedia = Boolean.parseBoolean(record.get("tersedia"));
                        JenisBuku jenisEnum = JenisBuku.valueOf(record.get("jenis").toUpperCase());
                        String jenisString = jenisEnum.toString();

                        Buku buku = null;
                        switch (jenisEnum) {
                            case JURNAL:
                                buku = new Jurnal(kode, judul, pengarang, tahunTerbit, tersedia, jenisString, record.get("institusiJURNAL"), record.get("terindeksSintaJURNAL"));
                                break;
                            case MAJALAH:
                                buku = new Majalah(kode, judul, pengarang, tahunTerbit, tersedia, jenisString, record.get("topikMAJALAH"));
                                break;
                            case NOVEL:
                                buku = new Novel(kode, judul, pengarang, tahunTerbit, tersedia, jenisString, record.get("genreNOVEL"));
                                break;
                            case TEXTBOOK:
                                buku = new TextBook(kode, judul, pengarang, tahunTerbit, tersedia, jenisString, record.get("bidangIlmuTEXTBOOK"));
                                break;
                        }
                        return buku;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Gagal membaca atau mem-parsing data buku dari CSV: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // FUNGSI UNTUK MENAMBAHKAN BUKU BARU KE REPOSITORI.
    // MENAMBAHKAN BUKU KE DAFTAR DAN MENYIMPAN PERUBAHAN.
    public static void tambah(Buku buku) {
        List<Buku> bukus = ambilSemuaMutable();
        bukus.add(buku);
        simpanSemua(bukus);
    }

    // FUNGSI UNTUK MENGAMBIL BUKU BERDASARKAN KODE ID.
    // MENCARI BUKU DALAM DAFTAR BERDASARKAN KODE UNIKNYA.
    public static Buku ambilBukuById(String kodeBuku) {
        return ambilSemuaMutable().stream()
                .filter(b -> b.getKode().equals(kodeBuku))
                .findFirst().orElse(null);
    }

    // FUNGSI UNTUK MENGAMBIL SEMUA BUKU SEBAGAI DAFTAR YANG TIDAK DAPAT DIUBAH.
    // MENGEMBALIKAN SALINAN DAFTAR BUKU UNTUK MENCEGAH MODIFIKASI LANGSUNG.
    public static List<Buku> ambilSemua() {
        return List.copyOf(ambilSemuaMutable());
    }

    // FUNGSI UNTUK MEMPERBARUI DATA BUKU YANG ADA.
    // MENGGANTI BUKU DENGAN KODE TERTENTU DENGAN DATA BUKU BARU.
    public static void updateBuku(String kodeBuku, Buku bukuBaru) {
        List<Buku> bukus = ambilSemuaMutable();
        boolean updated = false;
        for (int i = 0; i < bukus.size(); i++) {
            if (bukus.get(i).getKode().equals(kodeBuku)) {
                bukuBaru.setKode(kodeBuku);
                bukus.set(i, bukuBaru);
                updated = true;
                break;
            }
        }
        if (updated) {
            simpanSemua(bukus);
        } else {
            throw new IllegalArgumentException("Buku dengan kode " + kodeBuku + " tidak ditemukan!");
        }
    }

    // FUNGSI UNTUK MENGHAPUS BUKU DARI REPOSITORI.
    // MENGHAPUS BUKU BERDASARKAN KODE DAN MENYIMPAN PERUBAHAN.
    public static void hapusBuku(String kodeBuku) {
        List<Buku> bukus = ambilSemuaMutable();
        if (bukus.removeIf(buku -> buku.getKode().equals(kodeBuku))) {
            simpanSemua(bukus);
        }
    }

    // FUNGSI UNTUK MEMPERBARUI ATRIBUT SPESIFIK DARI BUKU.
    // MENEMUKAN BUKU BERDASARKAN ID DAN MEMPERBARUI ATRIBUT YANG DIMINTA.
    public static boolean updateAtribut(String id, String atribut, Object nilaiBaru) {
        Buku buku = ambilBukuById(id);
        if (buku != null) {
            buku.updateAttribute(atribut, nilaiBaru);
            updateBuku(id, buku);
            return true;
        }
        return false;
    }
}