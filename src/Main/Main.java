import Controllers.*;
import Models.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PenyimpananData data = new PenyimpananData();
        BukuController bukuController = new BukuController(data);
        PenggunaController penggunaController = new PenggunaController(data);
        PeminjamanController peminjamanController = new PeminjamanController(data);

        Map<String, String> akun = new HashMap<>();
        akun.put("admin", "admin123");
        akun.put("anggota", "user123");

        LoginSystem loginSystem = new LoginSystem(akun);

        while (true) {
            System.out.println("==============================================================");
            System.out.println("|=== LOGIN ===");
            System.out.println("1. Admin");
            System.out.println("2. Anggota");
            System.out.print("pilih login sebagai apa: ");
            String pilih = scanner.nextLine();

            System.out.println("==============================================================");
            System.out.print("Masukkan Username: ");
            String username = scanner.nextLine();
            System.out.print("Masukkan Password: ");
            String password = scanner.nextLine();

            boolean status = loginSystem.login(username, password);
            if (!status) {
                System.out.println("Username/Password Salah");
                continue;
            }

            if (username.equals("admin")) {
                halamanAdmin(scanner, bukuController, penggunaController, peminjamanController);
            } else {
                halamanAnggota(scanner);
            }
        }
    }

    // HALAMAN ADMIN
    public static void halamanAdmin(Scanner scanner, BukuController bukuController, PenggunaController penggunaController, PeminjamanController peminjamanController) {
        while (true) {
            System.out.println("==============================================================");
            System.out.println("--- Halaman Admin ---");
            System.out.println("1. Manajemen Buku");
            System.out.println("2. Manajemen Anggota");
            System.out.println("3. Manajemen Peminjaman");
            System.out.print("pilih menu: ");
            String menu = scanner.nextLine();

            switch (menu) {
                case "1":
                    manajemenBuku(scanner, bukuController);
                    break;
                case "2":
                    manajemenAnggota(scanner, penggunaController);
                    break;
                case "3":
                    manajemenPeminjaman(scanner, peminjamanController);
                    break;
                default:
                    System.out.println("Menu tidak tersedia");
            }
        }
    }

    // HALAMAN ANGGOTA
    public static void halamanAnggota(Scanner scanner) {
        System.out.println("==============================================================");
        System.out.println("--- Halaman Anggota ---");
        System.out.println("1. Peminjaman");
        System.out.println("2. Pengembalian");
        System.out.print("pilih menu: ");
        String pilih = scanner.nextLine();

        if (pilih.equals("1")) {
            System.out.println("--Peminjaman--");
            System.out.print("Masukkan nama lengkap Anda: ");
            scanner.nextLine();
            System.out.print("Masukkan alamat lengkap Anda: ");
            scanner.nextLine();
            System.out.print("Masukkan nomor handphone Anda: ");
            scanner.nextLine();
            System.out.print("Masukkan Judul buku: ");
            scanner.nextLine();
            System.out.print("Masukkan kategori buku: ");
            scanner.nextLine();
            System.out.println("Maaf, jumlah peminjaman buku untuk keanggotaan Anda telah dibatasi menjadi hanya 1 buku karena pernah terlambat mengembalikan buku sebelumnya.");
        } else if (pilih.equals("2")) {
            System.out.println("--Pengembalian--");
            System.out.print("Masukkan nama lengkap Anda: ");
            scanner.nextLine();
            System.out.print("Masukkan judul buku: ");
            scanner.nextLine();
            System.out.println("Keanggotaan Anda mulai saat ini akan dibatasi jumlah peminjaman bukunya menjadi 2 buku saja dikarenakan keterlambatan Anda dalam mengembalikan buku.");
            System.out.println("Terima kasih telah mengembalikan buku.");
        }
    }

    // MANAJEMEN BUKU
    public static void manajemenBuku(Scanner scanner, BukuController bukuController) {
        System.out.println("\n--Manajemen Buku--");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Edit Buku");
        System.out.println("3. Hapus Buku");
        System.out.println("4. Tampilkan Buku");
        System.out.println("5. Kembali ke halaman utama");
        System.out.print("pilih menu: ");
        int pilih = Integer.parseInt(scanner.nextLine());

        switch (pilih) {
            case 1:
                System.out.print("Masukkan kode buku: ");
                scanner.nextLine();
                System.out.print("Masukkan judul buku: ");
                scanner.nextLine();
                System.out.print("Masukkan kategori buku (Novel, Majalah, Textbook, Jurnal Ilmiah): ");
                scanner.nextLine();
                System.out.print("Masukkan pengarang buku: ");
                scanner.nextLine();
                System.out.print("Masukkan tahun terbit buku: ");
                scanner.nextLine();
                System.out.println("BUKU BERHASIL DITAMBAHKAN (dummy).");
                break;

            case 2:
                System.out.println("Edit Buku:");
                System.out.println("1. Buku x");
                System.out.println("2. Buku y");
                System.out.println("3. Buku z");
                System.out.print("Pilih index buku yang ingin diedit: ");
                scanner.nextLine();
                System.out.println("1. Kode Buku");
                System.out.println("2. Judul Buku");
                System.out.println("3. Kategori Buku");
                System.out.println("4. Pengarang Buku");
                System.out.println("5. Tahun Terbit Buku");
                System.out.print("pilih index pengeditan: ");
                scanner.nextLine();
                System.out.print("Masukkan data baru: ");
                scanner.nextLine();
                System.out.print("Ingin mengedit lagi? (y/n): ");
                scanner.nextLine();
                break;

            case 3:
                System.out.print("Masukkan kode buku: ");
                scanner.nextLine();
                System.out.print("Apakah Anda yakin ingin menghapus? (y/n): ");
                scanner.nextLine();
                System.out.println("BUKU DIHAPUS JIKA ADA (dummy).");
                break;

            case 4:
                System.out.println("Tampilkan Buku:");
                System.out.println("1. Buku x");
                System.out.println("2. Buku y");
                System.out.println("3. Buku z");
                break;

            default:
                break;
        }
    }

    // MANAJEMEN ANGGOTA
    public static void manajemenAnggota(Scanner scanner, PenggunaController penggunaController) {
        System.out.println("\n-- Manajemen Anggota --");
        System.out.println("1. Tambah Anggota");
        System.out.println("2. Hapus Anggota");
        System.out.println("3. Tampilkan Anggota");
        System.out.println("4. Kembali");
        System.out.print("pilih menu: ");
        int pilih = Integer.parseInt(scanner.nextLine());

        switch (pilih) {
            case 1:
                System.out.print("Masukkan ID Pengguna: ");
                scanner.nextLine();
                System.out.print("Masukkan Nama Pengguna: ");
                scanner.nextLine();
                System.out.println("PENGGUNA BERHASIL DITAMBAHKAN (dummy).");
                break;

            case 2:
                System.out.print("Masukkan ID Pengguna: ");
                scanner.nextLine();
                System.out.println("PENGGUNA BERHASIL DIHAPUS JIKA ADA (dummy).");
                break;

            case 3:
                System.out.println("1. Anggota A");
                System.out.println("2. Anggota B");
                break;

            default:
                break;
        }
    }

    // MANAJEMEN PEMINJAMAN
    public static void manajemenPeminjaman(Scanner scanner, PeminjamanController peminjamanController) {
        System.out.println("\n--Manajemen Peminjaman--");
        System.out.println("1. Tambah Peminjaman");
        System.out.println("2. Edit Peminjaman");
        System.out.println("3. Hapus Peminjaman");
        System.out.println("4. Tampilkan Peminjaman");
        System.out.println("5. Kembali ke halaman utama");
        System.out.print("pilih menu: ");
        int menu = Integer.parseInt(scanner.nextLine());

        switch (menu) {
            case 1:
                System.out.print("Masukkan id peminjaman: ");
                scanner.nextLine();
                System.out.print("Masukkan id Anggota: ");
                scanner.nextLine();
                System.out.print("Masukkan kode buku: ");
                scanner.nextLine();
                System.out.print("Tentukan deadline (3 hari, 1 minggu, 2 minggu): ");
                scanner.nextLine();
                System.out.println("PEMINJAMAN BERHASIL DITAMBAHKAN (dummy).");
                break;

            case 2:
                System.out.println("Edit Peminjaman:");
                System.out.println("1. Peminjaman x");
                System.out.println("2. Peminjaman y");
                System.out.println("3. Peminjaman z");
                System.out.print("Pilih index peminjaman yang ingin diedit: ");
                scanner.nextLine();
                System.out.println("1. Id Peminjaman");
                System.out.println("2. Id Anggota");
                System.out.println("3. Kode Buku");
                System.out.println("4. Deadline");
                System.out.println("5. Status keterlambatan");
                System.out.print("pilih index pengeditan: ");
                int index = Integer.parseInt(scanner.nextLine());
                if (index == 5) {
                    System.out.print("Pilih status (tepat waktu/terlambat): ");
                    scanner.nextLine();
                } else {
                    System.out.print("Masukkan data baru: ");
                    scanner.nextLine();
                }
                System.out.print("Ingin mengedit lagi? (y/n): ");
                scanner.nextLine();
                break;

            case 3:
                System.out.println("Hapus Peminjaman:");
                System.out.println("1. Peminjaman x");
                System.out.println("2. Peminjaman y");
                System.out.println("3. Peminjaman z");
                System.out.print("Pilih index peminjaman yang ingin dihapus: ");
                scanner.nextLine();
                System.out.print("Apakah Anda yakin ingin menghapus? (y/n): ");
                scanner.nextLine();
                break;

            case 4:
                System.out.println("Tampilkan Peminjaman:");
                System.out.println("1. Peminjaman x");
                System.out.println("2. Peminjaman y");
                System.out.println("3. Peminjaman z");
                break;

            case 5:
                return;

            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }
    }
}
