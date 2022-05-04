package com.example.clinicacorachan.ui.historial;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinicacorachan.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistorialFragment extends Fragment implements HijosAdapter.ItemClickListener{

    public static HistorialFragment newInstance() {
        return new HistorialFragment();
    }

    private HistorialViewModel mViewModel;
    private RecyclerView recyclerView;
    private Button btnVerHistorial;
    private HijosAdapter hijosAdapter;
    private DatabaseReference mDatabase;
    private String DNI;
    private List<Hijos> hijos =  new ArrayList();;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_historial, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvHijos);
        btnVerHistorial = view.findViewById(R.id.btnVerHistorial);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String defaultValue = "";
        DNI = sharedPref.getString(getString(R.string.saved_dni), defaultValue);
        
        btnVerHistorial.setOnClickListener(v->{
            
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                hijosAdapter = new HijosAdapter(requireContext(),hijos);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                hijosAdapter.setClickListener(HistorialFragment.this::onItemClickHijo);
                recyclerView.setAdapter(hijosAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDatabase.child("sons").child(DNI).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Hijos hijo = dataSnapshot.getValue(Hijos.class);
                hijos.add(hijo);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistorialViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onItemClickHijo(View view, int position) {

    }
}