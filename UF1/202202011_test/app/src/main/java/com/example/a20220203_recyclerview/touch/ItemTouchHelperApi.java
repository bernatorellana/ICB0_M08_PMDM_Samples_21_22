package com.example.a20220203_recyclerview.touch;

public interface ItemTouchHelperApi {

    void onItemDelete(int position);

    void onItemDragged( int positionFrom, int positionTo);
}
