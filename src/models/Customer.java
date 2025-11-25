package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    private double walletBalance;
    private int stampCount;
    private int totalOrders;
    private int totalSpent;

    private List<Order> orderHistory = new ArrayList<>();

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
        this.walletBalance = 0;
        this.stampCount = 0;
        this.totalOrders = 0;
        this.totalSpent = 0;
    }

    // ====== Getters ======
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public double getWalletBalance() { return walletBalance; }
    public int getStampCount() { return stampCount; }
    public int getTotalOrders() { return totalOrders; }
    public int getTotalSpent() { return totalSpent; }
    public List<Order> getOrderHistory() { return orderHistory; }

    // ====== Customer Actions ======
    public void addToWallet(double amount) {
        if (amount > 0) walletBalance += amount;
    }

    public boolean deductFromWallet(double amount) {
        if (amount > walletBalance) return false;
        walletBalance -= amount;
        return true;
    }

    public void addStamp() {
        stampCount++;
    }

    public void recordOrder(Order order) {
        totalOrders++;
        totalSpent += order.getTotalPrice();
        orderHistory.add(order);
    }

    public boolean hasFreeCoffeeReward() { return stampCount >= 10; }

    public boolean redeemFreeCoffee() {
        if (stampCount >= 10) {
            stampCount -= 10;
            return true;
        }
        return false;
    }

    // ====== Helper Methods for Orders ======
    public List<Order> getPendingOrders() {
        List<Order> pending = new ArrayList<>();
        for (Order o : orderHistory) {
            if (!o.isBrewed()) pending.add(o);
        }
        return pending;
    }

    public List<Order> getCompletedOrders() {
        List<Order> completed = new ArrayList<>();
        for (Order o : orderHistory) {
            if (o.isBrewed()) completed.add(o);
        }
        return completed;
    }
}
