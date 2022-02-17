package com.example.a20220203_recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.a20220203_recyclerview.model.Card;

public class EdicioActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PARAM_CARD = "card";
    public static final String PARAM_IDX = "idx";
    EditText edtNom;
    EditText edtDesc;
    Spinner spnElixir;
    ImageView imvFoto;
    Button btnPhoto;
    Button  btnSave;
    int indexCarta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicio);
        //-------------------------------------
        // Sé que m'estan invocant des d'una altra activity
        // Prenem els paràmetres de dins de l'intent
        Intent i = getIntent();
        Card c = i.getParcelableExtra(PARAM_CARD);
        indexCarta = i.getIntExtra(PARAM_IDX,-1);
        //-------------------------
        edtNom = findViewById(R.id.edtNom);
        edtDesc = findViewById(R.id.edtDesc);
        spnElixir = findViewById(R.id.spnElixir);
        imvFoto = findViewById(R.id.imvFoto);
        btnPhoto = findViewById(R.id.btnPhoto);
        btnSave = findViewById(R.id.btnSave);
        //--------------------------------------
        edtNom.setText(c.getName());
        edtDesc.setText(c.getDesc());
        //-------------------------------------
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(indexCarta!=-1) {
            Card c = Card.getCartes().get(indexCarta);
            c.setDesc(edtDesc.getText().toString());
            c.setName(edtNom.getText().toString());
        }
        setResult(Activity.RESULT_OK);
        finish(); // tanca l'Activity i torna a l'anterior
    }
}