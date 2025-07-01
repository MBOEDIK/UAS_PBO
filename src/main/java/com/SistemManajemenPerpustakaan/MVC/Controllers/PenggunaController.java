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

    // Letakkan kode ini di dalam class PenggunaController Anda
// Pastikan untuk memiliki instance dari PenggunaView, misalnya:
    private static PenggunaView view = new PenggunaView();

    /**
     * Mengelola alur utama untuk menu manajemen pengguna.
     * Metode ini berisi loop utama yang akan terus berjalan sampai pengguna memilih untuk kembali.
     */
    public static void kelolaMenuPengguna() {
        while (true) {
            int pilihan = view.tampilkanMenuPengguna();
            switch (pilihan) {
                case 1:
                    handleTambahPengguna();
                    break;
                case 2:
                    handleDetailPengguna();
                    break;
                case 3:
                    handleUpdatePengguna();
                    break;
                case 4:
                    handleHapusPengguna();
                    break;
                case 5:
                    return; // Kembali ke menu sebelumnya (misal: Halaman Admin)
                default:
                    view.tampilkanPesan("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    /**
     * Menangani logika untuk menambahkan pengguna baru.
     */
    public static void handleTambahPengguna() {
        view.tampilkanHeaderTambahPengguna();
        int jenisPilihan = view.mintaJenisPengguna();

        PenggunaDTO penggunaDTO = new PenggunaDTO();

        switch (jenisPilihan) {
            case 1: // Admin
                view.mintaInputAtributPengguna(penggunaDTO, "nama", "alamat", "nomorHP", "username", "password", "nipADMIN");
                penggunaDTO.jenis = JenisPengguna.ADMIN;
                break;
            case 2: // Anggota
                view.mintaInputAtributPengguna(penggunaDTO, "nama", "alamat", "nomorHP", "username", "password");
                penggunaDTO.jenis = JenisPengguna.ANGGOTA;
                // Nilai default untuk anggota baru diatur di sini, bukan di View
                penggunaDTO.terlambatANGGOTA = false;
                penggunaDTO.maksimalPinjamANGGOTA = 5; // Contoh nilai default
                penggunaDTO.jumlahPinjamANGGOTA = 0;
                break;
            default:
                view.tampilkanPesan("Pilihan jenis pengguna tidak valid.");
                return;
        }

        // Logika pembuatan ID dan penyimpanan data adalah murni tanggung jawab Controller
        penggunaDTO.id = IdGenerator.generateUniqueId(PenggunaController.ambilSemuaPengguna(), Pengguna::getId);
        PenggunaController.tambahPengguna(penggunaDTO);
        view.tampilkanPesan("Pengguna baru berhasil ditambahkan dengan ID: " + penggunaDTO.id);
    }

    /**
     * Menangani logika untuk menampilkan detail pengguna.
     */
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

    /**
     * Menangani logika untuk memperbarui atribut pengguna.
     */
    public static void handleUpdatePengguna() {
        while (true) {
            if (isPenggunaKosong()) return;

            List<Pengguna> semuaPengguna = PenggunaController.ambilSemuaPengguna();
            view.tampilkanDaftarPengguna(semuaPengguna, "Pilih pengguna untuk di-update");

            int pilihan = view.mintaPilihanPengguna("update");

            if (pilihan > 0 && pilihan <= semuaPengguna.size()) {
                Pengguna pengguna = semuaPengguna.get(pilihan - 1);

                // Lambda untuk logika update didefinisikan di dalam Controller.
                // InformationPrinter dipanggil dari sini, bukan dari View, karena mengandung logika bisnis.
                InformationPrinter.tampilkanUpdateAtribut(
                        pengguna,
                        " ",
                        pengguna.getId(),
                        data -> PenggunaController.updateAtribut(data.id, data.namaAtribut, data.nilaiBaru),
                        0, "id", "jenis"); // Atribut yang tidak bisa diubah
            } else {
                view.tampilkanPesan("Pilihan tidak valid.");
            }

            if (!view.mintaKonfirmasi("\nApakah anda ingin meng-update pengguna lainnya?").equals("Y")) {
                break;
            }
        }
    }

    /**
     * Menangani logika untuk menghapus pengguna.
     */
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

    /**
     * Metode helper internal di dalam Controller untuk memeriksa apakah ada pengguna.
     * @return true jika tidak ada pengguna, false jika ada.
     */
    private static boolean isPenggunaKosong() {
        if (PenggunaController.ambilSemuaPengguna().isEmpty()) {
            view.tampilkanPesan("Data pengguna kosong!");
            return true;
        }
        return false;
    }


//    //RUN
//    public static void jalankanPenggunaView(){
//        loop : while (true){
//            int pilihan = PenggunaView.menuPengguna();
//            switch (pilihan){
//                case 1: PenggunaView.tambahPengguna(); break;
//                case 2: if (!PenggunaView.isPenggunaKosong()) PenggunaView.detailPengguna(); break;
//                case 3: if (!PenggunaView.isPenggunaKosong()) PenggunaView.updatePengguna(); break;
//                case 4: if (!PenggunaView.isPenggunaKosong()) PenggunaView.hapusPengguna(); break;
//                case 5: break loop;
//            }
//        }
//    }

    //CREATE
    public static void tambahPengguna(PenggunaDTO dto){
        Pengguna pengguna = DTOtoModel.toPengguna(dto);
        PenggunaRepository.tambah(pengguna);
    }

    //READ
    public static Pengguna ambilPengguna(String kodePengguna){
        return  PenggunaRepository.ambilPenggunaById(kodePengguna);
    }

    public static List<Pengguna> ambilSemuaPengguna(){
        return PenggunaRepository.ambilSemua();
    }

    //UPDATE
    public static boolean updateAtribut(String kodePengguna, String atribut, Object nilaiBaru) {
        return PenggunaRepository.updateAtribut(kodePengguna, atribut, nilaiBaru);
    }

    public static void updatePengguna(String kodePengguna, PenggunaDTO dto){
        Pengguna pengguna = DTOtoModel.toPengguna(dto);
        PenggunaRepository.updatePengguna(kodePengguna, pengguna);
    }

    //DELETE
    public static void hapusPengguna(String kodePengguna) {
        PenggunaRepository.hapus(kodePengguna);
    }
}
