package menus;

import utils.MenuNavigator;

// Admin submenus
import menus.submenus.admin.EditMenuMenu;
import menus.submenus.admin.BrewMenu;
import menus.submenus.admin.TopCustomersMenu;
import menus.submenus.admin.PendingOrdersMenu;
import menus.submenus.admin.SalesReportMenu;
import menus.submenus.admin.InventoryMenu;
import menus.submenus.admin.EmployeeMenu;

public class AdminMenu {
    private String[] options = {
        "Edit Menu",
        "Brew Coffee / Process Orders",
        "Top Customers",
        "View Pending Orders",
        "Sales Report",
        "Inventory Management",
        "Manage Employees",
        "Logout"
    };

    public void show() {
        while (true) {
            int selected = MenuNavigator.navigate("=== Admin Dashboard ===", options);

            switch (selected) {
                case 0 -> new EditMenuMenu().show();
                case 1 -> new BrewMenu().show();
                case 2 -> new TopCustomersMenu().show();
                case 3 -> new PendingOrdersMenu().show();
                case 4 -> new SalesReportMenu().show();
                case 5 -> new InventoryMenu().show();
                case 6 -> new EmployeeMenu().show();
                case 7 -> {
                    System.out.println("Logging out...");
                    return;
                }
            }
        }
    }
}
