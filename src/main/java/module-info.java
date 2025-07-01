module com.SistemManajemenPerpustakaan.Main {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;
    requires com.almasb.fxgl.core;
    requires java.desktop;
    requires java.sql;
    requires org.apache.commons.csv;

    exports com.SistemManajemenPerpustakaan.Main;
    exports com.SistemManajemenPerpustakaan.MVC.Views.GUI;

    opens com.SistemManajemenPerpustakaan.MVC.Views.GUI.subController to javafx.fxml;
    opens com.SistemManajemenPerpustakaan.Main to javafx.fxml;
    opens com.SistemManajemenPerpustakaan.MVC.Views.GUI to javafx.fxml;
}