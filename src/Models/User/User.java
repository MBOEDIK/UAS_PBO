package Models.User;

public abstract class User {
    protected String id;
    protected String nama;

    public User(String id, String nama) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("ID tidak boleh kosong.");
        if (nama == null || nama.length() < 3) throw new IllegalArgumentException("Nama minimal 3 karakter.");
        this.id = id;
        this.nama = nama;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public void setNama(String nama) {
        if (nama == null || nama.length() < 3)
            throw new IllegalArgumentException("Nama minimal 3 karakter.");
        this.nama = nama;
    }

    public abstract String showRole();
}
