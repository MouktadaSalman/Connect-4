package com.example.connect4.DataStructures;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connect4.R;

public class CellDataViewHolder extends RecyclerView.ViewHolder {
    public ImageView cellDataView;

    public CellDataViewHolder(@NonNull View itemView) {
        super(itemView);
        cellDataView = itemView.findViewById(R.id.cell);
    }
}
