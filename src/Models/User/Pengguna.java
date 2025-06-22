package Models.User;

public abstract class Pengguna {
    protected String id;    // ID unik pengguna
    protected String nama;  // Nama pengguna

    /**
     * Konstruktor untuk kelas User.
     * @param id ID pengguna, tidak boleh kosong
     * @param nama Nama pengguna, minimal 3 karakter
     */
    public Pengguna(String id, String nama) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("ID tidak boleh kosong.");
        if (nama == null || nama.length() < 3) throw new IllegalArgumentException("Nama minimal 3 karakter.");
        this.id = id;
        this.nama = nama;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }

    /**
     * Setter nama pengguna dengan validasi minimal 3 karakter.
     * @param nama nama baru
     */
    public void setNama(String nama) {
        if (nama == null || nama.length() < 3)
            throw new IllegalArgumentException("Nama minimal 3 karakter.");
        this.nama = nama;
    }

    /**
     * Method abstrak untuk menunjukkan peran pengguna.
     * @return "Administrator" atau "Anggota"
     */
    public abstract String showRole();
}
