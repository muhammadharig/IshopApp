package com.example.ishopapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ItemModel> modelArrayList;
    ItemAdapter itemAdapter;

    // btn move
    Button btnGoCart;

    int[] image = new int[] {
            R.drawable.satu,
            R.drawable.dua,
            R.drawable.tiga,
            R.drawable.empat,
            R.drawable.lima,
            R.drawable.enam,
            R.drawable.tujuh,
            R.drawable.delapan,
            R.drawable.sembilan,
            R.drawable.sepuluh
    };

    // membuat array untuk data title
    String[] title = new String[] {
            "Barang satu",
            "Barang dua",
            "Barang tiga",
            "Barang empat",
            "Barang lima",
            "Barang enam",
            "Barang tujuh",
            "Barang delapan",
            "Barang sembilan",
            "Barang sepuluh"
    };

    String[] description = new String[] {
            "Cocok dipakai untuk santai...",
            "Cocok dipakai untuk santai... Cocok dipakai untuk santai... ",
            "Cocok dipakai untuk santai... ",
            "Cocok dipakai untuk santai... ",
            "Cocok dipakai untuk santai... ",
            "Cocok dipakai untuk santai... ",
            "Cocok dipakai untuk santai... ",
            "Cocok dipakai untuk santai... ",
            "Cocok dipakai untuk santai... ",
            "Cocok dipakai untuk santai... "
    };

    int[] price = new int[] {
            120000,
            150000,
            300000,
            234000,
            500000,
            700000,
            600000,
            800000,
            310000,
            126000
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        modelArrayList = new ArrayList<>();
        itemAdapter = new ItemAdapter(this, modelArrayList);
        recyclerView.setAdapter(itemAdapter);

        // looping
        for (int i = 0; i < title.length; i++) {
            ItemModel itemModel = new ItemModel(image[i], title[i], description[i], price[i], 1);
            modelArrayList.add(itemModel);
        }
        itemAdapter.notifyDataSetChanged();

        btnGoCart = findViewById(R.id.goCart);
        btnGoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                intent.putParcelableArrayListExtra("cartItems", itemAdapter.getCartList());
                startActivity(intent);
            }
        });
    }




}