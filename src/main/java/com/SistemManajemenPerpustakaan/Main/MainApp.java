package com.SistemManajemenPerpustakaan.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        try {
            Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Thin.ttf"), 10);
            Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Regular.ttf"), 10);
            Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Bold.ttf"), 10);
            Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Black.ttf"), 10);
            System.out.println("Varian font Poppins berhasil dimuat.");
        } catch (Exception e) {
            System.err.println("Gagal memuat font Poppins: " + e.getMessage());
        }

        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/com.SistemManajemenPerpustakaan/views/LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 938, 602);

        String cssPath = Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/StyleUtama.css")).toExternalForm();
        scene.getStylesheets().add(cssPath);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}