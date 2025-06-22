package Models.User;

/**
 * Kelas Member adalah turunan dari User yang mewakili anggota perpustakaan.
 */
public class Anggota extends Pengguna {
    public Anggota(String id, String nama) {
        super(id, nama);
    }

    @Override
    public String showRole() {
        return "Anggota";
    }
}