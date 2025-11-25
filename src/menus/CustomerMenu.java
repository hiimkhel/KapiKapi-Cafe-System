package menus;

import utils.MenuNavigator;

// import the customer menus from the submenus folder
import menus.submenus.customer.OrderMenu;
import menus.submenus.customer.CheckOrdersMenu;
import menus.submenus.customer.ProfileMenu;
import menus.submenus.customer.WrappedPage;
import models.Customer;

public class CustomerMenu {

    private Customer customer; // store the full customer object

    public CustomerMenu(Customer customer){
        this.customer = customer;
    }

    private final String[] options = {
        "Order Drinks",
        "My Orders",
        "KapiKapi Wrapped",
        "Profile & Rewards",
        "Logout"
    };

    public void show() {
        while (true) {
            int choice = MenuNavigator.navigate("Customer Menu - " + customer.getUsername(), options);

            switch (choice) {
                case 0 -> new OrderMenu(customer).show();       // pass customer object
                case 1 -> new CheckOrdersMenu(customer).show(); // pass customer object
                case 2 -> new WrappedPage(customer).show();     // pass customer object
                case 3 -> new ProfileMenu(customer).show();     // pass customer object
                case 4 -> {
                    System.out.println("Logged out.");
                    return;
                }
            }
        }
    }
}
