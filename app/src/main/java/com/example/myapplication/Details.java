package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
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
        Artikal item  = (Artikal)getIntent().getSerializableExtra("clickedImage");
        TextView naziv = findViewById(R.id.naziv);
        TextView opis = findViewById(R.id.opis);
        TextView sast = findViewById(R.id.sast);
        TextView cena = findViewById(R.id.cena);
        naziv.setText(item.name);
        opis.setText(item.about);
        sast.setText(item.ingredients);
        cena.setText(String.valueOf(item.price)+"din"); // Convert integer to string
        String sl = item.src;
        sl = sl.replace(".jpg", "");
        int resourceId = getResources().getIdentifier(sl, "drawable", this.getPackageName());
        ImageView slika = findViewById(R.id.slika);
        slika.setImageResource(resourceId);


        ListView lv = findViewById(R.id.commentListView);
        List<Comment> imgComments = new ArrayList<>();
        for(Comment c :MainActivity2.comments){
            if(c.artikl.name.equals(item.name))imgComments.add(c);
        }
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this, android.R.layout.simple_list_item_1, imgComments) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                Comment artikal = imgComments.get(position);
                textView.setText(artikal.comm + " - " + artikal.user.username);

                return view;
            }
        };
        lv.setAdapter(adapter);

    }


    public void addOrder(View view){
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userJson = preferences.getString("loggedIn", null);
        Artikal item  = (Artikal)getIntent().getSerializableExtra("clickedImage");
        User logged = null;
        if (userJson != null) {
            Gson gson = new Gson();
            logged = gson.fromJson(userJson, User.class);

        }
        EditText quant =  findViewById(R.id.quantity);
        int k = Integer.parseInt(quant.getText().toString());

        item.quantity = k;
        boolean exists = false;
        for(Order o :MainActivity2.orders){

            if(o.status.equals("u toku") && o.user.username.equals(logged.username)){
                o.items.add(item);
                o.total = o.total+item.price*k;
                exists=true;
            }

        }
        if(!exists){
            List<Artikal> items = new ArrayList<>();
            items.add(item);
            Order o  = new Order(logged,items,item.price*k,"u toku");
            MainActivity2.orders.add(o);
        }
        Toast.makeText(getApplicationContext(), "Dodato u korpu ", Toast.LENGTH_SHORT).show();

    }
    public void addComm(View view){
        EditText komentar = findViewById(R.id.comment);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userJson = preferences.getString("loggedIn", null);
        Artikal item  = (Artikal)getIntent().getSerializableExtra("clickedImage");
        User logged = null;
        if (userJson != null) {
            Gson gson = new Gson();
            logged = gson.fromJson(userJson, User.class);

        }
        Comment com = new Comment(komentar.getText().toString(),logged,item);
        MainActivity2.comments.add(com);
        komentar.setText("");
        Toast.makeText(getApplicationContext(), "Dodat komentar ", Toast.LENGTH_SHORT).show();
        ListView lv = findViewById(R.id.commentListView);
        ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) lv.getAdapter();
        adapter.add(com);
        adapter.notifyDataSetChanged();
    }




}
