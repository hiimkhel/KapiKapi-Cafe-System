package menus.submenus.customer;

import java.util.ArrayList;
import java.util.List;

import controllers.OrderQueue;
import models.Order;
import utils.MenuNavigator;

public class OrderMenu {

    private String customerName;

    private final String[] options = {
        "Browse Menu",
        "Checkout",
        "Back"
    };

    // Coffee Lists
    private final String[] coffeeNames = {
            "Americano",
            "Latte",
            "Cappuccino",
            "Mocha",
            "Caramel Macchiato"
    };

    private final int[] coffeePrices = {80, 120, 110, 130, 140};

    // Cart
    private List<String> cart = new ArrayList<>();
    private int cartTotal = 0;

    // Inject name from login
    public OrderMenu(String customerName) {
        this.customerName = customerName;
    }

    // ----------------------------------------------------------------------
    // MAIN ORDER MENU
    // ----------------------------------------------------------------------
    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("Order Menu", options);

            switch (choice) {
                case 0 -> browseMenu();
                case 1 -> checkout();
                case 2 -> { return; }
            }

            MenuNavigator.waitForEnter();
        }
    }

    // ----------------------------------------------------------------------
    // BROWSE COFFEE MENU
    // ----------------------------------------------------------------------
    private void browseMenu() {

        // Build dynamic menu with prices
        String[] menu = new String[coffeeNames.length + 1];
        for (int i = 0; i < coffeeNames.length; i++) {
            menu[i] = coffeeNames[i] + " -" + coffeePrices[i];
        }
        menu[menu.length - 1] = "Back";

        while (true) {
            int choice = MenuNavigator.navigate("Coffee Menu", menu);

            if (choice == menu.length - 1) break;

            String drink = coffeeNames[choice];
            int price = coffeePrices[choice];

            // Add to cart
            cart.add(drink);
            cartTotal += price;

            System.out.println(drink + " added to cart! (" + price + ")");
            MenuNavigator.waitForEnter();
        }
    }

    // ----------------------------------------------------------------------
    // CHECKOUT
    // ----------------------------------------------------------------------
    private void checkout() {

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty!");
            return;
        }

        MenuNavigator.clearScreen();
        System.out.println("=== Checkout ===\n");

        // Show items
        for (String item : cart) {
            System.out.println("- " + item);
        }

        System.out.println("\nTotal: " + cartTotal);
        System.out.println("\nConfirm order? (Y/N)");

        String confirm = MenuNavigator.getInput().toLowerCase();

        if (!confirm.equals("y")) {
            System.out.println("Order cancelled.");
            return;
        }

        // Create the order object
        Order order = new Order(customerName, new ArrayList<>(cart), cartTotal);
        OrderQueue.addOrder(order);

        // Reset cart
        cart.clear();
        cartTotal = 0;

        System.out.println("Order sent! Admin will brew it soon.");
    }

}
