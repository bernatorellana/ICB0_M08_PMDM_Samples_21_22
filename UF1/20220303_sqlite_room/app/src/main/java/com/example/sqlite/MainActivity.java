package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.sqlite.model.Persona;
import com.example.sqlite.modelview.PersonesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webview = findViewById(R.id.web);
        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new WebChromeClient());
        //webview.loadUrl("http://www.google.com");
        //webview.loadData("<html><body><h1>Hola <i>món</i></h1></body></html>",
        //        "text/html","utf-8");
        //webview.loadUrl("file:///android_asset/pagina.html");

        //----------------------------------

//@TODO: arreglar nosequé

        final PersonesViewModel viewModel = new ViewModelProvider(this).get(PersonesViewModel.class);
        viewModel.getPersones().observe(this, new Observer<List<Persona>>() {
            @Override
            public void onChanged(List<Persona> persones) {
                String html="";
                for(Persona p:persones){
                    html += p.toString();
                }
                webview.loadData(html,"text/html","utf-8");
            }
        });




        // ATENCIÓ: La línia següent força la recreació de la BD. Si la
        // BD ja ha estat creada anteriorment en altres execucions del programa
        // no es torna a cridar el onCreate

        // dbh.onUpgrade(db, 1, 1);


    }
}