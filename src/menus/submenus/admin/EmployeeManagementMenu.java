package menus.submenus.admin;

import database.Database;
import models.Employee;
import utils.MenuNavigator;

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
            int choice = MenuNavigator.navigate("=== Employee Management ===", options);

            switch (choice) {
                case 0 -> viewEmployees();
                case 1 -> addEmployee();
                case 2 -> updateEmployee();
                case 3 -> removeEmployee();
                case 4 -> { return; }
            }
        }
    }

    private void viewEmployees() {
        System.out.println("\n=== Employee List ===");
        for (Employee e : Database.getAllEmployees()) {
            System.out.println("- " + e.getUsername() + " (" + e.getRole() + ")");
        }
    }

    private void addEmployee() {
        System.out.print("Enter username: ");
        String username = MenuNavigator.getInput();

        if (Database.findEmployeeByUsername(username) != null) {
            System.out.println("Employee already exists.");
            return;
        }

        System.out.print("Enter password: ");
        String password = MenuNavigator.getInput();

        System.out.print("Enter role (Admin/Barista): ");
        String role = MenuNavigator.getInput();

        Database.addEmployee(new Employee(username, password, role));
        System.out.println("Employee added!");
    }

    private void updateEmployee() {
        System.out.print("Enter username of employee to update: ");
        String username = MenuNavigator.getInput();

        Employee e = Database.findEmployeeByUsername(username);
        if (e == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter new password (leave blank to keep current): ");
        String newPass = MenuNavigator.getInput();
        if (newPass.isEmpty()) newPass = e.getPassword();

        System.out.print("Enter new role (Admin/Barista, leave blank to keep current): ");
        String newRole = MenuNavigator.getInput();
        if (newRole.isEmpty()) newRole = e.getRole();

        Database.updateEmployee(username, newPass, newRole);
        System.out.println("Employee updated!");
    }

    private void removeEmployee() {
        System.out.print("Enter username of employee to remove: ");
        String username = MenuNavigator.getInput();

        Employee e = Database.findEmployeeByUsername(username);
        if (e == null) {
            System.out.println("Employee not found.");
            return;
        }

        Database.removeEmployee(username);
        System.out.println("Employee removed!");
    }
}
