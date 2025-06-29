package com.SistemManajemenPerpustakaan.Utils;

import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Consumer;

public class InformationPrinter {
    // Gunakan satu Scanner static untuk seluruh aplikasi
    private static final Scanner scanner = new Scanner(System.in);

    // ==================== VERSI 1: TAMPILKAN ATRIBUT SAJA ====================

    // Overload tanpa stringFilter
    public static void tampilkanAtribut(Object objek, String judul, int isInclude, String... atributTerpilih) {
        tampilkanAtribut(objek, judul, isInclude, null, atributTerpilih);
    }

    // Versi lengkap dengan stringFilter
    public static void tampilkanAtribut(Object objek, String judul, int isInclude,
                                        Function<String, String> stringFilter, String... atributTerpilih) {
        Class<?> kelas = objek.getClass();
        List<String> atributFilter = Arrays.asList(atributTerpilih);

        System.out.println(judul != null ? judul : "Daftar atribut dari " + kelas.getSimpleName() + ":");
        tampilkanAtributKelas(kelas, objek, 1, atributFilter, isInclude, false, stringFilter);
    }

    // ==================== VERSI 2: TAMPILKAN ATRIBUT DAN NILAI ====================

    // Overload tanpa stringFilter
    public static void tampilkanAtributDenganNilai(Object objek, String judul, int isInclude, String... atributTerpilih) {
        tampilkanAtributDenganNilai(objek, judul, isInclude, null, atributTerpilih);
    }

    // Versi lengkap dengan stringFilter
    public static void tampilkanAtributDenganNilai(Object objek, String judul, int isInclude,
                                                   Function<String, String> stringFilter, String... atributTerpilih) {
        Class<?> kelas = objek.getClass();
        List<String> atributFilter = Arrays.asList(atributTerpilih);

        System.out.println(judul != null ? judul : "Atribut dan nilai dari " + kelas.getSimpleName() + ":");
        tampilkanAtributKelas(kelas, objek, 1, atributFilter, isInclude, true, stringFilter);
    }

    // ==================== VERSI 3: INPUT NILAI ATRIBUT ====================

    // Overload tanpa stringFilter
    public static void tampilkanAtributDenganInput(Object objek, String judul, int isInclude, String... atributTerpilih) {
        tampilkanAtributDenganInput(objek, judul, isInclude, null, atributTerpilih);
    }

    // Versi lengkap dengan stringFilter
    public static void tampilkanAtributDenganInput(Object objek, String judul, int isInclude,
                                                   Function<String, String> stringFilter, String... atributTerpilih) {
        Class<?> kelas = objek.getClass();
        List<String> atributFilter = Arrays.asList(atributTerpilih);

        System.out.println(judul != null ? judul : "Masukkan nilai untuk atribut " + kelas.getSimpleName() + ":");
        tampilkanAtributDenganInputKelas(kelas, objek, 1, atributFilter, isInclude, stringFilter);
    }

    // ==================== METODE BANTU ====================

    // Metode untuk mengubah nama atribut menjadi format yang lebih mudah dibaca
    private static String formatNamaAtribut(String namaAtribut, Function<String, String> stringFilter) {
        // Daftar pengecualian khusus
        if (namaAtribut.equalsIgnoreCase("id")) return "ID";
        if (namaAtribut.equalsIgnoreCase("jURNAL")) return "Jurnal";
        if (namaAtribut.equalsIgnoreCase("sINTA")) return "Sinta";
        if (namaAtribut.equalsIgnoreCase("nYA")) return "Nya";

        // Pisahkan kata dengan benar
        String[] kataKata = namaAtribut.split("(?<=[a-z])(?=[A-Z])|_");
        StringBuilder hasil = new StringBuilder();

        for (int i = 0; i < kataKata.length; i++) {
            String kata = kataKata[i];
            if (kata.isEmpty()) continue;

            // Kapitalisasi huruf pertama
            if (i == 0) {
                kata = kata.substring(0, 1).toUpperCase() +
                        (kata.length() > 1 ? kata.substring(1).toLowerCase() : "");
            } else {
                kata = kata.toLowerCase();
            }

            hasil.append(kata).append(" ");
        }

        String formatted = hasil.toString().trim();

        // Terapkan filter untuk menggabungkan spasi yang tidak diinginkan
        formatted = formatted.replaceAll("\\s+", " ");

        // Terapkan string filter eksternal jika ada
        return stringFilter != null ? stringFilter.apply(formatted) : formatted;
    }

