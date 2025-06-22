package Controllers;

import java.util.Map;

// CLASS UNTUK MENGELOLA LOGIN USER TANPA OBJEK PENGGUNA
public class LoginSystem {
    private Map<String, String> data;

    // KONSTRUKTOR
    public LoginSystem(Map<String, String> data) {
        this.data = data;
    }

    // METHOD UNTUK MELAKUKAN LOGIN
    public boolean login(String id, String password) {
        if (id == null || password == null) {
            System.out.println("ID ATAU PASSWORD TIDAK BOLEH KOSONG");
            return false;
        }

        if (data != null && data.containsKey(id)) {
            if (data.get(id).equals(password)) {
                System.out.println("LOGIN BERHASIL");
                return true;
            } else {
                System.out.println("PASSWORD SALAH");
                return false;
            }
        } else {
            System.out.println("ID TIDAK DITEMUKAN");
            return false;
        }
    }
}
