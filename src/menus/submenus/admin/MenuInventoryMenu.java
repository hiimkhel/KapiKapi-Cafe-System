package menus.submenus.admin;

import models.CoffeeMenu;
import models.Coffee;
import utils.ConsoleUtils;
import utils.MenuNavigator;

import java.util.List;

public class MenuInventoryMenu {

    private final String[] options = {
            "View Menu",
            "Add Coffee",
            "Edit Coffee",
            "Remove Coffee",
            "Back"
    };

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("=== Menu & Inventory ===", options, true);

            switch (choice) {
                case 0 -> viewMenu();
                case 1 -> addCoffee();
                case 2 -> editCoffee();
                case 3 -> removeCoffee();
                case 4 -> { return; } // Back to Main Menu
            }

            MenuNavigator.waitForEnter();
        }
    }

    private void viewMenu() {
        ConsoleUtils.clearScreen();
        MenuNavigator.printHeaderCentered();
        ConsoleUtils.printCentered("============================================================");
        ConsoleUtils.printCentered(String.format("%-5s %-20s %-15s %-10s", "No.", "Coffee Name", "Price (₱)", "Stock"));
        ConsoleUtils.printCentered("------------------------------------------------------------");

        List<Coffee> list = CoffeeMenu.getCoffees();
        for (int i = 0; i < list.size(); i++) {
            Coffee c = list.get(i);
            ConsoleUtils.printCentered(String.format("%-5s %-20s %-15d %-10d", "[" + (i + 1) + "]", c.getName(), c.getPrice(), c.getStock()));
        }

        ConsoleUtils.printCentered("------------------------------------------------------------");
        ConsoleUtils.printCentered("Press Enter to return to Inventory Menu...");

    }

    private void addCoffee() {
        ConsoleUtils.clearScreen();
        MenuNavigator.printHeaderCentered();
        ConsoleUtils.printCentered("============================================================");
        ConsoleUtils.printCentered("                        ADD NEW COFFEE                       ");
        ConsoleUtils.printCentered("============================================================\n");

        System.out.print("\t\t\t\tCoffee Name: ");
        String name = MenuNavigator.getInput();

        System.out.print("\t\t\t\tPrice (₱): ");
        int price = MenuNavigator.getIntInput();

        System.out.print("\t\t\t\tStock: ");
        int stock = MenuNavigator.getIntInput();

        CoffeeMenu.addCoffee(new Coffee(name, price, stock));

        ConsoleUtils.printCentered("\n\t\t\t\t[" + name + " added successfully!]");


    }

    private void editCoffee() {
        ConsoleUtils.clearScreen();
        MenuNavigator.printHeaderCentered();
        ConsoleUtils.printCentered("============================================================");
        ConsoleUtils.printCentered("                        EDIT COFFEE                          ");
        ConsoleUtils.printCentered("============================================================\n");

        List<Coffee> list = CoffeeMenu.getCoffees();
        ConsoleUtils.printCentered(String.format("%-5s %-20s %-15s %-10s", "No.", "Coffee Name", "Price (₱)", "Stock"));
        ConsoleUtils.printCentered("------------------------------------------------------------");
        for (int i = 0; i < list.size(); i++) {
            Coffee c = list.get(i);
            ConsoleUtils.printCentered(String.format("%-5s %-20s %-15d %-10d", "[" + (i + 1) + "]", c.getName(), c.getPrice(), c.getStock()));
        }
        ConsoleUtils.printCentered("------------------------------------------------------------");

        System.out.print("\n\t\t\t\tSelect coffee number to edit: ");
        int index = MenuNavigator.getIntInput() - 1;

        System.out.print("\t\t\t\tNew Name: ");
        String name = MenuNavigator.getInput();

        System.out.print("\t\t\t\tNew Price (₱): ");
        int price = MenuNavigator.getIntInput();

        System.out.print("\t\t\t\tNew Stock: ");
        int stock = MenuNavigator.getIntInput();

        CoffeeMenu.updateCoffee(index, name, price, stock);

        ConsoleUtils.printCentered("\n\t\t\t\t[Coffee updated successfully!]");


    }

    private void removeCoffee() {
        ConsoleUtils.clearScreen();
        MenuNavigator.printHeaderCentered();
        ConsoleUtils.printCentered("============================================================");
        ConsoleUtils.printCentered("                        REMOVE COFFEE                        ");
        ConsoleUtils.printCentered("============================================================\n");

        List<Coffee> list = CoffeeMenu.getCoffees();
        ConsoleUtils.printCentered(String.format("%-5s %-20s %-15s %-10s", "No.", "Coffee Name", "Price (₱)", "Stock"));
        ConsoleUtils.printCentered("------------------------------------------------------------");
        for (int i = 0; i < list.size(); i++) {
            Coffee c = list.get(i);
            ConsoleUtils.printCentered(String.format("%-5s %-20s %-15d %-10d", "[" + (i + 1) + "]", c.getName(), c.getPrice(), c.getStock()));
        }
        ConsoleUtils.printCentered("------------------------------------------------------------");

        System.out.print("\n\t\t\t\tSelect coffee number to remove: ");
        int index = MenuNavigator.getIntInput() - 1;

        CoffeeMenu.removeCoffee(index);

        ConsoleUtils.printCentered("\n\t\t\t\t[Coffee removed successfully!]");

    }
}
