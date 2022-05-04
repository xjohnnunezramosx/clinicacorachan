package com.example.clinicacorachan.ui.citas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.clinicacorachan.R;

import java.util.ArrayList;

public class ConfirmacionFragment extends Fragment {

    public static ConfirmacionFragment newInstance() {
        return new ConfirmacionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_confirmacion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> list = getArguments().getStringArrayList("datos");
        Button btnHome = view.findViewById(R.id.btnHome);

        TextView tvNameDoctor = view.findViewById(R.id.tvDoctorName);
        TextView tvEspecialidad = view.findViewById(R.id.tvEspecialidad);
        TextView tvSede = view.findViewById(R.id.tvSede);
        TextView tvFecha = view.findViewById(R.id.tvFecha);
        TextView tvHora = view.findViewById(R.id.tvHora);

        tvNameDoctor.setText(list.get(4));
        tvEspecialidad.setText(list.get(0));
        tvSede.setText(list.get(1));
        tvFecha.setText(list.get(2));
        tvHora.setText(list.get(3));

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_confirmacion_to_nav_home);
            }
        });

    }

}