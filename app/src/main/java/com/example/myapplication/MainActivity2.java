package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity2 extends AppCompatActivity {

    public  static List<Order> orders = new ArrayList<>();
    public  static List<Comment> comments = new ArrayList<>();
    User logged ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userJson = preferences.getString("loggedIn", null);
        SharedPreferences.Editor editor = preferences.edit();

        if (userJson != null) {
            Gson gson = new Gson();
             logged = gson.fromJson(userJson, User.class);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu); // Assuming your menu resource file is named menu_main.xml


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.promocije) {
            promo();
            return true;
        } else if (itemId == R.id.torte) {
            torte();
            return true;
        } else if (itemId == R.id.obavestenja) {
            obavestenja();
            return true;
        } else if (itemId == R.id.kontakt) {
            kontakt();
            return true;
        } else if (itemId == R.id.korpa) {
            korpa();
            return true;
        } else if (itemId == R.id.change) {
            change();
            return true;
        } else if (itemId == R.id.odjava) {
            odjava();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    private void promo() {
        Intent intent = new Intent(this,PromocijeActivity.class);
        startActivity(intent);
        return;
    }

    private void torte() {
        Intent intent = new Intent(this,TorteActivity.class);
        intent.putExtra("loggedInUser",logged);
        startActivity(intent);
        return;
    }
    private void obavestenja() {

        List<Order> myOrders = new ArrayList<>();
        for (Order o : orders) {
            if (o.user.username.equals(logged.username)) myOrders.add(o);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_o, null);
        ListView listView = dialogView.findViewById(R.id.listViewOrders);

        ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, myOrders) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                Order order = myOrders.get(position);

                String orderDetails = "Status: " + order.status + "\nTotal: " + order.total + "\nArtikli:\n";
                for (Artikal item : order.items) {
                    orderDetails += "- " + item.name + "\n";
                }

                textView.setText(orderDetails);

                return view;
            }
        };

        listView.setAdapter(adapter);
        builder.setView(dialogView)
                .setNegativeButton("Zatvori", (dialogInterface, i) -> {
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }



    private void kontakt() {
        Intent intent = new Intent(this,Kontakt.class);
        startActivity(intent);
        return;
    }

    private void korpa() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Order cur=null;
        for (Order o :orders){
            if(o.user.username.equals(logged.username) && o.status.equals("u toku")) {
                cur = o;
                break;
            }
        }
        if (cur == null) {
            return;
        }
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_k, null);
        List<Artikal> items = cur.items;
        ListView listView = dialogView.findViewById(R.id.listViewItems);
        ArrayAdapter<Artikal> adapter = new ArrayAdapter<Artikal>(this, android.R.layout.simple_list_item_1, items) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                Artikal artikal = items.get(position);
                textView.setText(artikal.name + " - " + artikal.price + " din x " + artikal.quantity);

                return view;
            }
        };
        listView.setAdapter(adapter);
        builder.setView(dialogView)
                .setPositiveButton("Naruci", (dialogInterface, i) -> {
                    for (Order o :orders){
                        if(o.user.username.equals(logged.username) && o.status.equals("u toku")) {
                            o.status = "poslata";
                            break;
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Porudzbina poslata ", Toast.LENGTH_SHORT).show();

                })
                .setNegativeButton("Zatvori", (dialogInterface, i) -> {
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void change() {
        Intent intent = new Intent(this, Change.class);
        intent.putExtra("loggedInUser",logged);
       startActivity(intent);
        return;
    }

    private void odjava() {
        Intent intent = new Intent(this,MainActivity.class);
        User logged = (User) getIntent().getSerializableExtra("loggedInUser");
        getIntent().removeExtra("loggedInUser");
        startActivity(intent);
        return;
    }
}