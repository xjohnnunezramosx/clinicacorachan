package com.example.clinicacorachan;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clinicacorachan.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogueoActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_logueo);

        user = new User();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public void loguearse(View v){
        EditText email = this.findViewById(R.id.editTextDni);
        EditText clave = this.findViewById(R.id.editTextTextPassword2);

        String dniText = email.getText().toString();
        String passText = clave.getText().toString();

        mDatabase.child("users").child(dniText).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error obteniendo datos", task.getException());
                    Toast.makeText(LogueoActivity.this, "Error obteniendo datos", Toast.LENGTH_LONG).show();
                }
                else {
                    user = task.getResult().getValue(User.class);
                    if (user.equals(User.class) || user.email.isEmpty()){
                        Toast.makeText(LogueoActivity.this, "No se encontro al paciente, registrese", Toast.LENGTH_LONG).show();
                    } else {
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(user.email,passText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(getApplicationContext() ,MainActivity.class);
                                    intent.putExtra("usuario", dniText);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LogueoActivity.this, "Error contraseña incorrecta", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    public void registrar(View v){
        startActivity(new Intent(this,FormularioActivity.class));
    }

    public void historial(View v){
        startActivity(new Intent(this, HIstorialActivity.class));
    }
}