package com.SistemManajemenPerpustakaan.Main;

import com.SistemManajemenPerpustakaan.MVC.Controllers.LoginController;
import com.SistemManajemenPerpustakaan.MVC.Models.User.Pengguna;
import com.SistemManajemenPerpustakaan.Utils.DummyDataGenerator;

public class Main {
    public static void main(String[] args) {
        DummyDataGenerator.Generate();
        LoginController.login();

    }
}
