package com.example.connect4.DataStructures;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.example.connect4.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<CellDataViewHolder> {
    private ArrayList<CellData> cellDataArrayList;
    //private Context context;
    private double height;

    public RecyclerViewAdapter(ArrayList<CellData> cellDataArrayList, double inHeight) {
        this.cellDataArrayList = cellDataArrayList;
        this.height = inHeight;
        // this.context = context;
    }

    @NonNull
    @Override
    public CellDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_item_layout, parent, false);
        return new CellDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CellDataViewHolder holder, int position) {
        CellData cellData = cellDataArrayList.get(position);
        holder.cellDataView.setImageResource(cellData.getImageId());
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = (int) height;  // Set the row height dynamically
        holder.itemView.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return cellDataArrayList.size();
    }
}
