package com.example.a20220203_recyclerview;

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

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView rcyCards;
    CardAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //---------------------------------------------
        rcyCards = findViewById(R.id.rcyCards);
        rcyCards.setLayoutManager(new LinearLayoutManager(this));

        //---------------------------------------------
        mCardAdapter = new CardAdapter();
        rcyCards.setAdapter(mCardAdapter);

        //------------------------------------------------
        //----------------------------------------------
        // Programació el ItemTouchHelper del Recycler per habilitar el swipe i el drag&drop
        ItemTouchHelperCallback callback = new ItemTouchHelperCallback(mCardAdapter);
        ItemTouchHelper ith = new ItemTouchHelper(callback);

        //mCardAdapter.setItemTouchHelper(ith);

        ith.attachToRecyclerView(rcyCards);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView search = (SearchView)item.getActionView();
        search.setQueryHint("Busca pollastre!");
        search.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            /*case R.id.action_add:
                addSomething();
                return true;*/
            case R.id.action_settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mCardAdapter.setFilter(newText);
        return false;
    }
}