package Models.User;

public class Anggota extends Pengguna {
    //ATTRIBUTE
    private boolean terlambatMengembalikan ;
    private int maksimalPinjamBuku ;

    public Anggota(String idPengguna, String jenisPengguna, String namaPengguna, String alamatPengguna, String nomorHpPengguna, String username, String password, Boolean terlambatMengembalikan,int maksimalPinjamBuku) {
        super(idPengguna, jenisPengguna, namaPengguna, alamatPengguna, nomorHpPengguna, username, password);
        this.terlambatMengembalikan = terlambatMengembalikan ;
        this.maksimalPinjamBuku = maksimalPinjamBuku ;
    }

    //GETTER
    public boolean getTerlambatMengembalikan() {
        return terlambatMengembalikan;
    }
    public int getMaksimalPinjamBuku() {
        return maksimalPinjamBuku;
    }

    //SETTER
    public void setTerlambatMengembalikan(boolean terlambatMengembalikan) { this.terlambatMengembalikan = terlambatMengembalikan; }
    public void setMaksimalPinjamBuku(int maksimalPinjamBuku) { this.maksimalPinjamBuku = maksimalPinjamBuku; }
}
