// INI JUAN YANG KOMEN
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
    private Button btnLoginAdmin; // PASTIKAN FX:ID INI ADA DI FXML

    @FXML
    private Button btnLoginAnggota; // PASTIKAN FX:ID INI ADA DI FXML

    // METHOD YANG DIPANGGIL SAAT TOMBOL LOGIN DITEKAN
    @FXML
    // FUNGSI UNTUK MENGALIHKAN KE HALAMAN LOGIN ADMIN.
    // MEMBUKA TAMPILAN LOGIN KHUSUS UNTUK ADMIN.
    public void actLoginAdmin(ActionEvent event) throws IOException {
        Parent loginAdmin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/LoginAdminView.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(loginAdmin);
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    @FXML
    // FUNGSI UNTUK MENGALIHKAN KE HALAMAN LOGIN ANGGOTA.
    // MEMBUKA TAMPILAN LOGIN KHUSUS UNTUK ANGGOTA PERPUSTAKAAN.
    public void actLoginAnggota(ActionEvent event) throws IOException {
        Parent loginAdmin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/LoginAnggotaView.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(loginAdmin