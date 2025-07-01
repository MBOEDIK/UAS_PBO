package com.SistemManajemenPerpustakaan.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeTools {
    // MENGAMBIL TANGGAL HARI INI.
    // MENGEMBALIKAN TANGGAL SAAT INI DALAM FORMAT STRING.
    public static String getTanggalHariIni() {
        // Ambil tanggal hari ini
        LocalDate today = LocalDate.now();

        // Format tanggal (contoh: "2023-11-15" atau "15-Nov-2023")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        return today.format(formatter);
    }

    // MEMBUAT TANGGAL DEADLINE.
    // MENENTUKAN DEADLINE BERDASARKAN JUMLAH HARI DARI SEKARANG.
    public static String buatDeadline(int xHariLagi) {
        if (xHariLagi < 0) {
            throw new IllegalArgumentException("Jumlah hari tidak boleh negatif");
        }

        LocalDate today = LocalDate.now();
        LocalDate deadlineDate = today.plusDays(xHariLagi);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return deadlineDate.format(formatter);
    }

    // MENGHITUNG SISA HARI MENUJU DEADLINE.
    // MENGEMBALIKAN JUMLAH HARI TERSISA SEBELUM DEADLINE.
    public static int sisaHariMenujuDeadline(String deadlineDateString) {
        // Parse tanggal deadline
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate deadlineDate = LocalDate.parse(deadlineDateString, formatter);

        // Hitung selisih dengan hari ini
        LocalDate today = LocalDate.now();
        return (int) ChronoUnit.DAYS.between(today, deadlineDate);
    }

}