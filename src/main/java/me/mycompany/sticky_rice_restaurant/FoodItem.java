/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.mycompany.sticky_rice_restaurant;

/**
 *
 * @author ACER
 */
public class FoodItem {
     private String name;
    private String id;
    private double price; // Thêm trường giá tiền

    public FoodItem(String name, String id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - Giá: " + price;
    }
}
