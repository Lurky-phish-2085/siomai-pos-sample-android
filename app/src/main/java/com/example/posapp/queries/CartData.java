package com.example.posapp.queries;

import android.content.ContentValues;

import java.util.HashMap;

public class CartData extends GeneralQueries{
    final static String tableName = "CartData";
    final static String PrimaryKey = "InputID";
    final static String Name = "ProductName";
    final static String Quantity = "ItemQuantity";
    final static String Price = "Price";

    public static String getTableName(){
        return tableName;
    }

    public static ContentValues addToCart(String itemName, String itemQuantity, String ItemPrice){
        HashMap<String, String> ReturnVal = new HashMap<>();
        ReturnVal.put(Name, itemName);
        ReturnVal.put(Quantity, itemQuantity);
        ReturnVal.put(Price, ItemPrice);
        return GeneralQueries.addItem(ReturnVal);
    }

    public static String removeFromCart(String itemName){
        return GeneralQueries.deleteItem(tableName, Name, itemName);
    }

    public static String updateQuantity(String itemName, String itemQuantity){
        return GeneralQueries.updateItem(tableName, Name, itemName, Quantity, itemQuantity);
    }

    public String createTable() {
        return String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INT, %s DOUBLE)", tableName, PrimaryKey, Name, Quantity, Price);
    }
}
