package controllers;

import java.util.Scanner;

import javax.xml.crypto.Data;

import database.Database;
import menus.CustomerMenu;
import models.Customer;

public class CustomerController {
    private static final Scanner scanner = new Scanner(System.in);

    public static Customer login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        if (!Database.exists(username)) {
            System.out.println("User not found.");
            return null;
        }

        Customer c = Database.get(username);

        if (!c.getPassword().equals(password)) {
            System.out.println("Wrong password.");
            return null;
        }

        System.out.println("Login successful!");
        new CustomerMenu(c).show(); 
        return c; // FIXED: must return a Customer
    }

    public static Customer register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        if (Database.exists(username)) {
            System.out.println("Username already taken.");
            return null;
        }

        System.out.print("Create password: ");
        String password = scanner.nextLine().trim();

        Customer c = new Customer(username, password);

        Database.registerCustomer(c); // SAVE TO FILE/DATABASE

        System.out.println("Registration complete!");
        return c; // FIXED: must return a Customer
    }

    
    // --------------------------
    // WALLET OPERATIONS
    // --------------------------
    public static void topUpWallet(Customer customer, int amount) {
        if (amount > 0) {
            customer.addToWallet(amount);
        }
    }

    // --------------------------
    // STAMP OPERATIONS
    // --------------------------
    public static boolean canRedeemCoffee(Customer customer) {
        return customer.hasFreeCoffeeReward();
    }

    public static boolean redeemFreeCoffee(Customer customer) {
        return customer.redeemFreeCoffee();
    }

    // --------------------------
    // ACHIEVEMENTS
    // --------------------------
    public static int getTotalOrders(Customer customer) {
        return customer.getTotalOrders();
    }

    public static int getTotalSpent(Customer customer) {
        return customer.getTotalSpent();
    }

    public static int getStampCount(Customer customer) {
        return customer.getStampCount();
    }
    
}
