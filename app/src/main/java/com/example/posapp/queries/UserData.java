package com.example.posapp.queries;

import android.content.ContentValues;

public class UserData {
    final static String tableName = "UserData";
    final static String PrimaryKey = "UserID";
    final static String Name = "Name";
    final static String Password = "Password";

//    Table creation query
    public static String createTable(){
        return String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)", tableName, PrimaryKey, Name, Password );
    }

    public static String getTableName(){
        return tableName;
    }

//    Returns Content Values of the user to be used with appending data to database
    public static ContentValues registerUser(String name, String password){
        return GeneralQueries.addItem(Password, password, Name, name);
    }

//    Returns the Query for finding user passwords with cursor
    public static String findUserAndReturnPass(String givenName){
        return GeneralQueries.findItemAndReturnItem(tableName, Name, Password, givenName);
    }
}