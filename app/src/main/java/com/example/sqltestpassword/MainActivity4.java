package com.example.sqltestpassword;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity4 extends AppCompatActivity {

    Button btn;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets)-> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn = findViewById(R.id.btn);
        sharedPreferences = getSharedPreferences("PinPrefs", Context.MODE_PRIVATE); // Initialize SharedPreferences

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPinAndRedirect();}
        });
    }

    private void checkPinAndRedirect() {
        String storedPin = sharedPreferences.getString("pin", null);
        Intent intent;
        if (storedPin == null) {
            // PIN not created, redirect to MainActivity
            intent = new Intent(MainActivity4.this, MainActivity.class);
        } else {
            // PIN already created, redirect to MainActivity2
            intent = new Intent(MainActivity4.this, MainActivity2.class);
        }
        startActivity(intent);
    }
}