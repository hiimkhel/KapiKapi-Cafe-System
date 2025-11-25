package models;

import java.util.List;
import database.Database;

public class CoffeeMenu {

    // Return ONLY items of type Coffee
    public static List<Coffee> getCoffees() {
        return Database.getMenuItems();
    }

    public static void addCoffee(Coffee item) {
        Database.addMenuItem(item);
    }

    public static void removeCoffee(int index) {
        List<Coffee> coffees = Database.getMenuItems();
        if (index >= 0 && index < coffees.size()) {
            coffees.remove(index);
        }
    }

    public static void updateCoffee(int index, String name, int price, int stock) {
        List<Coffee> coffees = Database.getMenuItems();
        if (index >= 0 && index < coffees.size()) {
            Coffee c = coffees.get(index);
            c.setName(name);
            c.setPrice(price);
            c.setStock(stock);
        }
    }
}
