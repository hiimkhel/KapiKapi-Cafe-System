package menus;

import utils.MenuNavigator;
import models.Customer;

// import the customer menus from the submenus folder
import menus.submenus.customer.OrderMenu;
import menus.submenus.customer.CheckOrdersMenu;
import menus.submenus.customer.ProfileMenu;
import menus.submenus.customer.WrappedPage;

public class CustomerMenu extends BaseMenu {

    private final Customer customer;

    public CustomerMenu(Customer customer) {
        this.customer = customer;
        this.title = "Customer Menu - " + customer.getUsername();
        this.options = new String[]{
            "Order Drinks",
            "My Orders",
            "KapiKapi Wrapped",
            "Profile & Rewards",
            "Logout"
        };
    }

    @Override
    protected boolean handleSelection(int index) {
        switch (index) {
            case 0 -> new OrderMenu(customer).show();
            case 1 -> new CheckOrdersMenu(customer).show();
            case 2 -> new WrappedPage(customer).show();
            case 3 -> new ProfileMenu(customer).show();
            case 4, -1 -> { // Logout
                System.out.println("Logged out.");
                MenuNavigator.waitForEnter();
                return true; // exit menu loop
            }
        }
        return false;
    }
}
