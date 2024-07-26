package com.example.sqltestpassword;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etPin;
    private Button btnSubmit;
    private SharedPreferences sharedPreferences;

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


        etPin = findViewById(R.id.etPin);
        btnSubmit = findViewById(R.id.btnSubmit);
        sharedPreferences = getSharedPreferences("PinPrefs", Context.MODE_PRIVATE);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin = etPin.getText().toString();
                if (TextUtils.isEmpty(pin)) {
                    Toast.makeText(MainActivity.this, "Please enter a PIN", Toast.LENGTH_SHORT).show();
                } else if (pin.length() != 4) {
                    Toast.makeText(MainActivity.this, "PIN must be 4 digits", Toast.LENGTH_SHORT).show();
                } else {
                    savePin(pin);
                    Toast.makeText(MainActivity.this, "PIN saved successfully", Toast.LENGTH_SHORT).show();
                    // Redirect to the main activity or desired activity
//                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                    startActivity(intent);
//                    finish();
                }
            }
        });
    }


    private void savePin(String pin) {
        String hashedPin = SecurityUtils.hashPin(pin);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("pin", hashedPin);
        editor.apply();
    }
}