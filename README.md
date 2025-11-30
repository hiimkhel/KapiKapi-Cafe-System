# Cafe Management Console App

Welcome to the **Cafe Management Console App** – a fully console-based application for managing a cafe's customers, employees, menu, orders, and analytics. This project is built in **Java** and demonstrates object-oriented programming, file persistence, and menu-driven user interfaces.

---

## Table of Contents

- [Features](#features)  
- [Installation](#installation)  
- [Usage](#usage)  
- [Project Structure](#project-structure)  
- [Classes & Modules](#classes--modules)  
- [Data Persistence](#data-persistence)  
- [Menu Navigation](#menu-navigation)  
- [Contributing](#contributing)  
- [License](#license)

---

## Features

- **Customer Management**: Register, update, and view customer accounts and points.  
- **Employee Management**: Add, remove, and update employees.  
- **Menu Management**: Manage coffee items with stock, price, and inventory updates.  
- **Order Management**: Create, track, and mark orders as brewed.  
- **Brew Queue**: Automatic queue for pending orders.  
- **Analytics & Dashboard**: Customer analytics and order statistics.  
- **Console Navigation**: Interactive menu with keyboard navigation (W/S + Enter).  
- **Persistence**: All data saved to `.db` files using Java object serialization.  

---

## Installation

1. **Clone the repository**

```bash
git clone https://github.com/yourusername/KapiKapi-Cafe-System.git
cd KapiKapi-Cafe-Syste                      


---

2. Compile Java files:

javac -d bin src/**/*.java

Run the main application

java -cp bin Main


Make sure you have Java 17+ installed.

Usage

On startup, you will see the ASCII art and main menu.

Navigate using the keyboard:

W = move up

S = move down

Enter = select option

Q = quit

Main Features:

Customer Login → Access order menu, check orders, view profile, and earn points.

Admin Login → Manage employees, menu items, and view analytics dashboard.

Project Structure
src/
├── database/
│   └── Database.java
├── models/
│   ├── Customer.java
│   ├── Employee.java
│   ├── Coffee.java
│   └── Order.java
├── utils/
│   ├── MenuNavigator.java
│   └── ConsoleUtils.java
├── menus/
│   ├── BaseMenu.java
│   ├── MainMenu.java
│   ├── AuthMenu.java
│   ├── CustomerMenu.java
│   ├── AdminMenu.java
│   └── ... other submenus ...
└── Main.java

Classes & Modules

Database: Handles all CRUD operations and file persistence.

Models: Customer, Employee, Coffee, Order – store object data.

Utils: MenuNavigator & ConsoleUtils – console UI and helper functions.

Menus: BaseMenu abstraction with MainMenu, CustomerMenu, AdminMenu, and submenus.

Data Persistence

Data is serialized and stored in .db files:

customers.db → Stores customer accounts.

employees.db → Stores employees.

menu.db → Stores menu items and stock.

orders.db → Stores order history and brew status.

On startup, the app automatically loads existing data from these files.

On changes, the app saves updates automatically.

Menu Navigation

Main Menu → Customer or Admin options.

Customer Menu → Order Menu, Check Orders, Profile.

Admin Menu → Dashboard, Menu Inventory, Employee Management.

Submenus provide interactive actions with keyboard-based navigation.
