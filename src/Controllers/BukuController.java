package Controllers;

import Models.Book.*;
import Models.PenyimpananData;

/*
 * BukuController merupakan kelas utama yang mengatur semua operasi terkait manajemen buku.
 * Kelas ini berfungsi sebagai perantara antara antarmuka pengguna dan sistem penyimpanan data.
 * Bertanggung jawab untuk memvalidasi, memproses, dan mengkoordinasikan semua permintaan
 * yang berkaitan dengan buku seperti penambahan, pengeditan, penghapusan, dan penampilan data.
 * Kelas ini bekerja sama erat dengan model PenyimpananData untuk menyimpan dan mengambil informasi.
 */
public class BukuController {

//ATTRIBUTE


//OBJECTS


//METHODS
    //============================================================================================================================================================================================================

    /*
     * Method tambah ini khusus digunakan untuk menambahkan data buku berjenis Jurnal Ilmiah.
     * Menerima semua parameter dasar buku ditambah dua parameter khusus jurnal yaitu institusi
     * dan status indeksasi Sinta. Data yang diterima akan diverifikasi kemudian dikonversi
     * menjadi objek JurnalIlmiah sebelum disimpan ke sistem penyimpanan utama.
     * Method ini dipanggil ketika pengguna memilih untuk menambahkan buku jenis jurnal ilmiah.
     */
    public static void tambah(String kodeBuku, String judulBuku,String kategoriBuku,String pengarangBuku,String tahunTerbitBuku,
                              Boolean ketersediaanBuku, String institusi, String terindeksSinta){
        PenyimpananData.getBuku().add(new JurnalIlmiah(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku, institusi, terindeksSinta));
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /*
     * Merupakan versi overloading dari method tambah yang menangani tiga jenis buku sekaligus:
     * Majalah, Novel, dan Textbook. Parameter nilaiString memiliki makna berbeda tergantung
     * jenis buku: topik untuk Majalah, genre untuk Novel, dan bidang ilmu untuk Textbook.
     * Method ini secara otomatis menentukan kelas buku yang sesuai berdasarkan parameter
     * kategoriBuku yang diberikan, kemudian membuat instance yang tepat untuk disimpan.
     */
    public static void tambah(String kodeBuku, String judulBuku, String kategoriBuku, String pengarangBuku,
                              String tahunTerbitBuku, Boolean ketersediaanBuku, String nilaiString){
        if(kategoriBuku.toLowerCase().equals("majalah")){
            PenyimpananData.getBuku().add(new Majalah(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku, nilaiString));
        } else if (kategoriBuku.toLowerCase().equals("novel")) {
            PenyimpananData.getBuku().add(new Novel(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku, nilaiString));
        } else if (kategoriBuku.toLowerCase().equals("textbook")) {
            PenyimpananData.getBuku().add(new TextBook(kodeBuku, judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, ketersediaanBuku, nilaiString));
        }
    }

    //============================================================================================================================================================================================================

    /*
     * Method edit dengan parameter String digunakan untuk memodifikasi berbagai atribut
     * buku yang bertipe data teks. Parameter indeksPengeditan menentukan field mana yang
     * akan diubah, dengan nilai 1-5 untuk atribut dasar, 6 untuk atribut spesifik kategori,
     * dan 7 khusus untuk status Sinta pada jurnal ilmiah. Method ini melakukan type casting
     * dinamis untuk mengakses method setter yang sesuai dengan jenis buku yang sedang diedit.
     */
    public static void edit(int indeksBuku, int indeksPengeditan, String nilaiString){
        switch (indeksPengeditan){
            case 1: PenyimpananData.getBuku().get(indeksBuku).setKodeBuku(nilaiString); break;
            case 2: PenyimpananData.getBuku().get(indeksBuku).setJudulBuku(nilaiString); break;
            case 3: PenyimpananData.getBuku().get(indeksBuku).setKategoriBuku(nilaiString); break;
            case 4: PenyimpananData.getBuku().get(indeksBuku).setPengarangBuku(nilaiString); break;
            case 5: PenyimpananData.getBuku().get(indeksBuku).setTahunTerbitBuku(nilaiString); break;
            case 6:
                switch (PenyimpananData.getBuku().get(indeksBuku).getKategoriBuku().toLowerCase()){
                    case "jurnal ilmiah":
                        JurnalIlmiah jurnalIlmiah = (JurnalIlmiah) PenyimpananData.getBuku().get(indeksBuku);
                        jurnalIlmiah.setInstitusi(nilaiString);
                        break;
                    case "majalah":
                        Majalah majalah = (Majalah) PenyimpananData.getBuku().get(indeksBuku);
                        majalah.setTopikBuku(nilaiString);
                        break;
                    case "novel":
                        Novel novel = (Novel) PenyimpananData.getBuku().get(indeksBuku);
                        novel.setGenreBuku(nilaiString);
                        break;
                    case "textbook":
                        TextBook textbook = (TextBook) PenyimpananData.getBuku().get(indeksBuku);
                        textbook.setBidangIlmu(nilaiString);
                        break;
                }
                break;
            case 7:
                JurnalIlmiah jurnalIlmiah = (JurnalIlmiah) PenyimpananData.getBuku().get(indeksBuku);
                jurnalIlmiah.setTerindeksSinta(nilaiString);
                break;
        }
    }


    /*
     * Versi edit untuk tipe data Boolean khusus menangani perubahan status ketersediaan buku.
     * Meskipun parameter indeksPengeditan menerima berbagai nilai, pada dasarnya hanya
     * mempengaruhi atribut ketersediaanBuku. Ada duplikasi baris kode setKetersediaanBuku
     * yang mungkin merupakan kesalahan implementasi atau sengaja dibuat untuk kompatibilitas.
     */
    public static void edit(int indeksBuku, int indeksPengeditan, Boolean nilaiBoolean){
        switch (indeksPengeditan){
            case 7, 8:
                PenyimpananData.getBuku().get(indeksBuku).setKetersediaanBuku(nilaiBoolean);
                break;
        }
        PenyimpananData.getBuku().get(indeksBuku).setKetersediaanBuku(nilaiBoolean);
    }

    //============================================================================================================================================================================================================

    /*
     * Method hapus bertugas menghilangkan data buku dari sistem penyimpanan berdasarkan
     * indeks/posisi data dalam koleksi. Operasi ini bersifat permanen dan langsung
     * mempengaruhi struktur data utama. Tidak ada mekanisme undo atau konfirmasi
     * dalam implementasi saat ini.
     */
    public static void hapus(int indeks){
        PenyimpananData.getBuku().remove(indeks);
    }

    //============================================================================================================================================================================================================

    /*
     * Method tampilkan saat ini masih berupa stub (implementasi kosong). Rencananya akan
     * digunakan untuk menampilkan daftar buku kepada pengguna, baik melalui konsol
     * maupun antarmuka grafis. Di masa depan perlu diimplementasikan format tampilan
     * yang jelas dan informatif sesuai kebutuhan aplikasi.
     */
    public static void tampilkan(){

    }

    //============================================================================================================================================================================================================
}
