package controllers;

import java.util.LinkedList;
import java.util.Queue;

import models.Order;

public class OrderQueue {

    private static final Queue<Order> queue = new LinkedList<>();

    // Customer adds an order to the queue
    public static void addOrder(Order order) {
        queue.add(order);
    }

    // Admin brews (removes) the next order
    public static Order pollOrder() {
        return queue.poll();
    }

    // Admin views queue
    public static Queue<Order> getOrders() {
        return queue;
    }

    public static boolean isEmpty() {
        return queue.isEmpty();
    }
}
