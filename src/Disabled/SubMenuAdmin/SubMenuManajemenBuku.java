package com.SistemManajemenPerpustakaan.Sementara.SubMenuAdmin;

import com.SistemManajemenPerpustakaan.MVC.Controllers.Books.BukuController2;
import com.SistemManajemenPerpustakaan.MVC.Controllers.Users.PenggunaController2;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.*;
import com.SistemManajemenPerpustakaan.MVC.Models.PenyimpananData;
import com.SistemManajemenPerpustakaan.Utils.IdGenerator;
import com.SistemManajemenPerpustakaan.Utils.DataAccessHelper;

import java.util.Scanner;

public class SubMenuManajemenBuku implements DataAccessHelper {

    //ATTRIBUTES
    private static String inputStr;
    private static Boolean inputBool;
    private static int inputInt, subInputInt;
    private static String placeHolder1 = "", attributeDiEdit = "";


    //OBJECTS
    static Scanner input = new Scanner(System.in);

    //METHODS
    //============================================================================================================================================================================================================
    
    public static void menuTambahBuku(){
        /*
        * Minta input setiap attribut untuk buat buku baru
        * */
        System.out.print("\nTambah Buku ->");
        System.out.print("\nKategori buku (Jurnal Ilmiah, Majalah, Novel, Textbook): "); String kategoriBuku = input.nextLine();
        System.out.print("Judul buku: "); String judulBuku = input.nextLine();
        System.out.print("Pengarang buku: "); String pengarangBuku = input.nextLine();
        System.out.print("Tahun terbit buku: "); String tahunTerbitBuku = input.nextLine();
        
        
        
        /*
        * Minta input attribute tambahan berdasarkan kategori bukunya apa
        * */
        switch (kategoriBuku.toLowerCase()){
            case "jurnal ilmiah":
                System.out.print("Institusi: "); String institusiBuku = input.nextLine();
                System.out.print("Terindeks Sinta (y/n): "); String terindeksSinta = input.nextLine();
                switch (terindeksSinta.toLowerCase()){
                    case "y": terindeksSinta = "Terindeks"; break;
                    case "n": terindeksSinta = "Tidak Terindeks"; break;
                    default: terindeksSinta = "-"; break;
                }
                BukuController2.tambah(IdGenerator.generate(), judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, true, institusiBuku, terindeksSinta);
                break;
            case "majalah":
                System.out.print("Topik buku: "); String topikBuku = input.nextLine();
                BukuController2.tambah(IdGenerator.generate(), judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, true, topikBuku);
                break;
            case "novel":
                System.out.print("Genre buku: "); String genreBuku = input.nextLine();
                BukuController2.tambah(IdGenerator.generate(), judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, true, genreBuku);
                break;
            case "textbook":
                System.out.print("Bidang Ilmu: "); String bidangIlmu = input.nextLine();
                BukuController2.tambah(IdGenerator.generate(), judulBuku, kategoriBuku, pengarangBuku, tahunTerbitBuku, true, bidangIlmu);
                break;
            default:
                System.out.print("Kategori tidak valid, batal menambahkan buku.\n");
        }
    }

    //============================================================================================================================================================================================================
    
