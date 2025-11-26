package menus.submenus.admin;

import models.CoffeeMenu;
import models.Coffee;
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
            int choice = MenuNavigator.navigate("=== Menu & Inventory ===", options);

            switch (choice) {
                case 0 -> viewMenu();
                case 1 -> addCoffee();
                case 2 -> editCoffee();
                case 3 -> removeCoffee();
                case 4 -> { return; } // back
            }

            MenuNavigator.waitForEnter();
        }
    }

    private void viewMenu() {
        MenuNavigator.clearScreen();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();

        List<Coffee> list = CoffeeMenu.getCoffees();
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    private void addCoffee() {
        System.out.print("Coffee Name: ");
        String name = MenuNavigator.getInput();

        System.out.print("Price: ");
        int price = MenuNavigator.getIntInput();

        System.out.print("Stock: ");
        int stock = MenuNavigator.getIntInput();

        CoffeeMenu.addCoffee(new Coffee(name, price, stock));
        System.out.println(name + " added to menu!");
    }

    private void editCoffee() {
        viewMenu();
        System.out.print("Select coffee number to edit: ");
        int index = MenuNavigator.getIntInput() - 1;

        System.out.print("New Name: ");
        String name = MenuNavigator.getInput();

        System.out.print("New Price: ");
        int price = MenuNavigator.getIntInput();

        System.out.print("New Stock: ");
        int stock = MenuNavigator.getIntInput();

        CoffeeMenu.updateCoffee(index, name, price, stock);
        System.out.println("Coffee updated!");
    }

    private void removeCoffee() {
        viewMenu();
        System.out.print("Select coffee number to remove: ");
        int index = MenuNavigator.getIntInput() - 1;

        CoffeeMenu.removeCoffee(index);
        System.out.println("Coffee removed!");
    }
}
