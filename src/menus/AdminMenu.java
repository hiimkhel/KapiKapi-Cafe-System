package menus;

import menus.submenus.admin.*;
import utils.ConsoleUtils;
import utils.InputValidator;
import utils.MenuNavigator;

public class AdminMenu extends BaseMenu {

    public AdminMenu() {
        this.title = "Admin Dashboard";
        this.options = new String[]{
                "Orders Dashboard",
                "Menu & Inventory",
                "Customer Analytics",
                "Employee Management",
                "Logout"
        };
    }

    @Override
    protected boolean handleSelection(int index) {
        switch (index) {
            case 0 -> {
                ConsoleUtils.clearScreen();
                new OrdersDashboardMenu().show();
            }
            case 1 -> {
                ConsoleUtils.clearScreen();
                new MenuInventoryMenu().show();
            }
            case 2 -> {
                ConsoleUtils.clearScreen();
                new CustomerAnalyticsMenu().show();
            }
            case 3 -> {
                ConsoleUtils.clearScreen();
                new EmployeeManagementMenu().show();
            }
            case 4 -> { // Logout
                ConsoleUtils.clearScreen();
                System.out.println("Logging out...");
                return true;
            }
        }
        return false;
    }

    @Override
    public void show() {
        boolean exit = false;
        while (!exit) {
            int choice = MenuNavigator.navigate(title, options, true);
            exit = handleSelection(choice);
        }
    }
}
