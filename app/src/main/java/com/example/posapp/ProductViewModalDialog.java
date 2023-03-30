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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ProductViewModalDialog extends DialogFragment {


    // Getting the id from the product view modal
    private ImageView imageView;
    private TextView flavorView;
    private TextView priceView;
    private TextView totalQuantityView;

    //Jet is making the dialog dynamic by passing the data according to what user clicked
    //These are the data that needs to be passed so im making a constructor
    public int image;
    public String flavor;
    public double price;
    public int totalQuantity;

    //Jet's Testing THE ADD AND REDUCE BUTTON
    public ImageButton dialogAddBtn;
    public ImageButton dialogSubBtn;
    //
    EditText quantity;
    SQLiteDatabase db;
    ProductViewModalDialogListener listener;

    public ProductViewModalDialog(int image, String flavor, double price, int totalQuantity){
        super();
        this.image = image;
        this.flavor = flavor;
        this.price = price;
        this.totalQuantity = totalQuantity;

    }



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


        //This is Jet getting the id from the view to make it dynamic
        imageView = view.findViewById(R.id.imageView);
        flavorView = view.findViewById(R.id.flavorView);
        priceView = view.findViewById(R.id.priceView);
        totalQuantityView = view.findViewById(R.id.totalQuantityView);

        imageView.setImageResource(image);
        flavorView.setText(flavor);
        priceView.setText(Double.toString(price));
        totalQuantityView.setText(Integer.toString(totalQuantity));
        //end


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
