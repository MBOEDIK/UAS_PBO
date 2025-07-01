package com.SistemManajemenPerpustakaan.MVC.Views.GUI.subController;

import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Jurnal;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Majalah;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Novel;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.TextBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetailBukuController {

    // Elemen FXML untuk atribut umum
    @FXML private Label labelKode;
    @FXML private Label labelJudul;
    @FXML private Label labelPengarang;
    @FXML private Label labelTahunTerbit;
    @FXML private Label labelStatus;
    @FXML private Label labelJenis;

    // VBox untuk menampung atribut spesifik secara dinamis
    @FXML private VBox vboxAtributSpesifik;

    @FXML private Button btnTutup;

    /**
     * Metode ini berfungsi untuk menerima objek Buku dari controller sebelumnya
     * dan menggunakannya untuk mengisi data pada tampilan detail.
     * @param buku Objek buku yang dipilih dari list.
     */
    public void initData(Buku buku) {
        // 1. Isi semua atribut umum
        labelKode.setText(buku.getKode());
        labelJudul.setText(buku.getJudul());
        labelPengarang.setText(buku.getPengarang());
        labelTahunTerbit.setText(buku.getTahunTerbit());
        labelStatus.setText(buku.getTersedia() ? "Tersedia" : "Tidak Tersedia");
        labelJenis.setText(buku.getJenis().toString());

        // 2. Kosongkan VBox terlebih dahulu untuk memastikan tidak ada data lama
        vboxAtributSpesifik.getChildren().clear();

        // 3. Tambahkan atribut spesifik berdasarkan jenis buku
        // Menggunakan instanceof untuk memeriksa tipe objek sebenarnya
        if (buku instanceof Jurnal) {
            Jurnal jurnal = (Jurnal) buku;
            tambahAtributSpesifik("Institusi:", jurnal.getInstitusi());
            tambahAtributSpesifik("Terindeks Sinta:", jurnal.getTerindeksSinta());
        } else if (buku instanceof Novel) {
            Novel novel = (Novel) buku;
            tambahAtributSpesifik("Genre:", novel.getGenre());
        } else if (buku instanceof Majalah) {
            Majalah majalah = (Majalah) buku;
            tambahAtributSpesifik("Topik:", majalah.getTopik());
        } else if (buku instanceof TextBook) {
            TextBook textbook = (TextBook) buku;
            tambahAtributSpesifik("Bidang Ilmu:", textbook.getBidangIlmu());
        }
    }

    /**
     * Helper method untuk membuat dan menambahkan baris atribut spesifik ke VBox.
     * @param namaAtribut Nama atribut (misal: "Genre:").
     * @param nilaiAtribut Nilai dari atribut tersebut.
     */
    private void tambahAtributSpesifik(String namaAtribut, String nilaiAtribut) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.getColumnConstraints().add(new javafx.scene.layout.ColumnConstraints(100));

        Label labelNama = new Label(namaAtribut);
        Label labelNilai = new Label(nilaiAtribut != null ? nilaiAtribut : "-");
        labelNilai.setWrapText(true);

        grid.add(labelNama, 0, 0);
        grid.add(labelNilai, 1, 0);

        vboxAtributSpesifik.getChildren().add(grid);
    }

    /**
     * Method action untuk menutup jendela (stage) saat ini.
     */
    @FXML
    private void handleTutup(ActionEvent event) {
        // Dapatkan stage saat ini dari elemen manapun di scene, lalu tutup
        Stage stage = (Stage) btnTutup.getScene().getWindow();
        stage.close();
    }
}