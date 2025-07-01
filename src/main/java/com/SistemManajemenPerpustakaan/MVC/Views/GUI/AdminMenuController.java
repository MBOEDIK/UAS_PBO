package com.SistemManajemenPerpustakaan.MVC.Views.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class AdminMenuController {
    @FXML
    private Button btnAdminSubmit;

    @FXML
    private Button btnAdminBack;


    @FXML
    private Button btnDetailPeminjaman;

    @FXML
    private BorderPane contentArea;

    private void loadPage(String fxmlPath) {
        try {
            // Muat FXML sub-halaman
            Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            // Tempatkan FXML yang sudah dimuat ke tengah BorderPane placeholder
            contentArea.setCenter(page);
        } catch (IOException e) {
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
