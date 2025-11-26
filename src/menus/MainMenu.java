package menus;

import models.Customer;
import utils.ConsoleUtils;
public class MainMenu extends BaseMenu {

    public MainMenu() {
        this.title = "Welcome to our Cafe";
        this.options = new String[]{"Customer Login", "Admin Login", "Exit"};
    }

    @Override
    protected boolean handleSelection(int index) {
        switch (index) {
            case 0 -> handleCustomerLogin();
            case 1 -> handleAdminLogin();
            case 2 -> {
                System.out.println("Exiting...");
                waitForEnter();
                return true; // exit menu
            }
        }
        return false;
    }

    private void handleCustomerLogin() {
        AuthMenu authMenu = new AuthMenu();
        Customer loggedInCustomer = authMenu.show(); // handles login/register with password

        if (loggedInCustomer != null) {
            ConsoleUtils.printCentered("Customer login successful: " + loggedInCustomer.getUsername());
            new CustomerMenu(loggedInCustomer).show(); // pass customer object
        } else {
            System.out.println("Returning to main menu...");
            waitForEnter();
        }
    }

    private void handleAdminLogin() {
        ConsoleUtils.clearScreen();
        printHeaderCentered();
        int attempts = 0;

        while (attempts < 3) {
            System.out.print("\t\t\t\tEnter admin password: ");
            String password = scanner.nextLine().trim();

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
        waitForEnter();
    }

    private void waitForEnter() {
        ConsoleUtils.printCentered("Press Enter to continue...");
        scanner.nextLine();
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

        int width = 120; // <-- Change depending on your terminal width

        for (String line : lines) {
            int padding = (width - line.length()) / 2;
            System.out.println(" ".repeat(Math.max(0, padding)) + line);
        }
    }
}
