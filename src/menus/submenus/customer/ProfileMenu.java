package menus.submenus.customer;

import models.Customer;
import utils.MenuNavigator;
import controllers.CustomerController;
import database.Database;

public class ProfileMenu {

    private final Customer customer;

    private final String[] options = {
        "Wallet",
        "Stamp Card",
        "Achievements",
        "Back"
    };

    public ProfileMenu(Customer customer) {
        this.customer = customer;
    }

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("Profile & Rewards - " + customer.getUsername(), options);

            switch (choice) {
                case 0 -> walletMenu();
                case 1 -> stampCardMenu();
                case 2 -> achievementsMenu();
                case 3 -> { return; }
            }
        }
    }

    private void walletMenu() {
        MenuNavigator.clearScreen();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();
        System.out.println("=== Wallet ===");
        System.out.println("Balance: ₱" + customer.getWalletBalance());
        System.out.println("\nTop-up? (Y/N)");

        if (MenuNavigator.getInput().equalsIgnoreCase("y")) {
            System.out.print("Enter amount: ₱");
            int amount = MenuNavigator.getIntInput();
            CustomerController.topUpWallet(customer, amount);
            System.out.println("Wallet topped up! New balance: ₱" + customer.getWalletBalance());
        }

        MenuNavigator.waitForEnter();
    }

    private void stampCardMenu() {
        MenuNavigator.clearScreen();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();
        Customer freshCustomer = Database.findCustomerByUsername(customer.getUsername());
        System.out.println("=== Stamp Card ===");
        System.out.println("Stamps: " + freshCustomer.getStampCount() + " / 10");

        if (CustomerController.canRedeemCoffee(freshCustomer)) {
            System.out.println("Eligible for FREE coffee! Redeem? (Y/N)");
            if (MenuNavigator.getInput().equalsIgnoreCase("y")) {
                CustomerController.redeemFreeCoffee(freshCustomer);
                Database.updateCustomer(freshCustomer); // persist after redemption
                System.out.println("Coffee redeemed! Stamps remaining: " + freshCustomer.getStampCount());
            }
        }

        MenuNavigator.waitForEnter();
    }

    private void achievementsMenu() {
        MenuNavigator.clearScreen();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();
        System.out.println("=== Achievements ===");
        System.out.println("Total Orders  : " + CustomerController.getTotalOrders(customer));
        System.out.println("Total Spent   : ₱" + CustomerController.getTotalSpent(customer));
        System.out.println("Stamps Earned : " + CustomerController.getStampCount(customer));
        MenuNavigator.waitForEnter();
    }
}
