package com.SistemManajemenPerpustakaan.MVC.Views.GUI.subController;

import com.SistemManajemenPerpustakaan.DTOs.BukuDTO;
import com.SistemManajemenPerpustakaan.Enums.JenisBuku;
import com.SistemManajemenPerpustakaan.MVC.Controllers.BukuController;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TambahBukuController implements Initializable {

    @FXML private TextField textFieldJudul;
    @FXML private TextField textFieldPengarang;
    @FXML private TextField textFieldTahunTerbit;
    @FXML private ComboBox<JenisBuku> comboBoxJenis;
    @FXML private VBox containerAtributSpesifik;
    @FXML private Button btnSimpan;
    @FXML private Button btnBatal;

    private final Map<String, TextField> specificTextFields = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Isi ComboBox dengan semua nilai dari enum JenisBuku
        comboBoxJenis.setItems(FXCollections.observableArrayList(JenisBuku.values()));

        // Tambahkan listener untuk menampilkan field yang sesuai saat jenis buku dipilih
        comboBoxJenis.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            updateSpecificFields(newValue);
        });
    }

    private void updateSpecificFields(JenisBuku jenisBuku) {
        containerAtributSpesifik.getChildren().clear();
        specificTextFields.clear();

        if (jenisBuku == null) return;

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);
        grid.getColumnConstraints().add(new javafx.scene.layout.ColumnConstraints(120));

        switch (jenisBuku) {
            case JURNAL:
                addSpecificRow(grid, "institusiJURNAL", "Institusi");
                addSpecificRow(grid, "terindeksSintaJURNAL", "Terindeks Sinta");
                break;
            case NOVEL:
                addSpecificRow(grid, "genreNOVEL", "Genre");
                break;
            case MAJALAH:
                addSpecificRow(grid, "topikMAJALAH", "Topik");
                break;
            case TEXTBOOK:
                addSpecificRow(grid, "bidangIlmuTEXTBOOK", "Bidang Ilmu");
                break;
        }
        containerAtributSpesifik.getChildren().add(grid);
    }

    private void addSpecificRow(GridPane grid, String key, String labelText) {
        Label label = new Label(labelText);
        TextField textField = new TextField();
        specificTextFields.put(key, textField);
        int rowIndex = grid.getRowCount();
        grid.add(label, 0, rowIndex);
        grid.add(textField, 1, rowIndex);
    }

    @FXML
    void handleSimpan(ActionEvent event) {
        BukuDTO dto = new BukuDTO();

        // 1. Isi atribut yang diinput manual
        dto.judul = textFieldJudul.getText();
        dto.pengarang = textFieldPengarang.getText();
        dto.tahunTerbit = textFieldTahunTerbit.getText();
        dto.jenis = comboBoxJenis.getValue();

        // 2. Isi atribut otomatis
        dto.kode = IdGenerator.generateUniqueId(BukuController.ambilSemuaBuku(), Buku::getKode);
        dto.tersedia = true;

        // 3. Isi atribut spesifik dari textfield dinamis
        dto.institusiJURNAL = specificTextFields.getOrDefault("institusiJURNAL", new TextField()).getText();
        dto.terindeksSintaJURNAL = specificTextFields.getOrDefault("terindeksSintaJURNAL", new TextField()).getText();
        dto.genreNOVEL = specificTextFields.getOrDefault("genreNOVEL", new TextField()).getText();
        dto.topikMAJALAH = specificTextFields.getOrDefault("topikMAJALAH", new TextField()).getText();
        dto.bidangIlmuTEXTBOOK = specificTextFields.getOrDefault("bidangIlmuTEXTBOOK", new TextField()).getText();

        // Validasi sederhana
        if (dto.judul.isEmpty() || dto.jenis == null) {
            // Tampilkan alert error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Tidak Valid");
            alert.setHeaderText(null);
            alert.setContentText("Judul dan Jenis Buku tidak boleh kosong!");
            alert.showAndWait();
            return;
        }

        // 4. Panggil metode tambahBuku dari business logic controller
        BukuController.tambahBuku(dto);
        System.out.println("Buku baru berhasil ditambahkan!");

        // 5. Tutup jendela
        closeWindow(event);
    }

    @FXML
    void handleBatal(ActionEvent event) {
        closeWindow(event);
    }

    private void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}