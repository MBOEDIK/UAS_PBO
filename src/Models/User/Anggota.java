package Models.User;

public class Anggota extends Pengguna {
    //ATTRIBUTE
    private boolean terlambatMengembalikan ;
    private int maksimalPinjamBuku, jumlahPinjamBuku;

    public Anggota(String idPengguna, String jenisPengguna, String namaPengguna, String alamatPengguna, String nomorHpPengguna, String username, String password, Boolean terlambatMengembalikan,int maksimalPinjamBuku, int jumlahPinjamBuku) {
        super(idPengguna, jenisPengguna, namaPengguna, alamatPengguna, nomorHpPengguna, username, password);
        this.terlambatMengembalikan = terlambatMengembalikan ;
        this.maksimalPinjamBuku = maksimalPinjamBuku ;
        this.jumlahPinjamBuku = jumlahPinjamBuku;
    }

    //GETTER
    public boolean getTerlambatMengembalikan() {
        return terlambatMengembalikan;
    }
    public int getMaksimalPinjamBuku() {
        return maksimalPinjamBuku;
    }
    public int getJumlahPinjamBuku() {
        return jumlahPinjamBuku;
    }

    //SETTER
    public void setTerlambatMengembalikan(boolean terlambatMengembalikan) { this.terlambatMengembalikan = terlambatMengembalikan; }
    public void setMaksimalPinjamBuku(int maksimalPinjamBuku) { this.maksimalPinjamBuku = maksimalPinjamBuku; }
    public void setJumlahPinjamBuku(int jumlahPinjamBuku) { this.jumlahPinjamBuku += jumlahPinjamBuku; }
}
