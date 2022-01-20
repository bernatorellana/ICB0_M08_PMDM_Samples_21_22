package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txvMissatge;
    EditText edtMissatge;
    EditText edtMissatge2;
    Button btnGo;

    //---------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         String missatge = getResources().getString(R.string.go);
         int color = getResources().getColor(R.color.rosa_sang);

         // Anem a buscar els elements de la UI a l'xml i els associem a les referències
        txvMissatge = findViewById(R.id.txvMissatge);
        edtMissatge = findViewById(R.id.edtMissatge);
        edtMissatge2 = findViewById(R.id.edtMissatge2);
        btnGo =      findViewById(R.id.btnGo);
        btnGo.setOnClickListener(this);
        // desactivem el botó
        btnGo.setEnabled(false);

        // Programar esdeveniment de canvi a edtMissatge

        edtMissatge.addTextChangedListener(new ValidadorFormulari(edtMissatge, btnGo));
        edtMissatge2.addTextChangedListener(new ValidadorFormulari(edtMissatge2, btnGo));

        /*
        // Programar esdeveniment Click del botó
        // usant una classe anònima
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // això s'executa en el clic del botó
                CharSequence missatge = edtMissatge.getText();
                String m = missatge.toString();
                try {
                    int edat = Integer.parseInt(m);
                    txvMissatge.setText(edat+"");
                } catch (Exception ex){
                    txvMissatge.setText("Edat errònia");
                }

            }
        });*/

    }

    @Override
    public void onClick(View sender) {
        // això s'executa en el clic del botó

        CharSequence missatge = edtMissatge.getText();
        String m = missatge.toString();
        try {
            int edat = Integer.parseInt(m);
            txvMissatge.setText(edat+"");
        } catch (Exception ex){
            txvMissatge.setText("Edat errònia");
        }
    }

}