package com.example.posapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.posapp.Adapter.CartAdapter;
import com.example.posapp.Interface.RecyclerViewInterface;
import com.example.posapp.Model.Cart;
import com.example.posapp.Model.Siomai_Inventory;
import com.example.posapp.ViewModel.CartViewModel;

import java.util.List;

public class CartActivity extends AppCompatActivity implements RecyclerViewInterface {

    private CartViewModel cartViewModel;
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView recyclerView = findViewById(R.id.activity_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);

        adapter = new CartAdapter(this, this);;
        recyclerView.setAdapter(adapter);

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getAllCart().observe(this, new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> items) {
                System.out.println("WHATS GOOD???");
                System.out.println(items);
                adapter.setItems(items);
            }
        });
    }

    @Override
    public void onBtnClick(int position) {
        System.out.println("HAYO");
    }

    @Override
    public void onAddToCart(Cart cart) {

    }


}