package com.example.clinicacorachan.ui.citas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinicacorachan.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DoctoresFragment extends Fragment implements DoctorAdapter.ItemClickListener{

    private RecyclerView recyclerView;
    private DoctorAdapter doctorAdapter;
    private String Hora = "08:00 AM";
    private DatabaseReference mDatabase;
    private String DNI;

    public static DoctoresFragment newInstance() {
        return new DoctoresFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doctores, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String defaultValue = "";
        DNI = sharedPref.getString(getString(R.string.saved_dni), defaultValue);


        Button btnBack = view.findViewById(R.id.btnBack);
        Button btnConfirm = view.findViewById(R.id.btnConfirm);
        recyclerView = view.findViewById(R.id.rvDoctores);

        ArrayList<String> list = getArguments().getStringArrayList("datos");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_doctores_to_nav_citas);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                list.add(Hora);
                list.add(doctorAdapter.getItem(doctorAdapter.selected_position).nameDoctor);
                bundle.putStringArrayList("datos",list);

                Citas citas = new Citas(list.get(4),list.get(0), list.get(1),list.get(2), list.get(3));
                mDatabase.child("medicalAppointments").child(DNI).setValue(citas);
                Navigation.findNavController(view).navigate(R.id.action_nav_doctores_to_nav_confirmacion,bundle);
            }
        });

        RadioGroup opcionesCliente = (RadioGroup) view.findViewById(R.id.rg_horas);
        RadioButton radioButton = view.findViewById(R.id.radio_1);
        radioButton.setChecked(true);

        opcionesCliente.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_1: Hora = "08:00 AM"; break;
                    case R.id.radio_2: Hora = "09:00 AM"; break;
                    case R.id.radio_3: Hora = "10:00 AM"; break;
                    case R.id.radio_4: Hora = "11:00 AM"; break;
                    case R.id.radio_5: Hora = "12:00 M"; break;
                    case R.id.radio_6: Hora = "13:00 PM";break;
                    case R.id.radio_7: Hora = "14:00 PM";break;
                    case R.id.radio_8: Hora = "15:00 PM";break;
                    case R.id.radio_9: Hora = "16:00 PM"; break;
                    case R.id.radio_10: Hora = "17:00 PM"; break;
                }
            }
        });

        llenarDatos();
    }

    private void llenarDatos(){
        List<Doctor> list = new ArrayList();
        list.add(new Doctor(4,"David Neyra","CMP: 123456",false));
        list.add(new Doctor(5,"Estrella Sarit","CMP: 456123",true));
        list.add(new Doctor(4,"Juan Carlos Abarca","CMP: 789123",false));
        doctorAdapter = new DoctorAdapter(requireContext(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        doctorAdapter.setClickListener(this);
        recyclerView.setAdapter(doctorAdapter);
    }

    @Override
    public void onItemClickDoctor(View view, int position) {
        Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
        animation1.setDuration(4000);
        view.startAnimation(animation1);
    }
}