package com.example.a20220203_recyclerview.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20220203_recyclerview.R;
import com.example.a20220203_recyclerview.model.Card;
import com.example.a20220203_recyclerview.model.Rarity;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<Card> mCardList;
    private int mPosSeleccionada = -1;

    public CardAdapter(){
        mCardList = Card.getCartes();
    }



    @Override
    public int getItemViewType(int position) {
        return mCardList.get(position).getRarity()== Rarity.EPIC?1:0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int idLayout = (viewType==0)?R.layout.fila:R.layout.fila_epic;
        View fila = LayoutInflater.from(parent.getContext()).inflate(
                idLayout,
                parent,false);

        //----------------------------------------------------
        ViewHolder vh = new ViewHolder(fila);

        //----------------------------------------------------
        // Tenim accés a la fila i puc programar esdeveniment
        fila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos =  vh.getAdapterPosition();
                if(pos!=mPosSeleccionada) {
                    if(mPosSeleccionada!=-1) {
                        mCardList.get(mPosSeleccionada).setSelected(false);
                        notifyItemChanged(mPosSeleccionada);
                    }
                    mCardList.get(pos).setSelected(true);
                    mPosSeleccionada = pos;
                    notifyItemChanged(mPosSeleccionada);
                } else {
                    mCardList.get(pos).setSelected(false);
                    mPosSeleccionada = -1;
                    notifyItemChanged(pos);
                }

            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card c = mCardList.get(position);
        holder.txvNom.setText(c.getName());
        holder.txvDesc.setText(c.getDesc());
        holder.imvPhoto.setImageResource(c.getDrawable());
        holder.txvElixirCost.setText(""+c.getElixirCost());
        if(c.isSelected()) {
            holder.itemView.setBackgroundColor(Color.RED);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }
    //---------------------------------------------------------
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txvNom;
        public ImageView imvPhoto ;
        public TextView txvDesc;
        public TextView txvElixirCost;

        public ViewHolder(@NonNull View fila) {
            super(fila);
            txvNom = fila.findViewById(R.id.txvNom);
            imvPhoto = fila.findViewById(R.id.imvPhoto);
            txvDesc= fila.findViewById(R.id.txvDesc);
            txvElixirCost= fila.findViewById(R.id.txvElixirCost);
        }
    }
}
