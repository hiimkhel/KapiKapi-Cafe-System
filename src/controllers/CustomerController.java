package controllers;

import database.CustomerDatabase;
import models.Customer;
import utils.MenuNavigator;

public class CustomerController {

    public static Customer login() {
        System.out.print("Enter username: ");
        String username = MenuNavigator.getInput();

        System.out.print("Enter password: ");
        String password = MenuNavigator.getInput();

        Customer customer = CustomerDatabase.login(username, password);

        if (customer == null) {
            System.out.println("Invalid username or password!");
            return null;
        }

        System.out.println("Login successful! Welcome " + username + "!");
        return customer;
    }

    public static Customer register() {
        System.out.print("Choose a username: ");
        String username = MenuNavigator.getInput();

        System.out.print("Choose a password: ");
        String password = MenuNavigator.getInput();

        Customer newCustomer = new Customer(username, password);

        if (!CustomerDatabase.register(newCustomer)) {
            System.out.println("Username already taken!");
            return null;
        }

        System.out.println("Account created successfully!");
        return newCustomer;
    }
}
