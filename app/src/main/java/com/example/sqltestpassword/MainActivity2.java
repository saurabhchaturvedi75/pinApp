package com.example.sqltestpassword;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//
//public class MainActivity2 extends AppCompatActivity {
//    private EditText etPin;
//    private Button btnSubmit, btnEdit;
//    private SharedPreferences sharedPreferences;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main2);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//
//
//        btnEdit= findViewById(R.id.btnEdit);
//        etPin = findViewById(R.id.etPin);
//        btnSubmit = findViewById(R.id.btnSubmit);
//        sharedPreferences = getSharedPreferences("PinPrefs", Context.MODE_PRIVATE);
//
//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
//                startActivity(intent);
//              //  finish();
//            }
//        });
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String pin = etPin.getText().toString();
//                if (TextUtils.isEmpty(pin)) {
//                    Toast.makeText(MainActivity2.this, "Please enter a PIN", Toast.LENGTH_SHORT).show();
//                } else if (validatePin(pin)) {
//                    Toast.makeText(MainActivity2.this, "PIN validated successfully", Toast.LENGTH_SHORT).show();
//                    // Redirect to the main activity or desired activity
//                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
//                    startActivity(intent);
//                 //   finish();
//                } else {
//                    Toast.makeText(MainActivity2.this, "Invalid PIN", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    // In PinValidationActivity.java
//    private boolean validatePin(String pin) {
//        String hashedPin = SecurityUtils.hashPin(pin);
//        String storedPin = sharedPreferences.getString("pin", null);
//        return hashedPin != null && hashedPin.equals(storedPin);
//    }
//}

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity2 extends AppCompatActivity {

    private Integer   etPin;
    private EditText   digit1, digit2, digit3, digit4;
    private Button btnSubmit, btnEdit;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Handle window insets (for edge-to-edge display)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnEdit = findViewById(R.id.btnEdit);
       // etPin = findViewById(R.id.etPin);
        btnSubmit = findViewById(R.id.btnSubmit);
        digit1 = findViewById(R.id.digit1);
        digit2 = findViewById(R.id.digit2);
        digit3 = findViewById(R.id.digit3);
        digit4 = findViewById(R.id.digit4);

        sharedPreferences = getSharedPreferences("PinPrefs", Context.MODE_PRIVATE);

        btnEdit.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity.class); // Replace MainActivity with your actual activity
            startActivity(intent);
        });

        btnSubmit.setOnClickListener(v -> {
          //  String pin = etPin.toString();
            String pin = etPin != null ? String.valueOf(etPin) : "";
            if (TextUtils.isEmpty(pin)) {
                Toast.makeText(MainActivity2.this, "Please enter a PIN", Toast.LENGTH_SHORT).show();
            } else if (validatePin(pin)) {
                Toast.makeText(MainActivity2.this, "PIN validated successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class); // Replace MainActivity3 with your actual activity
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity2.this, "Invalid PIN", Toast.LENGTH_SHORT).show();
            }
        });

        // TextWatcher for each digit EditText
//        TextWatcher textWatcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // Not used
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // Not used
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // Move focus to the next EditText after a digit is entered
//                if (s.length() == 1) {
//                    View nextFocus = null;
//                    if (getCurrentFocus() == digit1) {
//                        nextFocus = digit2;
//                    } else if (getCurrentFocus() == digit2) {
//                        nextFocus = digit3;
//                    } else if (getCurrentFocus() == digit3) {
//                        nextFocus = digit4;
//                    }
//                    if (nextFocus != null) {
//                        nextFocus.requestFocus();
//                    }
//                }
//
//                // Update the hidden etPin with the combined PIN
//                StringBuilder pinBuilder = new StringBuilder();
//                pinBuilder.append(digit1.getText().toString());
//                pinBuilder.append(digit2.getText().toString());
//                pinBuilder.append(digit3.getText().toString());
//                pinBuilder.append(digit4.getText().toString());
//
//                if (pinBuilder.length() == 4) {
//                    try {
//                        etPin = Integer.parseInt(pinBuilder.toString());
//                    } catch (NumberFormatException e) {
//                        // Handle invalid input(e.g., show an error message)
//                        Toast.makeText(MainActivity2.this, "Invalid PIN format", Toast.LENGTH_SHORT).show();
//                    }
//                }
//               // etPin = Integer.parseInt(pinBuilder.toString());
//            }
//        };

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {// Not used
            }

            @Override
            public void afterTextChanged(Editable s) {
                EditText currentFocus = (EditText) getCurrentFocus();

                if (s.length() == 1) {
                    // Move focus forward if a digit is entered
                    View nextFocus = null;
                    if (currentFocus == digit1) {
                        nextFocus = digit2;
                    } else if (currentFocus == digit2) {
                        nextFocus = digit3;} else if (currentFocus == digit3) {
                        nextFocus = digit4;
                    }
                    if (nextFocus != null) {
                        nextFocus.requestFocus();
                    }
                }  else if (s.length() == 0 && currentFocus != null && currentFocus != digit1) {
                    // Move focus backward if a digit is deleted (backspace), except when in the first box
                    View previousFocus = null;
                    if (currentFocus == digit2) {
                        previousFocus = digit1;
                    } else if (currentFocus == digit3) {
                        previousFocus = digit2;
                    } else if (currentFocus == digit4) {
                        previousFocus = digit3;
                    }
                    if (previousFocus != null) {
                        previousFocus.requestFocus();
                        if (previousFocus instanceof EditText) {
                            ((EditText) previousFocus).setSelection(((EditText) previousFocus).getText().length()); // Set cursor to the end
                        }
                       // previousFocus.setSelection(previousFocus.getText().length());
                    }
                }

                // Update the hidden etPin with the combined PIN only if all digits are entered
                StringBuilder pinBuilder = new StringBuilder();
                pinBuilder.append(digit1.getText().toString());
                pinBuilder.append(digit2.getText().toString());
                pinBuilder.append(digit3.getText().toString());
                pinBuilder.append(digit4.getText().toString());

                try {
                    etPin = Integer.parseInt(pinBuilder.toString());
                } catch (NumberFormatException e) {
                    etPin = null; // Or handle appropriately
                }
            }
        };
        digit1.addTextChangedListener(textWatcher);
        digit2.addTextChangedListener(textWatcher);
        digit3.addTextChangedListener(textWatcher);
        digit4.addTextChangedListener(textWatcher);
    }

    private boolean validatePin(String pin) {
        String hashedPin = SecurityUtils.hashPin(pin); // Replace with your actual hashing logic
        String storedPin = sharedPreferences.getString("pin", null);
        return hashedPin != null && hashedPin.equals(storedPin);
    }
}