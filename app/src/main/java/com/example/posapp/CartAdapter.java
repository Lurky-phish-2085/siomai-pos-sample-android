package com.example.posapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<CartModel> cartList;

    public CartAdapter(List<CartModel> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        int cartImage = cartList.get(position).getImageItemView();
        String flavor = cartList.get(position).getFlavorTextView();
        double price = cartList.get(position).getPriceTextView();
        int totalQuantity = cartList.get(position).getTotalQuantityTextView();

        holder.setData(cartImage, flavor, price, totalQuantity);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        private ImageView cartImageView;
        private TextView cartFlavorView;
        private TextView cartPriceView;
        private TextView cartTotalQuantityView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cartImageView = itemView.findViewById(R.id.cart_image_view_1);
            cartFlavorView = itemView.findViewById(R.id.cart_flavor_textview);
            cartPriceView = itemView.findViewById(R.id.cart_price_textview);
            cartTotalQuantityView = itemView.findViewById(R.id.cart_total_qty_textview);
        }

        public void setData(int itemImage, String flavor, double price, int totalQuantity) {
            cartImageView.setImageResource(itemImage);
            cartFlavorView.setText(flavor);
            cartPriceView.setText(Double.toString(price));
            cartTotalQuantityView.setText(Integer.toString(totalQuantity));
        }
    }
}
