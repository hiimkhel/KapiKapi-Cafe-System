package menus.submenus.customer;

import java.util.ArrayList;
import java.util.List;

import controllers.OrderQueue;
import models.Order;
import utils.MenuNavigator;

public class OrderMenu {
     private final String[] options = {
        "Browse Menu",
        "Checkout",
        "Back"
    };

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("Order Menu", options);

            switch (choice) {
                case 0 -> orderCoffee();
                case 1 -> System.out.println("Checking out...");
                case 2 -> { return; }
            }

            pause();
        }
    }

    private void pause() {
        System.out.println("Press Enter to continue...");
        new java.util.Scanner(System.in).nextLine();
    }

    private void orderCoffee(){

        // List of all coffee
         String[] menu = {
                "Americano",
                "Latte",
                "Cappuccino",
                "Mocha",
                "Caramel Macchiato",
                "Back"
        };

        List<String> cart = new ArrayList<>();

        while (true){
            int choice = MenuNavigator.navigate("=== Coffee Menu", menu);


            if(choice == menu.length - 1) break;

            String selectedDrink = menu[choice];
            cart.add(selectedDrink);
            System.out.println(selectedDrink + " added to cart!");
            MenuNavigator.waitForEnter();
        }

            if (cart.isEmpty()) {
            System.out.println("No items selected.");
            return;
        }

        System.out.println("Confirm order? (Y/N)");
        String confirm = MenuNavigator.getInput().toLowerCase();

        if (confirm.equals("y")) {
            Order order = new Order("kelly", cart);
            OrderQueue.addOrder(order);
            System.out.println("Order sent! Admin will brew it soon.");
        } else {
            System.out.println("Order canceled.");
        }
    }

}
