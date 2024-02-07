package com.example.myapplication;

import java.io.Serializable;

public class Artikal implements Serializable {
    public  String name;
    public String src;
    public String about;
    public int price;

    public Artikal(String name, String src, String about, int price, String ingredients, boolean cake) {
        this.name = name;
        this.src = src;
        this.about = about;
        this.price = price;
        this.ingredients = ingredients;
        this.cake = cake;
    }

    public String ingredients;
    public boolean cake;
    public int quantity;

}
