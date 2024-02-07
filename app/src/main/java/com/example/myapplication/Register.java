package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void register(View view){
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        EditText name = findViewById(R.id.name);
        EditText surname = findViewById(R.id.surname);
        EditText phone = findViewById(R.id.phone);
        EditText address = findViewById(R.id.address);
        User newUser = new User(username.getText().toString(),password.getText().toString(),
                name.getText().toString(),surname.getText().toString(),phone.getText().toString(),
                address.getText().toString(),false);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("newUser",  newUser);
        startActivity(intent);
    }
}