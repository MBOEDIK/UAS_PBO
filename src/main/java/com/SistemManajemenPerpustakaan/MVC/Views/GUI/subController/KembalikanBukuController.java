package com.SistemManajemenPerpustakaan.MVC.Views.GUI.subController;

import com.SistemManajemenPerpustakaan.MVC.Controllers.BukuController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.LoginController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.PeminjamanController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.PenggunaController;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Models.Peminjaman;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Anggota;
import com.SistemManajemenPerpustakaan.Utils.DateTimeTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class KembalikanBukuController implements Initializable {

    @FXML private ListView<Peminjaman> listViewPinjamanAktif;
    private ObservableList<Peminjaman> observableListPinjaman;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataPinjaman();
    }

    private void loadDataPinjaman() {
        Anggota penggunaSaatIni = (Anggota) LoginController.getPenggunaSaatIni();
        if (penggunaSaatIni == null) return;

        List<Peminjaman> pinjamanAktif = PeminjamanController.ambilSemuaPeminjaman().stream()
                .filter(p -> p.getIdAnggota().equals(penggunaSaatIni.getId()) && p.getStatus().equals("Belum Dikembalikan"))
                .collect(Collectors.toList());

        observableListPinjaman = FXCollections.observableArrayList(pinjamanAktif);
        listViewPinjamanAktif.setItems(observableListPinjaman);
        listViewPinjamanAktif.setCellFactory(param -> new PeminjamanAktifCell());
    }

    private void handleKembalikan(Peminjaman peminjaman) {
        Anggota penggunaSaatIni = (Anggota) LoginController.getPenggunaSaatIni();
        long sisaHari = DateTimeTools.sisaHariMenujuDeadline(peminjaman.getDeadline());

        String pesanKonfirmasi = "Buku dikembalikan tepat waktu.";
        if (sisaHari < 0) {
            pesanKonfirmasi = "Anda terlambat mengembalikan buku. Batas peminjaman Anda akan dikurangi.";
        }

        // Proses pengembalian
        PeminjamanController.updateAtribut(peminjaman.getId(), "tanggalKembali", DateTimeTools.getTanggalHariIni());
        PeminjamanController.updateAtribut(peminjaman.getId(), "status", "Sudah dikembalikan");
        BukuController.updateAtribut(peminjaman.getKodebuku(), "tersedia", true);
        PenggunaController.updateAtribut(penggunaSaatIni.getId(), "jumlahPinjam", (penggunaSaatIni.getJumlahPinjam() - 1));

        if (sisaHari < 0) {
            PenggunaController.updateAtribut(penggunaSaatIni.getId(), "maksimalPinjam", (penggunaSaatIni.getMaksimalPinjam() - 1));
            PenggunaController.updateAtribut(penggunaSaatIni.getId(), "terlambat", true);
        }

        new Alert(Alert.AlertType.INFORMATION, pesanKonfirmasi).showAndWait();

        // Refresh list setelah pengembalian
        loadDataPinjaman();
    }

    // INNER CLASS UNTUK CUSTOM LIST CELL
    private class PeminjamanAktifCell extends ListCell<Peminjaman> {
        private final HBox hbox = new HBox(10);
        private final VBox vBoxInfo = new VBox(2);
        private final Label labelJudul = new Label();
        private final Label labelDeadline = new Label();
        private final Button btnKembalikan = new Button("Kembalikan");
        private final Pane pane = new Pane();

        public PeminjamanAktifCell() {
            super();
            labelJudul.setStyle("-fx-font-weight: bold;");
            vBoxInfo.getChildren().addAll(labelJudul, labelDeadline);
            hbox.getChildren().addAll(vBoxInfo, pane, btnKembalikan);
            HBox.setHgrow(pane, Priority.ALWAYS);
            hbox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

            btnKembalikan.setOnAction(event -> {
                Peminjaman item = getItem();
                if (item != null) {
                    handleKembalikan(item);
                }
            });
        }

        @Override
        protected void updateItem(Peminjaman peminjaman, boolean empty) {
            super.updateItem(peminjaman, empty);
            if (empty || peminjaman == null) {
                setGraphic(null);
            } else {
                Buku buku = BukuController.ambilBuku(peminjaman.getKodebuku());
                labelJudul.setText(buku != null ? buku.getJudul() : "Buku tidak ditemukan");
                labelDeadline.setText("Deadline: " + peminjaman.getDeadline());
                setGraphic(hbox);
            }
        }
    }
}