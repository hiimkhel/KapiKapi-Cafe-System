package menus;

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
                return true;
            }
        }
        return false;
    }

    private void handleCustomerLogin() {
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
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter admin password: ");
        String password = scanner.nextLine().trim();

        // simple password check (can be improved later)
        if (username.isEmpty()) {
            System.out.println("Username cannot be empty.");
            waitForEnter();
            return;
        }

        if (password.isEmpty()) {
            System.out.println("Password cannot be empty.");
            waitForEnter();
            return;
        }

        if (!password.equals("admin123")) {
            System.out.println("Incorrect admin password.");
            waitForEnter();
            return;
        }

        System.out.println("Admin login successful!");
        new AdminMenu().show();
    }

    private void waitForEnter() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
}
