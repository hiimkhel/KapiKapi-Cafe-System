package database;

import models.*;
import java.io.*;
import java.util.*;

public class Database {
    private static int orderIdCounter = 1;

    // ====== CUSTOMER DATABASE ======
    private static final String EMPLOYEE_FILE = "employees.db";
    private static Map<String, Employee> employees = new HashMap<>();
    private static final String CUSTOMER_FILE = "customers.db";
    private static final String ORDER_FILE = "orders.db";
    private static Map<String, Customer> customers = new HashMap<>();

    // ====== EMPLOYEE DATABASE ======
    // private static final List<Employee> employees = new ArrayList<>();

    // ====== MENU / INVENTORY DATABASE ======
    private static final List<Coffee> menuItems = new ArrayList<>();

    // ====== ORDERS DATABASE ======
    private static final List<Order> orders = new ArrayList<>();

    // ====== ORDER QUEUE FOR BREWING ======
    private static final Queue<Order> brewQueue = new LinkedList<>();

    // ====== STATIC INITIALIZER ======
    static {
        loadCustomers(); // load persisted customers
        loadOrders();
        loadEmployees();
        // Sample menu items
        menuItems.add(new Coffee("Americano", 80, 10));
        menuItems.add(new Coffee("Latte", 120, 8));
        menuItems.add(new Coffee("Cappuccino", 110, 7));
        menuItems.add(new Coffee("Mocha", 130, 5));

        // Sample employee
        // employees.add(new Employee("admin", "admin123", "Admin"));
    }

    // ==========================
    // CUSTOMER METHODS
    // ==========================
    @SuppressWarnings("unchecked")
    private static void loadCustomers() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(CUSTOMER_FILE))) {
            customers = (Map<String, Customer>) in.readObject();
        } catch (Exception e) {
            customers = new HashMap<>();
        }
    }

    private static void saveCustomers() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(CUSTOMER_FILE))) {
            out.writeObject(customers);
        } catch (Exception e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

        // Save
    public static void saveOrders() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ORDER_FILE))) {
            out.writeObject(orders);
        } catch (Exception e) {
            System.out.println("Error saving orders: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadOrders() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ORDER_FILE))) {
            List<Order> loaded = (List<Order>) in.readObject();
            orders.addAll(loaded);

            // Update orderIdCounter to avoid duplicate IDs
            int maxId = loaded.stream().mapToInt(Order::getId).max().orElse(0);
            orderIdCounter = maxId + 1;

            // Rebuild brewQueue for unbrewed orders
            for (Order o : loaded) {
                if (!o.isBrewed()) brewQueue.add(o);
            }

        } catch (FileNotFoundException e) {
            // No orders yet, create empty file
            saveOrders();
        } catch (Exception e) {
            System.out.println("Error loading orders: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadEmployees() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(EMPLOYEE_FILE))) {
            employees = (Map<String, Employee>) in.readObject();
        } catch (Exception e) {
            employees = new HashMap<>();
        }
    }

    private static void saveEmployees() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(EMPLOYEE_FILE))) {
            out.writeObject(employees);
        } catch (Exception e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }

    public static void registerCustomer(Customer c) {
        customers.put(c.getUsername(), c);
        saveCustomers();
    }

    public static boolean exists(String username) {
        return customers.containsKey(username);
    }

    public static Customer get(String username) {
        return customers.get(username);
    }

    public static List<Customer> getCustomers() {
        return new ArrayList<>(customers.values());
    }

    public static Customer findCustomerByUsername(String username) {
        return customers.get(username);
    }

    // ==========================
    // MENU METHODS
    // ==========================
    public static List<Coffee> getMenuItems() {
        return menuItems;
    }

    public static void addMenuItem(Coffee m) {
        menuItems.add(m);
    }

    public static Coffee findMenuItem(String name) {
        for (Coffee m : menuItems) {
            if (m.getName().equalsIgnoreCase(name)) return m;
        }
        return null;
    }

    public static void updateInventory(String itemName, int newStock) {
        Coffee m = findMenuItem(itemName);
        if (m != null) m.setStock(newStock);
    }

    // ==========================
    // ORDER METHODS
    // ==========================
    public static void addOrder(Order order) {
        order.setId(orderIdCounter);
        orders.add(order);
        brewQueue.add(order);
        saveOrders();
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static Queue<Order> getBrewQueue() {
        return brewQueue;
    }

    public static Order findOrderById(int id) {
        for (Order o : orders) {
            if (o.getId() == id) return o;
        }
        return null;
    }

    public static boolean markOrderAsBrewed(int id) {
        Order o = findOrderById(id);
        if (o != null) {
            o.setBrewed(true);
            return true;
        }
        return false;
    }

    // ==========================
    // EMPLOYEE METHODS
    // ==========================
    // Add Employee
    public static void addEmployee(Employee e) {
        employees.put(e.getUsername(), e);
        saveEmployees();
    }

    // Get Employee
    public static Employee findEmployeeByUsername(String username) {
        return employees.get(username);
    }

    // Remove Employee
    public static void removeEmployee(String username) {
        if (employees.containsKey(username)) {
            employees.remove(username);
            saveEmployees();
        }
    }

    // Update Employee
    public static void updateEmployee(String username, String newPassword, String newRole) {
        Employee e = employees.get(username);
        if (e != null) {
            e.setPassword(newPassword);
            e.setRole(newRole);
            saveEmployees();
        }
    }

    // Get all employees
    public static List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }
}
