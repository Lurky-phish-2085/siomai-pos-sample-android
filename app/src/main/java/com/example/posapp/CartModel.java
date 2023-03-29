package com.example.posapp;
public class CartModel {
    private int imageItemView;
    private String flavorTextView;
    private double priceTextView;
    private int totalQuantityView;

    public CartModel(int image, String name, double price, int totalQuantity) {
        this.imageItemView = image;
        this.flavorTextView = name;
        this.priceTextView = price;
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

}