package com.SistemManajemenPerpustakaan.MVC.Views.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class AnggotaMenuController {
    @FXML
    private Button btnAdminSubmit;

    @FXML
    private Button btnAdminBack;

    @FXML
    public void actAdminSubmit(ActionEvent event) throws IOException {

    }

    @FXML
    public void actBackToAnggotaLogin(ActionEvent event) throws IOException {
        Parent loginAdmin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/LoginAnggotaView.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(loginAdmin);
        stage.sizeToScene();
        stage.centerOnScreen();
    }
}
