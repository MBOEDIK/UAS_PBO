    package com.SistemManajemenPerpustakaan.Sementara.SubMenuAdmin;

    import com.SistemManajemenPerpustakaan.MVC.Controllers.Users.PenggunaController2;
    import com.SistemManajemenPerpustakaan.MVC.Models.User.Admin;
    import com.SistemManajemenPerpustakaan.MVC.Models.User.Anggota;
    import com.SistemManajemenPerpustakaan.Utils.IdGenerator;
    import com.SistemManajemenPerpustakaan.Utils.DataAccessHelper;

    import java.util.Scanner;

    public class SubMenuManajemenPengguna implements DataAccessHelper {

        //ATTRIBUTES
        private static String inputStr;
        private static Boolean inputBool;

        //OBJECTS
        private static Scanner input = new Scanner(System.in);

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
                    PenggunaController2.tambah(IdGenerator.generate(), jenisPengguna, namaPengguna, alamatPengguna, nomorHpPengguna, username, password, nipAdmin);
                    break;
                case "anggota":
                    System.out.print("*Buat Username: "); username = input.nextLine();
                    System.out.print("*Buat Password: "); password = input.nextLine();
                    PenggunaController2.tambah(IdGenerator.generate(), jenisPengguna, namaPengguna, jenisPengguna, nomorHpPengguna, username, password, true, 3, 0);
                    break;
                default:
                    System.out.print("Jenis Pengguna tidak valid, batal menambahkan Pengguna.\n");
            }
        }

        //============================================================================================================================================================================================================

        public static void menuEditPengguna(){
            int inputInt, subInputInt;

            loop : while (true){
                if (dataPengguna.size() == 0){
                    System.out.print("Pengguna kosong!\n");
                    break loop;
                }

                System.out.print("\nEdit Pengguna ->");
                for (int i = 0; i < dataPengguna.size(); i++){
                    System.out.print("\n      "+(i + 1)+". "+dataPengguna.get(i).getNama());
                }
                System.out.print("\nPilih index Pengguna yang ingin diedit: "); inputInt = input.nextInt() - 1; input.nextLine();

                if (inputInt > dataPengguna.size()) {
                    System.out.print("pilihan tidak ada!\n");
                    continue;
                }

                System.out.print("" +
                        "      1. Jenis Pengguna" +
                        "\n      2. Nama Pengguna" +
                        "\n      3. Alamat Pengguna" +
                        "\n      4. Nomor Handphone Pengguna");

                switch (dataPengguna.get(inputInt).getJenis().toLowerCase()){
                    case "admin":
                        System.out.print("" +
                                "\n      5. NIP Admin" +
                                "\n      6. Username" +
                                "\n      7. Password");
                        break;
                    case "anggota":
                        System.out.print("" +
                                "\n      5. Username" +
                                "\n      6. Password");
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
                        switch (dataPengguna.get(inputInt).getJenis().toLowerCase()){
                            case "admin": placeHolder1 = "NIP Admin baru"; attributeDiEdit = "nipAdmin"; break;
                            case "anggota": placeHolder1 = "username baru"; attributeDiEdit = "username"; break;
                        }
                        break;
                    case 6:
                        switch (dataPengguna.get(inputInt).getJenis().toLowerCase()){
                            case "admin": placeHolder1 = "username baru"; attributeDiEdit = "username"; break;
                            case "anggota": placeHolder1 = "password baru"; attributeDiEdit = "password"; break;
                        }
                        break;
                    case 7:
                        placeHolder1 = "password baru"; attributeDiEdit = "password";
                        break;
                }

                System.out.print("Masukkan "+placeHolder1+": "); inputStr = input.nextLine();

                PenggunaController2.edit(inputInt, attributeDiEdit, inputStr);

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
                if (dataPengguna.size() == 0){
                    System.out.print("Pengguna kosong!\n");
                    break loop;
                }

                System.out.print("\nHapus Pengguna ->");
                for (int i = 0; i < dataPengguna.size(); i++){
                    System.out.print("\n      "+(i + 1)+". "+dataPengguna.get(i).getNama());
                }
                System.out.print("\nPilih index Pengguna yang ingin dihapus: "); inputInt = input.nextInt() - 1; input.nextLine();

                if (inputInt > dataPengguna.size() || inputInt < 0) {
                    System.out.print("pilihan tidak ada!\n");
                    continue;
                }

                System.out.print("Kamu yakin ingin menghapus Pengguna \""+dataPengguna.get(inputInt).getNama()+"\"? (y/n): "); inputStr = input.nextLine();

                switch (inputStr.toLowerCase()){
                    case "y":
                        PenggunaController2.hapus(inputInt);
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
                if (dataPengguna.size() == 0){
                    System.out.print("Pengguna kosong!\n");
                    break loop;
                }

                System.out.print("\nDetail Pengguna ->");
                for (int i = 0; i < dataPengguna.size(); i++){
                    System.out.print("\n      "+(i + 1)+". "+dataPengguna.get(i).getNama());
                }
                System.out.print("\nPilih index Pengguna yang ingin ditampilkan detail informasinya: "); inputInt = input.nextInt() - 1; input.nextLine();

                if (inputInt > dataPengguna.size() || inputInt < 0) {
                    System.out.print("pilihan tidak ada!\n");
                    continue;
                }

                System.out.print("" +
                        "      1. ID Pengguna: "+dataPengguna.get(inputInt).getId()+"" +
                        "\n      2. Nama Pengguna: "+dataPengguna.get(inputInt).getNama()+"" +
                        "\n      3. Jenis Pengguna: "+dataPengguna.get(inputInt).getJenis()+"" +
                        "\n      4. Alamat Pengguna: "+dataPengguna.get(inputInt).getAlamat()+"" +
                        "\n      5. Nomor HP Pengguna: "+dataPengguna.get(inputInt).getNomorHPPengguna()+"");

                switch (dataPengguna.get(inputInt).getJenis().toLowerCase()){
                    case "admin":
                        Admin admin = (Admin) dataPengguna.get(inputInt);
                        System.out.print("" +
                                "\n      6. NIP Pengguna: "+admin.getNip()+"" +
                                "\n      7. Username: "+admin.getUsername()+"" +
                                "\n      8. Password: "+admin.getPassword()+"");
                        break;
                    case "anggota":
                        Anggota anggota = (Anggota) dataPengguna.get(inputInt);
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
                                "\n      8. Jumlah Pinjam Buku: "+anggota.getJumlahPinjamBuku()+"" +
                                "\n      9. Username: "+anggota.getUsername()+"" +
                                "\n      10. Password: "+anggota.getPassword()+"");
                        break;
                }

                System.out.print("\nIngin melihat detail Pengguna lain juga? (y/n): "); inputStr = input.nextLine();

                switch (inputStr.toLowerCase()){
                    case "y": continue loop;
                    case "n": break loop;
                }
            }
        }

        //============================================================================================================================================================================================================
    }
