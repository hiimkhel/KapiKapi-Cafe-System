package controllers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

import models.Order;

public class OrderQueue {

    // Orders waiting to be brewed
    private static final Queue<Order> pendingOrders = new LinkedList<>();

    // Orders already brewed (completed)
    private static final List<Order> completedOrders = new ArrayList<>();


    // ============================
    // ADD ORDER (Customer)
    // ============================
    public static void addOrder(Order order) {
        pendingOrders.add(order);
    }


    // ============================
    // POLL (Admin brews next order)
    // ============================
    public static Order brewNextOrder() {
        Order order = pendingOrders.poll();
        if (order != null) {
            order.markBrewed();
            completedOrders.add(order);
        }
        return order;
    }


    // ============================
    // GETTERS
    // ============================

    // Pending orders for admin / customer to view
    public static Queue<Order> getPendingOrders() {
        return pendingOrders;
    }

    // Completed orders (history)
    public static List<Order> getCompletedOrders() {
        return completedOrders;
    }

    public static boolean hasPendingOrders() {
        return !pendingOrders.isEmpty();
    }
}
