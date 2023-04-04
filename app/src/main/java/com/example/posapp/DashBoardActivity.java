package com.example.posapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.posapp.Adapter.CartAdapter;
import com.example.posapp.Adapter.ProductAdapter;
import com.example.posapp.Interface.RecyclerViewInterface;
import com.example.posapp.Model.Cart;
import com.example.posapp.Model.Siomai_Inventory;
import com.example.posapp.ViewModel.CartViewModel;
import com.example.posapp.ViewModel.SiomaiViewModel;

import java.util.List;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener, ProductViewModalDialog.ProductViewModalDialogListener, RecyclerViewInterface {

    public ImageButton historyBtn;
    public ImageButton dialogBtn;



    List<Siomai_Inventory> siomai_inventory;

    CartViewModel cartViewModel;
    SiomaiViewModel siomaiViewModel;
    ProductAdapter adapter;

    public ImageButton cartBtn;

    public ImageButton dialogBtn2;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        historyBtn = findViewById(R.id.history_btn);
        historyBtn.setOnClickListener(this);
        cartBtn = findViewById(R.id.cart_btn);
        cartBtn.setOnClickListener(this);
        db = openOrCreateDatabase("TransactionDB", 0, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Transactions(TransactionID INTEGER PRIMARY KEY AUTOINCREMENT, Product Text, Cost Double)");


        RecyclerView recyclerView = findViewById(R.id.item_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new ProductAdapter(DashBoardActivity.this ,DashBoardActivity.this);;
        recyclerView.setAdapter(adapter);


        siomaiViewModel = new ViewModelProvider(this).get(SiomaiViewModel.class);
        siomaiViewModel.getAllSiomai().observe(this, new Observer<List<Siomai_Inventory>>() {
            @Override
            public void onChanged(List<Siomai_Inventory> items) {
                siomai_inventory = items;
                adapter.setItems(items);
            }
        });


    }

    @Override
    public void onClick(View v) {
        if(v == historyBtn){
            showHistory();
        }

        if(v == cartBtn){
            Intent intent = new Intent(DashBoardActivity.this,  CartActivity.class);
            startActivity(intent);
//            CartViewModalDialog dialog = new CartViewModalDialog();
//            dialog.show(getSupportFragmentManager(), "hahaha");
        }

//        if(v == dialogBtn){
//            ProductViewModalDialog dialog = new ProductViewModalDialog(R.drawable.beef_icon, "Beef Flavor", 20.00, 30, this );
//            dialog.show(getSupportFragmentManager(), "hahahah");
//        }

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

    @Override
    public void onBtnClick(int position) {
        Siomai_Inventory state = siomai_inventory.get(position);
        ProductViewModalDialog dialog = new ProductViewModalDialog(state.getImg(), state.getFlavor(), state.getPrice(),state.getQuantity(), this);
        dialog.show(getSupportFragmentManager(), "hahahah");
    }


    public void onAddToCart(Cart cart) {
        System.out.println("ADDING FUNCTION!!");
        cartViewModel.insert(cart);
    }
}