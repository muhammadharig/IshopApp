package com.example.ishopapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ItemModel> cartItems;
    CartAdapter cartAdapter;
    TextView totalPayment, nameItem, totalPriceItem;

    Button btnPayment;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameItem = findViewById(R.id.nameItem);
        totalPriceItem = findViewById(R.id.priceWithQuantity);
        totalPayment = findViewById(R.id.totalAmount);
        recyclerView = findViewById(R.id.cartItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        cartItems = getIntent().getParcelableArrayListExtra("cartItems");
        cartAdapter = new CartAdapter(this, cartItems);
        recyclerView.setAdapter(cartAdapter);

        if (cartItems != null && !cartItems.isEmpty()) {
            StringBuilder itemNames = new StringBuilder();
            for (ItemModel item : cartItems) {
                itemNames.append(item.getTitle()).append("\n");
            }
            nameItem.setText(itemNames.toString());
            updateTotalPrice();
        }

        btnPayment = findViewById(R.id.btnBayar);
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, SuccesActivity.class);
                startActivity(intent);

                //close activity
                finish();
            }
        });
    }

    void updateTotalPrice() {
        int totalPrice = cartAdapter.calculateTotalPrice();
        totalPayment.setText(String.format("Rp. %d", totalPrice));
        // Update total price item
        StringBuilder itemPriceQuantitys = new StringBuilder();
        for (ItemModel item : cartItems) {
            int itemPrice = item.getPrice();
            int itemQuantity = item.getQuantity();
            int totalItemPrice = itemPrice * itemQuantity;
            itemPriceQuantitys.append("x").append(itemQuantity).append(" = Rp. ").append(totalItemPrice).append("\n");
        }
        // Memperbarui tampilan totalPriceItem dengan daftar harga total
        totalPriceItem.setText(itemPriceQuantitys.toString()); // Menampilkan total harga item
    }

}