package com.example.posapp;

import static com.example.posapp.queries.UserData.createTable;
import static com.example.posapp.queries.UserData.findUserAndReturnPass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.posapp.debugDisplayFunctions.Displays;
import com.example.posapp.queries.UserData;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText name, password;
    Button register, login;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        login = findViewById(R.id.login_btn);
        login.setOnClickListener(this);
        password = findViewById(R.id.edit_password);
        name = findViewById(R.id.edit_username);
        register = findViewById(R.id.register_btn);
        register.setOnClickListener(this);

        db = openOrCreateDatabase("PosDB", Context.MODE_PRIVATE, null);
//        db.execSQL(UserData.createTable());
        db.execSQL(createTable());
    }

    @Override
    public void onClick(View view) {
        String userNameText = name.getText().toString();
        if(login.equals(view)){
            String passwordText = password.getText().toString();
            loginCheck(passwordText, userNameText);
        }
        if(register.equals(view)){
            registerUser(userNameText);
        }
    }

    private void registerUser(String userName) {
        Cursor c = db.rawQuery(findUserAndReturnPass(userName),null);
        if(c.getCount() > 0){
            Displays.displayMessageBuilder("Error", "This user already exists", this);
            return;
        }
        ContentValues cv = UserData.registerUser(userName, password.getText().toString());
        db.insert(UserData.getTableName(), null, cv);
        Displays.displayMessageBuilder("Success!", "user registered!", this);
        c.close();
    }

    private void loginCheck(String Password, String userName) {
        Cursor c = db.rawQuery(findUserAndReturnPass(userName), null);
        //If no users have been found in our query
        if(c.getCount() == 0){
            Displays.displayMessageBuilder("Error", "No Users with this name have been found.", this);
            return;
        }
        //Goes to the first instance where the name in the column is same with the user
        //We can modify it to check for every user with the same name by using While(c.moveToNext) instead of if(moveToFirst)
        if(c.moveToFirst()){
            //Compares the password from the cursor with the password in the editText
            if(Password.equals(c.getString(0))){
                //Switches to dashboard if login successful
                switchToDashBoard();
            }
            else{
                //error message occurs otherwise
                Displays.displayMessageBuilder("Error", "Incorrect Password", this);
            }
        }
        c.close();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void switchToDashBoard() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        startActivity(intent);
        db.close();
        finish();
    }
}