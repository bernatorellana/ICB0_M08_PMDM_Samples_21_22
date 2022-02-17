package com.example.a20220203_recyclerview.touch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20220203_recyclerview.adapters.CardAdapter;
import com.example.a20220203_recyclerview.adapters.ItemTouchHelperInterface;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    ItemTouchHelperInterface mAdapter;

    public ItemTouchHelperCallback(ItemTouchHelperInterface pAdapter) {
        mAdapter = pAdapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder source,
                          @NonNull RecyclerView.ViewHolder target) {
        mAdapter.move(source.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.delete(viewHolder.getAdapterPosition());
    }
}
