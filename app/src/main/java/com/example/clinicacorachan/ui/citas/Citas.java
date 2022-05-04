package com.example.clinicacorachan.ui.citas;

public class Citas {
    public String nameDoctor = "";
    public String hour = "";
    public String speciality = "";
    public String headquarters = "";
    public String date = "";

    public Citas(){

    }

    public Citas(String nameDoctor, String speciality, String headquarters, String date, String hour){
        this.nameDoctor = nameDoctor;
        this.speciality = speciality;
        this.headquarters = headquarters;
        this.date = date;
        this.hour = hour;
    }
}
