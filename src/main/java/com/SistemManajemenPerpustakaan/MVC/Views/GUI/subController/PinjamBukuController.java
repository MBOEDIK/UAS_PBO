package com.SistemManajemenPerpustakaan.MVC.Views.GUI.subController;

import com.SistemManajemenPerpustakaan.DTOs.PeminjamanDTO;
import com.SistemManajemenPerpustakaan.MVC.Controllers.BukuController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.LoginController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.PeminjamanController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.PenggunaController;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Anggota;
import com.SistemManajemenPerpustakaan.Utils.DateTimeTools;
import com.SistemManajemenPerpustakaan.Utils.IdGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PinjamBukuController implements Initializable {

    @FXML private ListView<Buku> listViewBukuTersedia;
    @FXML private Button btnPinjam, btnBatal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ambil semua buku dan filter yang hanya tersedia
        List<Buku> bukuTersedia = BukuController.ambilSemuaBuku().stream()
                .filter(Buku::getTersedia)
                .collect(Collectors.toList());

        ObservableList<Buku> observableList = FXCollections.observableArrayList(bukuTersedia);
        listViewBukuTersedia.setItems(observableList);

        // Tampilkan judul buku di list
        listViewBukuTersedia.setCellFactory(param -> new ListCell<Buku>() {
            @Override
            protected void updateItem(Buku item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getJudul());
                }
            }
        });
    }

    @FXML
    void handlePinjam(ActionEvent event) {
        Buku bukuDipilih = listViewBukuTersedia.getSelectionModel().getSelectedItem();
        // Asumsi ada cara untuk mendapatkan pengguna yang sedang login
        Anggota penggunaSaatIni = (Anggota) LoginController.getPenggunaSaatIni();

        // Validasi
        if (bukuDipilih == null) {
            new Alert(Alert.AlertType.ERROR, "Silakan pilih buku terlebih dahulu!").showAndWait();
            return;
        }
        if (penggunaSaatIni == null) {
            new Alert(Alert.AlertType.ERROR, "Sesi tidak valid. Silakan login kembali.").showAndWait();
            return;
        }
        if (penggunaSaatIni.getJumlahPinjam() >= penggunaSaatIni.getMaksimalPinjam()) {
            new Alert(Alert.AlertType.WARNING, "Peminjaman ditolak! Anda telah mencapai batas maksimal peminjaman.").showAndWait();
            return;
        }

        // Proses peminjaman jika validasi lolos
        PeminjamanDTO dto = new PeminjamanDTO();
        dto.id = IdGenerator.generateUniqueId(PeminjamanController.ambilSemuaPeminjaman(), Peminjaman::getId);
        dto.idAnggota = penggunaSaatIni.getId();
        dto.kodeBuku = bukuDipilih.getKode();
        dto.tanggalPinjam = DateTimeTools.getTanggalHariIni();
        dto.deadline = DateTimeTools.buatDeadline(7);
        dto.tanggalKembali = "-";
        dto.status = "Belum Dikembalikan";

        // Eksekusi transaksi
        PeminjamanController.tambahPeminjaman(dto);
        BukuController.updateAtribut(bukuDipilih.getKode(), "tersedia", false);
        PenggunaController.updateAtribut(penggunaSaatIni.getId(), "jumlahPinjam", (penggunaSaatIni.getJumlahPinjam() + 1));

        new Alert(Alert.AlertType.INFORMATION, "Buku berhasil dipinjam! Deadline: " + dto.deadline).showAndWait();
        closeWindow(event);
    }

    @FXML
    void handleBatal(ActionEvent event) {
        closeWindow(event);
    }

    private void closeWindow(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}