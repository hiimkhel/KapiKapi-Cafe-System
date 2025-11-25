package models;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Order implements Serializable {

    private static final AtomicInteger counter = new AtomicInteger(1);

    private static final long serialVersionUID = 1L;
    private int id;
    private String customerName;
    private List<String> items;
    private double totalPrice;
    private boolean isBrewed;

    public Order(String customerName, List<String> items, int totalPrice) {
        this.id = counter.getAndIncrement();
        this.customerName = customerName;
        this.items = items;
        this.totalPrice = totalPrice;
        this.isBrewed = false;
    }

    public int getId() { return id;}
    public String getCustomerName() { return customerName; }
    public List<String> getItems() { return items; }
    public double getTotalPrice() { return totalPrice; }
    public boolean isBrewed() { return isBrewed; }
    

    // For analytics display
    public String getItemNames() {
        return String.join(", ", items);
    }

    public void markBrewed() {
        this.isBrewed = true;
    }

    // Helper for analytics
    public String getBrewStatus() {
        return isBrewed ? "BREWED" : "PENDING";
    }
    public void setId(int id) { this.id = id; }
    public void setBrewed(boolean brewed) { this.isBrewed = brewed; }
}

