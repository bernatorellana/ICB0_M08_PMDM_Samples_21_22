package com.example.a20220203_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.a20220203_recyclerview.adapters.CardAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcyCards;
    CardAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //---------------------------------------------
        rcyCards = findViewById(R.id.rcyCards);
        rcyCards.setLayoutManager(new LinearLayoutManager(this));

        //---------------------------------------------
        mCardAdapter = new CardAdapter();
        rcyCards.setAdapter(mCardAdapter);
    }
}