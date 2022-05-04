package com.example.clinicacorachan.ui.citas;

public class Sedes {
    public Integer lat = 0;
    public Integer lng = 0;
    public String name = "";

    public Sedes(){

    }

    public Sedes(Integer lat, Integer lng, String name){
        this.lat = lat;
        this.lng = lng;
        this.name = name;
    }
}
