package models;

import java.util.List;

public class Order {

    private String customerName;
    private List<String> items;     // coffee names
    private int totalPrice;         // computed price
    private long timestamp;         // for queue ordering display

    public Order(String customerName, List<String> items, int totalPrice) {
        this.customerName = customerName;
        this.items = items;
        this.totalPrice = totalPrice;
        this.timestamp = System.currentTimeMillis();
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<String> getItems() {
        return items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
    