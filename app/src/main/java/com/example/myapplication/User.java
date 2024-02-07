package com.example.myapplication;

import java.io.Serializable;

public class User implements Serializable {
    public String username;
    public String password;
    public String name;
    public String surname;
    public String phone;
    public String address;
    public boolean employee;

    public User (String username,String password,String name,String surname,String phone,String address,boolean emp) {
        this.address = address;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.surname = surname;
        this.employee = emp;
    }
}
