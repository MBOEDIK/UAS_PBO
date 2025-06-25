module com.example.tesssssssss {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.SistemManajemenPerpustakaan.Main to javafx.fxml;
    exports com.SistemManajemenPerpustakaan.Main;
}