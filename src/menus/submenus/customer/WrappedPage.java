package menus.submenus.customer;

import models.Customer;
import models.Order;
import utils.ConsoleUtils;
import utils.MenuNavigator;

import java.util.*;

public class WrappedPage {

    private final Customer customer;

    // ANSI color codes
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String BLUE = "\033[34m";
    private static final String CYAN = "\033[36m";
    private static final String MAGENTA = "\033[35m";
    private static final String BOLD = "\033[1m";

    public WrappedPage(Customer customer) {
        this.customer = customer;
    }

    public void show() {

        ConsoleUtils.clearScreen();
        MenuNavigator.printBorder();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();
        // Header
        ConsoleUtils.printCentered(BOLD + CYAN + "                     K A P I K A P I   W R A P P E D                  " + RESET);
        System.out.println();
        ConsoleUtils.printCentered(BOLD + "USER SUMMARY — " + customer.getUsername() + RESET);
        System.out.println();

        List<Order> orders = customer.getOrderHistory();
        if (orders.isEmpty()) {
            ConsoleUtils.printCentered(YELLOW + "No orders yet. Start your coffee journey!" + RESET);
            System.out.println();
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

        Map.Entry<String, Integer> topDrink = sortedDrinks.get(0);

        // Section A — Most Ordered Drink
        ConsoleUtils.printCentered(BOLD + MAGENTA + "────────────────────────────────────────────────────────────────────" + RESET);
        ConsoleUtils.printCentered(BOLD + MAGENTA + "A. MOST ORDERED DRINK" + RESET);
        ConsoleUtils.printCentered(BOLD + MAGENTA + "────────────────────────────────────────────────────────────────────" + RESET);
        System.out.println("\t\t\t\t• Drink Name: " + GREEN + topDrink.getKey() + RESET);
        System.out.println("\t\t\t\t• Frequency: " + GREEN + topDrink.getValue() + RESET);
        System.out.printf("\t\t\t\t• %% of Total Orders: " + GREEN + "%.2f%%" + RESET + "\n", (topDrink.getValue() * 100.0) / customer.getTotalOrders());
        System.out.println();

        // Section B — Drink Distribution
        ConsoleUtils.printCentered(BOLD + BLUE + "────────────────────────────────────────────────────────────────────" + RESET);
        ConsoleUtils.printCentered(BOLD + BLUE + "B. DRINK DISTRIBUTION" + RESET);
        ConsoleUtils.printCentered(BOLD + BLUE + "────────────────────────────────────────────────────────────────────" + RESET);
        for (Map.Entry<String, Integer> entry : sortedDrinks) {
            int value = entry.getValue();
            String bar = GREEN + "█".repeat(Math.min(value, 20)) + RESET;
            System.out.printf("\t\t\t\t%-12s | %-20s | %d%n", entry.getKey(), bar, value);
        }
        System.out.println();

        // Section C — Key Performance Metrics
        ConsoleUtils.printCentered(BOLD + YELLOW + "────────────────────────────────────────────────────────────────────" + RESET);
        ConsoleUtils.printCentered(BOLD + YELLOW + "C. KEY PERFORMANCE METRICS" + RESET);
        ConsoleUtils.printCentered(BOLD + YELLOW + "───────────────────────────────────────────────────────────────────" + RESET);
        System.out.println("\t\t\t\t• Total Orders          : " + CYAN + customer.getTotalOrders() + RESET);
        System.out.println("\t\t\t\t• Total Amount Spent    : " + CYAN + "₱" + customer.getTotalSpent() + RESET);
        System.out.println("\t\t\t\t• Loyalty Stamps Earned : " + CYAN + customer.getStampCount() + RESET);
        System.out.println();

        // Footer
        ConsoleUtils.printCentered(BOLD + MAGENTA + "═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════" + RESET);
        ConsoleUtils.printCentered(BOLD + GREEN + "Thank you for being part of KapiKapi Café!" + RESET);
        ConsoleUtils.printCentered(BOLD + GREEN + "Visit us again for more delicious coffee!" + RESET);
        ConsoleUtils.printCentered(BOLD + MAGENTA + "═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════" + RESET);

        MenuNavigator.waitForEnter();
    }
}
