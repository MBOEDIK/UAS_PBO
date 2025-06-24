package Main.Menu.SubMenuAdmin;

import Controllers.BukuController;
import Controllers.PenggunaController;
import Models.Book.JurnalIlmiah;
import Models.Book.Majalah;
import Models.Book.Novel;
import Models.Book.TextBook;
import Models.PenyimpananData;
import Models.User.Admin;
import Models.User.Anggota;
import Tools.IdGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SubMenuManajemenPengguna {

    //ATTRIBUTES
    private static String inputStr;
    private static Boolean inputBool;

    //OBJECTS
    static Scanner input = new Scanner(System.in);

    //METHODS
    //============================================================================================================================================================================================================

    public static void menuTambahPengguna(){

        System.out.print("\nTambah Pengguna ->");
        System.out.print("\nJenis Pengguna (Admin/Anggota): ");
        String jenisPengguna = input.nextLine();

        System.out.print("Nama lengkap Pengguna: "); String namaPengguna = input.nextLine();
        System.out.print("Alamat lengkap Pengguna: "); String alamatPengguna = input.nextLine();
        System.out.print("Nomor handphone Pengguna: "); String nomorHpPengguna = input.nextLine();

        String username, password;

        switch (jenisPengguna.toLowerCase()){
            case "admin":
                System.out.print("NIP Admin: "); String nipAdmin = input.nextLine();
                System.out.print("*Buat Username: "); username = input.nextLine();
                System.out.print("*Buat Password: "); password = input.nextLine();
                PenggunaController.tambah(IdGenerator.idPengguna(), jenisPengguna, namaPengguna, alamatPengguna, nomorHpPengguna, username, password, nipAdmin);
                break;
            case "anggota":
                System.out.print("*Buat Username: "); username = input.nextLine();
                System.out.print("*Buat Password: "); password = input.nextLine();
                PenggunaController.tambah(IdGenerator.idPengguna(), jenisPengguna, namaPengguna, jenisPengguna, nomorHpPengguna, username, password, true, 3);
                break;
            default:
                System.out.print("Jenis Pengguna tidak valid, batal menambahkan Pengguna.\n");
        }
    }

    //============================================================================================================================================================================================================

    public static void menuEditPengguna(){
        int inputInt, subInputInt;

        loop : while (true){
            if (PenyimpananData.getPengguna().size() == 0){
                System.out.print("Pengguna kosong!\n");
                break loop;
            }

            System.out.print("\nEdit Pengguna ->");
            for (int i = 0; i < PenyimpananData.getPengguna().size(); i++){
                System.out.print("\n      "+(i + 1)+". "+PenyimpananData.getPengguna().get(i).getNamaPengguna());
            }
            System.out.print("\nPilih index Pengguna yang ingin diedit: "); inputInt = input.nextInt() - 1; input.nextLine();

            if (inputInt > PenyimpananData.getPengguna().size()) {
                System.out.print("pilihan tidak ada!\n");
                continue;
            }

            System.out.print("" +
                    "      1. Jenis Pengguna" +
                    "\n      2. Nama Pengguna" +
                    "\n      3. Alamat Pengguna" +
                    "\n      4. Nomor Handphone Pengguna");

            switch (PenyimpananData.getPengguna().get(inputInt).getJenisPengguna().toLowerCase()){
                case "admin":
                    System.out.print("" +
                            "\n      6. NIP Admin" +
                            "\n      7. Username" +
                            "\n      8. Password");
                    break;
                case "anggota":
                    System.out.print("" +
                            "\n      6. Username" +
                            "\n      7. Password");
                    break;
            }

            System.out.print("\nPilih index pengeditan: "); subInputInt = input.nextInt(); input.nextLine();

            String placeHolder1 = "", attributeDiEdit = "";
            Boolean nilaiBoolean = true;

            switch (subInputInt){
                case 1: placeHolder1 = "jenis baru"; attributeDiEdit = "jenis"; break;
                case 2: placeHolder1 = "nama baru"; attributeDiEdit = "nama"; break;
                case 3: placeHolder1 = "alamat baru"; attributeDiEdit = "alamat";  break;
                case 4: placeHolder1 = "nomor handphone baru"; attributeDiEdit = "nomorHP"; break;
                case 5:
                    switch (PenyimpananData.getPengguna().get(inputInt).getJenisPengguna().toLowerCase()){
                        case "admin": placeHolder1 = "NIP Admin baru"; attributeDiEdit = "nipAdmin"; break;
                        case "anggota": placeHolder1 = "terlambat mengembalikan baru (y/n)"; attributeDiEdit = "terlambatMengembalikan"; break;
                    }
                    break;
                case 6:
                    switch (PenyimpananData.getPengguna().get(inputInt).getJenisPengguna().toLowerCase()){
                        case "admin": placeHolder1 = "username baru"; attributeDiEdit = "username"; break;
                        case "anggota": placeHolder1 = "maksimal pinjam buku baru (masukkan angka)"; attributeDiEdit = "maksimalPinjamBuku"; break;
                    }
                    break;
                case 7:
                    switch (PenyimpananData.getPengguna().get(inputInt).getJenisPengguna().toLowerCase()){
                        case "admin": placeHolder1 = "password baru"; attributeDiEdit = "password"; break;
                        case "anggota": placeHolder1 = "username baru"; attributeDiEdit = "username"; break;
                    }
                    break;
                case 8:
                    placeHolder1 = "password baru"; attributeDiEdit = "password"; break;
            }

            System.out.print("Masukkan "+placeHolder1+": "); inputStr = input.nextLine();

            if (subInputInt == 6 && PenyimpananData.getPengguna().get(inputInt).getJenisPengguna().toLowerCase().equals("anggota")){
                switch (inputStr.toLowerCase()){
                    case "y": nilaiBoolean = true; break;
                    case "n": nilaiBoolean = false; break;
                }
                PenggunaController.edit(inputInt, attributeDiEdit, nilaiBoolean);
            }
            if (subInputInt == 7 && PenyimpananData.getPengguna().get(inputInt).getJenisPengguna().toLowerCase().equals("anggota")){
                for (char c : inputStr.toCharArray()){
                    if (!Character.isDigit(c)){
                        System.out.print("Mengedit Pengguna batal dikarenakan input bukan angka.\n");
                        continue loop;
                    }
                }
                PenggunaController.edit(inputInt, attributeDiEdit, Integer.parseInt(inputStr));
            }

            PenggunaController.edit(inputInt, attributeDiEdit, inputStr);

            System.out.print("Ingin mengedit lagi? (y/n): "); inputStr = input.nextLine();

            switch (inputStr.toLowerCase()){
                case "y": continue loop;
                case "n": break loop;
            }
        }
    }

    //============================================================================================================================================================================================================

    public static void menuHapusPengguna(){
        int inputInt, subInputInt;
        String inputStr;

        loop : while (true){
            if (PenyimpananData.getPengguna().size() == 0){
                System.out.print("Pengguna kosong!\n");
                break loop;
            }

            System.out.print("\nHapus Pengguna ->");
            for (int i = 0; i < PenyimpananData.getPengguna().size(); i++){
                System.out.print("\n      "+(i + 1)+". "+PenyimpananData.getPengguna().get(i).getNamaPengguna());
            }
            System.out.print("\nPilih index Pengguna yang ingin dihapus: "); inputInt = input.nextInt() - 1; input.nextLine();

            if (inputInt > PenyimpananData.getPengguna().size() || inputInt < 0) {
                System.out.print("pilihan tidak ada!\n");
                continue;
            }

            System.out.print("Kamu yakin ingin menghapus Pengguna \""+PenyimpananData.getPengguna().get(inputInt).getNamaPengguna()+"\"? (y/n): "); inputStr = input.nextLine();

            switch (inputStr.toLowerCase()){
                case "y":
                    PenggunaController.hapus(inputInt);
                    break loop;
                default:
                    System.out.print("Batal menghapus objek.\n");
                    break loop;
            }
        }

    }

    //============================================================================================================================================================================================================

    public static void menuDetailPengguna(){
        int inputInt, subInputInt;
        String inputStr;

        loop : while (true){
            if (PenyimpananData.getPengguna().size() == 0){
                System.out.print("Pengguna kosong!\n");
                break loop;
            }

            System.out.print("\nDetail Pengguna ->");
            for (int i = 0; i < PenyimpananData.getPengguna().size(); i++){
                System.out.print("\n      "+(i + 1)+". "+PenyimpananData.getPengguna().get(i).getNamaPengguna());
            }
            System.out.print("\nPilih index Pengguna yang ingin ditampilkan detail informasinya: "); inputInt = input.nextInt() - 1; input.nextLine();

            if (inputInt > PenyimpananData.getPengguna().size() || inputInt < 0) {
                System.out.print("pilihan tidak ada!\n");
                continue;
            }

            System.out.print("" +
                    "      1. ID Pengguna: "+PenyimpananData.getPengguna().get(inputInt).getIdPengguna()+"" +
                    "\n      2. Nama Pengguna: "+PenyimpananData.getPengguna().get(inputInt).getNamaPengguna()+"" +
                    "\n      3. Jenis Pengguna: "+PenyimpananData.getPengguna().get(inputInt).getJenisPengguna()+"" +
                    "\n      4. Alamat Pengguna: "+PenyimpananData.getPengguna().get(inputInt).getAlamatPengguna()+"" +
                    "\n      5. Nomor HP Pengguna: "+PenyimpananData.getPengguna().get(inputInt).getNomorHPPengguna()+"");

            switch (PenyimpananData.getPengguna().get(inputInt).getJenisPengguna().toLowerCase()){
                case "admin":
                    Admin admin = (Admin) PenyimpananData.getPengguna().get(inputInt);
                    System.out.print("" +
                            "\n      6. NIP Pengguna: "+admin.getNipAdmin()+"" +
                            "\n      7. Username: "+admin.getUsername()+"" +
                            "\n      8. Password: "+admin.getPassword()+"");
                    break;
                case "anggota":
                    Anggota anggota = (Anggota) PenyimpananData.getPengguna().get(inputInt);
                    String terlambatMengembalikan;
                    if (anggota.getTerlambatMengembalikan()){
                        terlambatMengembalikan = "Ya";
                    }
                    else {
                        terlambatMengembalikan = "Tidak";
                    }
                    System.out.print("" +
                            "\n      6. Terlambat Mengembalikan: "+terlambatMengembalikan+"" +
                            "\n      7. Maksimal Pinjam Buku: "+anggota.getMaksimalPinjamBuku()+"" +
                            "\n      8. Username: "+anggota.getUsername()+"" +
                            "\n      9. Password: "+anggota.getPassword()+"");
                    break;
            }

            System.out.print("\nIngin melihat detail buku lain juga? (y/n): "); inputStr = input.nextLine();

            switch (inputStr.toLowerCase()){
                case "y": continue loop;
                case "n": break loop;
            }
        }
    }

    //============================================================================================================================================================================================================
    }
