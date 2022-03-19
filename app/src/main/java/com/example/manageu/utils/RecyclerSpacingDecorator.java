package com.example.manageu.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerSpacingDecorator extends RecyclerView.ItemDecoration {
    private final int space_height;

    public RecyclerSpacingDecorator(int space_height) {
        this.space_height = space_height;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = space_height;
    }
}
