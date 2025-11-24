package menus;

import utils.ConsoleUtils;
public class MainMenu extends BaseMenu {

    public MainMenu() {
        this.title = "Welcome to Kapikapi Cafe";
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
                return true;
            }
        }
        return false;
    }

    private void handleCustomerLogin() {
        ConsoleUtils.clearScreen();
        System.out.print("Enter your name/nickname: ");
        String nickname = scanner.nextLine().trim();

        if (nickname.isEmpty()) {
            System.out.println("Name or nickname is required.");
            waitForEnter();
            return;
        }

        System.out.println("Customer login successful!");
        new CustomerMenu(nickname).show();  // pass nickname dynamically
    }

    private void handleAdminLogin() {
        ConsoleUtils.clearScreen();
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter admin password: ");
            String password = scanner.nextLine().trim();

            if (password.isEmpty()) {
                System.out.println("Password cannot be empty.\n");
                continue;  // does not count as failure
            }

            if (password.equals("admin123")) {
                System.out.println("Admin login successful!");
                new AdminMenu().show();
                return;  // exit after success
            }

            attempts++;
            System.out.println("Incorrect admin password. Attempts left: " 
                            + (MAX_ATTEMPTS - attempts) + "\n");
        }

        System.out.println("Too many failed attempts. Returning to main menu...");
        waitForEnter();
    }
        

    private void waitForEnter() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
}
