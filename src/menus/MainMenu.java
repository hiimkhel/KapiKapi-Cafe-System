package menus;

// Import the subfolders for specific roles menu
import menus.CustomerMenu;
import menus.AdminMenu;
public class MainMenu extends BaseMenu {

    public MainMenu() {
        this.title = "Welcome to Kapikapi Café";
        this.options = new String[]{"Customer Login", "Admin Login", "Exit"};
    }

    @Override
    protected boolean handleSelection(int index) {
        switch (index) {
            case 0 -> {
                new CustomerMenu("Kelly").show();
            }
            case 1 -> {
                new AdminMenu().show();
            }
            case 2 -> {
                System.out.println("Exiting...");
                waitForEnter();
                return true; // exit menu
            }
        }
        return false;
    }

    private void waitForEnter() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
}
