package menus;

import models.Customer;
import utils.ConsoleUtils;
import utils.MenuNavigator;

public class MainMenu extends BaseMenu {

    public MainMenu() {
        this.title = "Welcome to our Cafe";
        this.options = new String[]{"Customer Login", "Admin Login", "Exit"};

    }

    @Override
    protected boolean handleSelection(int index){
        switch (index) {
            case 0 -> handleCustomerLogin();
            case 1 -> handleAdminLogin();
            case 2, -1 -> {
                exitApp();
                return true; // exit loop
            }
        }
        return false; // stay in menu
    }

    private static void handleCustomerLogin() {
        MenuNavigator.clearScreen(); // Clear before showing submenu
        AuthMenu authMenu = new AuthMenu();
        Customer loggedInCustomer = authMenu.showAuthMenu();

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
            MenuNavigator.clearScreen(); // Clear each attempt
            MenuNavigator.printHeaderCentered();
            System.out.println("=======================================================================================================================");
            System.out.print("\t\t\t\tEnter admin password: ");
            String password = MenuNavigator.getInput().trim();

            if (password.isEmpty()) {
                ConsoleUtils.printCentered("Password cannot be empty.\n");
                MenuNavigator.waitForEnter();
            } else if (password.equals("admin123")) {
                ConsoleUtils.printCentered("Admin login successful!");
                new AdminMenu().show();
                return;
            } else {
                attempts++;
                ConsoleUtils.printCentered("Incorrect admin password. Attempts left: " + (3 - attempts) + "\n");
                MenuNavigator.waitForEnter();
            }
        }

        ConsoleUtils.printCentered("Too many failed attempts. Returning to main menu...");
        MenuNavigator.waitForEnter();
    }

    private static void exitApp() {
        MenuNavigator.clearScreen();
        System.out.println("Exiting...");
        MenuNavigator.waitForEnter();
        System.exit(0);
    }

    public static void main(String[] args) {
        new MainMenu().show();
    }
}
