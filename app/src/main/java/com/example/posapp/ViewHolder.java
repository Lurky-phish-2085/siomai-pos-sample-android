package com.example.posapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView itemImageView;
    private TextView itemFlavorView;
    private TextView itemPriceView;
    private TextView itemTotalQuantityView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        itemImageView = itemView.findViewById(R.id.cart_image_view_1);
        itemFlavorView = itemView.findViewById(R.id.cart_flavor_textview);
        itemPriceView = itemView.findViewById(R.id.cart_price_textview);
        itemTotalQuantityView = itemView.findViewById(R.id.cart_total_qty_textview);
    }
}
