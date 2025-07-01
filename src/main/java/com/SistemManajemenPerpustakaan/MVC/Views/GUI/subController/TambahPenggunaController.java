package com.SistemManajemenPerpustakaan.MVC.Views.GUI.subController;

import com.SistemManajemenPerpustakaan.DTOs.PenggunaDTO;
import com.SistemManajemenPerpustakaan.Enums.JenisPengguna;
import com.SistemManajemenPerpustakaan.MVC.Controllers.PenggunaController;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;
import com.SistemManajemenPerpustakaan.Utils.IdGenerator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TambahPenggunaController implements Initializable {

    @FXML private TextField textFieldNama, textFieldAlamat, textFieldNomorHp, textFieldUsername, textFieldPassword;
    @FXML private ComboBox<JenisPengguna> comboBoxJenis;
    @FXML private VBox containerAtributSpesifik;
    @FXML private Button btnSimpan, btnBatal;

    private TextField textFieldNip; // Disimpan di sini untuk akses mudah

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxJenis.setItems(FXCollections.observableArrayList(JenisPengguna.values()));
        comboBoxJenis.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateSpecificFields(newVal));
    }

    private void updateSpecificFields(JenisPengguna jenis) {
        containerAtributSpesifik.getChildren().clear();
        textFieldNip = null;

        if (jenis == JenisPengguna.ADMIN) {
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.getColumnConstraints().add(new javafx.scene.layout.ColumnConstraints(120));

            textFieldNip = new TextField();
            grid.add(new Label("NIP"), 0, 0);
            grid.add(textFieldNip, 1, 0);

            containerAtributSpesifik.getChildren().add(grid);
        }
    }

    @FXML void handleSimpan(ActionEvent event) {
        PenggunaDTO dto = new PenggunaDTO();
        dto.nama = textFieldNama.getText();
        dto.alamat = textFieldAlamat.getText();
        dto.nomorHP = textFieldNomorHp.getText();
        dto.username = textFieldUsername.getText();
        dto.password = textFieldPassword.getText();
        dto.jenis = comboBoxJenis.getValue();

        if (dto.nama.isEmpty() || dto.jenis == null) {
            new Alert(Alert.AlertType.ERROR, "Nama dan Jenis Pengguna tidak boleh kosong!").showAndWait();
            return;
        }

        // Atribut otomatis
        dto.id = IdGenerator.generateUniqueId(PenggunaController.ambilSemuaPengguna(), Pengguna::getId);

        if (dto.jenis == JenisPengguna.ADMIN) {
            dto.nipADMIN = (textFieldNip != null) ? textFieldNip.getText() : "";
        } else if (dto.jenis == JenisPengguna.ANGGOTA) {
            // Nilai default untuk Anggota baru
            dto.terlambatANGGOTA = false;
            dto.maksimalPinjamANGGOTA = 3; // Sesuai deskripsi Anda
            dto.jumlahPinjamANGGOTA = 0;
        }

        PenggunaController.tambahPengguna(dto);
        closeWindow(event);
    }

    @FXML void handleBatal(ActionEvent event) {
        closeWindow(event);
    }

    private void closeWindow(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}