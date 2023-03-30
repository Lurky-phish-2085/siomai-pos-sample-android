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

public class CartViewModalDialog extends DialogFragment implements RecyclerViewInterface {
    EditText quantity;
    SQLiteDatabase db;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<CartModel> cartList;
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
        cartList.add(new CartModel(R.drawable.beef_icon, "Beef Siomai", 20.00, 1, 13));
        cartList.add(new CartModel(R.drawable.crab_icon, "Crab Siomai", 25.00, 1, 43));
        cartList.add(new CartModel(R.drawable.pork_icon, "Pork Siomai", 15.00, 2, 53));

        cartList.add(new CartModel(R.drawable.beef_icon, "Beef Siomai", 20.00, 1, 13));
        cartList.add(new CartModel(R.drawable.crab_icon, "Crab Siomai", 25.00, 1, 43));
        cartList.add(new CartModel(R.drawable.pork_icon, "Pork Siomai", 15.00, 2, 53));

        cartList.add(new CartModel(R.drawable.beef_icon, "Beef Siomai", 20.00, 1, 13));
        cartList.add(new CartModel(R.drawable.crab_icon, "Crab Siomai", 25.00, 1, 43));
        cartList.add(new CartModel(R.drawable.pork_icon, "Pork Siomai", 15.00, 2, 53));
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        //added context in the constructor don't know why? pls know - jet
        adapter = new CartAdapter(view.getContext(), cartList, this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBtnClick(int position, String function) {
        if(function == "addQty"){
            System.out.println("Add more Qty");
            cartList.get(position).addQuantity();
        }

        if(function == "subQty"){
            System.out.println("Sub more Qty");
            cartList.get(position).subQuantity();
        }

        if(function == "removeCart"){
            System.out.println("Remove Cart");
            cartList.remove(position);
        }
        adapter.notifyDataSetChanged();

    }
}
