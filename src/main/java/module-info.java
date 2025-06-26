module com.example.tesssssssss {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;

    opens com.SistemManajemenPerpustakaan.Main to javafx.fxml;
    exports com.SistemManajemenPerpustakaan.Main;
}