package Main;
import Controllers.LoginSystem;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LoginSystem loginSystem = new LoginSystem();

        loginSystem.dummyData();
        loginSystem.tampilanLoginUtama();
    }
}
