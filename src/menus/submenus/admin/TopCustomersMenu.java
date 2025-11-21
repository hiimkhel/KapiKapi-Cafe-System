package menus.submenus.admin;

import utils.MenuNavigator;

public class TopCustomersMenu {
    public void show() {
        String[] options = {"View Top 5 Customers", "View All Customers", "Back"};

        while (true) {
            int selected = MenuNavigator.navigate("=== Top Customers ===", options);

            switch (selected) {
                case 0 -> System.out.println("Top 5 customers displayed.");
                case 1 -> System.out.println("All customers displayed.");
                case 2 -> { return; } // Back
            }
            System.out.println("Press Enter to continue...");
            new java.util.Scanner(System.in).nextLine();
        }
    }
}
