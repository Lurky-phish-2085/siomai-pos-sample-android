package com.example.posapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener, ProductViewModalDialog.ProductViewModalDialogListener {

    private ProductViewModel viewModel;
    public ImageButton historyBtn;
    public ImageButton dialogBtn;

    public ImageButton cartBtn;

    public ImageButton dialogBtn2;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        historyBtn = findViewById(R.id.history_btn);
        historyBtn.setOnClickListener(this);
        dialogBtn = findViewById(R.id.TestClick);
        dialogBtn.setOnClickListener(this);
        cartBtn = findViewById(R.id.cart_btn);
        cartBtn.setOnClickListener(this);
        db = openOrCreateDatabase("TransactionDB", 0, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Transactions(TransactionID INTEGER PRIMARY KEY AUTOINCREMENT, Product Text, Cost Double)");
    }

    @Override
    public void onClick(View v) {
        if(v == historyBtn){
            showHistory();
        }

        if(v == cartBtn){
            CartViewModalDialog dialog = new CartViewModalDialog();
            dialog.show(getSupportFragmentManager(), "hahaha");
        }

        if(v == dialogBtn){
            ProductViewModalDialog dialog = new ProductViewModalDialog();
            dialog.show(getSupportFragmentManager(), "hahahah");
        }

    }

    //Buys Siomai
    @Override
    public void applyData(String quantity) {
        double Price = Integer.valueOf(quantity) * 25;
        ContentValues cv = new ContentValues();
        cv.put("Product", "Siomai");
        cv.put("Cost", Price);

        db.insert("Transactions", null, cv);
        Toast.makeText(getBaseContext(), quantity, Toast.LENGTH_LONG).show();
    }

    public void showHistory(){
        Cursor c = db.rawQuery("SELECT * FROM Transactions",null);
        StringBuilder builder = new StringBuilder();
        while(c.moveToNext()){
            builder.append("Transaction ID: " + c.getString(0) +"\n");
            builder.append("Product Name: " + c.getString(1) +"\n");
            builder.append("Transaction Cost: " + c.getString(2) +"\n\n");
        }
        DisplayMessage("Purchase History", builder.toString());
    }

    //Display for debug/purchase history
    public void DisplayMessage(String Title, String Message){
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setCancelable(true);
        build.setTitle(Title);
        build.setMessage(Message);
        build.show();
    }
}