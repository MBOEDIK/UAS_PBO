package com.SistemManajemenPerpustakaan.Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Membuat elemen GUI sederhana (sementara) sebagai tampilan awal
        Label label = new Label("Halo, ini GUI awal dari Manajer Peminjaman Buku!");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 500, 300);

        // Mengatur judul dan menampilkan jendela utama
        primaryStage.setTitle("Sistem Manajemen Peminjaman Buku");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
