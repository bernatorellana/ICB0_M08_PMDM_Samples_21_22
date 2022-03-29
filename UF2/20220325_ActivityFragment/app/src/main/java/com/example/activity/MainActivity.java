package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.View;

import com.example.activity.databinding.ActivityMainBinding;
import com.example.activity.viewmodel.DadesViewModel;

public class MainActivity extends AppCompatActivity implements Observer<String> {

    ActivityMainBinding binding;
    DadesViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //----------------------------------------
        ViewModelProvider vmp = new ViewModelProvider(this);
        viewModel = vmp.get(DadesViewModel.class);
        viewModel.getValor().observe(this, this);

    }



    @Override
    public void onChanged(String s) {

    }
}