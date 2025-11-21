package menus.submenus.customer;

import utils.MenuNavigator;

public class WrappedMenu {

    private final String[] options = {
        "Top Ordered Drink",
        "Your Favorites",
        "Order Stats",
        "Back"
    };

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("KapiKapi Wrapped", options);

            switch (choice) {
                case 0 -> System.out.println("Your top drink...");
                case 1 -> System.out.println("Your favorites...");
                case 2 -> System.out.println("Your stats...");
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
