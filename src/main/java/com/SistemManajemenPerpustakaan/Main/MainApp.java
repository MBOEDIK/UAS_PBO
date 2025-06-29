package com.SistemManajemenPerpustakaan.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application { // <- Extend Application

    public static void main(String[] args) {
        launch(args); // <- Jalankan JavaFX
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Load FXML (roti)
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));

        // Setup Scene (piring)
        Scene scene = new Scene(root, 800, 600);

        // Kasih ke Stage (meja)
        stage.setScene(scene);
        stage.setTitle("Burger Apps");
        stage.show();
    }
}