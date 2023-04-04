package com.example.posapp.Interface;

import com.example.posapp.Model.Cart;
import com.example.posapp.Model.Siomai_Inventory;

public interface RecyclerViewInterface {
    void onBtnClick(int position);

    void onAddToCart(Cart cart);
}
