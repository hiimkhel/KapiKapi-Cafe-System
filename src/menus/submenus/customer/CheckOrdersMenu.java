package menus.submenus.customer;

import controllers.OrderQueue;
import models.Customer;
import models.Order;
import utils.MenuNavigator;

public class CheckOrdersMenu {

    private final Customer customer;

    private final String[] options = {
        "Pending Orders",
        "Completed Orders",
        "Order History",
        "Back"
    };

    public CheckOrdersMenu(Customer customer) {
        this.customer = customer;
    }

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("Check Orders", options);

            switch (choice) {
                case 0 -> showPendingOrders();
                case 1 -> showCompletedOrders();
                case 2 -> showOrderHistory();
                case 3 -> { return; }
            }

            MenuNavigator.waitForEnter();
        }
    }

    // =============================
    // PENDING ORDERS
    // =============================
    private void showPendingOrders() {
        MenuNavigator.clearScreen();
        System.out.println("=== Pending Orders ===\n");

        var pending = OrderQueue.getPendingOrders().stream()
                .filter(o -> o.getCustomerName().equals(customer.getUsername()))
                .toList();

        if (pending.isEmpty()) {
            System.out.println("No pending orders.");
            return;
        }

        pending.forEach(order -> {
            System.out.println("• Items: " + order.getItems());
            System.out.println("  Total: ₱" + order.getTotalPrice());
            System.out.println();
        });
    }

    // =============================
    // COMPLETED ORDERS
    // =============================
    private void showCompletedOrders() {
        MenuNavigator.clearScreen();
        System.out.println("=== Completed Orders ===\n");

        var completed = OrderQueue.getCompletedOrders().stream()
                .filter(o -> o.getCustomerName().equals(customer.getUsername()))
                .toList();

        if (completed.isEmpty()) {
            System.out.println("No completed orders.");
            return;
        }

        completed.forEach(order -> {
            System.out.println("• Items: " + order.getItems());
            System.out.println("  Total: ₱" + order.getTotalPrice());
            System.out.println("  Status: Brewed ✔");
            System.out.println();
        });
    }

    // =============================
    // ORDER HISTORY (from Customer)
    // =============================
    private void showOrderHistory() {
        MenuNavigator.clearScreen();
        System.out.println("=== Your Order History ===\n");

        if (customer.getOrderHistory().isEmpty()) {
            System.out.println("No past orders.");
            return;
        }

        for (Order order : customer.getOrderHistory()) {
            System.out.println("• Items: " + order.getItems());
            System.out.println("  Total Paid: ₱" + order.getTotalPrice());
            System.out.println("  Brewed: " + (order.isBrewed() ? "✔" : "x"));
            System.out.println();
        }
    }
}
