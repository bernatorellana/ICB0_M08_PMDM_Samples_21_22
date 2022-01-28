package com.example.a20220120_fitxespersonals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.a20220120_fitxespersonals.model.Pais;
import com.example.a20220120_fitxespersonals.model.Persona;
import com.example.a20220120_fitxespersonals.model.Sexe;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    int mIndexPersonaActual=-1;
    //-------------------------------------------
    ImageView   imvFoto;
    EditText    edtNom;
    EditText    edtCognom;
    EditText    edtTelefon;
    EditText    edtMail;
    RadioGroup  rdgSexe;
    RadioButton rbDona;
    RadioButton rbHome;
    RadioButton rbNB;
    Spinner     spnPais;
    CheckBox    chkActiu;
    //-------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------------------
        edtNom = findViewById(R.id.edtNom);
        enllacarUI();
        mIndexPersonaActual=0;
        carregaSpinner();
        mostrarPersonaActual();
    }

    private void carregaSpinner() {
        SpinnerAdapter adapter = new ArrayAdapter<Pais>(this,
                android.R.layout.simple_spinner_item,Pais.getPaisos());
        spnPais.setAdapter(adapter);

    }

    private void mostrarPersonaActual() {

        List<Persona> persones = Persona.getPersones();
        Persona p = persones.get(mIndexPersonaActual);
        edtNom.setText(p.getNom());
        edtCognom.setText(p.getCognom());
        edtTelefon.setText(p.getTelefon());
        edtMail.setText(p.getEmail());
        //----------------------------------------
        Sexe s = p.getSexe();
        int id;
        switch (s){
            case DONA: id=R.id.rbDona;break;
            case HOME: id=R.id.rbHome;break;
            default: id=R.id.rbNB;break;
        }
        rdgSexe.check(id);
        //-----------------------------------------
        chkActiu.setChecked(p.isEsActiu());
        //-----------------------------------------
        // seleccionar el país correcte a l'spinner

        int idxPaisASeleccionar = Pais.getPaisos().indexOf(p.getPais());
        spnPais.setSelection(idxPaisASeleccionar);
        //------------------------------------------
        imvFoto.setImageResource(p.getRecursImatge());
    }

    private void enllacarUI() {
        imvFoto=findViewById(R.id.imvFoto);
        edtNom=findViewById(R.id.edtNom);
        edtCognom=findViewById(R.id.edtCognom);
        edtTelefon=findViewById(R.id.edtTelefon);
        edtMail=findViewById(R.id.edtMail);
        rdgSexe=findViewById(R.id.rdgSexe);
        rbDona=findViewById(R.id.rbDona);
        rbHome=findViewById(R.id.rbHome);
        rbNB=findViewById(R.id.rbNB);
        spnPais=findViewById(R.id.spnPais);
        chkActiu=findViewById(R.id.chkActiu);
    }
}