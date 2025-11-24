package menus.submenus.customer;

import controllers.OrderQueue;
import utils.MenuNavigator;

public class CheckOrdersMenu {

    private final String[] options = {
        "Pending Orders",
        "Completed Orders",
        "Order History",
        "Back"
    };

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("Check Orders", options);

            switch (choice) {
                case 0 -> checkOrders();
                case 1 -> System.out.println("Completed orders...");
                case 2 -> { return; }
            }

            pause();
        }
    }

    private void pause() {
        System.out.println("Press Enter to continue...");
        new java.util.Scanner(System.in).nextLine();
    }
        private void checkOrders() {
        System.out.println("=== Your Orders ===");

        OrderQueue.getOrders().stream()
                .filter(order -> order.getCustomerName().equals("kelly"))
                .forEach(order -> {
                    System.out.println("Order: " + order.getItems());
                });

        System.out.println("End of list.");
    }
}
