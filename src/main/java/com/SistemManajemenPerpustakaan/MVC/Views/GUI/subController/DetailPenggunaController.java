package com.SistemManajemenPerpustakaan.MVC.Views.GUI.subController;

import com.SistemManajemenPerpustakaan.MVC.Models.User.Admin;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Anggota;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetailPenggunaController {

    @FXML private Label labelId, labelJenis, labelNama, labelAlamat, labelNomorHp, labelUsername;
    @FXML private VBox vboxAtributSpesifik;
    @FXML private Button btnTutup;

    public void initData(Pengguna pengguna) {
        labelId.setText(pengguna.getId());
        labelJenis.setText(pengguna.getJenis());
        labelNama.setText(pengguna.getNama());
        labelAlamat.setText(pengguna.getAlamat());
        labelNomorHp.setText(pengguna.getNomorHP());
        labelUsername.setText(pengguna.getUsername());

        vboxAtributSpesifik.getChildren().clear();

        if (pengguna instanceof Admin) {
            Admin admin = (Admin) pengguna;
            tambahAtributSpesifik("NIP:", admin.getNip());
        } else if (pengguna instanceof Anggota) {
            Anggota anggota = (Anggota) pengguna;
            tambahAtributSpesifik("Status Terlambat:", anggota.getTerlambat() ? "Ya" : "Tidak");
            tambahAtributSpesifik("Maksimal Pinjam:", String.valueOf(anggota.getMaksimalPinjam()));
            tambahAtributSpesifik("Jumlah Dipinjam:", String.valueOf(anggota.getJumlahPinjam()));
        }
    }

    private void tambahAtributSpesifik(String namaAtribut, String nilaiAtribut) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.getColumnConstraints().add(new javafx.scene.layout.ColumnConstraints(120));
        grid.add(new Label(namaAtribut), 0, 0);
        grid.add(new Label(nilaiAtribut != null ? nilaiAtribut : "-"), 1, 0);
        vboxAtributSpesifik.getChildren().add(grid);
    }

    @FXML
    private void handleTutup(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}