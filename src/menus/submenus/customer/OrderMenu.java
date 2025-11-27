package menus.submenus.customer;

import java.util.ArrayList;
import java.util.List;

import controllers.OrderQueue;
import database.Database;
import models.CoffeeMenu;
import models.Coffee;
import models.Customer;
import models.Order;
import utils.ConsoleUtils;
import utils.MenuNavigator;

public class OrderMenu {

    private final Customer customer;
    private final String[] options = { "Browse Menu", "Cart", "Checkout", "Back" };
    private final List<Coffee> coffeeList = CoffeeMenu.getCoffees();

    public OrderMenu(Customer customer) {
        this.customer = customer;
    }

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("Order Menu - " + customer.getUsername(), options, true);

            switch (choice) {
                case 0 -> browseMenu();
                case 1 -> viewCart();
                case 2 -> checkout();
                case 3 -> { return; } // Back
            }

            MenuNavigator.waitForEnter();
        }
    }

    // ============================================================
    // 1. BROWSE MENU
    // ============================================================
    private void browseMenu() {
        MenuNavigator.clearScreen();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();

        String[] menuOptions = new String[coffeeList.size() + 1];
        for (int i = 0; i < coffeeList.size(); i++) {
            Coffee c = coffeeList.get(i);
            // Display with stock in brackets and dot leaders for price alignment
            menuOptions[i] = c.getName() + " " 
                            + ".".repeat(Math.max(0, 25 - c.getName().length())) 
                            + " ₱" + c.getPrice() 
                            + "   [" + c.getStock() + " cups left]";
        }
        menuOptions[menuOptions.length - 1] = "[ Back ]";

        while (true) {
            int choice = MenuNavigator.navigate("BROWSE MENU", menuOptions, true);
            if (choice == menuOptions.length - 1) break;

            Coffee selected = coffeeList.get(choice);
            customer.addToCart(selected); // logic remains the same

            System.out.println(selected.getName() + " added to cart! (₱" + selected.getPrice() + ")");
            MenuNavigator.waitForEnter();
        }
    }

    // ============================================================
    // 2. VIEW CART
    // ============================================================
    private void viewCart() {
        MenuNavigator.clearScreen();
        MenuNavigator.printBorder();
        MenuNavigator.printHeaderCentered();

        List<Coffee> cart = customer.getCart();

        MenuNavigator.printBorder();
        ConsoleUtils.printCentered("                           [YOUR CART]                      ");
        ConsoleUtils.printCentered("──────────────────────────────────────────────────────────────────────────");

        if (cart.isEmpty()) {
            ConsoleUtils.printCentered("Your cart is empty.");
            ConsoleUtils.printCentered("Press Enter to return...");
            MenuNavigator.waitForEnter();
            return;
        }

        // Table header
        ConsoleUtils.printCentered(String.format(
                "%-5s %-20s %-10s",
                "No.", "Item", "Price"
        ));
        ConsoleUtils.printCentered("------------------------------------------------------------------");

        // Cart items
        for (int i = 0; i < cart.size(); i++) {
            Coffee c = cart.get(i);

            ConsoleUtils.printCentered(String.format(
                    "%-5s %-20s ₱%-10d",
                    "[" + (i + 1) + "]",
                    c.getName(),
                    c.getPrice()
            ));
        }

        ConsoleUtils.printCentered("------------------------------------------------------------------");
        System.out.println("\t\t\t\tTotal: ₱" + customer.getCartTotal());
        ConsoleUtils.printCentered("------------------------------------------------------------------");

        // Footer input
        String prompt = "[?] Enter No. to remove from cart (0 to return): >> ";
        System.out.print(ConsoleUtils.centerTextInline(prompt));
        int choice = MenuNavigator.getIntInput();

        if (choice == 0) return;

        if (choice < 1 || choice > cart.size()) {
            ConsoleUtils.printCentered("Invalid selection.");
            ConsoleUtils.printCentered("Press Enter to continue...");
            MenuNavigator.waitForEnter();
            return;
        }

        // Remove item
        Coffee removed = customer.removeFromCart(choice - 1);

        ConsoleUtils.printCentered("------------------------------------------------------------------");
        ConsoleUtils.printCentered("Removed: " + removed.getName());
        ConsoleUtils.printCentered("Updated Total: ₱" + customer.getCartTotal());
        ConsoleUtils.printCentered("------------------------------------------------------------------");
        ConsoleUtils.printCentered("Press Enter to continue...");
        MenuNavigator.waitForEnter();
    }

    // ============================================================
    // 3. CHECKOUT
    // ============================================================
    private void checkout() {
        List<Coffee> cart = customer.getCart();

        MenuNavigator.clearScreen();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();

        if (cart.isEmpty()) {
            ConsoleUtils.printCentered("Your cart is empty!");
            MenuNavigator.waitForEnter();
            return;
        }

        // ========================= RECEIPT HEADER =========================
        ConsoleUtils.printCentered("[ CHECKOUT RECEIPT ]");
        ConsoleUtils.printCentered("──────────────────────────────────────────────────────────────────────────");
        ConsoleUtils.printCentered("");
        System.out.println("\t\t\tCustomer: " + customer.getUsername());
        ConsoleUtils.printCentered("");
        ConsoleUtils.printCentered("----------------------------------------------------------------------------");
        ConsoleUtils.printCentered("ITEM                 PRICE            ");
        ConsoleUtils.printCentered("---------------------------------------------------------------------------- ");

        // ========================= CART ITEMS =========================
        for (Coffee c : cart) {
            ConsoleUtils.printCentered(String.format(
                    "%-20s ₱%.2f",
                    c.getName(),
                    (double)c.getPrice()
            ));
        }

        ConsoleUtils.printCentered("----------------------------------------------------------------------------");
        ConsoleUtils.printCentered(String.format(
                "TOTAL:                                     ₱%.2f                      ",
                (double)customer.getCartTotal()
        ));
        ConsoleUtils.printCentered("----------------------------------------------------------------------------");
        ConsoleUtils.printCentered("");

        // ========================= WALLET CHECK =========================
        if (customer.getWalletBalance() < customer.getCartTotal()) {
            ConsoleUtils.printCentered("Insufficient wallet balance! Please top-up in Profile.");
            MenuNavigator.waitForEnter();
            return;
        }

            // ========================= CUSTOM OPTIONS NAVIGATOR =========================
        int choice = checkoutOptionsNavigator();

        if (choice == 1) { // Cancel
            ConsoleUtils.printCentered("Order cancelled.");
            MenuNavigator.waitForEnter();
            return;
        }

        // ========================= CREATE ORDER =========================
        List<String> items = new ArrayList<>();
        for (Coffee c : cart) items.add(c.getName());

        Order order = new Order(customer.getUsername(), items, customer.getCartTotal());

        Database.addOrder(order);
        customer.recordOrder(order);
        customer.deductFromWallet(customer.getCartTotal());
        OrderQueue.addOrder(order);
        customer.clearCart();

        ConsoleUtils.printCentered("Order placed! Admin will brew it soon.");
        MenuNavigator.waitForEnter();
    }

    // Helper functions 
    private int checkoutOptionsNavigator() {
        String[] options = { "Checkout", "Cancel" };
        int selected = 0;

        // Print header once
        System.out.println("\n================================================== Select an option: ===================================================\n");

        while (true) {
            // Draw options
            for (int i = 0; i < options.length; i++) {
                if (i == selected) {
                    System.out.println("\t\t\t\t\t\t    >> " + options[i] + " <<");
                } else {
                    System.out.println("\t\t\t\t\t\t      " + options[i]);
                }
            }

            // Prompt
            System.out.print("\t\t\t| Use W/S to navigate, Enter to select: ");
            String input = MenuNavigator.getInput().trim().toLowerCase();
            switch (input) {
                case "w" -> selected = (selected - 1 + options.length) % options.length;
                case "s" -> selected = (selected + 1) % options.length;
                case ""  -> { return selected; }  // Enter
                default  -> System.out.println("Invalid input! Use W/S or Enter.");
            }

            // Move cursor UP to redraw the options only
            System.out.print("\033[" + (options.length + 1) + "A"); // +1 for the prompt line
            System.out.print("\033[J"); // clear everything below cursor
        }
    }
}


