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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 5. Langsung panggil metode untuk memuat halaman daftar peminjaman
        loadPage("/com.SistemManajemenPerpustakaan/views/subViews/PeminjamanListView.fxml");
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

    @FXML
    public void actBackToAdminLogin(ActionEvent event) throws IOException {
        Parent loginAdmin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/LoginAdminView.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(loginAdmin);
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    @FXML
    public void actManajemenBuku(ActionEvent event) throws IOException {
        loadPage("/com.SistemManajemenPerpustakaan/views/subViews/BukuListView.fxml");
    }

    public void actManajemenPengguna(ActionEvent event) throws IOException {
        Parent loginAdmin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/subViews/PenggunaListView.fxml")));
        loadPage("/com.SistemManajemenPerpustakaan/views/subViews/PenggunaListView.fxml");
    }

    @FXML
    public void actDetailPeminjaman(ActionEvent event) {
        // Panggil helper untuk memuat halaman daftar peminjaman
        loadPage("/com.SistemManajemenPerpustakaan/views/subViews/PeminjamanListView.fxml");
    }
}
