package controllers;

import database.Database;
import models.Order;
import utils.ConsoleUtils;
import utils.MenuNavigator;

import java.util.*;

public class CustomerAnalyticsController {

    public void displayTopCustomers() {
        ConsoleUtils.clearScreen();
        MenuNavigator.printBorder();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();

        ConsoleUtils.printCentered("                      TOP 5 CUSTOMERS                        ");
        ConsoleUtils.printCentered("============================================================");

        Map<String, Double> spending = new HashMap<>();

        for (Order o : Database.getOrders()) {
            spending.merge(o.getCustomerName(), o.getTotalPrice(), Double::sum);
        }

        if (spending.isEmpty()) {
            ConsoleUtils.printCentered("No customer data available yet.");
            ConsoleUtils.printCentered("============================================================");
            return;
        }

        ConsoleUtils.printCentered(String.format("%-5s %-20s %-20s", "Rank", "Customer", "Total Spent"));
        ConsoleUtils.printCentered("------------------------------------------------------------");

        final int[] rank = {1};
        spending.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5)
                .forEach(e -> ConsoleUtils.printCentered(
                        String.format("%-5s %-20s ₱%-20.2f", "[" +
                                rank[0]++ + "]", e.getKey(), e.getValue())
                ));

        ConsoleUtils.printCentered("------------------------------------------------------------");
        ConsoleUtils.printCentered("Press Enter to return to Analytics Menu...");
    }
    
      private String groupItems(List<String> items) {
        Map<String, Integer> countMap = new LinkedHashMap<>();

        for (String item : items) {
            countMap.put(item, countMap.getOrDefault(item, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1)
                sb.append(entry.getKey());
            else
                sb.append(entry.getKey()).append(" x").append(entry.getValue());

            sb.append(", ");
        }

        // Remove trailing comma + space
        if (sb.length() > 2)
            sb.setLength(sb.length() - 2);

        return sb.toString();
    }
    
    
    
    public void displayAllOrders() {
        List<Order> orders = Database.getOrders();

        ConsoleUtils.clearScreen();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();
        ConsoleUtils.printCentered("                                 [ALL ORDERS]                                 ");
        ConsoleUtils.printCentered("===========================================================================================================\n");

        if (orders.isEmpty()) {
            ConsoleUtils.printCentered("No orders yet.");
            ConsoleUtils.printCentered("\nPress Enter to return...");
            MenuNavigator.waitForEnter();
            return;
        }

        ConsoleUtils.printCentered(String.format(
                "%-6s %-12s %-45s %-10s %-8s",
                "[ID]", "[Customer]", "[Items]", "[Total]", "[Brewed]"
        ));
        ConsoleUtils.printCentered("-------------------------------------------------------------------------------------------------------------");

        for (Order o : orders) {
            String groupedItems = groupItems(o.getItems()); // pass List<String>
            ConsoleUtils.printCentered(String.format(
                "%-6s %-12s %-45s %-10s %-8s",
                "#" + o.getId(),
                o.getCustomerName(),
                groupedItems,
                "₱" + o.getTotalPrice(),
                o.isBrewed() ? "Yes" : "No"
            ));
        }

        ConsoleUtils.printCentered("-------------------------------------------------------------------------------------------------------------");
    }

    public void displayCustomerSummary() {
        Map<String, Double> totals = new HashMap<>();

        for (Order o : Database.getOrders()) {
            totals.merge(o.getCustomerName(), o.getTotalPrice(), Double::sum);
        }

        ConsoleUtils.clearScreen();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();
        ConsoleUtils.printCentered("                  [CUSTOMER SPENDING SUMMARY]              ");
        ConsoleUtils.printCentered(" ============================================================\n");

        if (totals.isEmpty()) {
            ConsoleUtils.printCentered("No customer data available yet.");
            ConsoleUtils.printCentered("\nPress Enter to return...");
            MenuNavigator.waitForEnter();
            return;
        }

        // Table headers
        ConsoleUtils.printCentered(String.format("%-5s %-20s %-20s", "[No.]", "[Customer]", "[Total Spent]"));
        ConsoleUtils.printCentered("------------------------------------------------------------");

        final int[] counter = {1};
        totals.forEach((name, amount) -> ConsoleUtils.printCentered(
                String.format("%-5s %-20s ₱%-20.2f", "[" + counter[0]++ + "]", name, amount)
        ));

        ConsoleUtils.printCentered("------------------------------------------------------------");
    }
    
  

}

