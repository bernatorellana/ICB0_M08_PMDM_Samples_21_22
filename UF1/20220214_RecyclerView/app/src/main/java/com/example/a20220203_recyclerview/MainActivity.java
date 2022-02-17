package com.example.a20220203_recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.a20220203_recyclerview.adapters.CardAdapter;
import com.example.a20220203_recyclerview.touch.ItemTouchHelperCallback;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

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

        //----------------------------------------------
        // Assignem la toolbar a l'activity ! :-)
        Toolbar tb = findViewById(R.id.my_toolbar);
        setSupportActionBar(tb);
        //----------------------------------------------
        // Preparació pel drag&drop i el swipe
        ItemTouchHelperCallback callback = new ItemTouchHelperCallback(mCardAdapter);
        ItemTouchHelper ith = new ItemTouchHelper(callback);
        ith.attachToRecyclerView(rcyCards); // activem el touch helper al recycler
        //-----------------------------------------------
        // Configuració del Universal Image Loader
        configuraUniversalImageLoader();
    }

    private void configuraUniversalImageLoader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loading)
                //.imageScaleType(ImageScaleType.EXACTLY)
                .build();

        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
			.defaultDisplayImageOptions(options)
            .memoryCacheSize(16000)
            .threadPoolSize(6)
            .diskCacheSize(20000)
			.build();
        ImageLoader.getInstance().init(config);
    }

    //--------------------------------------------------------------------
    // SECCIÓ MENÚ
    // Indicar que volem carregar el menú dis de l'ActionBar de l'Activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        MenuItem itemDeCerca = menu.findItem(R.id.mniSearch);
        SearchView view =  (SearchView) itemDeCerca.getActionView();
        view.setOnQueryTextListener(this);

        /*MenuItem item = menu.findItem(R.id.action_search);
        SearchView search = (SearchView)item.getActionView();
        search.setQueryHint("Busca pollastre!");
        search.setOnQueryTextListener(this);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mniDelete:{
                mCardAdapter.deleteCard();
            }break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String textCercat) {
        mCardAdapter.setFiltre(textCercat);
        return true;
    }


    // FI SECCIÓ MENÚ
    //--------------------------------------------------------------------

}