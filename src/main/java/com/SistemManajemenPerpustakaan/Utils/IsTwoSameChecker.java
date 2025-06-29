package com.SistemManajemenPerpustakaan.Utils;

import java.util.List;
import java.util.function.Function;

public class IsTwoSameChecker {
    public static <T> T check(List<T> list, String id, Function<T, String> idExtractor) {
        if (id == null || list == null) {
            return null;
        }

        for (T objek : list) {
            if (id.equals(idExtractor.apply(objek))) {
                return objek;
            }
        }
        return null;
    }
}
