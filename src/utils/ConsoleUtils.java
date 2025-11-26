package utils;

public class ConsoleUtils {
    public static final int WIDTH = 120; // set your console width
    
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
}
