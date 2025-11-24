package database;

import models.Customer;
import java.util.HashMap;

public class CustomerDatabase {

    // username → Customer object
    private static HashMap<String, Customer> customers = new HashMap<>();

    public static boolean register(Customer customer) {
        if (customers.containsKey(customer.getUsername())) {
            return false;
        }
        customers.put(customer.getUsername(), customer);
        return true;
    }

    public static Customer login(String username, String password) {
        Customer customer = customers.get(username);
        if (customer == null) return null;
        if (!customer.getPassword().equals(password)) return null;
        return customer;
    }

    public static Customer get(String username) {
        return customers.get(username);
    }
}

