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

public class ProductViewModalDialog extends DialogFragment {


    //Jet's Testing THE ADD AND REDUCE BUTTON
    public ImageButton dialogAddBtn;
    public ImageButton dialogSubBtn;

    //
    EditText quantity;
    SQLiteDatabase db;
    ProductViewModalDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.product_view_modal, null);

        view.findViewById(R.id.add_cart_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ToDo: Function na naglalgay sa cart ng item xD

                System.out.println("ADDED TO CART BOI");
                listener.applyData(quantity.getText().toString());
                getDialog().dismiss();
            }
        });

        quantity = view.findViewById(R.id.editTextQuantity);
        dialogAddBtn = view.findViewById(R.id.btnAdd);
        dialogSubBtn = view.findViewById(R.id.btnSub);

        dialogAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity.setText(String.valueOf(Integer.valueOf(quantity.getText().toString()) + 1));
            }
        });

        dialogSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.valueOf(quantity.getText().toString());
                if (qty <= 0) return;
                quantity.setText(String.valueOf(Integer.valueOf(quantity.getText().toString()) - 1));
            }
        });

        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (ProductViewModalDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("please implement the listener");
        }
    }

    public interface ProductViewModalDialogListener {
        void applyData(String quantity);
    }
}
