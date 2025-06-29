package com.SistemManajemenPerpustakaan.Repositories;

import com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman;
import com.SistemManajemenPerpustakaan.Utils.CsvUtil;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PeminjamanRepository {

    private static final String FILE_PATH = "peminjaman.csv";
    private static final String[] HEADERS = {"id", "idAnggota", "kodeBuku", "tanggalPinjam", "tanggalKembali", "deadline", "status"};

    static {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
                CsvUtil.writeData(FILE_PATH, HEADERS, new ArrayList<>());
            }
        } catch (IOException e) {
            throw new RuntimeException("Gagal menginisialisasi file CSV Peminjaman: " + e.getMessage(), e);
        }
    }

    private static void simpanSemua(List<Peminjaman> peminjamans) {
        List<List<Object>> dataRows = new ArrayList<>();
        for (Peminjaman p : peminjamans) {
            List<Object> row = new ArrayList<>();
            row.add(p.getId());
            row.add(p.getIdAnggota());
            row.add(p.getKodebuku());
            row.add(p.getTanggalPinjam());
            row.add(p.getTanggalKembali());
            row.add(p.getDeadline());
            row.add(p.getStatus());
            dataRows.add(row);
        }

        try {
            CsvUtil.writeData(FILE_PATH, HEADERS, dataRows);
        } catch (FileSystemException e) {
            // ### PERBAIKAN DI SINI ###
            System.err.println("\n[GAGAL] Gagal menyimpan data peminjaman ke CSV: " + FILE_PATH + ". File ini sedang digunakan oleh program lain (misalnya Excel).");
            System.err.println("Harap tutup program tersebut dan coba lagi.");
        } catch (IOException e) {
            throw new RuntimeException("Gagal menyimpan data peminjaman ke CSV karena error I/O tak terduga: " + e.getMessage(), e);
        }
    }

    // ... sisa method tidak berubah ...
    private static List<Peminjaman> ambilSemuaMutable() {
        try {
            List<CSVRecord> records = CsvUtil.readData(FILE_PATH, HEADERS);
            return records.stream().map(record -> new Peminjaman(
                    record.get("id"),
                    record.get("idAnggota"),
                    record.get("kodeBuku"),
                    record.get("tanggalPinjam"),
                    record.get("tanggalKembali"),
                    record.get("deadline"),
                    record.get("status")
            )).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Gagal membaca data peminjaman dari CSV: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void tambah(Peminjaman peminjaman) {
        List<Peminjaman> peminjamans = ambilSemuaMutable();
        peminjamans.add(peminjaman);
        simpanSemua(peminjamans);
    }

    public static Peminjaman ambilPeminjamanById(String idPeminjaman) {
        return ambilSemuaMutable().stream()
                .filter(p -> p.getId().equals(idPeminjaman))
                .findFirst().orElse(null);
    }

    public static List<Peminjaman> ambilSemua() {
        return List.copyOf(ambilSemuaMutable());
    }

    public static void updatePeminjaman(String idPeminjaman, Peminjaman peminjamanBaru) {
        List<Peminjaman> peminjamans = ambilSemuaMutable();
        boolean updated = false;
        for (int i = 0; i < peminjamans.size(); i++) {
            if (peminjamans.get(i).getId().equals(idPeminjaman)) {
                peminjamanBaru.setId(idPeminjaman);
                peminjamans.set(i, peminjamanBaru);
                updated = true;
                break;
            }
        }
        if (updated) {
            simpanSemua(peminjamans);
        } else {
            throw new IllegalArgumentException("Peminjaman dengan ID " + idPeminjaman + " tidak ditemukan!");
        }
    }

    public static void hapus(String kodePeminjaman) {
        List<Peminjaman> peminjamans = ambilSemuaMutable();
        if (peminjamans.removeIf(p -> p.getId().equals(kodePeminjaman))) {
            simpanSemua(peminjamans);
        }
    }

    public static int jumlahPeminjaman() {
        return ambilSemuaMutable().size();
    }

    public static boolean updateAtribut(String id, String atribut, Object nilaiBaru) {
        Peminjaman peminjaman = ambilPeminjamanById(id);
        if (peminjaman != null) {
            peminjaman.updateAttribute(atribut, nilaiBaru);
            updatePeminjaman(id, peminjaman);
            return true;
        }
        return false;
    }
}