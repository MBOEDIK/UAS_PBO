package com.SistemManajemenPerpustakaan.MVC.Views.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AnggotaMenuController implements Initializable {

    // Deklarasi elemen FXML yang akan digunakan
    @FXML private BorderPane contentArea;
    @FXML private Button btnPinjamBuku;
    @FXML private Button btnBackToAnggotaLogin;

    /**
     * Metode ini dijalankan secara otomatis saat FXML selesai dimuat.
     * Kita akan langsung menampilkan halaman "Kembalikan Buku" di sini.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 5. Langsung panggil metode untuk memuat halaman daftar peminjaman
        loadPage("/com.SistemManajemenPerpustakaan/views/subViews/KembalikanBukuView.fxml");
    }

    private void loadPage(String fxmlPath) {
        try {
            Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            contentArea.setCenter(page);
        } catch (IOException | NullPointerException e) {
            // MENAMPILKAN ALERT JIKA FILE TIDAK DITEMUKAN ATAU GAGAL DIMUAT
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Memuat Halaman");
            alert.setHeaderText("Gagal memuat file: " + fxmlPath);
            alert.setContentText("Pesan Error: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    /**
     * Aksi untuk membuka jendela pop-up "Pinjam Buku".
     */
    @FXML
    public void actPinjamBuku(ActionEvent event) {
        try {
            // Muat FXML untuk pop-up
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/subViews/PinjamBukuView.fxml")));

            // Buat Stage (jendela) baru
            Stage stage = new Stage();
            stage.setTitle("Pinjam Buku");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // Blokir jendela utama

            // Tampilkan jendela dan tunggu sampai ditutup
            stage.showAndWait();

            // Setelah pop-up ditutup, segarkan kembali daftar pinjaman
            // untuk menampilkan buku yang baru saja dipinjam.
            loadPageIntoPlaceholder("/com.SistemManajemenPerpustakaan/views/subViews/KembalikanBukuView.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper untuk memuat FXML ke dalam placeholder contentArea.
     */
    private void loadPageIntoPlaceholder(String fxmlFile) {
        try {
            Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/subViews/KembalikanBukuView.fxml")));
            contentArea.setCenter(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metode logout, tidak ada perubahan
    @FXML
    public void actBackToAnggotaLogin(ActionEvent event) throws IOException {
        Parent loginAnggota = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/LoginAnggotaView.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(loginAnggota);
        stage.sizeToScene();
        stage.centerOnScreen();
    }
}