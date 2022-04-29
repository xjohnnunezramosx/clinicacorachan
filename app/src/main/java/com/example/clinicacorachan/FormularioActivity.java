package com.example.clinicacorachan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_formulario);
    }
    public void registrarse(View v){
        EditText nombre = this.findViewById(R.id.editTextTextPersonName);
        EditText dnipasaporte = this.findViewById(R.id.editTextNumber);
        EditText correo = this.findViewById(R.id.editTextTextEmailAddress);
        EditText telefono = this.findViewById(R.id.editTextPhone);
        EditText distrito = this.findViewById(R.id.editTextTextPersonName3);
        EditText direccion = this.findViewById(R.id.editTextTextPersonName4);
        EditText nombre_paciente = this.findViewById(R.id.editTextTextPersonName5);
        EditText fecha_nacimiento = this.findViewById(R.id.editTextDate);
        EditText contraseña = this.findViewById(R.id.editTextTextPassword);


        String a = nombre.getText().toString();
        String b = dnipasaporte.getText().toString();
        String c = correo.getText().toString();
        String d = telefono.getText().toString();
        String e = distrito.getText().toString();
        String f = direccion.getText().toString();
        String g = nombre_paciente.getText().toString();
        String h = fecha_nacimiento.getText().toString();
        String i = contraseña.getText().toString();

        Log.i("====>",a);
        Log.i("====>" ,b);
        Log.i("====>",c);
        Log.i("====>",d);
        Log.i("====>",e);
        Log.i("====>",f);
        Log.i("====>",g);
        Log.i("====>",h);
        Log.i("====> ",i);

        startActivity(new Intent(this,LogueoActivity.class));
    }

}