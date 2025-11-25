package models;

import java.io.Serializable;

public class Coffee implements Serializable {
    private String name;
    private int price;
    private int stock; // number of cups available

    public Coffee(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getStock() { return stock; }

    public void setName(String name) { this.name = name; }
    public void setPrice(int price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return name + " - ₱" + price + " (" + stock + " left)";
    }
}
