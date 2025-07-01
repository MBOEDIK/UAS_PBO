package com.SistemManajemenPerpustakaan.MVC.Views.GUI.subController;

import com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DetailPeminjamanPopUpController {

    @FXML private Label labelIdPeminjaman, labelIdAnggota, labelKodeBuku, labelTglPinjam, labelTglKembali, labelDeadline, labelStatus;
    @FXML private Button btnTutup;

    public void initData(Peminjaman peminjaman) {
        labelIdPeminjaman.setText(peminjaman.getId());
        labelIdAnggota.setText(peminjaman.getIdAnggota());
        labelKodeBuku.setText(peminjaman.getKodebuku());
        labelTglPinjam.setText(peminjaman.getTanggalPinjam());
        labelTglKembali.setText(peminjaman.getTanggalKembali());
        labelDeadline.setText(peminjaman.getDeadline());
        labelStatus.setText(peminjaman.getStatus());
    }

    @FXML
    private void handleTutup(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}