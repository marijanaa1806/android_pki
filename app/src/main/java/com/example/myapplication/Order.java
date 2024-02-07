package com.example.myapplication;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    public User user;
    public List<Artikal> items;
    public int total;
    public String status;

    public Order(User user, List<Artikal> items, int total, String status) {
        this.user = user;
        this.items = items;
        this.total = total;
        this.status = status;
    }
}
