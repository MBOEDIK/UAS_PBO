package Tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Generate ID dengan format: TAHUNBULAN-001 (202406-001)
public class IdGenerator {
    private static int counterIdPengguna = 1;
    private static int counterIdBuku = 1;

    public static String idPengguna() {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        String id = datePart + "-" + String.format("%03d", counterIdPengguna);
        counterIdPengguna++;
        return id;
    }

    public static String idBuku() {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        String id = datePart + "-" + String.format("%03d", counterIdPengguna);
        counterIdPengguna++;
        return id;
    }
}
