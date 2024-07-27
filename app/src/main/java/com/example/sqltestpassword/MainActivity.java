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
//
//public class MainActivity extends AppCompatActivity {
//    private EditText etPin;
//    private Button btnSubmit;
//    private SharedPreferences sharedPreferences;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//
//        etPin = findViewById(R.id.etPin);
//        btnSubmit = findViewById(R.id.btnSubmit);
//        sharedPreferences = getSharedPreferences("PinPrefs", Context.MODE_PRIVATE);
//
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String pin = etPin.getText().toString();
//                if (TextUtils.isEmpty(pin)) {
//                    Toast.makeText(MainActivity.this, "Please enter a PIN", Toast.LENGTH_SHORT).show();
//                } else if (pin.length() != 4) {
//                    Toast.makeText(MainActivity.this, "PIN must be 4 digits", Toast.LENGTH_SHORT).show();
//                } else {
//                    savePin(pin);
//                    Toast.makeText(MainActivity.this, "PIN saved successfully", Toast.LENGTH_SHORT).show();
//                    // Redirect to the main activity or desired activity
////                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
////                    startActivity(intent);
////                    finish();
//                }
//            }
//        });
//    }
//
//
//    private void savePin(String pin) {
//        String hashedPin = SecurityUtils.hashPin(pin);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("pin", hashedPin);
//        editor.apply();
//    }
//}

public class MainActivity extends AppCompatActivity {
    private EditText etPin;
    private Button btnSubmit;
    private SharedPreferences sharedPreferences;
    private boolean isEditing;
    private Button btnDelete;
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

        btnDelete = findViewById(R.id.btnDelete);
        etPin = findViewById(R.id.etPin);
        btnSubmit = findViewById(R.id.btnSubmit);
        sharedPreferences = getSharedPreferences("PinPrefs", Context.MODE_PRIVATE);

        // Check if a PIN alreadyexists in SharedPreferences
        String storedPin = sharedPreferences.getString("pin", null);
        if (storedPin == null) {
            // No PIN exists, set isEditing to false (create mode)
            isEditing = false;
            btnDelete.setVisibility(View.GONE);
            btnSubmit.setText("Create PIN");
        } else {
            // PIN exists, set isEditing to true (edit mode)
            isEditing = true;
            btnDelete.setVisibility(View.VISIBLE);
          //  etPin.setText(SecurityUtils.decryptPin(storedPin)); // Decrypt if necessary
            btnSubmit.setText("Update PIN");
        }

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
                    Toast.makeText(MainActivity.this, isEditing ? "PIN updated successfully" : "PIN created successfully", Toast.LENGTH_SHORT).show();

                    // Redirect to the desired activity after saving/updating
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class); // Or your desired activity
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePin();
                Toast.makeText(MainActivity.this, "PIN deleted successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void savePin(String pin) {
        String hashedPin = SecurityUtils.hashPin(pin); // Replace with your actual hashing logic
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("pin", hashedPin);
        editor.apply();
    }

    private void deletePin() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("pin");
        editor.apply();
    }
}