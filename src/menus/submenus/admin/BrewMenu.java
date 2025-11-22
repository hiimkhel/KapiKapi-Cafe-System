package menus.submenus.admin;

import utils.MenuNavigator;
import orders.Order;
import orders.OrderQueue;

public class BrewMenu {

    public void show() {
        while (true) {
            MenuNavigator.clearScreen();
            System.out.println("=== Brew Orders ===");

            if (OrderQueue.isEmpty()) {
                System.out.println("No orders in queue.");
                MenuNavigator.waitForEnter();
                return;
            }

            Order nextOrder = OrderQueue.pollOrder();

            System.out.println("Brewing for: " + nextOrder.getCustomerName());
            System.out.println("Items: " + nextOrder.getItems());
            System.out.println("\nPress Enter when finished brewing...");
            MenuNavigator.waitForEnter();
        }
    }
}
