package utils;

import java.util.Scanner;

public class MenuNavigator {

    private static Scanner scanner = new Scanner(System.in);

    public static int navigate(String title, String[] options) {
        int selected = 0;

        while (true) {
            clearScreen();

            System.out.println("=== " + title + " ===\n");

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
                    return selected; // ENTER pressed
                }
                default -> System.out.println("Invalid input.");
            }
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
