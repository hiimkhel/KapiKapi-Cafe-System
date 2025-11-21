package menus;

import utils.ConsoleUtils;
import java.util.Scanner;

public abstract class BaseMenu {

    protected String title;
    protected String[] options;
    protected int selected = 0;
    protected Scanner scanner = new Scanner(System.in);

    public void show() {
        while (true) {
            ConsoleUtils.clearScreen();
            System.out.println("=== " + title + " ===\n");

            // Print menu with highlight
            for (int i = 0; i < options.length; i++) {
                if (i == selected) {
                    System.out.println("> " + options[i]);
                } else {
                    System.out.println("  " + options[i]);
                }
            }

            System.out.println("\nUse W/S to navigate and Enter to select.");

            // Input
            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "w" -> selected = (selected - 1 + options.length) % options.length;
                case "s" -> selected = (selected + 1) % options.length;
                case "" -> {
                    if (handleSelection(selected)) return; // exit if menu returns true
                }
                default -> System.out.println("Invalid input! Use W/S or Enter.");
            }
        }
    }

    // Each menu will override this
    protected abstract boolean handleSelection(int index);
}
