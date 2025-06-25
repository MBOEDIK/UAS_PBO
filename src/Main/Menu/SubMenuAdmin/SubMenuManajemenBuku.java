package Main.Menu.SubMenuAdmin;

import Controllers.BukuController;
import Controllers.PenggunaController;
import Models.Book.*;
import Models.PenyimpananData;
import Tools.IdGenerator;

import java.util.Scanner;

/*
 * Kelas SubMenuManajemenBuku bertanggung jawab untuk menangani antarmuka pengguna terkait
 * manajemen buku dalam sistem. Berperan sebagai perantara antara pengguna admin dengan
 * controller buku, menyediakan menu interaktif untuk operasi CRUD buku. Kelas ini bekerja
 * sama erat dengan BukuController untuk pemrosesan data dan PenyimpananData untuk akses
 * penyimpanan.
 */
public class SubMenuManajemenBuku {

    //ATTRIBUTES
    private static String inputStr;
    private static Boolean inputBool;

    //OBJECTS
    static Scanner input = new Scanner(System.in);

    //METHODS
    //============================================================================================================================================================================================================

    /*
     * Menangani proses penambahan buku baru ke sistem berdasarkan input pengguna.
     * Method ini memandu pengguna melalui serangkaian input untuk semua atribut buku
     * yang diperlukan, dengan penanganan khusus untuk setiap kategori buku:
     * - Jurnal Ilmiah memerlukan institusi dan status Sinta
     * - Majalah memerlukan topik
     * - Novel memerlukan genre
     * - Textbook memerlukan bidang ilmu
     * Semua buku baru secara default dibuat dengan status tersedia (true).
     */
    public static void menuTambahBuku(){

        System.out.print("\nTambah Buku ->");
        System.out.print("\nKategori buku (Jurnal Ilmiah, Majalah, Novel, Textbook): ");
        String kategoriBuku = input.nextLine();

        System.out.print("Judul buku: "); String judulBuku = input.nextLine();
        System.out.print("Pengarang buku: "); String pengarangBuku = input.nextLine();
        System.out.print("Tahun terbit buku: "); String tahunTerbitBuku = input.nextLine();

        switch (kategoriBuku.toLowerCase()){
            case "jurnal ilmiah":
                System.out.print("Institusi: "); String institusiBuku = input.nextLine();
                System.out.print("Terindeks Sinta (y/n): "); String terindeksSinta = input.nextLine();
                switch (terindeksSinta.toLowerCase()){
                    case "y": terindeksSinta = "Terindeks"; break;
                    case "n": terindeksSinta = "Tidak Terindeks"; break;
                    default: terindeksSinta = "-"; break;
                }
                BukuController.tambah(IdGenerator.generate(), judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, true, institusiBuku, terindeksSinta);
                break;
            case "majalah":
                System.out.print("Topik buku: "); String topikBuku = input.nextLine();
                BukuController.tambah(IdGenerator.generate(), judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, true, topikBuku);
                break;
            case "novel":
                System.out.print("Genre buku: "); String genreBuku = input.nextLine();
                BukuController.tambah(IdGenerator.generate(), judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, true, genreBuku);
                break;
            case "textbook":
                System.out.print("Bidang Ilmu: "); String bidangIlmu = input.nextLine();
                BukuController.tambah(IdGenerator.generate(), judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, true, bidangIlmu);
                break;
            default:
                System.out.print("Kategori tidak valid, batal menambahkan buku.\n");
        }
    }

    //============================================================================================================================================================================================================

