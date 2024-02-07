package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

public class Change extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        Button backButton = findViewById(R.id.toolbarButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        EditText name = findViewById(R.id.name);
        EditText surname = findViewById(R.id.surname);
        EditText phone = findViewById(R.id.phone);
        EditText address = findViewById(R.id.address);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userJson = preferences.getString("loggedIn", null);
        User logged;
        if (userJson != null) {
            // Deserialize the String to a User object
            Gson gson = new Gson();
            logged = gson.fromJson(userJson, User.class);

            // Now you have the loggedInUser object
        }else logged=(User)getIntent().getSerializableExtra("loggedInUser");
        username.setText(logged.username);
        password.setText(logged.password);
        name.setText(logged.name);
        surname.setText(logged.surname);
        phone.setText(logged.phone);
        address.setText(logged.address);
    }
    public void update(View view){


        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        EditText name = findViewById(R.id.name);
        EditText surname = findViewById(R.id.surname);
        EditText phone = findViewById(R.id.phone);
        EditText address = findViewById(R.id.address);
        User newUser = new User(username.getText().toString(),password.getText().toString(),
                name.getText().toString(),surname.getText().toString(),phone.getText().toString(),
                address.getText().toString(),false);
          Intent intent = new Intent(this, MainActivity2.class);
          intent.putExtra("loggedInUser",  newUser);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        String userJson = gson.toJson(newUser);

        editor.putString("loggedIn", userJson);
        editor.apply();

        // Retrieve loggedInUser object from SharedPreferences


        startActivity(intent);
    }
}