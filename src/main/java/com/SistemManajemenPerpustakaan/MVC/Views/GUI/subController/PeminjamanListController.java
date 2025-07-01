package com.SistemManajemenPerpustakaan.MVC.Views.GUI.subController;

import com.SistemManajemenPerpustakaan.MVC.Controllers.PeminjamanController;
import com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PeminjamanListController implements Initializable {

    @FXML
    private ListView<Peminjaman> listViewPeminjaman;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Peminjaman> semuaPeminjaman = PeminjamanController.ambilSemuaPeminjaman();
        System.out.println("Jumlah data peminjaman yang ditemukan: " + semuaPeminjaman.size());

        ObservableList<Peminjaman> observableList = FXCollections.observableArrayList(semuaPeminjaman);

        listViewPeminjaman.setItems(observableList);
        listViewPeminjaman.setCellFactory(param -> new PeminjamanCell());

        // Listener untuk membuka pop-up detail saat item diklik
        listViewPeminjaman.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleTampilDetailPopUp(newSelection);
                listViewPeminjaman.getSelectionModel().clearSelection();
            }
        });
    }

    private void handleTampilDetailPopUp(Peminjaman peminjaman) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.SistemManajemenPerpustakaan/views/subViews/DetailPeminjamanPopUpView.fxml"));
            Parent root = loader.load();

            DetailPeminjamanPopUpController controller = loader.getController();
            controller.initData(peminjaman);

            Stage stage = new Stage();
            stage.setTitle("Detail Peminjaman - ID: " + peminjaman.getId());
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Inner class untuk tampilan kustom setiap baris
    private static class PeminjamanCell extends ListCell<Peminjaman> {
        private final HBox hbox = new HBox(10);
        private final Label labelTanggal = new Label();
        private final Label labelStatus = new Label();
        private final Pane pane = new Pane(); // Untuk mendorong status ke kanan

        public PeminjamanCell() {
            super();
            hbox.getChildren().addAll(labelTanggal, pane, labelStatus);
            HBox.setHgrow(pane, Priority.ALWAYS);
        }

        @Override
        protected void updateItem(Peminjaman peminjaman, boolean empty) {
            super.updateItem(peminjaman, empty);
            if (empty || peminjaman == null) {
                setGraphic(null);
            } else {
                labelTanggal.setText("Tanggal Pinjam: " + peminjaman.getTanggalPinjam());
                labelStatus.setText("Status: " + peminjaman.getStatus());

                // Memberi warna pada status untuk kejelasan visual
                if ("Sudah dikembalikan".equals(peminjaman.getStatus())) {
                    labelStatus.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                } else {
                    labelStatus.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                }

                setGraphic(hbox);
            }
        }
    }
}