    /*
     * Menyediakan antarmuka untuk mengedit atribut buku yang ada dalam sistem.
     * Menampilkan daftar buku yang tersedia dan memungkinkan pengguna memilih:
     * 1. Buku yang akan diedit
     * 2. Atribut spesifik yang akan diubah
     * 3. Nilai baru untuk atribut tersebut
     * Method ini secara dinamis menyesuaikan opsi edit berdasarkan kategori buku,
     * termasuk penanganan khusus untuk konversi input (seperti y/n ke boolean).
     */
    public static void menuEditBuku(){
        int inputInt, subInputInt;

        loop : while (true){
            if (PenyimpananData.getBuku().size() == 0){
                System.out.print("Buku kosong!\n");
                break loop;
            }

            System.out.print("\nEdit Buku ->");
            for (int i = 0; i < PenyimpananData.getBuku().size(); i++){
                System.out.print("\n      "+(i + 1)+". "+PenyimpananData.getBuku().get(i).getJudulBuku());
            }
            System.out.print("\nPilih index buku yang ingin diedit: "); inputInt = input.nextInt() - 1; input.nextLine();

            if (inputInt > PenyimpananData.getBuku().size()) {
                System.out.print("pilihan tidak ada!\n");
                continue;
            }

            System.out.print("" +
                    "      1. Kode Buku" +
                    "\n      2. Judul Buku" +
                    "\n      3. Kategori Buku" +
                    "\n      4. Pengarang Buku" +
                    "\n      5. Tahun Terbit Buku");

            switch (PenyimpananData.getBuku().get(inputInt).getKategoriBuku().toLowerCase()){
                case "jurnal ilmiah":
                    System.out.print("" +
                            "\n      6. Institusi" +
                            "\n      7. Terindeks Sinta");
                    break;
                case "majalah":
                    System.out.print("" +
                            "\n      6. topik Buku");
                    break;
                case "novel":
                    System.out.print("" +
                            "\n      6. Genre Buku");
                    break;
                case "textbook":
                    System.out.print("" +
                            "\n      6. Bidang Ilmu");
                    break;
            }

            System.out.print("\nPilih index pengeditan: "); subInputInt = input.nextInt(); input.nextLine();

            String placeHolder1 = "", attributeDiEdit = "";
            Boolean nilaiBoolean = true;

            switch (subInputInt){
                case 1: placeHolder1 = "kode buku baru"; attributeDiEdit = "kodeBuku"; break;
                case 2: placeHolder1 = "judul baru"; attributeDiEdit = "judul"; break;
                case 3: placeHolder1 = "kategori baru (Jurnal Ilmiah, Majalah, Novel, Textbook)"; attributeDiEdit = "kategori"; break;
                case 4: placeHolder1 = "pengarang baru"; attributeDiEdit = "pengarang"; break;
                case 5: placeHolder1 = "tahun terbit baru"; attributeDiEdit = "tahunTerbit"; break;
                case 6:
                    switch (PenyimpananData.getBuku().get(inputInt).getKategoriBuku().toLowerCase()){
                        case "jurnal ilmiah": placeHolder1 = "institusi baru"; attributeDiEdit = "institusi"; break;
                        case "majalah": placeHolder1 = "topik baru"; attributeDiEdit = "topik"; break;
                        case "novel": placeHolder1 = "genre baru"; attributeDiEdit = "genre"; break;
                        case "textbook": placeHolder1 = "bidang ilmu baru"; attributeDiEdit = "bidangIlmu"; break;
                    }
                    break;
                case 7:
                    placeHolder1 = "terindeks sinta baru (y/n)"; attributeDiEdit = "terindeksSinta";
                    break;
            }

            System.out.print("Masukkan "+placeHolder1+": "); inputStr = input.nextLine();

            if (subInputInt == 7 && PenyimpananData.getPengguna().get(inputInt).getJenisPengguna().toLowerCase().equals("jurnal ilmiah")){
                switch (inputStr.toLowerCase()){
                    case "y": inputStr = "Terindeks"; break;
                    case "n": inputStr = "Tidak Terindeks"; break;
                }
                PenggunaController.edit(inputInt, attributeDiEdit, inputStr);
            }

            BukuController.edit(inputInt, attributeDiEdit, inputStr);

            System.out.print("Ingin mengedit lagi? (y/n): "); inputStr = input.nextLine();

            switch (inputStr.toLowerCase()){
                case "y": continue loop;
                case "n": break loop;
            }
        }
    }

    //============================================================================================================================================================================================================

    /*
     * Menangani proses penghapusan buku dari sistem.
     * Menampilkan daftar buku yang tersedia dan meminta konfirmasi sebelum
     * menghapus. Memiliki penanganan khusus untuk kasus:
     * - Daftar buku kosong
     * - Input indeks tidak valid
     * - Konfirmasi penghapusan
     * Operasi penghapusan bersifat permanen dan langsung mempengaruhi data.
     */
    public static void menuHapusBuku(){
        int inputInt, subInputInt;
        String inputStr;

        loop : while (true){
            if (PenyimpananData.getBuku().size() == 0){
                System.out.print("Buku kosong!\n");
                break loop;
            }

            System.out.print("\nHapus Buku ->");
            for (int i = 0; i < PenyimpananData.getBuku().size(); i++){
                System.out.print("\n      "+(i + 1)+". "+PenyimpananData.getBuku().get(i).getJudulBuku());
            }
            System.out.print("\nPilih index buku yang ingin dihapus: "); inputInt = input.nextInt() - 1; input.nextLine();

            if (inputInt > PenyimpananData.getBuku().size() || inputInt < 0) {
                System.out.print("pilihan tidak ada!\n");
                continue;
            }

            System.out.print("Kamu yakin ingin menghapus buku \""+PenyimpananData.getBuku().get(inputInt).getJudulBuku()+"\"? (y/n): "); inputStr = input.nextLine();

            switch (inputStr.toLowerCase()){
                case "y":
                    BukuController.hapus(inputInt);
                    break loop;
                default:
                    System.out.print("Batal menghapus objek.\n");
                    break loop;
            }
        }

    }

