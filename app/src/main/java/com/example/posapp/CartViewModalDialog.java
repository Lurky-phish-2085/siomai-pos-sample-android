package com.example.posapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.posapp.Adapter.CartAdapter;
import com.example.posapp.Interface.RecyclerViewInterface;
import com.example.posapp.Model.Cart;
import com.example.posapp.Model.Siomai_Inventory;

import java.util.ArrayList;
import java.util.List;

public class CartViewModalDialog extends DialogFragment implements RecyclerViewInterface {
    EditText quantity;
    SQLiteDatabase db;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Cart> cartList;
    CartAdapter adapter;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.cart_view_modal, null);
        builder.setView(view);

        initData();
        initRecyclerView(view);

        return builder.create();
    }

    private void initData() {
        cartList = new ArrayList<>();
        cartList.add(new Cart("Beef Siomai", R.drawable.beef_icon, 20.00, 1));

    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        //added context in the constructor don't know why? pls know - jet
        adapter = new CartAdapter(view.getContext(), this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBtnClick(int position) {
//        if(function == "addQty"){
//            System.out.println("Add more Qty");
//            cartList.get(position).addQuantity();
//        }
//
//        if(function == "subQty"){
//            System.out.println("Sub more Qty");
//            cartList.get(position).subQuantity();
//        }
//
//        if(function == "removeCart"){
//            System.out.println("Remove Cart");
//            cartList.remove(position);
//        }
//        adapter.notifyDataSetChanged();

    }

    @Override
    public void onAddToCart(Cart cart) {

    }
}
