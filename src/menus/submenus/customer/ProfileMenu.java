package menus.submenus.customer;

import utils.MenuNavigator;

public class ProfileMenu {

    private final String[] options = {
        "Wallet",
        "Stamp Card",
        "Achievements",
        "Back"
    };

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("Profile", options);

            switch (choice) {
                case 0 -> System.out.println("Wallet info...");
                case 1 -> System.out.println("Stamp card progress...");
                case 2 -> System.out.println("Achievements...");
                case 3 -> { return; }
            }

            pause();
        }
    }

    private void pause() {
        System.out.println("Press Enter to continue...");
        new java.util.Scanner(System.in).nextLine();
    }
}
