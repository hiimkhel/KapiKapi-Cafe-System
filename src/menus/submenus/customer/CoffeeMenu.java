package menus.submenus.customer;

import java.util.ArrayList;
import java.util.List;
import models.Coffee;

public class CoffeeMenu {
    private static final List<Coffee> coffees = new ArrayList<>();

    static {
        coffees.add(new Coffee("Americano", 80));
        coffees.add(new Coffee("Latte", 120));
        coffees.add(new Coffee("Cappuccino", 110));
        coffees.add(new Coffee("Mocha", 130));
        coffees.add(new Coffee("Caramel Macchiato", 140));
    }

    public static List<Coffee> getCoffees() {
        return new ArrayList<>(coffees); // return copy to avoid external modification
    }
}
