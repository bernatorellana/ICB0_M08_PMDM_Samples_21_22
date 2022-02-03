package com.example.a20220120_fitxespersonals.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class TextChangedHelper  implements TextWatcher {

    TextChanged tc;
    EditText ed;
    public TextChangedHelper(TextChanged pM, EditText pEd){
        tc = pM;
        ed = pEd;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        tc.onTextChanged(ed);
    }


    @Override
    public void afterTextChanged(Editable s) {

    }
}
