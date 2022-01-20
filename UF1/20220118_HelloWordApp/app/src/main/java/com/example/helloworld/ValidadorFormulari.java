package com.example.helloworld;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class ValidadorFormulari implements TextWatcher {

    private EditText elPutoEditTextQueEsticVigilant;
    private Button btnForm;

    public ValidadorFormulari(EditText elPutoEditTextQueEsticVigilant, Button btnForm){
        this.elPutoEditTextQueEsticVigilant = elPutoEditTextQueEsticVigilant;
        this.btnForm = btnForm;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        boolean campCorrecte = true;
        switch(this.elPutoEditTextQueEsticVigilant.getId()) {
            case R.id.edtMissatge: {

                try {
                    int edat = Integer.parseInt(s.toString());
                } catch (Exception ex) {
                    campCorrecte = false;
                }
                //btnForm.setEnabled(numeroCorrecte);

                break;
            }
            case R.id.edtMissatge2: {
                // verifiquem que tingui com a mínim tres caràcters
                campCorrecte = (s.toString().length()>=3);
            }
        }
        /*int color = Color.TRANSPARENT;
        if(!campCorrecte) {
            color = Color.RED;
        }*/
        int backgroundId = R.drawable.marc;
        if(!campCorrecte) {
            backgroundId = R.drawable.marc_error;
        }
        this.elPutoEditTextQueEsticVigilant.setBackgroundResource(backgroundId);


    }
}
