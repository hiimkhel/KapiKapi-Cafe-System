package controllers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

import models.Order;
import models.Customer;
import database.Database;

public class OrderQueue {

    private static final Queue<Order> pendingOrders = new LinkedList<>();
    private static final List<Order> completedOrders = new ArrayList<>();

    // ==========================
    // ADD NEW ORDER
    // ==========================
    public static void addOrder(Order order) {
        pendingOrders.add(order);
        Database.addOrder(order); // persist in database
    }

    // ==========================
    // BREW NEXT ORDER (ADMIN)
    // ==========================
    public static Order brewNextOrder() {
        Order order = pendingOrders.poll();
        if (order != null) {
            order.markBrewed(); 
            Database.markOrderAsBrewed(order.getId()); // update persisted order
            completedOrders.add(order);

            // Give stamp to customer after brewing
            Customer customer = Database.findCustomerByUsername(order.getCustomerName());
            if (customer != null) {
                customer.addStamp();
                Database.updateCustomer(customer); // save updated customer
            }
        }
        return order;
    }

    // ==========================
    // QUEUE LOAD FROM DATABASE
    // ==========================
    public static void loadQueuesFromDatabase() {
        pendingOrders.clear();
        completedOrders.clear();

        for (Order o : Database.getOrders()) {
            if (!o.isBrewed()) pendingOrders.add(o);
            else completedOrders.add(o);
        }
    }

    // ==========================
    // GETTERS
    // ==========================
    public static Queue<Order> getPendingOrders() {
        return pendingOrders;
    }

    public static List<Order> getCompletedOrders() {
        return completedOrders;
    }

    public static boolean hasPendingOrders() {
        return !pendingOrders.isEmpty();
    }
}
