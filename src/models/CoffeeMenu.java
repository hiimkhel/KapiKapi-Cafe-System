package models;

import java.util.List;
import database.Database;

public class CoffeeMenu {

    // Return ONLY items of type Coffee (same as before)
    public static List<Coffee> getCoffees() {
        return Database.getMenuItems();
    }

    // Add new coffee item
    public static void addCoffee(Coffee item) {
        Database.addMenuItem(item); // >>> NEW: This now saves to file automatically
    }

    // Remove coffee by index
    public static void removeCoffee(int index) {
        // >>> UPDATED: Now calls Database remove which saves immediately
        Database.removeMenuItem(index);
    }

    // Update existing coffee details
    public static void updateCoffee(int index, String name, int price, int stock) {
        // >>> UPDATED: Uses Database method which also persists changes
        Database.updateMenuItem(index, name, price, stock);
    }
}
