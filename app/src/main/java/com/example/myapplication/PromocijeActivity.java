package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class PromocijeActivity extends MainActivity2 {



    private ViewPager viewPager;
    private ImageAdapter imageAdapter;
    private Handler handler;
    private Runnable runnable;
    private int delay = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promocije);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);
        handler = new Handler();

        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.cake);
        imageList.add(R.drawable.n);
        imageList.add(R.drawable.cheesecake);
        List<String> textList=new ArrayList<>();
        textList.add("Samo danas 250!!");
        textList.add("50% popusta!!");
        textList.add("3+1 gratis");
        imageAdapter = new ImageAdapter(this, imageList,textList);
        viewPager.setAdapter(imageAdapter);

        // Automatic slide change
        runnable = () -> {
            int currentItem = viewPager.getCurrentItem();
            int totalItems = imageAdapter.getCount();

            if (currentItem < totalItems - 1) {
                viewPager.setCurrentItem(currentItem + 1);
            } else {
                viewPager.setCurrentItem(0);
            }
        };

        handler.postDelayed(runnable, delay);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    handler.postDelayed(runnable, delay);
                } else {
                    handler.removeCallbacks(runnable);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}