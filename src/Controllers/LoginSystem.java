package Controllers;

import Models.Pengguna;
import Models.PenyimpananData;

/**
 * CLASS LOGINSYSTEM UNTUK MENGELOLA LOGIN USER
 */
public class LoginSystem {
    // ATTRIBUTE
    private PenyimpananData data;

    // CONSTRUCTOR
    public LoginSystem(PenyimpananData data) {
        this.data = data;
    }

    /**
     * METHOD UNTUK MELAKUKAN LOGIN
     * @param id ID PENGGUNA
     * @param password PASSWORD PENGGUNA
     * @return OBJEK PENGGUNA JIKA BERHASIL, NULL JIKA GAGAL
     */
    public Pengguna login(String id, String password) {
        if (id == null || password == null) {
            System.out.println("ID ATAU PASSWORD TIDAK BOLEH KOSONG");
            return null;
        }

        if (data != null && data.getPengguna() != null) {
            for (Pengguna p : data.getPengguna()) {
                if (p != null && p.getIdPengguna().equals(id) && p.getPassword().equals(password)) {
                    System.out.println("LOGIN BERHASIL");
                    return p;
                }
            }
        } else {
            System.out.println("DATA PENGGUNA KOSONG");
            return null;
        }

        System.out.println("LOGIN GAGAL");
        return null;
    }
}
