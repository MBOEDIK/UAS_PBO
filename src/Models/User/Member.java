package Models.User;

public class Member extends User {
    public Member(String id, String nama) {
        super(id, nama);
    }

    @Override
    public String showRole() {
        return "Anggota";
    }
}
