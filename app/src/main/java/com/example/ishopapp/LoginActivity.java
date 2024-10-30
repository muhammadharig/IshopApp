package com.example.ishopapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    // declarate editText
    private EditText email, password;
    private boolean passwordVisible;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // get id
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btnLogin);

        // setTouchListener
        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Kanan = 2; // indeks drawable di kanan
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Mengecek apakah titik sentuh berada di drawable kanan
                    if (event.getRawX() >= password.getRight() - password.getCompoundDrawables()[Kanan].getBounds().width()) {
                        int pilihan = password.getSelectionEnd();
                        if (passwordVisible) {
                            // Menampilkan ikon untuk menyembunyikan password
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_password, 0, R.drawable.ic_show, 0);
                            // Menyembunyikan password
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        } else {
                            // Menampilkan ikon untuk menunjukkan password
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_password, 0, R.drawable.ic_showoff, 0);
                            // Menampilkan password
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        password.setSelection(pilihan);
                        return true; // Mengindikasikan bahwa event telah diproses
                    }
                }
                return false; // Mengindikasikan bahwa event belum diproses
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEmailPassword();
            }
        });

    }

    private void checkEmailPassword() {
        if (email.getText().toString().equals("irma@gmail.com") && password.getText().toString().equals("irma123")) {
            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Login Gagal!, Silahkan Coba lagi.", Toast.LENGTH_SHORT).show();
        }
    }
}