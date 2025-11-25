package menus;

import utils.ConsoleUtils;
import java.util.Scanner;

public abstract class BaseMenu {

    protected String title;
    protected String[] options;
    protected int selected = 0;
    protected Scanner scanner = new Scanner(System.in);

    public void show() {
        int width = 120; 
        while (true) {
            ConsoleUtils.clearScreen();
            printHeaderCentered();
            // Center the title inside the border
            int totalPadding = width - title.length() - 2; // 2 for the spaces around title
            int leftPadding = totalPadding / 2;
            int rightPadding = totalPadding - leftPadding;

            System.out.println("\n" + "=".repeat(leftPadding) + " " + title + " " + "=".repeat(rightPadding) + "\n");

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

    public static void printHeaderCentered() {
        String[] lines = {
            "██╗  ██╗ █████╗ ██████╗ ██╗██╗  ██╗ █████╗ ██████╗ ██╗     ██████╗ █████╗ ███████╗███████╗",
            "██║ ██╔╝██╔══██╗██╔══██╗██║██║ ██╔╝██╔══██╗██╔══██╗██║    ██╔════╝██╔══██╗██╔════╝██╔════╝",
            "█████╔╝ ███████║██████╔╝██║█████╔╝ ███████║██████╔╝██║    ██║     ███████║█████╗  █████╗  ",
            "██╔═██╗ ██╔══██║██╔═══╝ ██║██╔═██╗ ██╔══██║██╔═══╝ ██║    ██║     ██╔══██║██╔══╝  ██╔══╝  ",
            "██║  ██╗██║  ██║██║     ██║██║  ██╗██║  ██║██║     ██║    ╚██████╗██║  ██║██║     ███████╗",
            "╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝     ╚═════╝╚═╝  ╚═╝╚═╝     ╚══════╝"
        };

        int width = 120; // <-- Change depending on your terminal width

        for (String line : lines) {
            int padding = (width - line.length()) / 2;
            System.out.println(" ".repeat(Math.max(0, padding)) + line);
        }
    }
}
