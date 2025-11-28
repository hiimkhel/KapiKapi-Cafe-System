package menus;

import controllers.CustomerController;
import models.Customer;
import utils.ConsoleUtils;
import utils.MenuNavigator;

public class AuthMenu extends BaseMenu {

    private Customer loggedInCustomer; // store result for BaseMenu handling

    public AuthMenu() {
        this.title = "Welcome Customer";
        this.options = new String[]{"Login", "Register", "Exit"};
    }

    @Override
    protected boolean handleSelection(int index) {
        switch (index) {
            case 0 -> handleLogin();
            case 1 -> handleRegister();
            case 2, -1 -> {
                loggedInCustomer = null; // exit
                return true;
            }
        }
        return false;
    }

    private void handleLogin() {
        MenuNavigator.clearScreen();
        printHeaderCentered();
        Customer customer = CustomerController.login();
        if (customer != null) {
            loggedInCustomer = customer;
            // returning true will exit the menu loop
            MenuNavigator.clearScreen();
        } else {
            ConsoleUtils.printCentered("Login failed. Returning to menu...");
            MenuNavigator.waitForEnter();
        }
    }

    private void handleRegister() {
        MenuNavigator.clearScreen();
        printHeaderCentered();
        Customer customer = CustomerController.register();
        if (customer != null) {
            loggedInCustomer = customer;
            MenuNavigator.clearScreen();
        } else {
            ConsoleUtils.printCentered("Registration failed. Returning to menu...");
            MenuNavigator.waitForEnter();
        }
    }

    public Customer showAuthMenu() {
        // Use BaseMenu's show() loop
        this.show();
        return loggedInCustomer;
    }

    public static void printHeaderCentered() {
        String[] lines = {
            "██╗  ██╗ █████╗ ██████╗ ██╗██╗  ██╗ █████╗ ██████╗ ██╗     ██████╗ █████╗ ███████╗███████╗",
            "██║ ██╔╝██╔══██╗██╔══██╗██║██║ ██╔╝██╔══██╗██╔══██╗██║    ██╔════╝██╔══██╗██╔════╝██╔════╝",
            "█████╔╝ ███████║██████╔╝██║█████╔╝ ███████║██████╔╝██║    ██║     ███████║█████╗  █████╗  ",
            "██╔═██╗ ██╔══██║██╔═══╝ ██║██╔═██╗ ██╔══██║██╔═══╝ ██║    ██║     ██╔══██║██╔══╝  ██╔══╝  ",
            "██║  ██╗██║  ██║██║     ██║██║  ██╗██║  ██║██║     ██║    ╚██████╗██║  ██║██║     ███████╗",
            "╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝     ╚═════╝╚═╝  ╚═╝╚═╝     ╚══════╝",
            "======================================================================================================================="
        };

        int width = 120; // Adjust to terminal width

        for (String line : lines) {
            int padding = (width - line.length()) / 2;
            System.out.println(" ".repeat(Math.max(0, padding)) + line);
        }
    }
}
