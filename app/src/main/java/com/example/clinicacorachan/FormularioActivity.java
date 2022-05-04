package com.example.clinicacorachan;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clinicacorachan.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormularioActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_formulario);

        mDatabase = FirebaseDatabase.getInstance().getReference();
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


        String name = nombre.getText().toString();
        String dni = dnipasaporte.getText().toString();
        String email = correo.getText().toString();
        String phone = telefono.getText().toString();
        String district = distrito.getText().toString();
        String direction = direccion.getText().toString();
        String namePacient = nombre_paciente.getText().toString();
        String date = fecha_nacimiento.getText().toString();
        String password = contraseña.getText().toString();

        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            User user = new User(name,dni,email,phone,district,direction,namePacient,date);
                            mDatabase.child("users").child(dni).setValue(user);

                            startActivity(new Intent(getApplicationContext(),LogueoActivity.class));
                        }
                    }
                });
    }

}