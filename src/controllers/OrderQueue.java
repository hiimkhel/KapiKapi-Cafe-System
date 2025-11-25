package controllers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

import models.Order;

public class OrderQueue {

    private static final Queue<Order> pendingOrders = new LinkedList<>();
    private static final List<Order> completedOrders = new ArrayList<>();

    public static void addOrder(Order order) {
        pendingOrders.add(order);
    }

    /** Admin brews next order */
    public static Order brewNextOrder() {
        Order order = pendingOrders.poll();
        if (order != null) {
            order.markBrewed(); // mark order as brewed
            completedOrders.add(order);
        }
        return order;
    }

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
