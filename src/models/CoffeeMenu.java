package models;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMenu {

    private static final List<Coffee> coffees = new ArrayList<>();

    static {
        // Initial menu
        coffees.add(new Coffee("Americano", 80, 10));
        coffees.add(new Coffee("Latte", 120, 10));
        coffees.add(new Coffee("Cappuccino", 110, 10));
        coffees.add(new Coffee("Mocha", 130, 10));
        coffees.add(new Coffee("Caramel Macchiato", 140, 10));
    }

    public static List<Coffee> getCoffees() {
        return coffees;
    }

    public static void addCoffee(Coffee coffee) {
        coffees.add(coffee);
    }

    public static void removeCoffee(int index) {
        if (index >= 0 && index < coffees.size()) {
            coffees.remove(index);
        }
    }

    public static void updateCoffee(int index, String name, int price, int stock) {
        if (index >= 0 && index < coffees.size()) {
            Coffee c = coffees.get(index);
            c.setName(name);
            c.setPrice(price);
            c.setStock(stock);
        }
    }
}
