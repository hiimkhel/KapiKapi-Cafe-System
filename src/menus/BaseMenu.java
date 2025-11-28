package menus;

import utils.ConsoleUtils;
import utils.MenuNavigator;

public abstract class BaseMenu {

    protected String title;
    protected String[] options;
    protected int selected = 0;

    public void show() {
        while (true) {
            ConsoleUtils.clearScreen();
            MenuNavigator.printHeaderCentered();
            int choice = MenuNavigator.navigate(title, options, true);
            // Exit signal
            if (choice == -1) return;

            // Handle menu selection
            if (handleSelection(choice)) return;
        }
    }

    // Each menu implements its own action
    protected abstract boolean handleSelection(int index);
}
