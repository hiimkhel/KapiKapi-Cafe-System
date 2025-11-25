package menus.submenus.customer;

import java.util.ArrayList;
import java.util.List;

import controllers.OrderQueue;
import models.Customer;
import models.Order;
import utils.MenuNavigator;

public class OrderMenu {

    private final Customer customer;

    private final String[] options = {
        "Browse Menu",
        "Cart",
        "Checkout",
        "Back"
    };

    // Coffee menu
    private final String[] coffeeNames = {
            "Americano",
            "Latte",
            "Cappuccino",
            "Mocha",
            "Caramel Macchiato"
    };

    private final int[] coffeePrices = {80, 120, 110, 130, 140};

    private final List<String> cart = new ArrayList<>();
    private int cartTotal = 0;

    public OrderMenu(Customer customer) {
        this.customer = customer;
    }

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("Order Menu - " + customer.getUsername(), options);

            switch (choice) {
                case 0 -> browseMenu();
                case 1 -> viewCart();
                case 2 -> checkout();
                case 3 -> { return; }
            }

            MenuNavigator.waitForEnter();
        }
    }

    // ============================================================
    // 1. BROWSE MENU
    // ============================================================
    private void browseMenu() {
        String[] menu = new String[coffeeNames.length + 1];

        for (int i = 0; i < coffeeNames.length; i++) {
            menu[i] = coffeeNames[i] + " - ₱" + coffeePrices[i];
        }
        menu[menu.length - 1] = "Back";

        while (true) {
            int choice = MenuNavigator.navigate("Coffee Menu", menu);

            if (choice == menu.length - 1) break;

            String drink = coffeeNames[choice];
            int price = coffeePrices[choice];

            cart.add(drink);
            cartTotal += price;

            System.out.println(drink + " added to cart! (₱" + price + ")");
            MenuNavigator.waitForEnter();
        }
    }

    // ============================================================
    // 2. VIEW CART
    // ============================================================
    private void viewCart() {
        MenuNavigator.clearScreen();
        System.out.println("=== Your Cart ===");

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        for (int i = 0; i < cart.size(); i++) {
            System.out.println((i + 1) + ". " + cart.get(i));
        }

        System.out.println("\nTotal: ₱" + cartTotal);
        System.out.println("Remove an item? (Enter number or 0 to go back)");

        int removeChoice = MenuNavigator.getIntInput();

        if (removeChoice == 0) return;
        if (removeChoice < 1 || removeChoice > cart.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        String removed = cart.remove(removeChoice - 1);
        int priceIndex = findPriceIndex(removed);
        cartTotal -= coffeePrices[priceIndex];

        System.out.println(removed + " removed from cart.");
    }

    private int findPriceIndex(String drink) {
        for (int i = 0; i < coffeeNames.length; i++) {
            if (coffeeNames[i].equals(drink)) return i;
        }
        return 0;
    }

    // ============================================================
    // 3. CHECKOUT
    // ============================================================
    private void checkout() {
        customer.addToWallet(1000); // TEMPORARY
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty!");
            return;
        }

        MenuNavigator.clearScreen();
        System.out.println("=== Checkout ===");

        for (String item : cart) {
            System.out.println("- " + item);
        }

        System.out.println("\nTotal: ₱" + cartTotal);

        // Wallet check
        if (customer.getWalletBalance() < cartTotal) {
            System.out.println("\nInsufficient wallet balance!");
            return;
        }

        System.out.println("\nConfirm order? (Y/N)");
        String confirm = MenuNavigator.getInput().toLowerCase();

        if (!confirm.equals("y")) {
            System.out.println("Order cancelled.");
            return;
        }

        // Create order object
        Order order = new Order(
                customer.getUsername(),
                new ArrayList<>(cart),
                cartTotal
        );

        // Save to customer history
        customer.recordOrder(order);

        // Deduct wallet
        customer.deductFromWallet(cartTotal);

        // Add stamp
        customer.addStamp();

        // Add to brewing queue
        OrderQueue.addOrder(order);

        // Reset cart
        cart.clear();
        cartTotal = 0;

        System.out.println("Order placed! Admin will brew it soon.");
    }

}
