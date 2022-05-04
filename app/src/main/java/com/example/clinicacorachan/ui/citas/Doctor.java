package com.example.clinicacorachan.ui.citas;

public class Doctor {
    public Integer ratingDoctor = 0;
    public String nameDoctor = "";
    public String cmpDoctor = "";
    public Boolean genderDoctor = false;

    public Doctor(){

    }

    public Doctor(Integer ratingDoctor, String nameDoctor, String cmpDoctor, Boolean genderDoctor){
        this.ratingDoctor = ratingDoctor;
        this.nameDoctor = nameDoctor;
        this.cmpDoctor = cmpDoctor;
        this.genderDoctor = genderDoctor;
    }
}
