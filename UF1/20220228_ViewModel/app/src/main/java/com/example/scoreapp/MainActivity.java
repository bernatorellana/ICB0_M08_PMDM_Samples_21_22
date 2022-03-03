package com.example.scoreapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.scoreapp.databinding.ActivityMainBinding;
import com.example.scoreapp.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //---------------------------------------------
        // Recupero el ViewModel de l'Activity
        viewmodel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        //---------------------------------------------
        binding.btnLocalDown.setOnClickListener(this);
        binding.btnLocalUp.setOnClickListener(this);
        binding.btnVisitorDown.setOnClickListener(this);
        binding.btnVisitorUp.setOnClickListener(this);
        //-----------------------------------------------
        // Ens registrem als canvis de les dades del viewmodel per actualitzar la interficie
        viewmodel.getLocalScore().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer valor) {
                binding.txtLocalScore.setText(""+valor);
            }
        });
        viewmodel.getVisitorScore().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer valor) {
                binding.txtVistorScore.setText(""+valor);
            }
        });
        viewmodel.getTime().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                binding.txtTime.setText(""+aDouble);
            }
        });
        //showScore();
        Log.d("ModelView", "OnCreate");
    }
/*
    private void showScore(){
        binding.txtLocalScore.setText(""+ viewmodel.getLocalScore());
        binding.txtVistorScore.setText(""+viewmodel.getVisitorScore());
    }*/

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLocalDown:viewmodel.incLocal(-1); break;
            case R.id.btnLocalUp: viewmodel.incLocal(+1);break;
            case R.id.btnVisitorDown:viewmodel.incVisitor(-1); break;
            case R.id.btnVisitorUp: viewmodel.incVisitor(+1);break;
        }
        //showScore();
    }
}