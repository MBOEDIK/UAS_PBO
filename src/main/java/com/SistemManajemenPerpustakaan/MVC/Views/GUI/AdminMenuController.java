// INI YANG KOMEN JUAN

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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    @FXML
    private Button btnAdminSubmit;
    @FXML
    private Button btnAdminBack;
    @FXML
    private Button btnDetailPeminjaman;
    @FXML
    private BorderPane contentArea;

    // SAAT PERTAMA DIBUKA LANGSUNG TAMPILKAN HALAMAN PEMINJAMAN
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPage("/com.SistemManajemenPerpustakaan/views/subViews/PeminjamanListView.fxml");
    }

    // MEMUAT HALAMAN BARU KE TENGAH LAYAR
    // TAMPILKAN ERROR JIKA GAGAL
    private void loadPage(String fxmlPath) {
        try {
            Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            contentArea.setCenter(page);
        } catch (IOException | NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR MEMUAT HALAMAN");
            alert.setHeaderText("GAGAL MEMUAT FILE: " + fxmlPath);
            alert.setContentText("PESAN ERROR: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    // TOMBOL UNTUK KEMBALI KE LOGIN ADMIN
    @FXML
    public void actBackToAdminLogin(ActionEvent event) throws IOException {
        Parent loginAdmin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/LoginAdminView.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(loginAdmin);
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    // TOMBOL UNTUK BUKA MENU MANAJEMEN BUKU
    @FXML
    public void actManajemenBuku(ActionEvent event) throws IOException {
        loadPage("/com.SistemManajemenPerpustakaan/views/subViews/BukuListView.fxml");
    }

    // TOMBOL UNTUK BUKA MENU MANAJEMEN PENGGUNA
    public void actManajemenPengguna(ActionEvent event) throws IOException {
        Parent loginAdmin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/subViews/PenggunaListView.fxml")));
        loadPage("/com.SistemManajemenPerpustakaan/views/subViews/PenggunaListView.fxml");
    }

    // TOMBOL UNTUK MELIHAT DAFTAR PEMINJAMAN
    @FXML
    public void actDetailPeminjaman(ActionEvent event) {
        loadPage("/com.SistemManajemenPerpustakaan/views/subViews/PeminjamanListView.fxml");
    }
}
