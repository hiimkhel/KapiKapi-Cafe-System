package utils;

import java.util.Scanner;

public class MenuNavigator {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int WIDTH = 120; // Total menu width
    private static final String ASCII_ART = 
            "⣀⣤⣤⣤⠀⠀⠀⠀⠀⣴⡿⠻⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡟⠀⠀⠈⢻⣦⠀⣠⣾⠛⠉⠀⢹⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣤⣶⠶⠾⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠷⢶⣦⣤⣿⣾⠟⠀⣠⣶⣷⠀⣿⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⢀⣠⣴⠶⠟⠛⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⢻⠀⠰⠿⠟⠃⣴⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⢠⣶⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⠤⡤⣴⣾⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⣰⡿⠁⡠⠒⠋⠉⠓⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣄⡀⠀⢇⠀⠘⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⢰⡟⠀⡜⠁⠀⠀⠀⠀⠈⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣿⣿⠟⠀⢸⠗⠀⢸⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⣾⠇⢸⠁⣴⣦⠀⢠⣶⠀⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢄⣊⣸⠀⠀⠀⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⣿⠀⡈⠀⠙⠋⠀⠈⠛⠀⠘⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⠋⠀⠀⡎⡆⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⣿⠀⡇⠀⠀⢀⢠⠻⠆⠀⠀⢣⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠣⣀⣀⣰⠟⠁⠀⠀⢸⣇⣀⣤⣤⣶⣶⠶⠶⣶⣶⣤⣤⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⢿⡆⢀⠀⣰⡹⢸⡇⠀⠀⠀⠀⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠃⠀⠀⠀⣴⢻⡟⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠙⠻⢷⣦⣄⠀⠀⠀⠀⠀⠀⠀\n" +
            "⢸⣇⢸⣴⡿⢃⠈⣿⠀⢠⡀⠀⠀⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⠁⠀⠀⠀⢴⠞⡐⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣦⡀⠀⠀⠀⠀\n" +
            "⠀⢿⡆⢣⠀⠘⠿⠟⠛⠛⠀⠀⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠋⠀⠀⠀⠀⠀⠀⢠⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡝⢿⣆⠀⠀⠀\n" +
            "⠀⠘⢿⡌⢧⡀⠀⠀⠀⢀⠶⡀⢠⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠴⠊⢀⣴⠶⠀⠀⠀⠀⢠⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⠈⢻⣧⠀⠀\n" +
            "⠀⠀⠈⢿⣆⠑⠦⣀⣴⣯⣂⠴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠤⠊⠁⢀⣴⠿⢋⠀⠀⠀⢀⡔⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⢹⣧⠀\n" +
            "⠀⠀⠀⠀⠙⢷⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡤⠐⠋⠀⣀⣤⡾⠛⠁⠐⠀⠀⢀⠔⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⢻⡇\n" +
            "⠀⠀⠀⠀⠀⠀⠙⠿⢶⣴⣲⣤⠀⠀⠀⠀⡤⠔⠂⢉⣀⣠⣤⡶⠿⠛⣉⣤⠖⠁⢀⠠⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠴⠀⢸⣷\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠛⠛⠿⠷⠶⠾⠿⠟⠛⢻⡏⠁⠀⠀⠘⣈⡡⠖⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⣿\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠁⠀⠀⠀⣿\n" +
            "⠀⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡎⠀⠀⠀⢠⣿\n" +
            "⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⢀⠀⠀⠀⠀⠀⠀⠀⡼⠀⠀⠀⠀⣸⡏\n" +
            "⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠎⠁⠈⠑⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⠊⠁⣀⣳⠀⠀⠀⠀⠀⣰⠁⠀⠀⡠⢠⣿⠁\n" +
            "⠠⣿⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⡠⡄⠀⠀⠀⠀⢸⠀⢠⡄⠀⠀⠙⠢⠤⣀⡀⠀⠀⠀⠤⠐⠉⠀⢀⠀⠉⣰⡆⠀⠀⠀⢀⠃⠀⠠⡔⢤⡿⠃⠀\n" +
            "⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣇⡠⣴⣧⣿⠠⠤⠤⠄⣸⡀⣿⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡢⠆⣀⣤⣿⡧⠔⠒⠀⡾⠒⠢⠥⣰⡿⠁⠀⠀\n" +
            "⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⡄⠙⡇⢻⣇⠀⠀⠀⣺⠉⣿⠛⠷⣶⣦⣤⣤⣄⣀⣀⣀⣠⣤⣼⣶⣶⡟⢻⠉⢹⣧⠀⠀⠀⠁⠀⠀⢰⡿⠁⠀⠀⠀\n" +
            "⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣷⣄⡀⣈⣿⣄⠀⠀⡏⣸⣿⣀⣀⣀⣀⣀⣉⣉⣉⣉⣉⣉⣉⣀⣀⣹⣧⡈⣆⠀⣿⣦⡂⠀⡀⠀⣠⣿⠃⠀⠀⠀⠀\n" +
            "⠀⢻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⢾⣿⢿⠿⠿⣿⣿⣿⣿⣿⣿⣷⣾⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣾⣿⣿⣿⣷⣿⣿⣿⡿⠿⢿⡿⡿⠖" +
            "\n" + " \n" ;
    public static int navigate(String title, String[] options, boolean clear) {
        int selected = 0;
        String lastInput = "";

        int width = 120;
        while (true) {
            if (clear) {  // Only clear if requested
                clearScreen();
                printBorder();
                printHeaderCentered();
            }
            // Center the title inside the border
            int totalPadding = width - title.length() - 2; // 2 for the spaces around title
            int leftPadding = totalPadding / 2;
            int rightPadding = totalPadding - leftPadding;

            System.out.println("\n" + "=".repeat(leftPadding) + " " + title + " " + "=".repeat(rightPadding) + "\n");

            for (int i = 0; i < options.length; i++) {
                boolean highlight = (i == selected);
                String text = highlight
                        ? ">> " + options[i] + " <<"
                        : options[i];
                printCentered(text);
            }

            System.out.println();
            printFooter();
            
        // Move cursor UP 2 lines and RIGHT to just after "Input: "
        System.out.print("\033[2A");  // move cursor up 2 lines (above bottom border)
        System.out.print("\r");       // go to start of line
        System.out.print("| >>> "); // print up to Input: again
        String input = scanner.nextLine().toLowerCase();
            lastInput = input;

            switch (input) {
                case "w" -> selected = (selected - 1 + options.length) % options.length;
                case "s" -> selected = (selected + 1) % options.length;
                case ""  -> { return selected; }
                case "q" -> { return -1; } // optional quit
                default  -> { 
                    // User-friendly error message
                    System.out.println("\n[!] Invalid input! Use W/S to navigate or Enter to select. Press Enter to continue...");
                    scanner.nextLine(); // wait for user acknowledgment
                }
            }
        }
    }

