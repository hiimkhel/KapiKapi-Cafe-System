import database.Database;
import models.Order;
import models.Coffee;
import java.util.*;

public class TestDatabase {
    public static void main(String[] args) {
        // 1. Add a test customer
        if (!Database.exists("john")) {
            Database.registerCustomer(new models.Customer("john", "1234"));
        }

        // 2. Add a test order
        List<String> items = new ArrayList<>();
        items.add("Americano");
        items.add("Latte");

        Order order = new Order("john", items, 200);
        Database.addOrder(order);

        System.out.println("Added order: #" + order.getId());

        // 3. Display all orders
        System.out.println("\nAll orders:");
        for (Order o : Database.getOrders()) {
            System.out.println(
                "#" + o.getId() + " | " + o.getCustomerName() +
                " | " + o.getItemNames() +
                " | ₱" + o.getTotalPrice() +
                " | Brewed: " + o.isBrewed()
            );
        }

        // 4. Test analytics
        controllers.CustomerAnalyticsController analytics = new controllers.CustomerAnalyticsController();
        analytics.displayTopCustomers();
        analytics.displayCustomerSummary();
    }
}