    public static void menuEditBuku(){
        loop : while (true){
            /*
            Cek apakah buku ada atau kosong, kalo kosong maka langsung keluar dari method ini
            */
            if (dataBuku.isEmpty()){
                System.out.print("Buku kosong!\n");
                break loop;
            }
            
            
            
            /*
            Nampilin semua buku yang ada
            */
            System.out.print("\nEdit Buku ->");
            for (int i = 0; i < dataBuku.size(); i++){
                System.out.print("\n      "+(i + 1)+". "+dataBuku.get(i).getJudul());
            }
            System.out.print("\nPilih index buku yang ingin diedit: "); inputInt = input.nextInt() - 1; input.nextLine();
            
            
            
            /*
            * Validasi apakah pengguna milih sesuai pilihan yg dikasih atau engga, kalo engga maka ulangi loop utama
            */
            if (inputInt > dataBuku.size()) {
                System.out.print("pilihan tidak ada!\n");
                continue;
            }
            
            
            
            /*
            * Minta input pilihan attribute yg bisa diedit
            */
            System.out.print("" +
                    "      1. Kode Buku" +
                    "\n      2. Judul Buku" +
                    "\n      3. Kategori Buku" +
                    "\n      4. Pengarang Buku" +
                    "\n      5. Tahun Terbit Buku");

            switch (dataBuku.get(inputInt).getKategori().toLowerCase()){
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
            switch (subInputInt){
                case 1: placeHolder1 = "kode buku baru"; attributeDiEdit = "kodeBuku"; break;
                case 2: placeHolder1 = "judul baru"; attributeDiEdit = "judul"; break;
                case 3: placeHolder1 = "kategori baru (Jurnal Ilmiah, Majalah, Novel, Textbook)"; attributeDiEdit = "kategori"; break;
                case 4: placeHolder1 = "pengarang baru"; attributeDiEdit = "pengarang"; break;
                case 5: placeHolder1 = "tahun terbit baru"; attributeDiEdit = "tahunTerbit"; break;
                case 6:
                    switch (dataBuku.get(inputInt).getKategori().toLowerCase()){
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
            
            
            
            /*
            * Simpen perubahan buku yang baru aja diedit salah satu attributenya
            * */
            if (subInputInt == 7 && PenyimpananData.getPengguna().get(inputInt).getJenis().toLowerCase().equals("jurnal ilmiah")){
                switch (inputStr.toLowerCase()){
                    case "y": inputStr = "Terindeks"; break;
                    case "n": inputStr = "Tidak Terindeks"; break;
                }
                PenggunaController2.edit(inputInt, attributeDiEdit, inputStr);
            }            
            BukuController2.edit(inputInt, attributeDiEdit, inputStr);
            
            
            
            /*
            * Konfirmasi mau ngedit lagi atau ga
            * */
            System.out.print("Ingin mengedit lagi? (y/n): "); inputStr = input.nextLine();
            switch (inputStr.toLowerCase()){
                case "y": continue loop;
                case "n": break loop;
            }
        }
    }

    //============================================================================================================================================================================================================
    
    public static void menuHapusBuku(){
        loop : while (true){
            /*
            * Ngecek buku kosong ga, kalo iya maka keluar dari method ini
            * */
            if (dataBuku.isEmpty()){
                System.out.print("Buku kosong!\n");
                break loop;
            }



            /*
            * Nampilin daftar buku terus minta input pilihan buku yg ingin dihapus
            * */
            System.out.print("\nHapus Buku ->");
            for (int i = 0; i < dataBuku.size(); i++){
                System.out.print("\n      "+(i + 1)+". "+dataBuku.get(i).getJudul());
            }
            System.out.print("\nPilih index buku yang ingin dihapus: "); inputInt = input.nextInt() - 1; input.nextLine();



            /*
            * kalo input ga sesuai dengan pilihan yg dikasih, maka method ini diulang
            * */
            if (inputInt > dataBuku.size() || inputInt < 0) {
                System.out.print("pilihan tidak ada!\n");
                continue;
            }



            /*
            * Konfirmasi lagi apakah beneran mau hapus atau ga jadi
            * */
            System.out.print("Kamu yakin ingin menghapus buku \""+dataBuku.get(inputInt).getJudul()+"\"? (y/n): "); inputStr = input.nextLine();
            switch (inputStr.toLowerCase()){
                case "y":
                    BukuController2.hapus(inputInt);
                    break loop;
                default:
                    System.out.print("Batal menghapus objek.\n");
                    break loop;
            }
        }

    }

    //============================================================================================================================================================================================================
    
    public static void menuDetailBuku(){
        loop : while (true){
            /*
            * Ngecek buku kosong ga, kalo iya maka keluar dari method ini
             * */
            if (dataBuku.isEmpty()){
                System.out.print("Buku kosong!\n");
                break loop;
            }



            /*
            * Nampilin dan minta pilihan buku yang mau diliat detailnya
            * */
            System.out.print("\nDetail Buku ->");
            for (int i = 0; i < dataBuku.size(); i++){
                System.out.print("\n      "+(i + 1)+". "+dataBuku.get(i).getJudul());
            }
            System.out.print("\nPilih index buku yang ingin ditampilkan detail informasinya: "); inputInt = input.nextInt() - 1; input.nextLine();



            /*
            * Kalo buku yg pilih ga sesuai dengan yg ditunjukin, maka method ini diulang
            * */
            if (inputInt > dataBuku.size() || inputInt < 0) {
                System.out.print("pilihan tidak ada!\n");
                continue;
            }



            /*
            * Nunjukin detail buku yg dipilih
            * */
            System.out.print("" +
                    "      1. Kode Buku: "+dataBuku.get(inputInt).getKode()+"" +
                    "\n      2. Judul: "+dataBuku.get(inputInt).getJudul()+"" +
                    "\n      3. Kategori: "+dataBuku.get(inputInt).getKategori()+"" +
                    "\n      4. Pengarang: "+dataBuku.get(inputInt).getPengarang()+"" +
                    "\n      5. Tahun Terbit: "+dataBuku.get(inputInt).getTahunTerbit()+"");
            String ketersediaanBuku;
            if (dataBuku.get(inputInt).getTersedia()){
                ketersediaanBuku = "Tersedia";
            }else {
                ketersediaanBuku = "Tidak Tersedia";
            }
            switch (dataBuku.get(inputInt).getKategori().toLowerCase()){
                case "jurnal ilmiah":
                    Jurnal jurnalIlmiah = (Jurnal) dataBuku.get(inputInt);
                    System.out.print("" +
                            "\n      6. Institusi: "+jurnalIlmiah.getInstitusi()+"" +
                            "\n      7. Terindeks Sinta: "+jurnalIlmiah.getTerindeksSinta()+"" +
                            "\n      8. Ketersediaan Buku: "+ketersediaanBuku+"");
                    break;
                case "majalah":
                    Majalah majalah = (Majalah) dataBuku.get(inputInt);
                    System.out.print("" +
                            "\n      6. topik Buku: "+majalah.getTopik()+"" +
                            "\n      7. Ketersediaan Buku: "+ketersediaanBuku+"");
                    break;
                case "novel":
                    Novel novel = (Novel) dataBuku.get(inputInt);
                    System.out.print("" +
                            "\n      6. Genre Buku: "+novel.getGenre()+"" +
                            "\n      7. Ketersediaan Buku: "+ketersediaanBuku+"");
                    break;
                case "textbook":
                    TextBook textBook = (TextBook) dataBuku.get(inputInt);
                    System.out.print("" +
                            "\n      6. Bidang Ilmu: "+textBook.getBidangIlmu()+"" +
                            "\n      7. Ketersediaan Buku: "+ketersediaanBuku+"");
                    break;
            }



            /*
            *Minta konfirmasi mau liat detail buku lainnya atau engga
            * */
            System.out.print("\nIngin melihat detail buku lain juga? (y/n): "); inputStr = input.nextLine();
            switch (inputStr.toLowerCase()){
                case "y": continue loop;
                case "n": break loop;
            }
        }
    }
}
