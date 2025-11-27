package utils;

import java.util.Scanner;

public class ConsoleUtils {
    public static final int WIDTH = 120; // set your console width
    private static final Scanner scanner = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String centerText(String text) {
        int padding = (WIDTH - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }

    public static void printCentered(String text) {
        System.out.println(centerText(text));
    }

    // Helper functions to center only the label
    public static String centerTextInline(String text) {
        int width = 110;
        int pad = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, pad)) + text;
    }

     public static int navigateOptionsOnly(String title, String[] options) {
        int selected = 0;

        while (true) {

            // Title
            System.out.println();
            printCentered(title);
            System.out.println("=".repeat(WIDTH));
            System.out.println();

            // Options
            for (int i = 0; i < options.length; i++) {
                boolean highlight = (i == selected);
                String line = highlight ? ">> " + options[i] + " <<" : options[i];
                printCentered(line);
            }

            System.out.println();
            printCentered("| Use W/S to navigate, Enter to select |");

            // Handle input
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "w" -> selected = (selected - 1 + options.length) % options.length;
                case "s" -> selected = (selected + 1) % options.length;
                case ""  -> { return selected; } // Enter pressed
                default  -> {
                    printCentered("[!] Invalid input. Use W/S to navigate or Enter to select.");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                }
            }
        }
    }
}
