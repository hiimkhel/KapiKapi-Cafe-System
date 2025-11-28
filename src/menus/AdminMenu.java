package menus;

import menus.submenus.admin.*;
import utils.ConsoleUtils;

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
        ConsoleUtils.clearScreen();
        switch (index) {
            case 0 -> new OrdersDashboardMenu().show();
            case 1 -> new MenuInventoryMenu().show();
            case 2 -> new CustomerAnalyticsMenu().show();
            case 3 -> new EmployeeManagementMenu().show();
            case 4 -> { // Logout
                System.out.println("Logging out...");
                return true;
            }
        }
        return false;
    }
}