    // Metode pembantu rekursif untuk menampilkan atribut
    private static int tampilkanAtributKelas(Class<?> kelas, Object objek,
                                             int startNumber,
                                             List<String> atributFilter,
                                             int isInclude,
                                             boolean tampilkanNilai,
                                             Function<String, String> stringFilter) {
        int counter = startNumber;
        Class<?> currentClass = kelas;

        while (currentClass != null) {
            for (Field field : currentClass.getDeclaredFields()) {
                boolean isInFilter = atributFilter.contains(field.getName());
                if ((isInclude == 1 && isInFilter) || (isInclude != 1 && !isInFilter)) {
                    try {
                        field.setAccessible(true);
                        String namaDisplay = formatNamaAtribut(field.getName(), stringFilter);
                        Object nilai = tampilkanNilai ? field.get(objek) : "";
                        System.out.println("      "+ counter + ". " + namaDisplay + ": " + nilai);
                        counter++;
                    } catch (IllegalAccessException e) {
                        System.out.println("      "+ counter + ". " + field.getName() + ": [ERROR]");
                        counter++;
                    }
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        return counter - startNumber; // Jumlah atribut yang benar-benar ditampilkan
    }

    // Metode pembantu rekursif untuk meminta input atribut
    private static int tampilkanAtributDenganInputKelas(Class<?> kelas, Object objek,
                                                        int nomorUrut, List<String> atributFilter,
                                                        int isInclude, Function<String, String> stringFilter) {
        // Cek parent class terlebih dahulu
        Class<?> parent = kelas.getSuperclass();
        if (parent != null && parent != Object.class) {
            nomorUrut = tampilkanAtributDenganInputKelas(parent, objek, nomorUrut, atributFilter, isInclude, stringFilter);
        }

        // Tampilkan atribut dari kelas saat ini
        Field[] daftarAtribut = kelas.getDeclaredFields();
        for (Field atribut : daftarAtribut) {
            boolean isAtributTerpilih = atributFilter.contains(atribut.getName());

            // Filter berdasarkan mode isInclude
            if ((isInclude == 0 && isAtributTerpilih) ||
                    (isInclude == 1 && !isAtributTerpilih && !atributFilter.isEmpty())) {
                continue;
            }

            String namaDisplay = formatNamaAtribut(atribut.getName(), stringFilter);
            System.out.print("      "+nomorUrut + ". " + namaDisplay + ": ");
            String masukan = scanner.nextLine();

            try {
                atribut.setAccessible(true);
                // Konversi masukan ke tipe yang sesuai
                Class<?> tipeAtribut = atribut.getType();
                if (tipeAtribut == String.class) {
                    // Terapkan string filter pada input jika perlu
                    String nilai = stringFilter != null ? stringFilter.apply(masukan) : masukan;
                    atribut.set(objek, nilai);
                } else if (tipeAtribut == int.class || tipeAtribut == Integer.class) {
                    atribut.set(objek, Integer.parseInt(masukan));
                } else if (tipeAtribut == double.class || tipeAtribut == Double.class) {
                    atribut.set(objek, Double.parseDouble(masukan));
                } else if (tipeAtribut == boolean.class || tipeAtribut == Boolean.class) {
                    atribut.set(objek, Boolean.parseBoolean(masukan));
                } else if (tipeAtribut == float.class || tipeAtribut == Float.class) {
                    atribut.set(objek, Float.parseFloat(masukan));
                } else if (tipeAtribut == long.class || tipeAtribut == Long.class) {
                    atribut.set(objek, Long.parseLong(masukan));
                }
            } catch (IllegalAccessException e) {
                System.out.println("Gagal mengatur nilai untuk " + namaDisplay);
            } catch (NumberFormatException e) {
                System.out.println("Format masukan tidak valid untuk " + namaDisplay);
            }
            nomorUrut++;
        }

        return nomorUrut;
    }

    public static void tampilkanUpdateAtribut(Object objek, String judul, String id,
                                              Consumer<UpdateData> updateMethod,
                                              int isInclude,
                                              Function<String, String> stringFilter,
                                              String... atributTerpilih) {
        Class<?> kelas = objek.getClass();
        List<String> atributFilter = Arrays.asList(atributTerpilih);

        System.out.println(judul != null ? judul : "Update atribut dari " + kelas.getSimpleName() + ":");

        // Tampilkan daftar atribut yang bisa diupdate
        int jumlahAtribut = tampilkanAtributKelas(kelas, objek, 1, atributFilter, isInclude, false, stringFilter);

        // Pilih atribut yang akan diupdate
        System.out.print("\nPilih nomor atribut yang akan diupdate (1-" + jumlahAtribut + "): ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Validasi input
        if (pilihan < 1 || pilihan > jumlahAtribut) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        // Cari atribut yang dipilih
        Field atributDipilih = cariAtributBerdasarkanNomor(kelas, pilihan, atributFilter, isInclude);

        if (atributDipilih == null) {
            System.out.println("Atribut tidak ditemukan!");
            return;
        }

        // Tampilkan nama atribut yang dipilih
        String namaDisplay = formatNamaAtribut(atributDipilih.getName(), stringFilter);
        System.out.print("Masukkan nilai baru untuk " + namaDisplay + ": ");
        String nilaiBaru = scanner.nextLine();

        try {
            // Konversi nilai ke tipe yang sesuai
            Object nilaiConverted = konversiNilai(nilaiBaru, atributDipilih.getType());

            // Panggil method update dengan parameter yang dibutuhkan
            updateMethod.accept(new UpdateData(id, atributDipilih.getName(), nilaiConverted));

            System.out.println("Update berhasil!");
        } catch (Exception e) {
            System.out.println("Gagal update: " + e.getMessage());
        }
    }

    // Overload tanpa stringFilter
    public static void tampilkanUpdateAtribut(Object objek, String judul, String id,
                                              Consumer<UpdateData> updateMethod,
                                              int isInclude,
                                              String... atributTerpilih) {
        tampilkanUpdateAtribut(objek, judul, id, updateMethod, isInclude, null, atributTerpilih);
    }

    // Kelas pembungkus untuk data update
    public static class UpdateData {
        public final String id;
        public final String namaAtribut;
        public final Object nilaiBaru;

        public UpdateData(String id, String namaAtribut, Object nilaiBaru) {
            this.id = id;
            this.namaAtribut = namaAtribut;
            this.nilaiBaru = nilaiBaru;
        }
    }

    // ========== METHOD BANTU ==========

    private static Field cariAtributBerdasarkanNomor(Class<?> kelas, int nomor,
                                                     List<String> atributFilter,
                                                     int isInclude) {
        int counter = 1;
        Class<?> currentClass = kelas;

        while (currentClass != null) {
            for (Field field : currentClass.getDeclaredFields()) {
                boolean isInFilter = atributFilter.contains(field.getName());
                // Jika isInclude == 1, hanya tampilkan yang ADA di filter
                // Jika isInclude != 1, tampilkan yang TIDAK ADA di filter
                if ((isInclude == 1 && isInFilter) || (isInclude != 1 && !isInFilter)) {
                    if (counter == nomor) {
                        return field;
                    }
                    counter++;
                }
            }
            currentClass = currentClass.getSuperclass(); // Handle parent class
        }
        return null;
    }

//    private static Field cariAtributBerdasarkanNomor(Class<?> kelas, int nomor,
//                                                     List<String> atributFilter, int isInclude) {
//        return cariAtributBerdasarkanNomorRecursive(kelas, nomor, 1, atributFilter, isInclude);
//    }

    private static Field cariAtributBerdasarkanNomorRecursive(Class<?> kelas, int nomorTarget,
                                                              int nomorSekarang,
                                                              List<String> atributFilter, int isInclude) {
        // Cek parent class terlebih dahulu
        Class<?> parent = kelas.getSuperclass();
        if (parent != null && parent != Object.class) {
            Field result = cariAtributBerdasarkanNomorRecursive(parent, nomorTarget, nomorSekarang, atributFilter, isInclude);
            if (result != null) {
                return result;
            }
            // Update nomorSekarang berdasarkan jumlah field di parent
            nomorSekarang += parent.getDeclaredFields().length;
        }

        // Cari di kelas saat ini
        Field[] fields = kelas.getDeclaredFields();
        for (Field field : fields) {
            boolean isAtributTerpilih = atributFilter.contains(field.getName());

            if ((isInclude == 0 && isAtributTerpilih) ||
                    (isInclude == 1 && !isAtributTerpilih && !atributFilter.isEmpty())) {
                continue;
            }

            if (nomorSekarang == nomorTarget) {
                return field;
            }
            nomorSekarang++;
        }

        return null;
    }

    private static Object konversiNilai(String nilai, Class<?> targetType) throws Exception {
        if (targetType == String.class) {
            return nilai;
        } else if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(nilai);
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(nilai);
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(nilai);
        } else if (targetType == float.class || targetType == Float.class) {
            return Float.parseFloat(nilai);
        } else if (targetType == long.class || targetType == Long.class) {
            return Long.parseLong(nilai);
        }
        throw new Exception("Tipe data tidak didukung: " + targetType.getSimpleName());
    }

    public static <T> void tampilkanObjek(List<T> daftarObjek, String judul, String namaAtribut,
                                          Function<String, String> stringFilter) {
        if (daftarObjek == null || daftarObjek.isEmpty()) {
            System.out.println("Daftar objek kosong!");
            return;
        }

        if (judul == null) System.out.println("\nDaftar Nilai Atribut '" + namaAtribut + "':");
        System.out.println("\n"+judul);

        for (int i = 0; i < daftarObjek.size(); i++) {
            T objek = daftarObjek.get(i);
            try {
                Field field = cariAtribut(objek.getClass(), namaAtribut);
                field.setAccessible(true);
                Object nilai = field.get(objek);

                String namaDisplay = formatNamaAtribut(namaAtribut, stringFilter);
                String nilaiDisplay = nilai != null ? nilai.toString() : "null";

                System.out.println("      "+(i + 1) + ". (" + objek.getClass().getSimpleName()
                        + ") " + namaDisplay + ": " + nilaiDisplay);
            } catch (Exception e) {
                System.out.println("      "+(i + 1) + ". (" + objek.getClass().getSimpleName()
                        + ") Error: " + e.getMessage());
            }
        }
    }

    public static <T> void tampilkanObjek(List<T> daftarObjek, String judul,
                                          String namaAtributFilter, Object nilaiFilter,
                                          String namaAtributTampil,
                                          Function<String, String> stringFilter) {
        if (daftarObjek == null || daftarObjek.isEmpty()) {
            System.out.println("Daftar objek kosong!");
            return;
        }

        System.out.println(judul != null ? judul :
                "\nDaftar Objek dengan " + namaAtributFilter + " = " + nilaiFilter + ":");

        int counter = 1;
        for (T objek : daftarObjek) {
            try {
                // Dapatkan field untuk filter
                Field fieldFilter = cariAtribut(objek.getClass(), namaAtributFilter);
                fieldFilter.setAccessible(true);
                Object nilaiAtributFilter = fieldFilter.get(objek);

                // Bandingkan nilai filter
                if ((nilaiAtributFilter == null && nilaiFilter == null) ||
                        (nilaiAtributFilter != null && nilaiAtributFilter.equals(nilaiFilter))) {

                    // Dapatkan field untuk ditampilkan
                    Field fieldTampil = cariAtribut(objek.getClass(), namaAtributTampil);
                    fieldTampil.setAccessible(true);
                    Object nilaiAtributTampil = fieldTampil.get(objek);

                    // Format output
                    String namaDisplay = formatNamaAtribut(namaAtributTampil, stringFilter);
                    String nilaiDisplay = nilaiAtributTampil != null ?
                            nilaiAtributTampil.toString() : "null";

                    System.out.printf("      %d. (%s) %s: %s%n",
                            counter++,
                            objek.getClass().getSimpleName(),
                            namaDisplay,
                            nilaiDisplay);
                }
            } catch (Exception e) {
                System.out.printf("      %d. (%s) Error: %s%n",
                        counter++,
                        objek.getClass().getSimpleName(),
                        e.getMessage());
            }
        }

        if (counter == 1) {
            System.out.println("Tidak ada objek yang memenuhi kriteria");
        }
    }

    // Overload tanpa stringFilter
    public static <T> void tampilkanObjek(List<T> daftarObjek, String judul,
                                          String namaAtributFilter, Object nilaiFilter,
                                          String namaAtributTampil) {
        tampilkanObjek(daftarObjek, judul, namaAtributFilter, nilaiFilter,
                namaAtributTampil, null);
    }

    // Overload jika ingin menampilkan toString() objek
    public static <T> void tampilkanObjek(List<T> daftarObjek, String judul,
                                          String namaAtributFilter, Object nilaiFilter) {
        if (daftarObjek == null || daftarObjek.isEmpty()) {
            System.out.println("Daftar objek kosong!");
            return;
        }

        System.out.println(judul != null ? judul :
                "\nDaftar Objek dengan " + namaAtributFilter + " = " + nilaiFilter + ":");

        int counter = 1;
        for (T objek : daftarObjek) {
            try {
                Field fieldFilter = cariAtribut(objek.getClass(), namaAtributFilter);
                fieldFilter.setAccessible(true);
                Object nilaiAtributFilter = fieldFilter.get(objek);

                if ((nilaiAtributFilter == null && nilaiFilter == null) ||
                        (nilaiAtributFilter != null && nilaiAtributFilter.equals(nilaiFilter))) {

                    System.out.printf("      %d. %s%n", counter++, objek.toString());
                }
            } catch (Exception e) {
                System.out.printf("      %d. (%s) Error: %s%n",
                        counter++,
                        objek.getClass().getSimpleName(),
                        e.getMessage());
            }
        }

        if (counter == 1) {
            System.out.println("Tidak ada objek yang memenuhi kriteria");
        }
    }

    // Overload tanpa stringFilter
    public static <T> void tampilkanObjek(List<T> daftarObjek, String judul, String namaAtribut) {
        tampilkanObjek(daftarObjek, judul, namaAtribut, null);
    }

    // Method bantu untuk mencari atribut
    private static Field cariAtribut(Class<?> kelas, String namaAtribut)
            throws NoSuchFieldException {
        try {
            return kelas.getDeclaredField(namaAtribut);
        } catch (NoSuchFieldException e) {
            Class<?> parent = kelas.getSuperclass();
            if (parent != null && parent != Object.class) {
                return cariAtribut(parent, namaAtribut);
            }
            throw e;
        }
    }

    // Metode untuk menutup scanner (opsional, panggil saat aplikasi benar-benar selesai)
    public static void tutupScanner() {
        scanner.close();
    }
}