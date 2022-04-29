package com.example.clinicacorachan.ui.digital;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.clinicacorachan.MainActivity;
import com.example.clinicacorachan.R;
import com.example.clinicacorachan.databinding.FragmentDigitalBinding;

public class DigitalFragment extends Fragment {

    private DigitalViewModel mViewModel;
    private FragmentDigitalBinding binding;

    public static DigitalFragment newInstance() {
        return new DigitalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        DigitalViewModel homeViewModel=
                new ViewModelProvider(this).get(DigitalViewModel.class);

        binding = FragmentDigitalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button b1 = root.findViewById(R.id.btnGuardarImg);
        b1 .setOnClickListener(v -> {
                    startActivity(new Intent(getContext(), MainActivity.class));
                }

        );


        return root;
    }



}