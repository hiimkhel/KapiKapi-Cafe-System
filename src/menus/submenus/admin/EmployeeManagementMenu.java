package menus.submenus.admin;

import database.Database;
import models.Employee;
import utils.ConsoleUtils;
import utils.MenuNavigator;

import java.util.List;

public class EmployeeManagementMenu {

    private final String[] options = {
            "View Employees",
            "Add Employee",
            "Update Employee",
            "Remove Employee",
            "Back"
    };

    public void show() {
        while (true) {
            MenuNavigator.clearScreen();
            MenuNavigator.printHeaderCentered();
            ConsoleUtils.printCentered("============================================================");
            ConsoleUtils.printCentered("                    [EMPLOYEE MANAGEMENT]                  ");
            ConsoleUtils.printCentered("============================================================");

            int choice = MenuNavigator.navigate("Employee Management", options);

            switch (choice) {
                case 0 -> { viewEmployees(); MenuNavigator.waitForEnter(); }
                case 1 -> { addEmployee(); MenuNavigator.waitForEnter(); }
                case 2 -> { updateEmployee(); MenuNavigator.waitForEnter(); }
                case 3 -> { removeEmployee(); MenuNavigator.waitForEnter(); }
                case 4 -> { return; } // Back to previous menu
            }
        }
    }

    private void viewEmployees() {
        List<Employee> employees = Database.getAllEmployees();

        ConsoleUtils.clearScreen();
        MenuNavigator.printHeaderCentered();
        ConsoleUtils.printCentered("============================================================");
        ConsoleUtils.printCentered("                       [EMPLOYEE LIST]                      ");
        ConsoleUtils.printCentered("============================================================");

        if (employees.isEmpty()) {
            ConsoleUtils.printCentered("No employees found.");
            ConsoleUtils.printCentered("\nPress Enter to return...");
            return;
        }

        ConsoleUtils.printCentered(String.format("%-5s %-20s %-20s", "[No.]", "[Username]", "[Role]"));
        ConsoleUtils.printCentered("------------------------------------------------------------");

        int count = 1;
        for (Employee e : employees) {
            ConsoleUtils.printCentered(String.format("%-5s %-20s %-20s", "[" + count++ + "]", e.getUsername(), e.getRole()));
        }

        ConsoleUtils.printCentered("------------------------------------------------------------");
        ConsoleUtils.printCentered("Press Enter to return...");
    }

    private void addEmployee() {
        ConsoleUtils.clearScreen();
        MenuNavigator.printHeaderCentered();
        ConsoleUtils.printCentered("============================================================");
        ConsoleUtils.printCentered("                        [ADD EMPLOYEE]                      ");
        ConsoleUtils.printCentered("============================================================\n");

        System.out.print("\t\t\t\tEnter username: ");
        String username = MenuNavigator.getInput();

        if (Database.findEmployeeByUsername(username) != null) {
            ConsoleUtils.printCentered("Employee already exists.");
            return;
        }

        System.out.print("\t\t\t\tEnter password: ");
        String password = MenuNavigator.getInput();

        System.out.print("\t\t\t\tEnter role (Admin/Barista): ");
        String role = MenuNavigator.getInput();

        Database.addEmployee(new Employee(username, password, role));

        ConsoleUtils.printCentered("\n\t\t\t\t[" + username + " added successfully!]");
    }

    private void updateEmployee() {
         ConsoleUtils.clearScreen();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();
        ConsoleUtils.printCentered("                       [UPDATE EMPLOYEE]                     ");
        ConsoleUtils.printCentered("============================================================\n");

        // Get employees
        List<Employee> employees = Database.getAllEmployees();
        if (employees.isEmpty()) {
            ConsoleUtils.printCentered("No employees found.");
            ConsoleUtils.printCentered("Press Enter to return...");
            MenuNavigator.waitForEnter();
            return;
        }

        // Build options from employee usernames
         // Build options with Back option
        String[] empOptions = new String[employees.size() + 1];
        for (int i = 0; i < employees.size(); i++) empOptions[i] = employees.get(i).getUsername();
        empOptions[employees.size()] = "[ Back ]";
        int choice = MenuNavigator.navigate("Select an employee to update:", empOptions);
        if (choice == employees.size()) return; // Back selected

        Employee e = employees.get(choice);

        

        System.out.print("\t\t\t\tEnter new password (leave blank to keep current): ");
        String newPass = MenuNavigator.getInput();
        if (newPass.isEmpty()) newPass = e.getPassword();

        System.out.print("\t\t\t\tEnter new role (Admin/Barista, leave blank to keep current): ");
        String newRole = MenuNavigator.getInput();
        if (newRole.isEmpty()) newRole = e.getRole();

        Database.updateEmployee(e.getUsername(), newPass, newRole);

        ConsoleUtils.printCentered("\n\t\t\t\t[Employee updated successfully!]");
    }

    private void removeEmployee() {
        ConsoleUtils.clearScreen();
        MenuNavigator.printHeaderCentered();
        MenuNavigator.printBorder();
        ConsoleUtils.printCentered("                       [REMOVE EMPLOYEE]                     ");
        ConsoleUtils.printCentered("============================================================\n");

        List<Employee> employees = Database.getAllEmployees();
        if (employees.isEmpty()) {
            ConsoleUtils.printCentered("No employees found.");
            ConsoleUtils.printCentered("Press Enter to return...");
            MenuNavigator.waitForEnter();
            return;
        }

        // Build options with Back option
        String[] empOptions = new String[employees.size() + 1];
        for (int i = 0; i < employees.size(); i++) empOptions[i] = employees.get(i).getUsername();
        empOptions[employees.size()] = "[ Back ]";

        int choice = MenuNavigator.navigate("Select an employee to remove:", empOptions);

        if (choice == employees.size()) return; // Back selected

        Employee e = employees.get(choice);
        Database.removeEmployee(e.getUsername());

        ConsoleUtils.printCentered("\n[Employee removed successfully!]");
    }
}
