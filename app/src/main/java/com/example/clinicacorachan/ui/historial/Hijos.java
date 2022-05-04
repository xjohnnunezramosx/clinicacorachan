package com.example.clinicacorachan.ui.historial;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Hijos {
    public String name = "";
    public Integer age = 0;
    public String gender = "";
    public String blood = "";
    public String date = "";

    public Hijos(){

    }

    public Hijos(Integer edad,String blood, String date,  String gender,String name){
        this.name = name;
        this.age = edad;
        this.gender = gender;
        this.blood = blood;
        this.date = date;
    }
}
