package com.example.posapp.queries;

import android.content.ContentValues;

import java.util.HashMap;

public abstract class GeneralQueries {

    public final static String DataBaseName = "POSDBRevised";

//    Add 2 Items ContentValues
    public static ContentValues addItem(String ColumnName, String ColumnValue, String Column2Name, String Column2Value){
        ContentValues cv = new ContentValues();
        cv.put(ColumnName, ColumnValue);
        cv.put(Column2Name, Column2Value);
        return cv;
    }
//    Add Multiple Items using HashMap
    public static ContentValues addItem(HashMap<String, String> ColNameAndVal){
        ContentValues cv = new ContentValues();
            ColNameAndVal.forEach(cv::put);
        return cv;
    }
//    Read/Search
    public static String findItemAndReturnItem(String TableName,String SearchParameterColumn, String ColumnToFind, String ValueOfParameter){
        return String.format("SELECT %s FROM %s WHERE %s='%s';", ColumnToFind, TableName, SearchParameterColumn, ValueOfParameter);
    }
//    Delete
    public static String deleteItem(String TableName, String SearchParameterColumn, String ValueOfParameter){
        return String.format("DELETE FROM %s WHERE %s='%s';", TableName, SearchParameterColumn, ValueOfParameter );
    }
//    Update Item
    public static String updateItem(String TableName, String SearchParameterColumn, String ValueOfParameter, String ColumnToUpdate, String UpdatedColumnValue){
        return String.format("UPDATE %s SET %s='%s' WHERE %s='%s';", TableName, ColumnToUpdate, UpdatedColumnValue, SearchParameterColumn, ValueOfParameter);
    }

}
