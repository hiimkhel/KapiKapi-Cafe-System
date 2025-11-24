package menus;

import models.Customer;

public class MainMenu extends BaseMenu {

    public MainMenu() {
        this.title = "Welcome to Kapikapi Café";
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
            System.out.println("Customer login successful: " + loggedInCustomer.getUsername());
            new CustomerMenu(loggedInCustomer).show(); // pass customer object
        } else {
            System.out.println("Returning to main menu...");
            waitForEnter();
        }
    }

    private void handleAdminLogin() {
        int attempts = 0;

        while (attempts < 3) {
            System.out.print("Enter admin password: ");
            String password = scanner.nextLine().trim();

            if (password.isEmpty()) {
                System.out.println("Password cannot be empty.\n");
            } else if (password.equals("admin123")) {
                System.out.println("Admin login successful!");
                new AdminMenu().show();
                return;
            } else {
                attempts++;
                System.out.println("Incorrect admin password. Attempts left: " + (3 - attempts) + "\n");
            }
        }

        System.out.println("Too many failed attempts. Returning to main menu...");
        waitForEnter();
    }

    private void waitForEnter() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
}
