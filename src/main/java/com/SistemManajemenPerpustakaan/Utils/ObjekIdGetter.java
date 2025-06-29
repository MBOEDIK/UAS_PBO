package com.SistemManajemenPerpustakaan.Utils;

import com.SistemManajemenPerpustakaan.MVC.Controllers.PeminjamanController;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

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
                System.out.println("Pilihan tidak ada!");
                return null;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input harus berupa angka!");
            input.nextLine();
            return null;
        }
    }
}
