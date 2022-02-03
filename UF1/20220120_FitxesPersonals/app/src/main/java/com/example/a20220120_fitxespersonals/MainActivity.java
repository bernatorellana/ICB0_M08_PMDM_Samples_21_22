package com.example.a20220120_fitxespersonals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.a20220120_fitxespersonals.model.Pais;
import com.example.a20220120_fitxespersonals.model.Persona;
import com.example.a20220120_fitxespersonals.model.Sexe;
import com.example.a20220120_fitxespersonals.util.TextChanged;
import com.example.a20220120_fitxespersonals.util.TextChangedHelper;

import java.util.List;

public class MainActivity
            extends AppCompatActivity
        implements
            View.OnClickListener,
            TextChanged,
            RadioGroup.OnCheckedChangeListener,
            CompoundButton.OnCheckedChangeListener,
            AdapterView.OnItemSelectedListener {

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
    Button      btnFwd, btnBack;
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
        //-----------------------------------
        btnFwd.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        //-----------------------------------
        edtNom.addTextChangedListener(new TextChangedHelper(this,edtNom));
        edtCognom.addTextChangedListener(new TextChangedHelper(this,edtCognom));
        edtMail.addTextChangedListener(new TextChangedHelper(this,edtMail));
        edtTelefon.addTextChangedListener(new TextChangedHelper(this,edtTelefon));
        //-----------------------------------
        rdgSexe.setOnCheckedChangeListener(this);
        //------------------------------------
        chkActiu.setOnCheckedChangeListener(this);
        //------------------------------------
        spnPais.setOnItemSelectedListener(this);
    }

    private void carregaSpinner() {
        SpinnerAdapter adapter = new ArrayAdapter<Pais>(this,
                android.R.layout.simple_spinner_item,Pais.getPaisos());
        spnPais.setAdapter(adapter);

    }

    private void mostrarPersonaActual() {

        Persona p = getPersonaActual();
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
        btnFwd = findViewById(R.id.btnFwd);
        btnBack = findViewById(R.id.btnBack);
    }

    @Override
    public void onClick(View sender) {
        if (sender.getId() == R.id.btnBack) {
            this.mIndexPersonaActual--;
            if(this.mIndexPersonaActual<0) this.mIndexPersonaActual = Persona.getPersones().size()-1;
        } else {
            this.mIndexPersonaActual++;
            if(this.mIndexPersonaActual>=Persona.getPersones().size()) this.mIndexPersonaActual = 0;
        }
        mostrarPersonaActual();
    }


    @Override
    public void onTextChanged(EditText sender) {
        Persona actual = getPersonaActual();
        String text = sender.getText().toString();
        switch (sender.getId()){
            case R.id.edtNom: actual.setNom(text);break;
            case R.id.edtCognom: actual.setCognom(text);break;
            case R.id.edtMail: actual.setEmail(text);break;
            case R.id.edtTelefon: actual.setTelefon(text);break;
        }
    }

    private Persona getPersonaActual() {
        List<Persona> persones = Persona.getPersones();
        return persones.get(mIndexPersonaActual);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Persona p = getPersonaActual();
        switch (checkedId) {
            case R.id.rbDona: p.setSexe(Sexe.DONA);break;
            case R.id.rbHome: p.setSexe(Sexe.HOME);break;
            case R.id.rbNB: p.setSexe(Sexe.NO_BINARI);break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Persona p = getPersonaActual();
        p.setEsActiu(isChecked);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Persona p = getPersonaActual();
        p.setPais(Pais.getPaisos().get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Persona p = getPersonaActual();
        p.setPais(null);
    }
}