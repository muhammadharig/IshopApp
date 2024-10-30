package com.example.ishopapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    Context context;
    ArrayList<ItemModel> modelArrayList;
    ArrayList<ItemModel> cartList = new ArrayList<>(); // Keranjang

    public ItemAdapter(Context context, ArrayList<ItemModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemModel itemModel = modelArrayList.get(position);
        holder.item_image.setImageResource(itemModel.getImage());
        holder.item_title.setText(itemModel.getTitle());
        holder.item_description.setText(itemModel.getDescription());
        holder.item_price.setText(String.format("Rp. %d", itemModel.getPrice()));

        holder.addCart.setOnClickListener(view -> {
            cartList.add(itemModel); // Tambahkan item ke keranjang
            Toast.makeText(context, itemModel.getTitle() + " Berhasil ditambahkan ke keranjang", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public ArrayList<ItemModel> getCartList() {
        return cartList;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
        TextView item_title, item_description, item_price;
        Button addCart;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.image);
            item_title = itemView.findViewById(R.id.title);
            item_description = itemView.findViewById(R.id.description);
            item_price = itemView.findViewById(R.id.price);
            addCart = itemView.findViewById(R.id.addCart);
        }
    }
}
