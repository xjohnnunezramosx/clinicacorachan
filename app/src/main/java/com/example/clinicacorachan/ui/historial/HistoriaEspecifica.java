package com.example.clinicacorachan.ui.historial;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clinicacorachan.R;

public class HistoriaEspecifica extends Fragment {

    private HistoriaEspecificaViewModel mViewModel;

    public static HistoriaEspecifica newInstance() {
        return new HistoriaEspecifica();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.historia_especifica_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoriaEspecificaViewModel.class);
        // TODO: Use the ViewModel
    }

}