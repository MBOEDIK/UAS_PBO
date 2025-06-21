package Models.User;

public class Admin extends User {
    private int roleLevel;

    public Admin(String id, String nama, int roleLevel) {
        super(id, nama);
        this.roleLevel = roleLevel;
    }

    public int getRoleLevel() { return roleLevel; }
    public void setRoleLevel(int roleLevel) { this.roleLevel = roleLevel; }

    @Override
    public String showRole() {
        return "Administrator";
    }
}
