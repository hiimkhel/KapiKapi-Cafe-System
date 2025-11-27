package menus.submenus.customer;

import controllers.OrderQueue;
import models.Customer;
import models.Order;
import utils.ConsoleUtils;
import utils.MenuNavigator;

public class CheckOrdersMenu {

    private final Customer customer;

    private final String[] options = {
        "Pending Orders",
        "Order History",
        "Back"
    };

    public CheckOrdersMenu(Customer customer) {
        this.customer = customer;
    }

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("Check Orders", options, true);

            switch (choice) {
                case 0 -> showPendingOrders();
                case 1 -> showOrderHistory();
                case 2 -> { return; }
            }

            MenuNavigator.waitForEnter();
        }
    }

    // =============================
    // PENDING ORDERS
    // =============================
    private void showPendingOrders() {
        MenuNavigator.clearScreen();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();
        ConsoleUtils.printCentered("P E N D I N G   O R D E R S ");
        System.out.println("\t\t\tUSER: " + customer.getUsername());
        ConsoleUtils.printCentered("──────────────────────────────────────────────────────────────────────────");
        System.out.println();

        var pendingOrders = OrderQueue.getPendingOrders().stream()
                .filter(o -> o.getCustomerName().equals(customer.getUsername()))
                .toList();

        if (pendingOrders.isEmpty()) {
            ConsoleUtils.printCentered("You have no pending orders.");
            System.out.println();
        } else {
            for (Order order : pendingOrders) {
                ConsoleUtils.printCentered("Order #" + order.getId());
                System.out.println("\t\t\t\tItems:");
                for (String item : order.getItems()) {
                    System.out.println("\t\t\t\t  • " + item);
                }
                System.out.println("\t\t\t\tTotal: ₱" + order.getTotalPrice());
                ConsoleUtils.printCentered("──────────────────────────────────────────────────────────────────────────");
            }
        }

        printFooter();
    }

    // =============================
    // ORDER HISTORY (from Customer)
    // =============================
    private void showOrderHistory() {
        ConsoleUtils.clearScreen();
        MenuNavigator.printBorder();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();
        ConsoleUtils.printCentered("                  O R D E R   H I S T O R Y                  ");
        System.out.println("\t\t\tUSER: " + customer.getUsername());
        ConsoleUtils.printCentered("──────────────────────────────────────────────────────────────────────────");
        System.out.println();

        var orderHistory = customer.getOrderHistory();

        if (orderHistory.isEmpty()) {
            ConsoleUtils.printCentered("No past orders found.");
            System.out.println();
        } else {
            for (Order order : orderHistory) {
                ConsoleUtils.printCentered("Order #" + order.getId());
                System.out.println("\t\t\t\tItems:");
                for (String item : order.getItems()) {
                    System.out.println(" \t\t\t\t • " + item);
                }
                System.out.println("\t\t\t\tTotal Paid: ₱" + order.getTotalPrice());
                System.out.println("\t\t\t\tBrewed Status: " + (order.isBrewed() ? "✔ Brewed" : "✗ Pending"));
                ConsoleUtils.printCentered("──────────────────────────────────────────────────────────────────────────");
            }
        }

        printFooter();
    }

    // Optional footer method
    private void printFooter() {
        ConsoleUtils.printCentered("Thank you for choosing KapiKapi Café!");
        ConsoleUtils.printCentered("══════════════════════════════════════════════════════════════════════════");
    }

}
