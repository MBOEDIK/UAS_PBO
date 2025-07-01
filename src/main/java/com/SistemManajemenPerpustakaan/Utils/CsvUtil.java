// INI JUAN YANG KOMEN
package com.SistemManajemenPerpustakaan.Utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvUtil {

    /**
     * Membaca semua record dari sebuah file CSV.
     * @param filePath Path ke file CSV.
     * @param headers Header dari file CSV.
     * @return List dari CSVRecord.
     */
    // FUNGSI UNTUK MEMBACA SEMUA DATA DARI FILE CSV.
    // MENGEMBALIKAN DAFTAR RECORD CSV SESUAI DENGAN HEADER YANG DIBERIKAN.
    public static List<CSVRecord> readData(String filePath, String[] headers) throws IOException {
        try (
                Reader reader = new FileReader(filePath);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .builder()
                        .setHeader(headers)
                        .setSkipHeaderRecord(true) // Lewati baris header saat membaca
                        .build())
        ) {
            return csvParser.getRecords();
        }
    }

    /**
     * Menulis (atau menimpa) semua data ke sebuah file CSV.
     * @param filePath Path ke file CSV.
     * @param headers Header untuk ditulis di baris pertama.
     * @param data List dari baris data, dimana setiap baris adalah List<Object>.
     */
    // FUNGSI UNTUK MENULIS ATAU MENIMPA SEMUA DATA KE FILE CSV.
    // MENULISKAN HEADER DAN SEMUA BARIS DATA YANG DISEDIAKAN KE FILE.
    public static void writeData(String filePath, String[] headers, List<List<Object>> data) throws IOException {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .builder()
                        .setHeader(headers)
                        .build())
        ) {
            // Menulis header
            // (CSVPrinter dengan .withHeader() sudah menanganinya secara otomatis)

            // Menulis data baris per baris
            for (List<Object> record : data) {
                csvPrinter.printRecord(record);
            }
            csvPrinter.flush();
        }
    }
}