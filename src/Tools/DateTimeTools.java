package Tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeTools {
    // Atribut untuk menyimpan tanggal sebagai String
    private static String tanggalHariIni;

    // Method untuk menyimpan tanggal hari ini ke atribut
    public static void simpanTanggalHariIni() {
        // Ambil tanggal hari ini
        LocalDate today = LocalDate.now();

        // Format tanggal (contoh: "2023-11-15" atau "15-Nov-2023")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        tanggalHariIni = today.format(formatter);
    }

    // Getter untuk mengambil nilai tanggal
    public static String getTanggalHariIni() {
        return tanggalHariIni;
    }
}
