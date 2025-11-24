package models;

public class Customer {
    private String name;
    private double walletBalance;
    private int stampCount;
    private int totalOrders;
    private int totalSpent;

    public Customer(String name) {
        this.name = name;
        this.walletBalance = 0.0;
        this.stampCount = 0;
        this.totalOrders = 0;
        this.totalSpent = 0;
    }

    // ===== Getters =====
    public String getName() {
        return name;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public int getStampCount() {
        return stampCount;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public int getTotalSpent() {
        return totalSpent;
    }

    // ===== Customer Actions =====

    /** Add funds to wallet */
    public void addToWallet(double amount) {
        if (amount > 0) walletBalance += amount;
    }

    /** Deduct wallet for purchases */
    public boolean deductFromWallet(double amount) {
        if (amount > walletBalance) return false;
        walletBalance -= amount;
        return true;
    }

    /** Increase stamps (e.g., earn 1 stamp per order) */
    public void addStamp() {
        stampCount++;
    }

    /** Increment total orders made */
    public void recordOrder(int price) {
        totalOrders++;
        totalSpent += price;
    }

    /** Returns true if eligible for free coffee (10 stamps) */
    public boolean hasFreeCoffeeReward() {
        return stampCount >= 10;
    }

    /** Redeem 10 stamps for free coffee */
    public boolean redeemFreeCoffee() {
        if (stampCount >= 10) {
            stampCount -= 10;
            return true;
        }
        return false;
    }
}
