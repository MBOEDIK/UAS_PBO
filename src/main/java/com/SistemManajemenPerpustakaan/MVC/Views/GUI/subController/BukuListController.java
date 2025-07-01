package com.SistemManajemenPerpustakaan.MVC.Views.GUI.subController;
import com.SistemManajemenPerpustakaan.MVC.Controllers.BukuController;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
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

public class BukuListController implements Initializable {

    // Deklarasi elemen dari FXML dengan anotasi @FXML
    @FXML
    private ListView<Buku> listViewBuku;

    // ObservableList untuk menyimpan data buku agar ListView dapat memantaunya
    private ObservableList<Buku> observableListBuku;

    @FXML
    private Button btnTambahBuku;

    /**
     * Metode ini dijalankan secara otomatis setelah FXML dimuat.
     * Digunakan untuk inisialisasi awal.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 1. Muat data dari business logic controller
        List<Buku> semuaBuku = BukuController.ambilSemuaBuku();
        observableListBuku = FXCollections.observableArrayList(semuaBuku);

        // 2. Set item ke ListView
        listViewBuku.setItems(observableListBuku);

        // 3. Atur CellFactory untuk membuat tampilan kustom per baris
        listViewBuku.setCellFactory(param -> new BukuListCell());

        // 4. Tambahkan listener untuk menangani klik pada item list (di luar tombol)
        listViewBuku.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleTampilDetail(newSelection);
                // Kosongkan seleksi agar item yang sama bisa diklik lagi nanti
                listViewBuku.getSelectionModel().clearSelection();
            }
        });
    }

    public void handleTampilDetail(ListView.EditEvent<Buku> bukuEditEvent) {
    }

    /**
     * Inner class untuk mendefinisikan bagaimana setiap sel/baris dalam ListView akan ditampilkan.
     */
    private class BukuListCell extends ListCell<Buku> {
        private final HBox hbox = new HBox(10); // HBox untuk menampung label dan tombol
        private final Label labelJudul = new Label();
        private final Button btnEdit = new Button("Edit");
        private final Button btnHapus = new Button("Hapus");
        private final Pane pane = new Pane(); // Pane kosong untuk mendorong tombol ke kanan

        public BukuListCell() {
            super();
            hbox.getChildren().addAll(labelJudul, pane, btnEdit, btnHapus);
            HBox.setHgrow(pane, Priority.ALWAYS); // Membuat pane fleksibel agar mengisi ruang kosong

            btnEdit.setOnAction(event -> {
                Buku buku = getItem();
                if (buku != null) {
                    handleEdit(buku);
                }
            });

            btnHapus.setOnAction(event -> {
                Buku buku = getItem();
                if (buku != null) {
                    handleHapus(buku);
                }
            });
        }

        @Override
        protected void updateItem(Buku buku, boolean empty) {
            super.updateItem(buku, empty);
            if (empty || buku == null) {
                setText(null);
                setGraphic(null);
            } else {
                // Atur judul buku pada label
                labelJudul.setText(buku.getJudul());
                // Tampilkan HBox yang sudah dirancang sebagai grafik sel
                setGraphic(hbox);
            }
        }
    }

    /**
     * Method action ketika tombol "Edit" diklik.
     * Membuka jendela baru untuk mengedit buku.
     */
    @FXML
    private void handleEdit(Buku buku) {
        try {
            // 1. Buat FXMLLoader untuk memuat file FXML edit
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.SistemManajemenPerpustakaan/views/subViews/EditBukuView.fxml"));
            Parent root = loader.load();

            // 2. Dapatkan instance controller dari jendela edit yang baru dimuat
            EditBukuController editController = loader.getController();

            // 3. Panggil metode initData() pada editController untuk mengirim objek buku
            editController.initData(buku);

            // 4. Buat Stage (jendela) baru untuk menampilkan form edit
            Stage stage = new Stage();
            stage.setTitle("Edit Data - " + buku.getJudul());
            stage.setScene(new Scene(root));

            // 5. Atur modality agar jendela utama tidak bisa di-klik sebelum jendela edit ditutup
            stage.initModality(Modality.APPLICATION_MODAL);

            // 6. Tampilkan jendela dan tunggu sampai pengguna selesai mengedit
            stage.showAndWait();
            List<Buku> daftarBukuTerbaru = BukuController.ambilSemuaBuku();
            observableListBuku.clear();
            observableListBuku.addAll(daftarBukuTerbaru);

            // 7. (PENTING) Refresh ListView untuk menampilkan data yang mungkin sudah berubah
            listViewBuku.refresh();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Gagal Memuat Tampilan Edit");
            alert.setContentText("File EditBukuView.fxml tidak ditemukan atau rusak.");
            alert.showAndWait();
        };
    }

