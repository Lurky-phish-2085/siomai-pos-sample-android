package com.example.posapp.Adapter;


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

import com.example.posapp.Model.Cart;
import com.example.posapp.Model.Siomai_Inventory;
import com.example.posapp.R;
import com.example.posapp.Interface.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    //I dont know what this is for? What is final btw?
    private final RecyclerViewInterface recyclerViewInterface;

    //I need for some reason pls find the reason -jet
    Context context;
    private List<Siomai_Inventory> siomai_inventories =  new ArrayList<>();

    public ProductAdapter(Context context, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;

        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        int cartImage = siomai_inventories.get(position).getImg();
        String flavor = siomai_inventories.get(position).getFlavor();
        double price = siomai_inventories.get(position).getPrice();

        holder.setData(cartImage, flavor, price);
    }


    public void setItems(List<Siomai_Inventory> siomai_inventories) {
        this.siomai_inventories = siomai_inventories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return siomai_inventories.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {

        private ImageView itemImageView;
        private TextView itemFlavorView, itemPriceView;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            itemImageView = itemView.findViewById(R.id.item_image_view);
            itemFlavorView = itemView.findViewById(R.id.item_flavor_textview);
            itemPriceView = itemView.findViewById(R.id.item_price_textview);

            itemView.findViewById(R.id.CardView).setOnClickListener(new View.OnClickListener() {
                @Override
                //Im finding ways to remover the recyclerViewInterface but it turns out
                //it is needed to bring out the data outside
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getLayoutPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onBtnClick(pos);
                        }
                    }
                }
            });

            itemView.findViewById(R.id.item_image_view).setOnClickListener(new View.OnClickListener() {
                @Override
                //Im finding ways to remover the recyclerViewInterface but it turns out
                //it is needed to bring out the data outside
                public void onClick(View view) {
                    int pos = getLayoutPosition();
                    recyclerViewInterface.onBtnClick(pos);
                }
            });

        }

        public void setData(int itemImage, String flavor, double price) {
            itemImageView.setImageResource(itemImage);
            itemFlavorView.setText(flavor);
            itemPriceView.setText(Double.toString(price));
        }



    }
}
