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
            int choice = MenuNavigator.navigate("=== Customer Analytics ===", options);

            switch (choice) {
                case 0 -> controller.displayTopCustomers();
                case 1 -> controller.displayAllOrders();
                case 2 -> controller.displayCustomerSummary();
                case 3 -> { return; }
            }
        }
    }
}
