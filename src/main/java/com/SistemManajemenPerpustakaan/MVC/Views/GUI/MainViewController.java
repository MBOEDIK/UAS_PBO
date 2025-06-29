package com.SistemManajemenPerpustakaan.MVC.Views.GUI;

import javafx.fxml.FXML;

import java.awt.*;

public class MainViewController {

    @FXML
    private Button myButton; // <- Variabel ini otomatis terhubung ke FXML

    @FXML
    private void handleClick() { // <- Aksi ketika tombol diklik
        System.out.println("Bro, kamu klik aku!");
    }
}
