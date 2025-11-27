package controllers;

import java.util.Scanner;
import database.Database;
import menus.CustomerMenu;
import models.Customer;

public class CustomerController {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int WIDTH = 120; // match AuthMenu width

    // =========================
    // LOGIN & REGISTER LOGIC
    // =========================
    public static Customer login() {
        System.out.print(centerText("Username: >> "));
        String username = scanner.nextLine().trim();

        System.out.print(centerText("Password: >> "));
        String password = scanner.nextLine().trim();

        if (!Database.exists(username)) {
            System.out.println(centerText("User not found."));
            return null;
        }

        Customer c = Database.get(username);

        if (!c.getPassword().equals(password)) {
            System.out.println(centerText("Incorrect password."));
            return null;
        }

        System.out.println(centerText("Login successful!"));
        new CustomerMenu(c).show();
        return c;
    }

    public static Customer register() {
        System.out.println(centerText("CUSTOMER REGISTRATION"));
        System.out.print(centerText("Create Username: "));
        String username = scanner.nextLine().trim();

        if (Database.exists(username)) {
            System.out.println(centerText("Username already taken."));
            return null;
        }

        System.out.print(centerText("Create Password: "));
        String password = scanner.nextLine().trim();

        Customer c = new Customer(username, password);
        Database.registerCustomer(c);

        System.out.println(centerText("Registration complete!"));
        return c;
    }

    // =========================
    // WALLET OPERATIONS
    // =========================
    public static void topUpWallet(Customer customer, int amount) {
        if (amount > 0) {
            customer.addToWallet(amount);
            Database.updateCustomer(customer); // persist
            System.out.println(centerText("Wallet topped up by ₱" + amount + "!"));
            System.out.println(centerText("New balance: ₱" + customer.getWalletBalance()));
        }
    }

    // =========================
    // STAMP OPERATIONS
    // =========================
    public static boolean canRedeemCoffee(Customer customer) {
        return customer.hasFreeCoffeeReward();
    }

    public static boolean redeemFreeCoffee(Customer customer) {
        boolean redeemed = customer.redeemFreeCoffee();
        if (redeemed) {
            Database.updateCustomer(customer); // persist immediately
        }
        return redeemed;
    }

    // =========================
    // ACHIEVEMENTS
    // =========================
    public static int getTotalOrders(Customer customer) {
        return customer.getTotalOrders();
    }

    public static int getTotalSpent(Customer customer) {
        return customer.getTotalSpent();
    }

    public static int getStampCount(Customer customer) {
        // Always fetch fresh customer from DB to reflect brewing updates
        Customer fresh = Database.findCustomerByUsername(customer.getUsername());
        return fresh != null ? fresh.getStampCount() : customer.getStampCount();
    }

    // =========================
    // UTILITY
    // =========================
    public static String centerText(String text) {
        int padding = (WIDTH - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }
}
