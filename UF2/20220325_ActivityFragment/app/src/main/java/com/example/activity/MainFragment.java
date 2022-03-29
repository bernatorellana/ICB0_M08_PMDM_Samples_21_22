package com.example.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.activity.databinding.ActivityMainBinding;
import com.example.activity.databinding.FragmentMainBinding;
import com.example.activity.viewmodel.DadesViewModel;


public class MainFragment extends Fragment implements Observer<String> {

    FragmentMainBinding binding;
    DadesViewModel viewModel;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle farcell = getArguments();
        String url = farcell.getString("URL");
        binding.txtMissatge.setText(url);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //----------------------------------------
        ViewModelProvider vmp = new ViewModelProvider(this);
        viewModel = vmp.get(DadesViewModel.class);
        viewModel.getValor().observe(this, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentMainBinding.inflate(getLayoutInflater());
        return binding.getRoot();
     }

    @Override
    public void onChanged(String s) {

    }
}