package menus.submenus.customer;

import models.Customer;
import utils.ConsoleUtils;
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
            int choice = MenuNavigator.navigate("Profile & Rewards - " + customer.getUsername(), options, true);

            switch (choice) {
                case 0 -> walletMenu();
                case 1 -> stampCardMenu();
                case 2 -> achievementsMenu();
                case 3 -> { return; }
            }
        }
    }

    private void walletMenu() {
        String[] options = { "Top-up Wallet", "Return" };
        int selected = 0;

        while (true) {
            ConsoleUtils.clearScreen();
            MenuNavigator.printHeaderCentered();
            MenuNavigator.printBorder();

            // ========================= WALLET HEADER =========================
            ConsoleUtils.printCentered("███████╗ █████╗ ██╗      ██████╗ ██╗     ███████╗");
            ConsoleUtils.printCentered("██╔════╝██╔══██╗██║     ██╔═══██╗██║     ██╔════╝");
            ConsoleUtils.printCentered("█████╗  ███████║██║     ██║   ██║██║     █████╗  ");
            ConsoleUtils.printCentered("██╔══╝  ██╔══██║██║     ██║   ██║██║     ██╔══╝  ");
            ConsoleUtils.printCentered("██║     ██║  ██║███████╗╚██████╔╝███████╗███████╗");
            ConsoleUtils.printCentered("╚═╝     ╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚══════╝╚══════╝");
            ConsoleUtils.printCentered("──────────────────────────────────────────────────────────────");

            // ========================= BALANCE =========================
            ConsoleUtils.printCentered("Current Wallet Balance: ₱" + customer.getWalletBalance());
            ConsoleUtils.printCentered("");

            // ========================= OPTIONS =========================
            for (int i = 0; i < options.length; i++) {
                boolean highlight = (i == selected);
                String line = highlight ? ">> " + options[i] + " <<" : options[i];
                ConsoleUtils.printCentered(line);
            }

            ConsoleUtils.printCentered("| Use W/S to navigate, Enter to select |");

            // ========================= INPUT =========================
            String input = MenuNavigator.getInput().trim().toLowerCase();

            switch (input) {
                case "w" -> selected = (selected - 1 + options.length) % options.length;
                case "s" -> selected = (selected + 1) % options.length;
                case "" -> {
                    // ENTER pressed
                    if (selected == 1) { // Return
                        return;
                    }

                    // Top-up wallet
                    ConsoleUtils.printCentered("Enter amount to top-up: ₱");
                    int amount = MenuNavigator.getIntInput();
                    if (amount <= 0) {
                        ConsoleUtils.printCentered("[!] Invalid amount. Press Enter to continue...");
                        MenuNavigator.waitForEnter();
                        break;
                    }

                    CustomerController.topUpWallet(customer, amount);
                    ConsoleUtils.printCentered("Wallet successfully topped up! New balance: ₱" + customer.getWalletBalance());
                    MenuNavigator.waitForEnter();
                }
            }
        }
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
