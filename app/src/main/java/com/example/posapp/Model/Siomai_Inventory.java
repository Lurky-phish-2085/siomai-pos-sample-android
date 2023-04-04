package com.example.posapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "siomai_inventory_table")
public class Siomai_Inventory {

    //Auto generates primary key
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String flavor;

    private double price;

    private int quantity;

    private int img;

    public Siomai_Inventory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Siomai_Inventory(String flavor, double price, int quantity, int img) {
        this.flavor = flavor;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
    }
}
