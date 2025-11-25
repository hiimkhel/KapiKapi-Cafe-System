package controllers;

import database.Database;
import models.Order;
import java.util.*;

public class CustomerAnalyticsController {

    public void displayTopCustomers() {
        Map<String, Double> spending = new HashMap<>();

        for (Order o : Database.getOrders()) {
            spending.merge(o.getCustomerName(), o.getTotalPrice(), Double::sum);
        }

        System.out.println("\n=== Top Customers ===");
        spending.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5)
                .forEach(e -> System.out.println(e.getKey() + " — ₱" + e.getValue()));
    }

    public void displayAllOrders() {
        List<Order> orders = Database.getOrders();

        System.out.println("\n=== All Orders ===");
        if (orders.isEmpty()) {
            System.out.println("No orders yet.");
            return;
        }

        orders.forEach(o -> {
            System.out.println(
                "#" + o.getId() + " | " + o.getCustomerName() +
                " | " + o.getItemNames() + " | ₱" + o.getTotalPrice() +
                " | Brewed: " + o.isBrewed()
            );
        });
    }

    public void displayCustomerSummary() {
        Map<String, Double> totals = new HashMap<>();

        for (Order o : Database.getOrders()) {
            totals.merge(o.getCustomerName(), o.getTotalPrice(), Double::sum);
        }

        System.out.println("\n=== Customer Spending Summary ===");
        totals.forEach((name, amount) -> System.out.println(name + " — Total Spent: ₱" + amount));
    }
}
