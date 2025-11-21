package menus;

import utils.MenuNavigator;

// import the customer menus from the submenus folder
import menus.submenus.customer.OrderMenu;
import menus.submenus.customer.CheckOrdersMenu;
import menus.submenus.customer.ProfileMenu;
import menus.submenus.customer.WrappedMenu;
public class CustomerMenu {

    private final String[] options = {
        "Order",
        "Check Orders",
        "Profile",
        "KapiKapi Wrapped",
        "Logout"
    };

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("Customer Menu", options);

            switch (choice) {
                case 0 -> new OrderMenu().show();
                case 1 -> new CheckOrdersMenu().show();
                case 2 -> new ProfileMenu().show();
                case 3 -> new WrappedMenu().show();
                case 4 -> {
                    System.out.println("Logged out.");
                    return;
                }
            }
        }
    }
}
