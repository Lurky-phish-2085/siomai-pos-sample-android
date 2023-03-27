package com.example.posapp.debugDisplayFunctions;

import android.app.AlertDialog;
import android.content.Context;

public class Displays {
    public static void displayMessageBuilder(String Title, String Message, Context context){
        AlertDialog.Builder build = new AlertDialog.Builder(context);
        build.setCancelable(true);
        build.setTitle(Title);
        build.setMessage(Message);
        build.show();
    }
}
