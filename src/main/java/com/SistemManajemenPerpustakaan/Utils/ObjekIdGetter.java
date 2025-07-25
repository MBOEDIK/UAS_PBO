package com.SistemManajemenPerpustakaan.Utils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ObjekIdGetter {
    public static <T> String get(List<T> list, String pesanSebelumInput, Function<T, String> idExtractor) {
        Scanner input = new Scanner(System.in);
        System.out.print(pesanSebelumInput != null ? pesanSebelumInput : "Pilih index: ");

        try {
            int x = input.nextInt() - 1;
            input.nextLine();

            if (x >= 0 && x < list.size()) {
                return idExtractor.apply(list.get(x));
            } else {
                return null;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input harus berupa angka!");
            input.nextLine();
            return null;
        }
    }

    public static <T, V> String getBersyarat(List<T> list,
                                    String pesanSebelumInput,
                                    Function<T, String> idExtractor,
                                    Function<T, V> att,
                                    V attVal) {
        // Filter list berdasarkan kriteria att == attVal (jika attVal tidak null)
        List<T> filteredList = attVal == null
                ? list
                : list.stream()
                .filter(item -> attVal.equals(att.apply(item)))
                .collect(Collectors.toList());

        Scanner input = new Scanner(System.in);
        System.out.print(pesanSebelumInput != null ? pesanSebelumInput : "Pilih index: ");

        try {
            int x = input.nextInt() - 1;
            input.nextLine();

            if (x >= 0 && x < filteredList.size()) {
                return idExtractor.apply(filteredList.get(x));
            } else {
                return null;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input harus berupa angka!");
            input.nextLine();
            return null;
        }
    }
}
