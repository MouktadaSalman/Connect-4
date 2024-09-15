package com.example.connect4.DataStructures;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import com.example.connect4.GameData;
import com.example.connect4.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<CellDataViewHolder> {
    private ArrayList<CellData> cellDataArrayList;
    private double height;
    private GameData gameDataViewModel;

    public RecyclerViewAdapter(ArrayList<CellData> cellDataArrayList, double inHeight, GameData gameDataViewModel) {
        this.cellDataArrayList = cellDataArrayList;
        this.height = inHeight;
        this.gameDataViewModel = gameDataViewModel;
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

        holder.cellDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentTurn = gameDataViewModel.getPlayerTurn().getValue();

                /* Game logic is housed here. */
                /* --------------------------------------------------------------------- */
                // This is a block of code responsible for the turns.
                if (currentTurn == 1) {
                    /* This block is responsible for 'dropping' the balls to the bottom-most position. */
                    /* ------------------------------------------------------------------------ */
                    if (cellData.getRowPosition() < 7) {
                        for (int r = 7; r >= 0; r--) {
                            if (cellData.getRowPosition() == r)
                            if (cellData.getImageId() == R.drawable.empty_cell) {
                                holder.cellDataView.setImageResource(R.drawable.filled_box);
                                gameDataViewModel.setPlayerTurn(2);
                            }
                        }
                    }
                    /* ------------------------------------------------------------------------ */
                } else if (currentTurn == 2) {
                    if (cellData.getRowPosition() < 7) {
                        for (int i = 7; i >= 0; i--) {
                            if (cellData.getImageId() == R.drawable.empty_cell) {
                                holder.cellDataView.setImageResource(R.drawable.logomc);
                                gameDataViewModel.setPlayerTurn(1);
                            }
                        }
                    }
                }
                /* --------------------------------------------------------------------- */

                //holder.cellDataView.setImageResource(R.drawable.filled_box);
                Toast.makeText(view.getContext(), "Cell clicked:  " + cellData.getRowPosition() + ", " + cellData.getColPosition(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void resetCellImage(ArrayList<CellData> newData) {
        cellDataArrayList = newData;


    }

    @Override
    public int getItemCount() {
        return cellDataArrayList.size();
    }
}
