package menus.submenus.customer;

import utils.MenuNavigator;

public class OrderMenu {
     private final String[] options = {
        "Browse Menu",
        "Add to Order",
        "Checkout",
        "Back"
    };

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("Order Menu", options);

            switch (choice) {
                case 0 -> System.out.println("Browsing menu...");
                case 1 -> System.out.println("Add to order...");
                case 2 -> System.out.println("Checkout...");
                case 3 -> { return; }
            }

            pause();
        }
    }

    private void pause() {
        System.out.println("Press Enter to continue...");
        new java.util.Scanner(System.in).nextLine();
    }
}
