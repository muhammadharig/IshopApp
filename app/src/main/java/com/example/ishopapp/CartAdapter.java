package com.example.ishopapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    ArrayList<ItemModel> cartItems;

    public CartAdapter(Context context, ArrayList<ItemModel> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        ItemModel item = cartItems.get(position);
        holder.itemImage.setImageResource(item.getImage());
        holder.itemTitle.setText(item.getTitle());
        holder.itemPrice.setText(String.format("Rp. %d", item.getPrice()));
        holder.itemTotal.setText(String.valueOf(item.getQuantity()));

        holder.btnPlus.setOnClickListener(view -> {
            item.setQuantity(item.getQuantity() + 1); // tambah quantity
            holder.itemTotal.setText(String.valueOf(item.getQuantity())); // update quantity
            if (context instanceof CartActivity) {
                ((CartActivity) context).updateTotalPrice(); // Update total price in CartActivity
            }
        });

        holder.btnMinus.setOnClickListener(view -> {
            if (item.getQuantity() > 0) {
                item.setQuantity(item.getQuantity() - 1); // kurang quantity
                holder.itemTotal.setText(String.valueOf(item.getQuantity())); // update quantity
                if (context instanceof CartActivity) {
                    ((CartActivity) context).updateTotalPrice(); // Update total price in CartActivity
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    // Metode untuk menghitung total harga
    public int calculateTotalPrice() {
        int total = 0;
        for (ItemModel item : cartItems) {
            total += item.getPrice() * item.getQuantity(); // Menghitung total harga
        }
        return total;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemTitle, itemPrice, itemTotal;
        ImageButton btnPlus, btnMinus;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.image);
            itemTitle = itemView.findViewById(R.id.title);
            itemPrice = itemView.findViewById(R.id.price);
            itemTotal = itemView.findViewById(R.id.total);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
        }
    }
}
