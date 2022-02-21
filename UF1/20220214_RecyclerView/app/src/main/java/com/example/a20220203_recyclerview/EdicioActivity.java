package com.example.a20220203_recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Debug;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.a20220203_recyclerview.databinding.ActivityEdicioBinding;
import com.example.a20220203_recyclerview.model.Card;

import java.io.FileNotFoundException;
import java.io.InputStream;

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
    //-----------------
    private Bitmap thumbnail;
    private ActivityEdicioBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEdicioBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //setContentView(R.layout.activity_edicio);
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
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturePhoto();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(indexCarta!=-1) {
            Card c = Card.getCartes().get(indexCarta);
            c.setDesc(edtDesc.getText().toString());
            c.setName(edtNom.getText().toString());
            if(thumbnail!=null) {
                c.setBitmap(thumbnail);
            }
        }
        setResult(Activity.RESULT_OK);
        finish(); // tanca l'Activity i torna a l'anterior
    }

    //------------------------------------------------
    // Gestió de l'Intent de Càmera
    //------------------------------------------------
    static final int REQUEST_IMAGE_CAPTURE = 1;


    public static final int PICK_IMAGE = 1;

    public void capturePhoto(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && data != null) {
            try {
                InputStream inputStream = this.getContentResolver().openInputStream(
                        data.getData());
                thumbnail = BitmapFactory.decodeStream(inputStream);
                    imvFoto.setImageBitmap(thumbnail);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap..
        }
    }

    /*

    public void capturePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Log.d("Aplicacio", "Passo a dins????");
        //---------------------------------------------------------------------
        // Engeguem l'activity de càmera (si és que el mòbil la té, és clar! )
        try {
            Log.d("Aplicacio", "SIIII Passo a dins");
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException ex) {
            Log.e("Aplicacio","Activity no trobada", ex);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            thumbnail = data.getParcelableExtra("data");
            imvFoto.setImageBitmap(thumbnail);
        }
    }*/
}