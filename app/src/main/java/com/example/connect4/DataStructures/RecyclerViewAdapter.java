package com.example.connect4.DataStructures;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.example.connect4.GameData;
import com.example.connect4.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<CellDataViewHolder> {
    private ArrayList<CellData> cellDataArrayList;
    private double height;
    private GameData gameDataViewModel;
    private int numOfColumns;

    public RecyclerViewAdapter(ArrayList<CellData> cellDataArrayList, double inHeight, GameData gameDataViewModel) {
        this.cellDataArrayList = cellDataArrayList;
        this.height = inHeight;
        this.gameDataViewModel = gameDataViewModel;
        this.numOfColumns = gameDataViewModel.getGridColumns().getValue();

        CellData cellData;
        int arraySize = cellDataArrayList.size();
        for(int i = 0; i < arraySize; i++){
            cellData = cellDataArrayList.get(i);
            if (i >= arraySize-numOfColumns){
                cellData.setIsValid(true);
            }
        }
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
                int cellPosition = holder.getBindingAdapterPosition();
                CellData nextCell;
                int j = 1;
                for (int i = cellPosition; i < cellDataArrayList.size(); i+=numOfColumns){

                    nextCell = cellDataArrayList.get(i);

                    if (nextCell.getIsValid()){

                        nextCell.setIsValid(false);

                        if(i-numOfColumns >= 0) {

                            cellDataArrayList.get(i-numOfColumns).setIsValid(true);
                        }

                        Toast.makeText(view.getContext(), "Cell position: " + i, Toast.LENGTH_SHORT).show();
                        if (currentTurn == 1){

                            nextCell.setImageId(R.drawable.filled_box);
                            j = 2; // to set the player turn to 2 and assign to data view model after checks
                        }
                        else if (currentTurn == 2){

                            j = 1; // to set the player turn to 1 and assign to data view model after checks
                            nextCell.setImageId(R.drawable.mouktada_great_circle);
                        }
                        gameDataViewModel.setPlayerTurn(j);
                    }
                    notifyItemChanged(i);
                }
                /* --------------------------------------------------------------------- */
            }
        });
    }

    @Override
    public int getItemCount() {
        return cellDataArrayList.size();
    }
}