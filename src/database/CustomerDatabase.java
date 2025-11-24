package database;

import models.Customer;
import java.io.*;
import java.util.*;

public class CustomerDatabase {

    private static final String FILE_NAME = "customers.db"; // simple text file
    private static Map<String, Customer> customers = new HashMap<>();

    static {
        loadDatabase();
    }

    // Save all customers to file
    public static void saveDatabase() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(customers);
        } catch (Exception e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    // Load customers from file
    @SuppressWarnings("unchecked")
    private static void loadDatabase() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            customers = (Map<String, Customer>) in.readObject();
        } catch (Exception e) {
            customers = new HashMap<>(); // empty if file missing
        }
    }

    // Add new customer
    public static void registerCustomer(Customer c) {
        customers.put(c.getUsername(), c);
        saveDatabase();
    }

    // Check if user exists
    public static boolean exists(String username) {
        return customers.containsKey(username);
    }

    // Fetch customer
    public static Customer get(String username) {
        return customers.get(username);
    }
}
