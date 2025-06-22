package Models.User;

public class Anggota extends Pengguna {
    //ATTRIBUTE
    private boolean terlambatMengembalikan ;
    private int maksimalPinjamBuku ;

    Anggota(String idPengguna, String namaPengguna, String alamatPengguna, String nomorHpPengguna, Boolean terlambatMengembalikan,int maksimalPinjamBuku) {
        super(idPengguna, namaPengguna, alamatPengguna, nomorHpPengguna);
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
