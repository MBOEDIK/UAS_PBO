package com.SistemManajemenPerpustakaan.Utils;

import java.util.List;
import java.util.function.Function;

public class IsIdExist {
    public static <T> boolean check(String id, List<T> list, Function<T, String> idExtractor) {
        if (id == null || list == null || list.isEmpty() || idExtractor == null) {
            return false;
        }
        return list.stream().anyMatch(obj -> id.equals(idExtractor.apply(obj)));
    }
}
