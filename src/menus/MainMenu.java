package menus;

import java.util.Scanner;

public class MainMenu {
    private String[] options = {"Customer Login", "Admin Login", "Exit"};
    private int selected = 0; // currently selected menu option

    private Scanner scanner = new Scanner(System.in);

    public void show() {
        while (true) {
            clearScreen();
            System.out.println("=== Welcome to Kapikapi Café ===\n");

            // Display menu options with highlight
            for (int i = 0; i < options.length; i++) {
                if (i == selected) {
                    System.out.println("> " + options[i]); // highlight
                } else {
                    System.out.println("  " + options[i]);
                }
            }

            System.out.println("\nUse W/S to navigate and Enter to select.");

            // Read input
            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "w":
                    selected = (selected - 1 + options.length) % options.length;
                    break;
                case "s":
                    selected = (selected + 1) % options.length;
                    break;
                case "":
                    // Enter pressed
                    handleSelection();
                    if (selected == options.length - 1) return; // Exit
                    break;
                default:
                    System.out.println("Invalid input! Use W/S or Enter.");
            }
        }
    }

    private void handleSelection() {
        String option = options[selected];
        switch (option) {
            case "Customer Login" -> System.out.println("Customer login selected.");
            case "Admin Login" -> System.out.println("Admin login selected.");
            case "Exit" -> {
                System.out.println("Exiting...");
            }
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private void clearScreen() {
        // Works on most terminals
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
