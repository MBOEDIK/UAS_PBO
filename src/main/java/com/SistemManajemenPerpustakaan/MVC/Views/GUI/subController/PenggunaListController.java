package com.SistemManajemenPerpustakaan.MVC.Views.GUI.subController;

import com.SistemManajemenPerpustakaan.MVC.Controllers.PenggunaController;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PenggunaListController implements Initializable {

    @FXML private ListView<Pengguna> listViewPengguna;
    @FXML private Button btnTambahPengguna;

    private ObservableList<Pengguna> observableListPengguna;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Pengguna> semuaPengguna = PenggunaController.ambilSemuaPengguna();
        observableListPengguna = FXCollections.observableArrayList(semuaPengguna);

        listViewPengguna.setItems(observableListPengguna);
        listViewPengguna.setCellFactory(param -> new PenggunaListCell());

        listViewPengguna.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleTampilDetail(newSelection);
                listViewPengguna.getSelectionModel().clearSelection();
            }
        });
    }

    // INNER CLASS UNTUK CUSTOM LIST CELL
    private class PenggunaListCell extends ListCell<Pengguna> {
        private final HBox hbox = new HBox(10);
        private final Label labelNama = new Label();
        private final Button btnEdit = new Button("Edit");
        private final Button btnHapus = new Button("Hapus");
        private final Pane pane = new Pane();

        public PenggunaListCell() {
            super();
            hbox.getChildren().addAll(labelNama, pane, btnEdit, btnHapus);
            HBox.setHgrow(pane, Priority.ALWAYS);

            btnEdit.setOnAction(event -> handleEdit(getItem()));
            btnHapus.setOnAction(event -> handleHapus(getItem()));
        }

        @Override
        protected void updateItem(Pengguna pengguna, boolean empty) {
            super.updateItem(pengguna, empty);
            if (empty || pengguna == null) {
                setGraphic(null);
            } else {
                labelNama.setText(pengguna.getNama());
                setGraphic(hbox);
            }
        }
    }

    // ACTION HANDLERS
    @FXML
    private void handleTambahPengguna(ActionEvent event) {
        openWindow("/com.SistemManajemenPerpustakaan/views/subViews/TambahPenggunaView.fxml", "Tambah Pengguna Baru", null, true);
    }

    private void handleTampilDetail(Pengguna pengguna) {
        openWindow("/com.SistemManajemenPerpustakaan/views/subViews/DetailPenggunaView.fxml", "Detail Pengguna", pengguna, false);
    }

    private void handleEdit(Pengguna pengguna) {
        openWindow("/com.SistemManajemenPerpustakaan/views/subViews/EditPenggunaView.fxml", "Edit Pengguna", pengguna, true);
    }

    private void handleHapus(Pengguna pengguna) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Hapus");
        alert.setHeaderText("Hapus Pengguna: " + pengguna.getNama());
        alert.setContentText("Apakah Anda yakin ingin menghapus pengguna ini?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            PenggunaController.hapusPengguna(pengguna.getId());
            observableListPengguna.remove(pengguna);
        }
    }

    private void openWindow(String fxmlFile, String title, Pengguna pengguna, boolean refreshAfterClose) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            if (pengguna != null) { // Untuk Detail dan Edit
                if (fxmlFile.contains("Detail")) {
                    ((DetailPenggunaController) loader.getController()).initData(pengguna);
                } else if (fxmlFile.contains("Edit")) {
                    ((EditPenggunaController) loader.getController()).initData(pengguna);
                }
            }

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            if (refreshAfterClose) {
                List<Pengguna> daftarPenggunaTerbaru = PenggunaController.ambilSemuaPengguna();
                observableListPengguna.clear();
                observableListPengguna.addAll(daftarPenggunaTerbaru);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}