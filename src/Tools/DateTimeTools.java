package Tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeTools {
    // Method untuk menyimpan tanggal hari ini ke atribut
    public static String getTanggalHariIni() {
        // Ambil tanggal hari ini
        LocalDate today = LocalDate.now();

        // Format tanggal (contoh: "2023-11-15" atau "15-Nov-2023")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        return today.format(formatter);
    }

    public static String buatDeadline(int xHariLagi) {
        if (xHariLagi < 0) {
            throw new IllegalArgumentException("Jumlah hari tidak boleh negatif");
        }

        LocalDate today = LocalDate.now();
        LocalDate deadlineDate = today.plusDays(xHariLagi);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return deadlineDate.format(formatter);
    }

}
