package com.example.fragmentmestredetall.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentmestredetall.ListFragment;
import com.example.fragmentmestredetall.R;
import com.example.fragmentmestredetall.network.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>  {

    //private List<String> persones = Arrays.asList("Paco", "Maite", "Marta", "Joan");
    List<Pokemon> pokemons;
    private OnListClick onListClickListener;

    public interface OnListClick {
        void onItemListClicked(int position);
    }

    public ListAdapter(OnListClick listener, List<Pokemon> pokemons) {
        onListClickListener = listener;
        this.pokemons = pokemons;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.row,
                parent,false);

        //----------------------------------------------------
        ViewHolder vh = new ViewHolder(fila);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txvNom.setText( pokemons.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onListClickListener.onItemListClicked(holder.getAdapterPosition());

            }
        });


    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvNom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvNom = itemView.findViewById(R.id.txvNom);
        }
    }
}
