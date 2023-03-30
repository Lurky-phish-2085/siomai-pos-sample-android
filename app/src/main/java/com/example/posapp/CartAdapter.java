package com.example.posapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    //I dont know what this is for? What is final btw?
    private final RecyclerViewInterface recyclerViewInterface;

    //I need for some reason pls find the reason -jet
    Context context;
    private List<CartModel> cartList;

    public CartAdapter(Context context, List<CartModel> cartList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.cartList = cartList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        int cartImage = cartList.get(position).getImageItemView();
        String flavor = cartList.get(position).getFlavorTextView();
        double price = cartList.get(position).getPriceTextView();
        int quantity = cartList.get(position).getQuantityTextView();
        int totalQuantity = cartList.get(position).getTotalQuantityTextView();

        holder.setData(cartImage, flavor, price,quantity , totalQuantity);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {

        private ImageView cartImageView;
        private TextView cartFlavorView, cartPriceView, cartTotalQuantityView;

        private EditText cartQuantity;
        //Add and Sub Button
        public ImageButton cartAddBtn, cartSubBtn , removeCart;


        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            cartImageView = itemView.findViewById(R.id.cart_image_view_1);
            cartFlavorView = itemView.findViewById(R.id.cart_flavor_textview);
            cartPriceView = itemView.findViewById(R.id.cart_price_textview);
            cartQuantity = itemView.findViewById(R.id.cart_quantity);
            cartTotalQuantityView = itemView.findViewById(R.id.cart_total_qty_textview);

            cartAddBtn = itemView.findViewById(R.id.cartBtnAdd);
            cartSubBtn = itemView.findViewById(R.id.cartBtnSub);
            removeCart = itemView.findViewById(R.id.remove_Cart);
            //Still trying to understand,

            cartAddBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                //Im finding ways to remover the recyclerViewInterface but it turns out
                //it is needed to bring out the data outside
                public void onClick(View view) {
                    //add this Boilerplate for filter and error handling
                    //if (recyclerViewInterface != null) &&  (pos != RecyclerView.NO_POSITION)
                    int pos = getLayoutPosition();
                    recyclerViewInterface.onBtnClick(pos, "addQty");
                }
            });
            cartSubBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getLayoutPosition();
                    recyclerViewInterface.onBtnClick(pos, "subQty");
                }
            });

            removeCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getLayoutPosition();
                    recyclerViewInterface.onBtnClick(pos, "removeCart");
                }
            });
        }

        public void setData(int itemImage, String flavor, double price,int quantity , int totalQuantity) {
            cartImageView.setImageResource(itemImage);
            cartFlavorView.setText(flavor);
            cartPriceView.setText(Double.toString(price));
            cartQuantity.setText(Integer.toString(quantity));
            cartTotalQuantityView.setText(Integer.toString(totalQuantity));
        }



    }
}
