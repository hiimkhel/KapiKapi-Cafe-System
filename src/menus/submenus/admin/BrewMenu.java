package menus.submenus.admin;

import utils.MenuNavigator;
import controllers.OrderQueue;
import models.Order;

public class BrewMenu {

    public void show() {
        while (true) {
            MenuNavigator.clearScreen();
            System.out.println("=== Brew Orders ===");

            if (!OrderQueue.hasPendingOrders()) {
                System.out.println("No orders in queue.");
                MenuNavigator.waitForEnter();
                return;
            }

            Order nextOrder = OrderQueue.brewNextOrder();
            System.out.println("Brewing for: " + nextOrder.getCustomerName());
            System.out.println("Items: " + nextOrder.getItems());
            System.out.println("\nPress Enter when finished brewing...");
            MenuNavigator.waitForEnter();
        }
    }
}
