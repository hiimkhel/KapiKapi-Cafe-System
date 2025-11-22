package orders;

import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {

    private static final Queue<Order> queue = new LinkedList<>();

    public static void addOrder(Order order) {
        queue.add(order);
    }

    public static Order pollOrder() {
        return queue.poll(); // admin "brews" (removes) the next order
    }

    public static Queue<Order> getOrders() {
        return queue;
    }

    public static boolean isEmpty() {
        return queue.isEmpty();
    }
}
