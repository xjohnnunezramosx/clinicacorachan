package com.example.clinicacorachan.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String username;
    public String dni;
    public String email;
    public String phone;
    public String district;
    public String direction;
    public String namePacient;
    public String date;

    public User(String username,String dni, String email,String phone,String district, String direction, String namePacient, String date) {
        this.username = username;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
        this.district = district;
        this.direction = direction;
        this.namePacient = namePacient;
        this.date = date;
    }

}