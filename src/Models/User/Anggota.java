package Models.User;

public class Anggota extends Pengguna {

    private boolean terlambatMengembalikan ;
    private int maksimalPinjamBuku ;

    //menerapkan super
    Anggota(String idUser, String nama, String alamatUser, int nomorHpUser) {
        super(idUser, nama, alamatUser, nomorHpUser);
        this.terlambatMengembalikan = false ;
        this.maksimalPinjamBuku = 3 ;
    }

    // Getter untuk status keterlambatan
    public boolean getTerlambatMengembalikan() {
        return terlambatMengembalikan;
    }

    // Setter untuk status keterlambatan
    public void setTerlambatMengembalikan(boolean terlambatMengembalikan) {
        this.terlambatMengembalikan = terlambatMengembalikan;
    }

    // Getter untuk batas maksimal pinjam buku
    public int getMaksimalPinjamBuku() {
        return maksimalPinjamBuku;
    }

    // Setter untuk batas maksimal pinjam buku
    public void setMaksimalPinjamBuku(int maksimalPinjamBuku) {
        this.maksimalPinjamBuku = maksimalPinjamBuku;
    }
}
