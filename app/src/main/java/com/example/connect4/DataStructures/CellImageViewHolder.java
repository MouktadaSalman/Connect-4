package com.example.connect4.DataStructures;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connect4.R;

public class CellImageViewHolder extends RecyclerView.ViewHolder {
    public ImageView cellImageView;

    public CellImageViewHolder(@NonNull View itemView) {
        super(itemView);
        cellImageView = itemView.findViewById(R.id.cellImage);
    }
}
