package com.example.posapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartViewModalDialog extends DialogFragment {

    //
    EditText quantity;
    SQLiteDatabase db;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<CartModel> itemList;
    CartAdapter adapter;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.cart_view_modal, null);
        builder.setView(view);

        //Recyler Functions Pls Check
        initData();
        initRecyclerView();

        return builder.create();
    }

    private void initData() {
        itemList = new ArrayList<>();
        itemList.add(new CartModel(R.drawable.beef_icon, "Beef Siomai", 20.00, 50));
        itemList.add(new CartModel(R.drawable.crab_icon, "Crab Siomai", 25.00, 50));
    }

    private void initRecyclerView() {
        recyclerView = getActivity().findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        adapter = new CartAdapter(itemList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }



}
