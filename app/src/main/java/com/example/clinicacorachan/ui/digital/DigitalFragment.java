package com.example.clinicacorachan.ui.digital;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.clinicacorachan.MainActivity;
import com.example.clinicacorachan.R;
import com.example.clinicacorachan.databinding.FragmentDigitalBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class DigitalFragment extends Fragment {

    private DigitalViewModel mViewModel;
    private FragmentDigitalBinding binding;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int RESULT_OK = -1;
    private Bitmap imageBitmap;
    private String DNI;
    private DatabaseReference mDatabase;

    public static DigitalFragment newInstance() {
        return new DigitalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        DigitalViewModel homeViewModel=
                new ViewModelProvider(this).get(DigitalViewModel.class);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String defaultValue = "";
        DNI = sharedPref.getString(getString(R.string.saved_dni), defaultValue);

        binding = FragmentDigitalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnTomarFoto.setOnClickListener(view -> {
            abrirCamara();
        });

        binding.btnGuardarImg.setOnClickListener(view -> {
            if(imageBitmap.getWidth()>0){
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

                mDatabase.child("images").child(DNI).setValue(encoded);
                Toast.makeText(getActivity(), "Se guardo la imagen correctamente", Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }

    private void abrirCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            binding.imageView5.setImageBitmap(imageBitmap);
        }
    }


}