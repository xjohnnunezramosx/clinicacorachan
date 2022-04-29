package com.example.clinicacorachan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.clinicacorachan.ui.historial.HistorialClinico;

public class LogueoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_logueo);
    }
    public void loguearse(View v){
        EditText dni = this.findViewById(R.id.editTextDni);
        EditText clave = this.findViewById(R.id.editTextTextPassword2);

        String x = dni.getText().toString();
        String y = clave.getText().toString();

        Log.i("====>",x);
        Log.i("====>" ,y);
        startActivity(new Intent(this,MainActivity.class));
    }

    public void registrar(View v){
        startActivity(new Intent(this,FormularioActivity.class));
    }

    public void historial(View v){
        startActivity(new Intent(this, HIstorialActivity.class));
    }
}