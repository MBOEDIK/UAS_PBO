package com.SistemManajemenPerpustakaan.MVC.Views.GUI.subController;

import com.SistemManajemenPerpustakaan.DTOs.BukuDTO;
import com.SistemManajemenPerpustakaan.Enums.JenisBuku;
import com.SistemManajemenPerpustakaan.MVC.Controllers.BukuController;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Jurnal;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Novel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class EditBukuController {

    @FXML private GridPane gridPaneUmum;
    @FXML private GridPane gridPaneSpesifik;
    @FXML private Button btnSimpan;
    @FXML private Button btnBatal;

    private Buku bukuToEdit;
    private final Map<String, TextField> textFields = new HashMap<>();

    /**
     * Metode untuk menerima data buku yang akan diedit dari controller sebelumnya.
     */
    public void initData(Buku buku) {
        this.bukuToEdit = buku;
        populateForm();
    }

    /**
     * Mengisi form dengan data dari objek buku.
     */
    private void populateForm() {
        // Atribut yang tidak bisa diedit
        addRow(gridPaneUmum, "Kode", new Label(bukuToEdit.getKode()), null);
        addRow(gridPaneUmum, "Status", new Label(bukuToEdit.getTersedia() ? "Tersedia" : "Dipinjam"), null);
        // REVISI: Atribut 'Jenis' sekarang secara eksplisit dibuat non-editable.
        addRow(gridPaneUmum, "Jenis", new Label(bukuToEdit.getJenis().toString()), null);

        // Atribut umum yang bisa diedit
        addEditableRow(gridPaneUmum, "judul", "Judul", bukuToEdit.getJudul());
        addEditableRow(gridPaneUmum, "pengarang", "Pengarang", bukuToEdit.getPengarang());
        addEditableRow(gridPaneUmum, "tahunTerbit", "Tahun Terbit", bukuToEdit.getTahunTerbit());

        // Atribut spesifik berdasarkan jenis buku
        if (bukuToEdit instanceof Jurnal) {
            Jurnal j = (Jurnal) bukuToEdit;
            addEditableRow(gridPaneSpesifik, "institusiJURNAL", "Institusi", j.getInstitusi());
            addEditableRow(gridPaneSpesifik, "terindeksSintaJURNAL", "Sinta", j.getTerindeksSinta());
        } else if (bukuToEdit instanceof Novel) {
            Novel n = (Novel) bukuToEdit;
            addEditableRow(gridPaneSpesifik, "genreNOVEL", "Genre", n.getGenre());
        }
        // ... Tambahkan else if untuk Majalah dan Textbook jika diperlukan
    }

    // Ganti metode handleSimpan di file EditBukuController.java dengan ini

    @FXML
    void handleSimpan(ActionEvent event) {
        // 1. Buat objek DTO baru untuk menampung data yang telah diubah.
        BukuDTO updatedDto = new BukuDTO();

        // 2. Isi DTO dengan data dari TextField dan data asli yang tidak berubah.
        // Data yang tidak bisa diedit diambil dari objek asli
        updatedDto.kode = bukuToEdit.getKode();
        updatedDto.tersedia = bukuToEdit.getTersedia();

        // =======================================================================
        // FIX: Baris ini adalah kunci perbaikannya.
        // Pastikan Anda menyalin 'jenis' dari objek buku asli ke DTO baru.
        updatedDto.jenis = JenisBuku.valueOf(bukuToEdit.getJenis().toUpperCase());
        // =======================================================================

        // Data yang bisa diedit diambil dari textfields
        updatedDto.judul = textFields.get("judul").getText();
        updatedDto.pengarang = textFields.get("pengarang").getText();
        updatedDto.tahunTerbit = textFields.get("tahunTerbit").getText();

        // Atribut spesifik
        if (textFields.containsKey("institusiJURNAL")) {
            updatedDto.institusiJURNAL = textFields.get("institusiJURNAL").getText();
            updatedDto.terindeksSintaJURNAL = textFields.get("terindeksSintaJURNAL").getText();
        }
        if (textFields.containsKey("genreNOVEL")) {
            updatedDto.genreNOVEL = textFields.get("genreNOVEL").getText();
        }

        // 3. Panggil metode update dari business logic controller
        BukuController.updateBuku(bukuToEdit.getKode(), updatedDto);

        System.out.println("Data buku berhasil diperbarui!");

        // 4. Tutup jendela edit
        closeWindow(event);
    }

    /**
     * Aksi saat tombol "Batal" diklik.
     */
    @FXML
    void handleBatal(ActionEvent event) {
        closeWindow(event);
    }

    // --- Helper Methods ---

    /**
     * Menambahkan baris yang dapat diedit ke GridPane.
     */
    private void addEditableRow(GridPane grid, String key, String labelText, String currentValue) {
        TextField textField = new TextField(currentValue);
        textFields.put(key, textField); // Simpan referensi textfield
        addRow(grid, labelText, new Label(currentValue), textField);
    }

    /**
     * Menambahkan satu baris lengkap ke GridPane.
     */
    private void addRow(GridPane grid, String labelText, Node nodeKolom2, Node nodeKolom3) {
        int rowIndex = grid.getRowCount();
        grid.add(new Label(labelText), 0, rowIndex);
        if (nodeKolom2 != null) grid.add(nodeKolom2, 1, rowIndex);
        if (nodeKolom3 != null) grid.add(nodeKolom3, 2, rowIndex);
    }

    /**
     * Menutup jendela (Stage) saat ini.
     */
    private void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}