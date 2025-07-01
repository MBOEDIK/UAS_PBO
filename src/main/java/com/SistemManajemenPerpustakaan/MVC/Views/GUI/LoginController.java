package com.SistemManajemenPerpustakaan.MVC.Views.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class LoginController {
    @FXML
    private Button btnLoginAdmin; // Pastikan fx:id ini ada di FXML

    @FXML
    private Button btnLoginAnggota; // Pastikan fx:id ini ada di FXML

    // Method yang dipanggil saat tombol login ditekan
    @FXML
    public void actLoginAdmin(ActionEvent event) throws IOException {
        Parent loginAdmin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/LoginAdminView.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(loginAdmin);
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    @FXML
    public void actLoginAnggota(ActionEvent event) throws IOException {
        Parent loginAdmin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/LoginAnggotaView.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(loginAdmin);
        stage.sizeToScene();
        stage.centerOnScreen();
    }
}