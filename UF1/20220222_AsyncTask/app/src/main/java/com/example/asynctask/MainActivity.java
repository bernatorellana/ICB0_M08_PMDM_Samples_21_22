package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.asynctask.databinding.ActivityMainBinding;
import com.example.asynctask.network.NetworkUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        DownloadAyncTask at = new DownloadAyncTask(this);
        at.executeOnExecutor(
                AsyncTask.THREAD_POOL_EXECUTOR,
                "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=200");
    }

    public void mostraResultat(String json) {
        binding.txvSortida.setText(json);
    }
}