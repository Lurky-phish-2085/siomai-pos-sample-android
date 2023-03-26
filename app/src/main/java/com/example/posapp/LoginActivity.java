package com.example.posapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button loginBtn;
    EditText pass, userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
        pass = findViewById(R.id.edit_password);
        userName = findViewById(R.id.edit_username);

    }

    @Override
    public void onClick(View view) {
        if(loginBtn.equals(view)){
            if(true){
                if(pass.getText().toString().equals("Admin") && userName.getText().toString().equals("Admin")){
                    Intent intent = new Intent(this, DashBoardActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
            else{
                DisplayMessage("Error", "Incorrect Username or Password");
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void DisplayMessage(String Title, String Message){
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setCancelable(true);
        build.setTitle(Title);
        build.setMessage(Message);
        build.show();
    }
}