    /**
     * Method action ketika tombol "Hapus" diklik.
     * Menghapus item dari list dan database.
     */
    @FXML
    private void handleHapus(Buku buku) {
        // Tampilkan dialog konfirmasi sebelum menghapus
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Hapus");
        alert.setHeaderText("Hapus Buku");
        alert.setContentText("Apakah Anda yakin ingin menghapus buku berjudul \"" + buku.getJudul() + "\"?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Panggil metode dari business logic controller untuk menghapus dari database
            BukuController.hapusBuku(buku.getKode());

            // Hapus buku dari ObservableList, yang akan otomatis memperbarui ListView
            observableListBuku.remove(buku);

            System.out.println("Buku \"" + buku.getJudul() + "\" berhasil dihapus.");
        }
    }

    /**
     * (Opsional) Helper method untuk memuat jendela baru (detail/edit).
     * Anda perlu membuat file FXML dan Controller yang sesuai (misal: DetailBukuController).
     */
    private void loadWindow(String fxmlFile, String title, Buku buku) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Dapatkan controller dari jendela baru untuk mengirim data buku
            // Contoh jika controller Anda bernama DetailBukuController
            // DetailBukuController controller = loader.getController();
            // controller.initData(buku);

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));

            // Modality.APPLICATION_MODAL membuat jendela utama tidak bisa diklik sampai jendela baru ditutup
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            // Tampilkan error kepada pengguna jika file FXML tidak ditemukan
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Gagal Memuat Tampilan");
            alert.setContentText("Tidak dapat memuat file: " + fxmlFile);
            alert.showAndWait();
        }
    }

    /**
     * Method action ketika item di list (bukan tombolnya) diklik.
     * Membuka jendela baru untuk menampilkan detail buku.
     */

    @FXML
    private void handleTampilDetail(Buku buku) {
        try {
            // 1. Buat FXMLLoader untuk memuat file FXML detail
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.SistemManajemenPerpustakaan/views/subViews/DetailBukuView.fxml"));
            Parent root = loader.load();

            // 2. Dapatkan instance controller dari jendela detail yang baru dimuat
            DetailBukuController detailController = loader.getController();

            // 3. Panggil metode initData() pada detailController untuk mengirim objek buku
            detailController.initData(buku);

            // 4. Buat Stage (jendela) baru untuk menampilkan tampilan detail
            Stage stage = new Stage();
            stage.setTitle("Detail Buku - " + buku.getJudul());
            stage.setScene(new Scene(root));

            // 5. Atur modality agar jendela utama tidak bisa di-klik sebelum jendela detail ditutup
            stage.initModality(Modality.APPLICATION_MODAL);

            // 6. Tampilkan jendela dan tunggu sampai ditutup
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            // Opsional: Tampilkan pesan error jika FXML gagal dimuat
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Gagal Memuat Tampilan");
            alert.setContentText("File DetailBukuView.fxml tidak ditemukan atau rusak.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleTambahBuku(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.SistemManajemenPerpustakaan/views/subViews/TambahBukuView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Tambah Buku Baru");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            // Tampilkan jendela dan tunggu sampai ditutup
            stage.showAndWait();

            // Setelah jendela tambah ditutup, muat ulang data untuk menampilkan buku baru
            List<Buku> daftarBukuTerbaru = BukuController.ambilSemuaBuku();
            observableListBuku.clear();
            observableListBuku.addAll(daftarBukuTerbaru);

        } catch (IOException e) {
            e.printStackTrace();
            // Tampilkan pesan error jika FXML gagal dimuat
        }
    }
}