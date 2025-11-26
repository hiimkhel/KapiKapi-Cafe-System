package menus.submenus.customer;

import java.util.ArrayList;
import java.util.List;

import controllers.OrderQueue;
import database.Database;
import models.CoffeeMenu;
import models.Coffee;
import models.Customer;
import models.Order;
import utils.MenuNavigator;

public class OrderMenu {

    private final Customer customer;
    private final String[] options = { "Browse Menu", "Cart", "Checkout", "Back" };
    private final List<Coffee> coffeeList = CoffeeMenu.getCoffees();

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
                case 3 -> { return; } // Back
            }

            MenuNavigator.waitForEnter();
        }
    }

    // ============================================================
    // 1. BROWSE MENU
    // ============================================================
    private void browseMenu() {
        String[] menuOptions = new String[coffeeList.size() + 1];
        for (int i = 0; i < coffeeList.size(); i++) {
            Coffee c = coffeeList.get(i);
            menuOptions[i] = c.getName() + " - ₱" + c.getPrice();
        }
        menuOptions[menuOptions.length - 1] = "Back";

        while (true) {
            int choice = MenuNavigator.navigate("Coffee Menu", menuOptions);
            if (choice == menuOptions.length - 1) break;

            Coffee selected = coffeeList.get(choice);
            customer.addToCart(selected); // automatically updates database

            System.out.println(selected.getName() + " added to cart! (₱" + selected.getPrice() + ")");
            MenuNavigator.waitForEnter();
        }
    }

    // ============================================================
    // 2. VIEW CART
    // ============================================================
    private void viewCart() {
        List<Coffee> cart = customer.getCart();
        MenuNavigator.clearScreen();
        System.out.println("=== Your Cart ===");

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        for (int i = 0; i < cart.size(); i++) {
            Coffee c = cart.get(i);
            System.out.println((i + 1) + ". " + c.getName() + " - ₱" + c.getPrice());
        }

        System.out.println("\nTotal: ₱" + customer.getCartTotal());
        System.out.println("Remove an item? (Enter number or 0 to go back)");

        int removeChoice = MenuNavigator.getIntInput();
        if (removeChoice == 0) return;

        if (removeChoice < 1 || removeChoice > cart.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Coffee removed = customer.removeFromCart(removeChoice - 1); // updates database
        System.out.println(removed.getName() + " removed from cart.");
    }

    // ============================================================
    // 3. CHECKOUT
    // ============================================================
    private void checkout() {
        List<Coffee> cart = customer.getCart();

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty!");
            return;
        }

        MenuNavigator.clearScreen();
        System.out.println("=== Checkout ===");

        for (Coffee c : cart) {
            System.out.println("- " + c.getName() + " - ₱" + c.getPrice());
        }

        System.out.println("\nTotal: ₱" + customer.getCartTotal());

        // Wallet check
        if (customer.getWalletBalance() < customer.getCartTotal()) {
            System.out.println("\nInsufficient wallet balance! Please top-up in Profile.");
            return;
        }

        System.out.println("\nConfirm order? (Y/N)");
        String confirm = MenuNavigator.getInput().toLowerCase();
        if (!confirm.equals("y")) {
            System.out.println("Order cancelled.");
            return;
        }

        // Create order object
        List<String> items = new ArrayList<>();
        for (Coffee c : cart) items.add(c.getName());

        Order order = new Order(customer.getUsername(), items, customer.getCartTotal());

        // Add to database and queue
        Database.addOrder(order);  
        customer.recordOrder(order);  // updates wallet, stamps, totalOrders, totalSpent
        customer.deductFromWallet(customer.getCartTotal());

        OrderQueue.addOrder(order);

        // Clear cart after checkout
        customer.clearCart();

        System.out.println("Order placed! Admin will brew it soon.");
    }
}
