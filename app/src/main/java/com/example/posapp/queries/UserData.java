package com.example.posapp.queries;

import android.content.ContentValues;

public class UserData {
    final static String tableName = "UserData";
    final static String ID = "UserID";
    final static String Name = "Name";
    final static String Password = "Password";

//    Table creation query
    public static String createTable(){
        return String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)", tableName, ID, Name, Password );
    }

    public static String getTableName(){
        return tableName;
    }

//    Returns Content Values of the user to be used with appending data to database
    public static ContentValues registerUser(String name, String password){
         ContentValues cv = new ContentValues();
         cv.put(Password, password);
         cv.put(Name, name);
        return cv;
    }

//    Returns the Query for finding user passwords with cursor
    public static String findUserAndReturnPass(String givenName){
        return String.format("SELECT %s FROM %s WHERE %s='%s';", Password, tableName, Name, givenName);
    }
}