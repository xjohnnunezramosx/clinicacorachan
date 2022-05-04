package com.example.clinicacorachan.ui.ubicacion;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.clinicacorachan.R;
import com.example.clinicacorachan.ui.citas.Sedes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UbicacionFragment extends Fragment implements OnMapReadyCallback{

    private UbicacionViewModel mViewModel;
    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private List<Sedes> sedes;

    public static UbicacionFragment newInstance() {
        return new UbicacionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_ubicacion, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();




        sedes = new ArrayList();
        mDatabase.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(sedes.size()>5){
                    SupportMapFragment supportMapFragment=(SupportMapFragment)
                            getChildFragmentManager().findFragmentById(R.id.google_map);
                    supportMapFragment.getMapAsync(UbicacionFragment.this::onMapReady);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mDatabase.child("headquarters").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Sedes sede = dataSnapshot.getValue(Sedes.class);
                sedes.add(sede);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Sedes sede = dataSnapshot.getValue(Sedes.class);
                sedes.remove(sede);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(Sedes sede: sedes){
            LatLng latLng = new LatLng(sede.lat,sede.lng);
            mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(sede.name));

        }
        // Add a marker in Sydney and move the camera
        LatLng cercado = new LatLng(-12.050166, -77.077709);
        LatLng cercado2 = new LatLng(-12.056462, -77.059638);
        LatLng cercado3 = new LatLng(-12.067313, -77.074268);

        mMap.addMarker(new MarkerOptions()
                .position(cercado)
                .title("Clinica Corachan cercado1"));

        mMap.addMarker(new MarkerOptions()
                .position(cercado2)
                .title("Clinica Corachan cercado2"));

        mMap.addMarker(new MarkerOptions()
                .position(cercado3)
                .title("Clinica Corachan cercado3"));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(13));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cercado));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UbicacionViewModel.class);
        // TODO: Use the ViewModel
    }
}