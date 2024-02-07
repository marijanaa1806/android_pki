package com.example.myapplication;

import java.io.Serializable;

public class Comment implements Serializable {
    public String comm;
    public User user;
    public Artikal artikl;

    public Comment(String comm,User user, Artikal artikl){
        this.comm = comm;
        this.user = user;
        this.artikl = artikl;
    }

}
