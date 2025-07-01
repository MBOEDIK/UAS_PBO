package com.SistemManajemenPerpustakaan.Utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

//Generate ID dengan format: TAHUNBULAN-001 (202406-001)
public class IdGenerator {
    // MENGHASILKAN ID UNIK.
    // MEMASTIKAN ID BARU BELUM ADA DALAM DAFTAR.
    public static <T> String generateUniqueId(List<T> list, Function<T, String> idExtractor) {

        Set<String> existingIds = new HashSet<>();
        if (list != null && idExtractor != null) {
            existingIds = list.stream()
                    .map(idExtractor)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
        }

        // Generate ID baru sampai menemukan yang unik
        String newId;
        do {
            newId = generateRandomId(); // Memanggil method helper
        } while (existingIds.contains(newId));

        return newId;
    }

    // MENGHASILKAN ID ACAK.
    // MEMBUAT ID DENGAN KOMBINASI HURUF DAN ANGKA.
    private static String generateRandomId() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        // 3 huruf acak
        for (int i = 0; i < 3; i++) {
            sb.append(letters.charAt(random.nextInt(letters.length())));
        }

        sb.append("-");

        // 5 angka acak
        for (int i = 0; i < 5; i++) {
            sb.append(numbers.charAt(random.nextInt(numbers.length())));
        }

        return sb.toString();
    }
}