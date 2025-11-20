package utils;

import java.util.Scanner;

public class InputValidator {

    public static int getInt(Scanner scanner) {
        int value = -1;
        while (true) {
            try {
                value = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        return value;
    }
}
