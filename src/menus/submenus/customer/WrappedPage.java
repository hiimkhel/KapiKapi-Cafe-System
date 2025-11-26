package menus.submenus.customer;

import models.Customer;
import models.Order;
import utils.MenuNavigator;

import java.util.*;

public class WrappedPage {

    private final Customer customer;

    public WrappedPage(Customer customer) {
        this.customer = customer;
    }

    public void show() {
        MenuNavigator.clearScreen();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();
        System.out.println("Hello, " + customer.getUsername() + "!\n");

        List<Order> orders = customer.getOrderHistory();
        if (orders.isEmpty()) {
            System.out.println("No orders yet. Start your coffee journey!\n");
            MenuNavigator.waitForEnter();
            return;
        }

        // Count drinks
        Map<String, Integer> drinkCount = new HashMap<>();
        for (Order order : orders) {
            for (String drink : order.getItems()) {
                drinkCount.put(drink, drinkCount.getOrDefault(drink, 0) + 1);
            }
        }

        // Sort by most ordered
        List<Map.Entry<String, Integer>> sortedDrinks = new ArrayList<>(drinkCount.entrySet());
        sortedDrinks.sort((a, b) -> b.getValue() - a.getValue());

        // Top drink
        Map.Entry<String, Integer> topDrink = sortedDrinks.get(0);
        System.out.println("Your Top Drink: " + topDrink.getKey() + " (" + topDrink.getValue() + " orders)\n");

        // Favorite drinks
        System.out.println("Your Favorite Drinks:");
        for (Map.Entry<String, Integer> entry : sortedDrinks) {
            String drink = entry.getKey();
            int count = entry.getValue();
            String bar = "[" + "#".repeat(Math.min(count, 20)) + "]"; // max 20 chars
            System.out.printf("%-15s %s %d%n", drink, bar, count);
        }

        // Stats
        System.out.println("\nYour Stats:");
        System.out.println("Total Orders : " + customer.getTotalOrders());
        System.out.println("Total Spent  : ₱" + customer.getTotalSpent());
        System.out.println("Stamps Earned: " + customer.getStampCount());

        System.out.println("\nThanks for being part of KapiKapi Café!");
        MenuNavigator.waitForEnter();
    }
}
