package menus.submenus.admin;

import utils.MenuNavigator;

public class PendingOrdersMenu {
    public void show() {
        String[] options = {"Back"};

        while (true) {
            int selected = MenuNavigator.navigate("=== Pending Orders ===", options);

            if (selected == 0) return; // Back
        }
    }
}
