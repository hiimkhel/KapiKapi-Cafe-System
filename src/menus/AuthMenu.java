package menus;

import controllers.CustomerController;
import models.Customer;
import utils.MenuNavigator;

public class AuthMenu {

    private final String[] options = {
        "Login",
        "Register",
        "Exit"
    };

    public Customer show() {
        while (true) {
            int choice = MenuNavigator.navigate("Welcome to KapiKapi", options);

            switch (choice) {
                case 0 -> {
                    Customer customer = CustomerController.login();
                    if (customer != null) return customer;
                }
                case 1 -> {
                    Customer customer = CustomerController.register();
                    if (customer != null) return customer;
                }
                case 2 -> System.exit(0);
            }

            MenuNavigator.waitForEnter();
        }
    }
}
