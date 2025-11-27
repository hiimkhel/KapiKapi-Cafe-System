package menus.submenus.admin;

import utils.ConsoleUtils;
import utils.MenuNavigator;
import controllers.OrderQueue;
import models.Order;

public class OrdersDashboardMenu {

       private final String[] brewFrames = {
        "(•ᴥ•)つ     ☕\nBrewing...",
        "( •ᴥ•)つ    ☕\nBrewing...",
        " ( •ᴥ•)つ   ☕\n Brewing...",
        "  ( •ᴥ•)つ  ☕\n  Brewing...",
        "   ( •ᴥ•)つ ☕\n   DONE!"
    };

    public void show() {
        while (true) {
            MenuNavigator.clearScreen();
            MenuNavigator.printHeaderCentered();
            MenuNavigator.printBorder();

            if (!OrderQueue.hasPendingOrders()) {
                System.out.println();
                ConsoleUtils.printCentered("No pending orders — All caught up! (•ᴥ•)つ☕");
                System.out.println();
                MenuNavigator.printBorder();
                MenuNavigator.waitForEnter();
                return;
            }

            // Display brewing header
            ConsoleUtils.printCentered("╔══════════════════════════════════════════════════════════════╗");
            ConsoleUtils.printCentered("                     (•ᴥ•)つ ☕ Brewing Order...                ");
            ConsoleUtils.printCentered("╚══════════════════════════════════════════════════════════════╝");
            System.out.println();

            // Show queue list
            ConsoleUtils.printCentered("Next in Queue:");
            ConsoleUtils.printCentered("──────────────────────────────────────────────────────────────");

            int index = 1;
            for (Order o : OrderQueue.getPendingOrders()) {
                System.out.println("\t\t\t\t[ " + index + " ] Customer: " + o.getCustomerName());
                System.out.println("\t\t\t\t     Items: " + o.getItems());
                System.out.println("\t\t\t\t     Total: ₱" + o.getTotalPrice());
                ConsoleUtils.printCentered("──────────────────────────────────────────────────────────────");
                index++;
            }

            // Prompt to brew next order
            ConsoleUtils.printCentered("Press Enter to start brewing the next order...");
            MenuNavigator.waitForEnter();

            // Brewing animation
            for (String frame : brewFrames) {
                MenuNavigator.clearScreen();
                MenuNavigator.printBorder();
                MenuNavigator.printHeaderCentered();
                MenuNavigator.printBorder();
                ConsoleUtils.printCentered(frame);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Finish brewing
            OrderQueue.brewNextOrder();
            ConsoleUtils.clearScreen();
            MenuNavigator.printBorder();
            MenuNavigator.printHeaderCentered();
            MenuNavigator.printBorder();
            System.out.println();
            ConsoleUtils.printCentered("Order completed! (•ᴥ•)つ☕");
            MenuNavigator.waitForEnter();
        }
    }
}
