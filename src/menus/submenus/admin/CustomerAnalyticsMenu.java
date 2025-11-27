package menus.submenus.admin;

import controllers.CustomerAnalyticsController;
import utils.MenuNavigator;

public class CustomerAnalyticsMenu {

    private final CustomerAnalyticsController controller = new CustomerAnalyticsController();

    private final String[] options = {
        "Top Customers",
        "View All Orders",
        "Customer Spending Summary",
        "Back"
    };

    public void show() {
        while (true) {
            MenuNavigator.clearScreen();
            MenuNavigator.printHeaderCentered();
            MenuNavigator.printBorder();

            int choice = MenuNavigator.navigate("=== Customer Analytics ===", options, true);

            switch (choice) {
                case 0 -> {
                    controller.displayTopCustomers();
                    MenuNavigator.waitForEnter();

                }
                case 1 -> {
                    controller.displayAllOrders();
                    MenuNavigator.waitForEnter();
                }
                case 2 -> {
                    controller.displayCustomerSummary();
                    MenuNavigator.waitForEnter();
                }
                case 3 -> { return; }
            }
        }
    }
}
