package menus.submenus.admin;

import utils.MenuNavigator;

public class EmployeeMenu {
    public void show() {
        String[] options = {"Add Employee", "Remove Employee", "View Employees", "Back"};

        while (true) {
            int selected = MenuNavigator.navigate("=== Manage Employees ===", options);

            switch (selected) {
                case 0 -> System.out.println("Adding employee...");
                case 1 -> System.out.println("Removing employee...");
                case 2 -> System.out.println("Viewing employees...");
                case 3 -> { return; } // Back
            }
            System.out.println("Press Enter to continue...");
            new java.util.Scanner(System.in).nextLine();
        }
    }
}
