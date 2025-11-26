package menus.submenus.admin;

import utils.MenuNavigator;
import controllers.OrderQueue;
import models.Order;

public class OrdersDashboardMenu {

    private final String[] brewFrames = {
        "  (o_o) ☕ Brewing...\n",
        "  (o_o)  ☕ Brewing...\n",
        "  (o_o)   ☕ Brewing...\n",
        "  (o_o)    ☕ Brewing...\n",
        "  (o_o)     ☕ Done!\n"
    };

    public void show() {
        while (true) {
            MenuNavigator.clearScreen();
            MenuNavigator.printHeaderCentered();
            MenuNavigator.printBorder();


            if (!OrderQueue.hasPendingOrders()) {
                System.out.println("No pending orders. All caught up!");
                MenuNavigator.waitForEnter();
                return;
            }

            Order order = OrderQueue.getPendingOrders().peek(); // peek to show but not remove yet
            System.out.println("Next Order:");
            System.out.println("Customer: " + order.getCustomerName());
            System.out.println("Items: " + order.getItems());
            System.out.println("Total: ₱" + order.getTotalPrice());
            System.out.println("\nPress Enter to start brewing...");
            MenuNavigator.waitForEnter();

            // Brewing animation
            for (String frame : brewFrames) {
                MenuNavigator.clearScreen();
                System.out.println(frame);
                try {
                    Thread.sleep(500); // half-second per frame
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Brew the order
            OrderQueue.brewNextOrder();
            System.out.println("\nOrder brewed and completed!");
            MenuNavigator.waitForEnter();
        }
    }
}
