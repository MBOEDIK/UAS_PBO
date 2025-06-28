module com.SistemManajemenPerpustakaan.Main {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;
    requires com.almasb.fxgl.core;

    opens com.SistemManajemenPerpustakaan.Main to javafx.fxml;
    exports com.SistemManajemenPerpustakaan.Main;
}