package menus.submenus.admin;

import utils.MenuNavigator;

public class BrewMenu {
    public void show() {
        String[] options = {"Process Next Order", "View All Orders", "Back"};

        while (true) {
            int selected = MenuNavigator.navigate("=== Brew Coffee / Process Orders ===", options);

            switch (selected) {
                case 0 -> System.out.println("Processing next order...");
                case 1 -> System.out.println("Displaying all orders...");
                case 2 -> { return; } // Back
            }
            System.out.println("Press Enter to continue...");
            new java.util.Scanner(System.in).nextLine();
        }
    }
}
