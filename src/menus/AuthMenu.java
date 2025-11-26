package menus;

import controllers.CustomerController;
import models.Customer;
import utils.ConsoleUtils;
import utils.MenuNavigator;

public class AuthMenu {

    private final String[] options = {
        "Login",
        "Register",
        "Exit"
    };

    public Customer show() {
        while (true) {
            int choice = MenuNavigator.navigate("Welcome Customer", options);

            switch (choice) {
                case 0 -> {
                    ConsoleUtils.clearScreen();
                    printHeaderCentered();
                    Customer customer = CustomerController.login();
                    if (customer != null) return customer;
                }
                case 1 -> {
                    ConsoleUtils.clearScreen();
                    printHeaderCentered();
                    Customer customer = CustomerController.register();
                    if (customer != null) return customer;
                }
                case 2 -> System.exit(0);
            }

            MenuNavigator.waitForEnter();
        }
    }

    public static void printHeaderCentered() {
        String[] lines = {
            "██╗  ██╗ █████╗ ██████╗ ██╗██╗  ██╗ █████╗ ██████╗ ██╗     ██████╗ █████╗ ███████╗███████╗",
            "██║ ██╔╝██╔══██╗██╔══██╗██║██║ ██╔╝██╔══██╗██╔══██╗██║    ██╔════╝██╔══██╗██╔════╝██╔════╝",
            "█████╔╝ ███████║██████╔╝██║█████╔╝ ███████║██████╔╝██║    ██║     ███████║█████╗  █████╗  ",
            "██╔═██╗ ██╔══██║██╔═══╝ ██║██╔═██╗ ██╔══██║██╔═══╝ ██║    ██║     ██╔══██║██╔══╝  ██╔══╝  ",
            "██║  ██╗██║  ██║██║     ██║██║  ██╗██║  ██║██║     ██║    ╚██████╗██║  ██║██║     ███████╗",
            "╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝     ╚═════╝╚═╝  ╚═╝╚═╝     ╚══════╝",
        "======================================================================================================================="
        };

        int width = 120; // <-- Change depending on your terminal width

        for (String line : lines) {
            int padding = (width - line.length()) / 2;
            System.out.println(" ".repeat(Math.max(0, padding)) + line);
        }
    }
}
