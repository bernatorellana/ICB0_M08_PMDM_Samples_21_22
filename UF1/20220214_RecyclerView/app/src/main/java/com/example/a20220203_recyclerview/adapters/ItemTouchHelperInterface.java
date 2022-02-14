package com.example.a20220203_recyclerview.adapters;

public interface ItemTouchHelperInterface {

    void move(int fromIndex, int toIndex);

    void delete(int toDelete);
}
