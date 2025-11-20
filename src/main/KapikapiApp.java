package main;

import menus.MainMenu;

public class KapikapiApp {
    public static void main(String[] args) {
        System.out.println("=== Welcome to Kapikapi Café ===");
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }
}
