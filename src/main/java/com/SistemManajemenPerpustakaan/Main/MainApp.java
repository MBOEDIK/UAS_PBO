// INI JUAN YANG KASI KOMEN
// SEMOGA BERMANFAAT BUAT NGERTIIN KODENYA

package com.SistemManajemenPerpustakaan.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * KELAS UTAMA UNTUK MENJALANKAN APLIKASI
 * EXTEND DARI JAVAFX APPLICATION
 */
public class MainApp extends Application {

    /**
     * METHOD YANG DIPANGGIL SAAT APLIKASI DIMULAI
     * MEMUAT FONT, FXML, DAN CSS
     */
    @Override
    public void start(Stage stage) throws IOException {

        // MEMUAT VARIAN FONT POPPINS DARI RESOURCE
        // AKAN TAMPIL PESAN JIKA BERHASIL ATAU GAGAL
        try {
            Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Thin.ttf"), 10);
            Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Regular.ttf"), 10);
            Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Bold.ttf"), 10);
            Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Black.ttf"), 10);
            System.out.println("Varian font Poppins berhasil dimuat.");
        } catch (Exception e) {
            System.err.println("Gagal memuat font Poppins: " + e.getMessage());
        }

        // MEMUAT FILE FXML UNTUK TAMPILAN LOGIN
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/com.SistemManajemenPerpustakaan/views/LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 938, 602);

        // MENAMBAHKAN FILE CSS KE SCENE
        String cssPath = Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/StyleUtama.css")).toExternalForm();
        scene.getStylesheets().add(cssPath);

        // MENGATUR STAGE UTAMA DAN MENAMPILKANNYA
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * METHOD UTAMA UNTUK MENJALANKAN APLIKASI
     * AKAN MEMANGGIL launch() DARI JAVAFX
     */
    public static void main(String[] args) {
        launch(args);
    }
}
