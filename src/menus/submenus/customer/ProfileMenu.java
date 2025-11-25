package menus.submenus.customer;

import models.Customer;
import utils.MenuNavigator;
import controllers.CustomerController;

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
        System.out.println("=== Stamp Card ===");
        System.out.println("Stamps: " + customer.getStampCount() + " / 10");

        if (CustomerController.canRedeemCoffee(customer)) {
            System.out.println("Eligible for FREE coffee! Redeem? (Y/N)");
            if (MenuNavigator.getInput().equalsIgnoreCase("y")) {
                CustomerController.redeemFreeCoffee(customer);
                System.out.println("Coffee redeemed! Stamps remaining: " + customer.getStampCount());
            }
        }

        MenuNavigator.waitForEnter();
    }

    private void achievementsMenu() {
        MenuNavigator.clearScreen();
        System.out.println("=== Achievements ===");
        System.out.println("Total Orders  : " + CustomerController.getTotalOrders(customer));
        System.out.println("Total Spent   : ₱" + CustomerController.getTotalSpent(customer));
        System.out.println("Stamps Earned : " + CustomerController.getStampCount(customer));
        MenuNavigator.waitForEnter();
    }
}
