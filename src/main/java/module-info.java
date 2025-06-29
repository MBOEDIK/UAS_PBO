module com.SistemManajemenPerpustakaan.Main {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;
    requires com.almasb.fxgl.core;
    requires java.desktop;
    requires java.sql;
    requires org.apache.commons.csv;

    opens com.SistemManajemenPerpustakaan.Main to javafx.fxml;
    exports com.SistemManajemenPerpustakaan.Main;
}