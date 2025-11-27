package menus.submenus.customer;

import models.Customer;
import utils.ConsoleUtils;
import utils.MenuNavigator;
import controllers.CustomerController;
import database.Database;

public class ProfileMenu {

    private final Customer customer;
    private final int MAX_STAMPS = 10;

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
                case 0 -> {walletMenu();}
                case 1 -> {stampCardMenu();
                    MenuNavigator.clearScreen();
                }
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
            ConsoleUtils.printCentered("YOUR WALLET");
            ConsoleUtils.printCentered("──────────────────────────────────────────────────────────────");

            // ========================= WALLET CARD =========================
            String nameLine = "Customer: " + customer.getUsername();
            String balanceLine = "Wallet Balance: ₱" + customer.getWalletBalance();

            int cardWidth = Math.max(nameLine.length(), balanceLine.length()) + 10;
            String border = "╔" + "═".repeat(cardWidth) + "╗";

            ConsoleUtils.printCentered(border);
            ConsoleUtils.printCentered("║" + " ".repeat((cardWidth - nameLine.length()) / 2) + nameLine + " ".repeat((cardWidth - nameLine.length() + 1) / 2) + "║");
            ConsoleUtils.printCentered("║" + " ".repeat((cardWidth - balanceLine.length()) / 2) + balanceLine + " ".repeat((cardWidth - balanceLine.length() + 1) / 2) + "║");
            ConsoleUtils.printCentered("╚" + "═".repeat(cardWidth) + "╝");

            ConsoleUtils.printCentered(""); // spacing

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
                    ConsoleUtils.centerText("──────────────────────────────────────────────────────────────");
                    System.out.print("\t\t\t\t\tEnter amount to top-up: ₱ ");
                    int amount = MenuNavigator.getIntInput();
                    if (amount <= 0) {
                        ConsoleUtils.printCentered("[!] Invalid amount. Press Enter to continue...");
                        MenuNavigator.waitForEnter();
                        break;
                    }

                    CustomerController.topUpWallet(customer, amount);
                    ConsoleUtils.printCentered(">> [!] Wallet successfully topped up!");
                    MenuNavigator.waitForEnter();
                }
            }
        }
    }

    private void stampCardMenu() {
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String GRAY  = "\u001B[37m";

        MenuNavigator.clearScreen();
        MenuNavigator.printBorder();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();
        ConsoleUtils.printCentered("╔════════════════════════════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║                           CAFE MOOLA™                          ║");
        ConsoleUtils.printCentered("║                           STAMP CARD                           ║");
        ConsoleUtils.printCentered("╠════════════════════════════════════════════════════════════════╣");
        ConsoleUtils.printCentered("║ Customer: " + customer.getUsername() 
                + "                        Stamps: " + customer.getStampCount() + "/" + MAX_STAMPS + "            ║");
        ConsoleUtils.printCentered("╠════════════════════════════════════════════════════════════════╣");

        // ========================= STAMP ROWS =========================
        String filledStamp = "[" + GREEN + "ʕ•ᴥ•ʔ" + RESET + "]";  // 7 chars visually
        String emptyStamp  = "[" + GRAY + "   " + RESET + "]";    // 7 chars visually


         // Build first row
        StringBuilder firstRow = new StringBuilder("\t\t\t   ║              ");
        for (int i = 0; i < 5; i++) {
            if (i < customer.getStampCount()) firstRow.append(filledStamp);
            else firstRow.append(emptyStamp);
            firstRow.append(" "); // space between stamps
        }
        firstRow.append("                ║");
        ConsoleUtils.printCentered(firstRow.toString());

        // Build second row
        StringBuilder secondRow = new StringBuilder("\t\t\t   ║              ");
        for (int i = 5; i < 10; i++) {
            if (i < customer.getStampCount()) secondRow.append(filledStamp);
            else secondRow.append(emptyStamp);
            secondRow.append(" ");
        }
        secondRow.append("                    ║");
        ConsoleUtils.printCentered(secondRow.toString());

        ConsoleUtils.printCentered("╚════════════════════════════════════════════════════════════════╝");

        // Footer action
        String[] options = {"Return"};
        ConsoleUtils.navigateOptionsOnly("Collect all 10 stamps and get a FREE coffee! Keep sipping & smiling ☕", options);
        
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
