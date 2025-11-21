package menus.submenus.admin;

import utils.MenuNavigator;

public class EditMenuMenu {
    public void show() {
        String[] options = {"Add Item", "Remove Item", "Update Item", "Back"};

        while (true) {
            int selected = MenuNavigator.navigate("=== Edit Menu ===", options);

            switch (selected) {
                case 0 -> System.out.println("Add Item selected.");
                case 1 -> System.out.println("Remove Item selected.");
                case 2 -> System.out.println("Update Item selected.");
                case 3 -> { return; } // Back
            }
            System.out.println("Press Enter to continue...");
            new java.util.Scanner(System.in).nextLine();
        }
    }
}
