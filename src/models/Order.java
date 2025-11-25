package models;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private String customerName;
    private List<String> items;
    private int totalPrice;
    private boolean isBrewed;

    public Order(String customerName, List<String> items, int totalPrice) {
        this.customerName = customerName;
        this.items = items;
        this.totalPrice = totalPrice;
        this.isBrewed = false;
    }

    public String getCustomerName() { return customerName; }
    public List<String> getItems() { return items; }
    public int getTotalPrice() { return totalPrice; }
    public boolean isBrewed() { return isBrewed; }

    public void markBrewed() {
        this.isBrewed = true;
    }
}