    // -------------------------------------------------------
    // ---------------------- HELPERS ------------------------
    // -------------------------------------------------------

    private static void printCentered(String text) {
        int padding = (WIDTH - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }

    private static void printTitleBorder(String title) {
        int total = WIDTH - title.length() - 2;
        int left = total / 2;
        int right = total - left;
        System.out.println("=".repeat(left) + " " + title + " " + "=".repeat(right));
    }

    private static void printFooter() {
        int leftWidth = (int) (WIDTH * 0.60);
        int rightWidth = WIDTH - leftWidth;

        String leftLabel = ">>>";
        String instructions = "| W/S Key: Navigate | Enter: Select | Q: Quit";

        // Compute empty space after Input:
        int leftRemaining = leftWidth - leftLabel.length();
        if (leftRemaining < 0) leftRemaining = 0;

        // Compute empty space for right side
        int rightRemaining = rightWidth - instructions.length();
        if (rightRemaining < 0) rightRemaining = 0;

        String leftPad = " ".repeat(leftRemaining);
        String rightPad = " ".repeat(rightRemaining);

        System.out.println("+" + "-".repeat(WIDTH) + "+");
        System.out.print("|" + leftLabel + leftPad + instructions + rightPad + "|");
        System.out.println();
        System.out.println("+" + "-".repeat(WIDTH) + "+");
    }


    // -------------------------------------------------------
    // ------------------- REUSED METHODS --------------------
    // -------------------------------------------------------

    public static void waitForEnter() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

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
        // Print ASCII art
        String[] artLines = ASCII_ART.split("\n");
        for (String line : artLines) {
            int padding = (WIDTH - line.length()) / 2;
            System.out.println(" ".repeat(Math.max(0, padding)) + line);
        }   

        for (String line : lines) {
            int padding = (width - line.length()) / 2;
            System.out.println(" ".repeat(Math.max(0, padding)) + line);
        }
         
    }
    public static void printBorder(){
        System.out.println("=======================================================================================================================");
    }                         
    
    
}