    //============================================================================================================================================================================================================

    /*
     * Menampilkan detail lengkap dari buku yang dipilih pengguna.
     * Menyajikan semua atribut buku dalam format terstruktur, dengan penyesuaian
     * otomatis berdasarkan kategori buku. Memungkinkan pengguna untuk melihat
     * detail multiple buku secara berurutan tanpa kembali ke menu utama.
     */
    public static void menuDetailBuku(){
        int inputInt, subInputInt;
        String inputStr;

        loop : while (true){
            if (PenyimpananData.getBuku().size() == 0){
                System.out.print("Buku kosong!\n");
                break loop;
            }

            System.out.print("\nDetail Buku ->");
            for (int i = 0; i < PenyimpananData.getBuku().size(); i++){
                System.out.print("\n      "+(i + 1)+". "+PenyimpananData.getBuku().get(i).getJudulBuku());
            }
            System.out.print("\nPilih index buku yang ingin ditampilkan detail informasinya: "); inputInt = input.nextInt() - 1; input.nextLine();

            if (inputInt > PenyimpananData.getBuku().size() || inputInt < 0) {
                System.out.print("pilihan tidak ada!\n");
                continue;
            }

            System.out.print("" +
                    "      1. Kode Buku: "+PenyimpananData.getBuku().get(inputInt).getKodeBuku()+"" +
                    "\n      2. Judul: "+PenyimpananData.getBuku().get(inputInt).getJudulBuku()+"" +
                    "\n      3. Kategori: "+PenyimpananData.getBuku().get(inputInt).getKategoriBuku()+"" +
                    "\n      4. Pengarang: "+PenyimpananData.getBuku().get(inputInt).getPengarangBuku()+"" +
                    "\n      5. Tahun Terbit: "+PenyimpananData.getBuku().get(inputInt).getTahunTerbitBuku()+"");

            String ketersediaanBuku;
            if (PenyimpananData.getBuku().get(inputInt).getKetersediaanBuku()){
                ketersediaanBuku = "Tersedia";
            }else {
                ketersediaanBuku = "Tidak Tersedia";
            }


            switch (PenyimpananData.getBuku().get(inputInt).getKategoriBuku().toLowerCase()){
                case "jurnal ilmiah":
                    JurnalIlmiah jurnalIlmiah = (JurnalIlmiah) PenyimpananData.getBuku().get(inputInt);
                    System.out.print("" +
                            "\n      6. Institusi: "+jurnalIlmiah.getInstitusi()+"" +
                            "\n      7. Terindeks Sinta: "+jurnalIlmiah.getTerindeksSinta()+"" +
                            "\n      8. Ketersediaan Buku: "+ketersediaanBuku+"");
                    break;
                case "majalah":
                    Majalah majalah = (Majalah) PenyimpananData.getBuku().get(inputInt);
                    System.out.print("" +
                            "\n      6. topik Buku: "+majalah.getTopikBuku()+"" +
                            "\n      7. Ketersediaan Buku: "+ketersediaanBuku+"");
                    break;
                case "novel":
                    Novel novel = (Novel) PenyimpananData.getBuku().get(inputInt);
                    System.out.print("" +
                            "\n      6. Genre Buku: "+novel.getGenreBuku()+"" +
                            "\n      7. Ketersediaan Buku: "+ketersediaanBuku+"");
                    break;
                case "textbook":
                    TextBook textBook = (TextBook) PenyimpananData.getBuku().get(inputInt);
                    System.out.print("" +
                            "\n      6. Bidang Ilmu: "+textBook.getBidangIlmu()+"" +
                            "\n      7. Ketersediaan Buku: "+ketersediaanBuku+"");
                    break;
            }

            System.out.print("\nIngin melihat detail buku lain juga? (y/n): "); inputStr = input.nextLine();

            switch (inputStr.toLowerCase()){
                case "y": continue loop;
                case "n": break loop;
            }
        }
    }
}
