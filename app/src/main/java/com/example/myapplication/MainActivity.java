package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    List<User> users = new ArrayList<>();
    User buyer = new User("marijana", "123", "marijana", "vacic", "0692314344", "avalska",false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users.add(buyer);
        User newUser = (User) getIntent().getSerializableExtra("newUser");

        if(newUser!=null){
            users.add(newUser);
            Toast.makeText(getApplicationContext(), "new user "+newUser.username, Toast.LENGTH_SHORT).show();

        }
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userJson = preferences.getString("loggedIn", null);
        SharedPreferences.Editor editor = preferences.edit();

        if (userJson != null) {
            Gson gson = new Gson();
            User logged = gson.fromJson(userJson, User.class);
            String ind = preferences.getString("index","-1");
            if(!ind.equals("-1")){
                int index = Integer.parseInt(ind);
                users.set(index,logged);
                editor.remove("loggedIn");
                editor.remove("index");

                editor.apply();
            }

        }


    }

    public void login(View view) {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        for (int i=0;i<users.size();i++) {
            User u = users.get(i);
            if (u.username.equals(username.getText().toString()) && u.password.equals(password.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                Gson gson = new Gson();
                String userJson = gson.toJson(u);

                editor.putString("loggedIn", userJson);
                editor.putString("index", String.valueOf(i));

                editor.apply();

                Intent intent = new Intent(this,MainActivity2.class);
                intent.putExtra("loggedInUser",  u);
                startActivity(intent);
                return;
            }
        }

        Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
    }

    public void register(View view) {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
        return;
    }
}
