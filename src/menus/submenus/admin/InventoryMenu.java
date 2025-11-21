package menus.submenus.admin;

import utils.MenuNavigator;

public class InventoryMenu {
    public void show() {
        String[] options = {"View Stock", "Update Stock", "Back"};

        while (true) {
            int selected = MenuNavigator.navigate("=== Inventory Management ===", options);

            switch (selected) {
                case 0 -> System.out.println("Stock displayed.");
                case 1 -> System.out.println("Stock updated.");
                case 2 -> { return; } // Back
            }
            System.out.println("Press Enter to continue...");
            new java.util.Scanner(System.in).nextLine();
        }
    }
}
