// INI JUAN YANG KOMEN
package com.SistemManajemenPerpustakaan.MVC.Views.GUI;

import com.SistemManajemenPerpustakaan.MVC.Controllers.PenggunaController;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Admin;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class LoginAdminController {
    @FXML private TextField inputUsername;
    @FXML private PasswordField inputPassword;
    @FXML private Button btnAdminSubmit;
    @FXML private Button btnAdminBack;

    @FXML
    // FUNGSI UNTUK MENGHANDLE SUBMIT LOGIN ADMIN.
    // MEMVERIFIKASI KREDENSIAL DAN MENGARAHKAN KE MENU ADMIN JIKA BERHASIL.
    public void actAdminSubmit(ActionEvent event) throws IOException {
        String username = inputUsername.getText();
        String password = inputPassword.getText();
        boolean loginBerhasil = false;

        // Mengambil logika iterasi dari LoginController versi konsol
        for (Pengguna pengguna : PenggunaController.ambilSemuaPengguna()) {
            if (pengguna.getUsername().equals(username) &&
                    pengguna.getPassword().equals(password) &&
                    pengguna instanceof Admin) {

                loginBerhasil = true;
                break;
            }
        }

        if (loginBerhasil) {
            Parent adminMenu = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/AdminMenuView.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(adminMenu);
            stage.sizeToScene();
            stage.centerOnScreen();
        } else {
            new Alert(Alert.AlertType.ERROR, "Username atau password salah!").showAndWait();
        }
    }


    @FXML
    // FUNGSI UNTUK KEMBALI KE HALAMAN LOGIN UTAMA.
    // MENGALIHKAN PENGGUNA DARI LOGIN ADMIN KE HALAMAN LOGIN UMUM.
    public void actAdminBack(ActionEvent event) throws IOException {
        Parent loginAdmin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.SistemManajemenPerpustakaan/views/LoginView.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(loginAdmin);
        stage.sizeToScene();
        stage.centerOnScreen();
    }
}