package menus;

import utils.MenuNavigator;

public class AdminMenu {

    private final String[] options = {
        "Edit Menu",
        "Brew Coffee",
        "Top Customers",
        "Logout"
    };

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("Admin Menu", options);

            // switch (choice) {
            //     case 0 -> new EditMenuMenu().show();
            //     case 1 -> new BrewMenu().show();
            //     case 2 -> new TopCustomersMenu().show();
            //     case 3 -> {
            //         System.out.println("Logged out.");
            //         return;
            //     }
            // }
        }
    }
}
