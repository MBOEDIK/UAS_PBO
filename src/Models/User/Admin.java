package Models.User;

/**
 * Kelas Admin adalah turunan dari User yang mewakili pengguna dengan akses admin.
 */
public class Admin extends Pengguna {
    private int roleLevel; // Tingkat akses admin

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