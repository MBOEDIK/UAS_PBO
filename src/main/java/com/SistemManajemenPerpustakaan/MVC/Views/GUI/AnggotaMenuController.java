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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AnggotaMenuController implements Initializable {

    @FXML private BorderPane contentArea;
    @FXML private Button btnPinjamBuku;
    @FXML private Button btnBackToAnggotaLogin;

    // SAAT PERTAMA DIBUKA LANGSUNG TAMPILKAN HALAMAN PENGEMBALIAN BUKU
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPage("/com.SistemManajemenPerpustakaan/views/subViews/KembalikanBukuView.fxml");
    }

    // MEMUAT HALAMAN KE DALAM TAMPILAN UTAMA
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

    // TAMPILKAN POPUP UNTUK PINJAM BUKU
    // SETELAH ITU SEGARKAN TAMPILAN
    @FXML
    public void actPinjamBuku(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/subViews/PinjamBukuView.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Pinjam Buku");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            loadPageIntoPlaceholder("/com.SistemManajemenPerpustakaan/views/subViews/KembalikanBukuView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MEMUAT HALAMAN PENGEMBALIAN BUKU KE DALAM BORDERPANE
    private void loadPageIntoPlaceholder(String fxmlFile) {
        try {
            Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/subViews/KembalikanBukuView.fxml")));
            contentArea.setCenter(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TOMBOL UNTUK KEMBALI KE LOGIN ANGGOTA
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
