// INI JUAN YANG KASI KOMEN
// NGATUR FITUR ADMIN BUAT TAMBAH, LIHAT, EDIT, HAPUS PENGGUNA

package com.SistemManajemenPerpustakaan.MVC.Controllers;

import com.SistemManajemenPerpustakaan.DTOs.PenggunaDTO;
import com.SistemManajemenPerpustakaan.Enums.JenisPengguna;
import com.SistemManajemenPerpustakaan.MVC.Controllers.Factory.DTOtoModel;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;
import com.SistemManajemenPerpustakaan.MVC.Views.Console.PenggunaView;
import com.SistemManajemenPerpustakaan.Repositories.PenggunaRepository;
import com.SistemManajemenPerpustakaan.Utils.IdGenerator;
import com.SistemManajemenPerpustakaan.Utils.InformationPrinter;

import java.util.List;

public class PenggunaController {

    // OBJEK VIEW UNTUK INTERAKSI USER DI TERMINAL
    private static PenggunaView view = new PenggunaView();

    // MENU UTAMA UNTUK KELOLA PENGGUNA
    // DIJALANKAN DI MENU ADMIN
    public static void kelolaMenuPengguna() {
        while (true) {
            int pilihan = view.tampilkanMenuPengguna();
            switch (pilihan) {
                case 1 -> handleTambahPengguna();      // TAMBAH USER BARU
                case 2 -> handleDetailPengguna();      // LIHAT DETAIL PENGGUNA
                case 3 -> handleUpdatePengguna();      // UPDATE DATA PENGGUNA
                case 4 -> handleHapusPengguna();       // HAPUS PENGGUNA
                case 5 -> return;                      // KELUAR KE MENU SEBELUMNYA
                default -> view.tampilkanPesan("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    // HANDLE LOGIKA PENAMBAHAN USER BARU
    // BISA JADI ADMIN ATAU ANGGOTA
    public static void handleTambahPengguna() {
        view.tampilkanHeaderTambahPengguna();
        int jenisPilihan = view.mintaJenisPengguna();

        PenggunaDTO penggunaDTO = new PenggunaDTO();

        switch (jenisPilihan) {
            case 1: // ADMIN
                view.mintaInputAtributPengguna(penggunaDTO, "nama", "alamat", "nomorHP", "username", "password", "nipADMIN");
                penggunaDTO.jenis = JenisPengguna.ADMIN;
                break;
            case 2: // ANGGOTA
                view.mintaInputAtributPengguna(penggunaDTO, "nama", "alamat", "nomorHP", "username", "password");
                penggunaDTO.jenis = JenisPengguna.ANGGOTA;
                penggunaDTO.terlambatANGGOTA = false;
                penggunaDTO.maksimalPinjamANGGOTA = 5;
                penggunaDTO.jumlahPinjamANGGOTA = 0;
                break;
            default:
                view.tampilkanPesan("Pilihan jenis pengguna tidak valid.");
                return;
        }

        // BUAT ID UNIK LALU TAMBAHKAN KE DATABASE
        penggunaDTO.id = IdGenerator.generateUniqueId(PenggunaController.ambilSemuaPengguna(), Pengguna::getId);
        PenggunaController.tambahPengguna(penggunaDTO);
        view.tampilkanPesan("Pengguna baru berhasil ditambahkan dengan ID: " + penggunaDTO.id);
    }

    // LIHAT DETAIL TIAP PENGGUNA
    // ADMIN BISA PILIH USER MANA SAJA
    public static void handleDetailPengguna() {
        while (true) {
            if (isPenggunaKosong()) return;

            List<Pengguna> semuaPengguna = PenggunaController.ambilSemuaPengguna();
            view.tampilkanDaftarPengguna(semuaPengguna, "Pengguna yang tersedia");

            int pilihan = view.mintaPilihanPengguna("tampilkan detailnya");

            if (pilihan > 0 && pilihan <= semuaPengguna.size()) {
                Pengguna pengguna = semuaPengguna.get(pilihan - 1);
                view.tampilkanDetailPengguna(pengguna);
            } else {
                view.tampilkanPesan("Pilihan tidak valid.");
            }

            if (!view.mintaKonfirmasi("\nApakah anda ingin melihat detail pengguna lainnya?").equals("Y")) {
                break;
            }
        }
    }

    // UPDATE ATRIBUT USER SEPERTI NAMA, HP, DLL
    public static void handleUpdatePengguna() {
        while (true) {
            if (isPenggunaKosong()) return;

            List<Pengguna> semuaPengguna = PenggunaController.ambilSemuaPengguna();
            view.tampilkanDaftarPengguna(semuaPengguna, "Pilih pengguna untuk di-update");

            int pilihan = view.mintaPilihanPengguna("update");

            if (pilihan > 0 && pilihan <= semuaPengguna.size()) {
                Pengguna pengguna = semuaPengguna.get(pilihan - 1);

                // GUNAKAN LAMBDA UNTUK UPDATE TIAP ATRIBUT
                InformationPrinter.tampilkanUpdateAtribut(
                        pengguna,
                        " ",
                        pengguna.getId(),
                        data -> PenggunaController.updateAtribut(data.id, data.namaAtribut, data.nilaiBaru),
                        0, "id", "jenis"); // ID DAN JENIS TIDAK BOLEH DIUBAH
            } else {
                view.tampilkanPesan("Pilihan tidak valid.");
            }

            if (!view.mintaKonfirmasi("\nApakah anda ingin meng-update pengguna lainnya?").equals("Y")) {
                break;
            }
        }
    }

    // HAPUS USER BERDASARKAN PILIHAN
    public static void handleHapusPengguna() {
        if (isPenggunaKosong()) return;

        List<Pengguna> semuaPengguna = PenggunaController.ambilSemuaPengguna();
        view.tampilkanDaftarPengguna(semuaPengguna, "Pilih pengguna untuk dihapus");

        int pilihan = view.mintaPilihanPengguna("hapus");

        if (pilihan > 0 && pilihan <= semuaPengguna.size()) {
            Pengguna pengguna = semuaPengguna.get(pilihan - 1);
            String konfirmasi = view.mintaKonfirmasiHapus(pengguna.getNama());

            if (konfirmasi.equals("Y")) {
                PenggunaController.hapusPengguna(pengguna.getId());
                view.tampilkanPesan("Pengguna berhasil dihapus.");
            } else {
                view.tampilkanPesan("Hapus pengguna dibatalkan.");
            }
        } else {
            view.tampilkanPesan("Pilihan tidak valid.");
        }
    }

    // CEK APAKAH DATA PENGGUNA MASIH KOSONG
    private static boolean isPenggunaKosong() {
        if (PenggunaController.ambilSemuaPengguna().isEmpty()) {
            view.tampilkanPesan("Data pengguna kosong!");
            return true;
        }
        return false;
    }

    // ======================== CRUD SECTION ========================

    // TAMBAHKAN DATA PENGGUNA BARU KE REPOSITORY
    public static void tambahPengguna(PenggunaDTO dto){
        Pengguna pengguna = DTOtoModel.toPengguna(dto);
        PenggunaRepository.tambah(pengguna);
    }

    // AMBIL DATA USER BERDASARKAN ID
    public static Pengguna ambilPengguna(String kodePengguna){
        return PenggunaRepository.ambilPenggunaById(kodePengguna);
    }

    // AMBIL SEMUA USER YANG TERDAFTAR
    public static List<Pengguna> ambilSemuaPengguna(){
        return PenggunaRepository.ambilSemua();
    }

    // UPDATE SATU ATRIBUT PENGGUNA
    public static boolean updateAtribut(String kodePengguna, String atribut, Object nilaiBaru) {
        return PenggunaRepository.updateAtribut(kodePengguna, atribut, nilaiBaru);
    }

    // GANTI DATA LAMA DENGAN OBJEK BARU
    public static void updatePengguna(String kodePengguna, PenggunaDTO dto){
        Pengguna pengguna = DTOtoModel.toPengguna(dto);
        PenggunaRepository.updatePengguna(kodePengguna, pengguna);
    }

    // HAPUS PENGGUNA DARI DATABASE
    public static void hapusPengguna(String kodePengguna) {
        PenggunaRepository.hapusPengguna(kodePengguna);
    }
}
