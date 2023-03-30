package com.example.posapp;
public class CartModel {
    private int imageItemView;
    private String flavorTextView;
    private double priceTextView;
    private int quantityTextView;
    private int totalQuantityView;

    public CartModel(int image, String name, double price, int quantity, int totalQuantity) {
        this.imageItemView = image;
        this.flavorTextView = name;
        this.priceTextView = price;
        this.quantityTextView = quantity;
        this.totalQuantityView = totalQuantity;
    }

    public int getImageItemView() {
        return imageItemView;
    }

    public String getFlavorTextView() {
        return flavorTextView;
    }

    public double getPriceTextView() {
        return priceTextView;
    }

    public int getTotalQuantityTextView() {
        return totalQuantityView;
    }

    public int getQuantityTextView() {
        return quantityTextView;
    }

    public void addQuantity(){
        this.quantityTextView++;
    }

    public void subQuantity(){
        this.quantityTextView--;
    }
}