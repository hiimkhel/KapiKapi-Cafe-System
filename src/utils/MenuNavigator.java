package utils;

import java.util.Scanner;

public class MenuNavigator {

    private static final Scanner scanner = new Scanner(System.in);

    public static int navigate(String title, String[] options) {
        int selected = 0;
        int width = 120;
        while (true) {
            clearScreen();
            printBorder();
            printHeaderCentered();
            // Center the title inside the border
            int totalPadding = width - title.length() - 2; // 2 for the spaces around title
            int leftPadding = totalPadding / 2;
            int rightPadding = totalPadding - leftPadding;

            System.out.println("\n" + "=".repeat(leftPadding) + " " + title + " " + "=".repeat(rightPadding) + "\n");

            for (int i = 0; i < options.length; i++) {
                if (i == selected)
                    System.out.println("> " + options[i]);
                else
                    System.out.println("  " + options[i]);
            }

            System.out.println("\nUse W/S to navigate, Enter to select.");

            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "w" -> selected = (selected - 1 + options.length) % options.length;
                case "s" -> selected = (selected + 1) % options.length;
                case "" -> {
                    return selected;
                }
                default -> System.out.println("Invalid input.");
            }
        }
    }

    public static void waitForEnter() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // NEW — safe public input method
    public static String getInput() {
        return scanner.nextLine();
    }

    
    public static int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again:");
            }
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
        };

        int width = 120; // <-- Change depending on your terminal width

        for (String line : lines) {
            int padding = (width - line.length()) / 2;
            System.out.println(" ".repeat(Math.max(0, padding)) + line);
        }
    }
    public static void printBorder(){
        System.out.println("=======================================================================================================================");
    }
}

