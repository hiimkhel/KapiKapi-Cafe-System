package controllers;

import java.util.Scanner;

import database.CustomerDatabase;
import menus.CustomerMenu;
import models.Customer;

public class CustomerController {
    private static final Scanner scanner = new Scanner(System.in);

    public static Customer login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        if (!CustomerDatabase.exists(username)) {
            System.out.println("User not found.");
            return null;
        }

        Customer c = CustomerDatabase.get(username);

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

        if (CustomerDatabase.exists(username)) {
            System.out.println("Username already taken.");
            return null;
        }

        System.out.print("Create password: ");
        String password = scanner.nextLine().trim();

        Customer c = new Customer(username, password);

        CustomerDatabase.registerCustomer(c); // SAVE TO FILE/DATABASE

        System.out.println("Registration complete!");
        return c; // FIXED: must return a Customer
    }
}
