package menus;

import menus.submenus.admin.*;
import utils.MenuNavigator;

public class AdminMenu {

    private final String[] options = {
        "Orders Dashboard",
        "Menu & Inventory",
        "Customer Analytics",
        "Employee Management",
        "Logout"
    };

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("==== Admin Dashboard", options);

            switch (choice) {
                case 0 -> new OrdersDashboardMenu().show();
                // case 1 -> new MenuInventoryMenu().show();
                // case 2 -> new CustomerAnalyticsMenu().show();
                // case 3 -> new EmployeeManagementMenu().show();
                case 4 -> { 
                    System.out.println("Logging out...");
                    return;
                }
            }
        }
    }
}
