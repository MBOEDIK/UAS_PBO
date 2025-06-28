module com.SistemManajemenPerpustakaan.Main {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;
    requires com.almasb.fxgl.core;
    requires org.apache.commons.csv;
    requires annotations;

    // ==========================================================
    // ### TAMBAHKAN BARIS INI UNTUK MEMPERBAIKI ERROR ###
    // ==========================================================
    requires java.sql;


    opens com.SistemManajemenPerpustakaan.Main to javafx.fxml;
    exports com.SistemManajemenPerpustakaan.Main;
}