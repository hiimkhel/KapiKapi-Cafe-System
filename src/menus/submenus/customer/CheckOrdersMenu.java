package menus.submenus.customer;

import utils.MenuNavigator;

public class CheckOrdersMenu {

    private final String[] options = {
        "View Pending Orders",
        "View Completed Orders",
        "Back"
    };

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("Check Orders", options);

            switch (choice) {
                case 0 -> System.out.println("Pending orders...");
                case 1 -> System.out.println("Completed orders...");
                case 2 -> { return; }
            }

            pause();
        }
    }

    private void pause() {
        System.out.println("Press Enter to continue...");
        new java.util.Scanner(System.in).nextLine();
    }
}
