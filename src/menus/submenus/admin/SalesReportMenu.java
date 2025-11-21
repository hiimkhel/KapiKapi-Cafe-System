package menus.submenus.admin;

import utils.MenuNavigator;

public class SalesReportMenu {
    public void show() {
        String[] options = {"Daily Report", "Weekly Report", "Monthly Report", "Back"};

        while (true) {
            int selected = MenuNavigator.navigate("=== Sales Report ===", options);

            switch (selected) {
                case 0 -> System.out.println("Daily report generated.");
                case 1 -> System.out.println("Weekly report generated.");
                case 2 -> System.out.println("Monthly report generated.");
                case 3 -> { return; } // Back
            }
            System.out.println("Press Enter to continue...");
            new java.util.Scanner(System.in).nextLine();
        }
    }
}
