package menus;

import models.Customer;
import utils.ConsoleUtils;
import utils.MenuNavigator;

public class MainMenu {

    public static void show() {
        String title = "Welcome to our Cafe";
        String[] options = {"Customer Login", "Admin Login", "Exit"};

        while (true) {
            int choice = MenuNavigator.navigate(title, options, true);

            switch (choice) {
                case 0 -> {handleCustomerLogin();
                    MenuNavigator.clearScreen();
                }
                case 1 -> {handleAdminLogin();
                    MenuNavigator.clearScreen();
                }
                case 2, -1 -> {
                    System.out.println("Exiting...");
                    MenuNavigator.waitForEnter();
                    System.exit(0);
                }
            }
        }
    }

    private static void handleCustomerLogin() {
        AuthMenu authMenu = new AuthMenu();
        Customer loggedInCustomer = authMenu.show();

        if (loggedInCustomer != null) {
            ConsoleUtils.printCentered("Customer login successful: " + loggedInCustomer.getUsername());
            new CustomerMenu(loggedInCustomer).show();
        } else {
            ConsoleUtils.printCentered("Returning to main menu...");
            MenuNavigator.waitForEnter();
        }
    }

    private static void handleAdminLogin() {
        int attempts = 0;

        while (attempts < 3) {
            MenuNavigator.clearScreen();
            MenuNavigator.printHeaderCentered();
            System.out.println("=======================================================================================================================");
            System.out.print("\t\t\t\tEnter admin password: ");
            String password = MenuNavigator.getInput().trim();

            if (password.isEmpty()) {
                ConsoleUtils.printCentered("Password cannot be empty.\n");
            } else if (password.equals("admin123")) {
                ConsoleUtils.printCentered("Admin login successful!");
                new AdminMenu().show();
                return;
            } else {
                attempts++;
                ConsoleUtils.printCentered("Incorrect admin password. Attempts left: " + (3 - attempts) + "\n");
            }
        }

        ConsoleUtils.printCentered("Too many failed attempts. Returning to main menu...");
        MenuNavigator.waitForEnter();
    }

    public static void main(String[] args) {
        show();
    }
}
