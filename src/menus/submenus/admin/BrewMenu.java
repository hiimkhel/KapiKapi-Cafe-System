package menus.submenus.admin;

import utils.MenuNavigator;
import controllers.OrderQueue;
import models.Order;

public class BrewMenu {

    public void show() {
        while (true) {
            MenuNavigator.clearScreen();
            System.out.println("=== Brew Orders ===\n");

            if (!OrderQueue.hasPendingOrders()) {
                System.out.println("No pending orders.");
                MenuNavigator.waitForEnter();
                return;
            }

            Order next = OrderQueue.brewNextOrder(); // Updated Method

            if (next == null) {
                System.out.println("No orders to brew.");
                MenuNavigator.waitForEnter();
                return;
            }

            System.out.println("Brewing Order for: " + next.getCustomerName());
            System.out.println("Items: " + next.getItems());
            System.out.println("\nStatus: BREWING...");

            System.out.println("\nPress Enter when brewing is complete...");
            MenuNavigator.waitForEnter();

            System.out.println("Order marked as COMPLETED!");
            MenuNavigator.waitForEnter();
        }
    }
}
