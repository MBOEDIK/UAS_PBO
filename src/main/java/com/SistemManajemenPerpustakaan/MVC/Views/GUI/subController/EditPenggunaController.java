package com.SistemManajemenPerpustakaan.MVC.Views.GUI.subController;

import com.SistemManajemenPerpustakaan.DTOs.PenggunaDTO;
import com.SistemManajemenPerpustakaan.Enums.JenisPengguna;
import com.SistemManajemenPerpustakaan.MVC.Controllers.PenggunaController;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Admin;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Anggota;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;
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

public class EditPenggunaController {

    @FXML private GridPane gridPaneUmum;
    @FXML private GridPane gridPaneSpesifik;
    @FXML private Button btnSimpan, btnBatal;

    private Pengguna penggunaToEdit;
    private final Map<String, TextField> textFields = new HashMap<>();

    public void initData(Pengguna pengguna) {
        this.penggunaToEdit = pengguna;
        populateForm();
    }

    private void populateForm() {
        // Atribut yang tidak bisa diedit
        addRow(gridPaneUmum, "ID", new Label(penggunaToEdit.getId()), null);
        addRow(gridPaneUmum, "Jenis", new Label(penggunaToEdit.getJenis()), null);

        // Atribut umum yang bisa diedit
        addEditableRow(gridPaneUmum, "nama", "Nama", penggunaToEdit.getNama());
        addEditableRow(gridPaneUmum, "alamat", "Alamat", penggunaToEdit.getAlamat());
        addEditableRow(gridPaneUmum, "nomorHP", "Nomor HP", penggunaToEdit.getNomorHP());
        addEditableRow(gridPaneUmum, "username", "Username", penggunaToEdit.getUsername());
        addEditableRow(gridPaneUmum, "password", "Password", penggunaToEdit.getPassword());

        if (penggunaToEdit instanceof Admin) {
            addEditableRow(gridPaneSpesifik, "nipADMIN", "NIP", ((Admin) penggunaToEdit).getNip());
        } else if (penggunaToEdit instanceof Anggota) {
            Anggota a = (Anggota) penggunaToEdit;
            addRow(gridPaneSpesifik, "Status Terlambat", new Label(a.getTerlambat() ? "Ya" : "Tidak"), null);
            addRow(gridPaneSpesifik, "Maks Pinjam", new Label(String.valueOf(a.getMaksimalPinjam())), null);
            addRow(gridPaneSpesifik, "Jumlah Pinjam", new Label(String.valueOf(a.getJumlahPinjam())), null);
        }
    }

    @FXML void handleSimpan(ActionEvent event) {
        PenggunaDTO dto = new PenggunaDTO();

        // Data non-editable & umum
        dto.id = penggunaToEdit.getId();
        dto.jenis = JenisPengguna.valueOf(penggunaToEdit.getJenis().toUpperCase());
        dto.nama = textFields.get("nama").getText();
        dto.alamat = textFields.get("alamat").getText();
        dto.nomorHP = textFields.get("nomorHP").getText();
        dto.username = textFields.get("username").getText();
        dto.password = textFields.get("password").getText();

        // Data spesifik
        if (dto.jenis == JenisPengguna.ADMIN) {
            dto.nipADMIN = textFields.get("nipADMIN").getText();
        } else if (dto.jenis == JenisPengguna.ANGGOTA) {
            // Salin data asli karena tidak diedit manual
            dto.terlambatANGGOTA = ((Anggota) penggunaToEdit).getTerlambat();
            dto.maksimalPinjamANGGOTA = ((Anggota) penggunaToEdit).getMaksimalPinjam();
            dto.jumlahPinjamANGGOTA = ((Anggota) penggunaToEdit).getJumlahPinjam();
        }

        PenggunaController.updatePengguna(penggunaToEdit.getId(), dto);
        closeWindow(event);
    }

    @FXML void handleBatal(ActionEvent event) {
        closeWindow(event);
    }

    // HELPER METHODS
    private void addEditableRow(GridPane grid, String key, String labelText, String currentValue) {
        TextField textField = new TextField(currentValue);
        textFields.put(key, textField);
        addRow(grid, labelText, new Label(currentValue), textField);
    }

    private void addRow(GridPane grid, String labelText, Node nodeKolom2, Node nodeKolom3) {
        int rowIndex = grid.getRowCount();
        // Set column widths to match FXML
        grid.getColumnConstraints().clear();
        grid.getColumnConstraints().addAll(
                new javafx.scene.layout.ColumnConstraints(120),
                new javafx.scene.layout.ColumnConstraints(180),
                new javafx.scene.layout.ColumnConstraints(250)
        );
        grid.add(new Label(labelText), 0, rowIndex);
        if (nodeKolom2 != null) grid.add(nodeKolom2, 1, rowIndex);
        if (nodeKolom3 != null) grid.add(nodeKolom3, 2, rowIndex);
    }

    private void closeWindow(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}