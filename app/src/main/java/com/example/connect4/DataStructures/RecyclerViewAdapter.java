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
        cellData.setPositionInArray(position);

        int columnsInGrid = gameDataViewModel.getGridColumns().getValue();
//        int rowsInGrid = gameDataViewModel.getGridRows().getValue();
        int arraySize = cellDataArrayList.size();

        if (position >= arraySize-columnsInGrid){
            cellData.setIsValid(true);
        }

        if (cellData.getIsValid()) {
            // Enable the ImageView
            holder.itemView.setClickable(true);
        } else {
            // Disable the ImageView for valid cells
            holder.itemView.setClickable(false);
        }

//        for (int i = 0; i < arraySize/rowsInGrid; i++){
//            // Create a new ArrayList for each row
//            ArrayList<CellData> rowsArrayList = new ArrayList<>();
//
//            for (int j = 0; j < arraySize/columnsInGrid; j++){
//                rowsArrayList.add(cellData);
//            }
//            arrayListTracker.add(rowsArrayList);
//        }

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
                for (int i = cellPosition; i < cellDataArrayList.size(); i+=numOfColumns){

                    nextCell = cellDataArrayList.get(i);

                    if (nextCell.getIsValid()){

                        nextCell.setIsValid(false);

                        if(i-numOfColumns >= 0) {

                            cellDataArrayList.get(i-numOfColumns).setIsValid(true);
                        }

                        if (currentTurn == 1){

                            nextCell.setImageId(R.drawable.filled_box);
                            gameDataViewModel.setPlayerTurn(2);
                        }
                        else if (currentTurn == 2){

                            nextCell.setImageId(R.drawable.mouktada_great_circle);
                            gameDataViewModel.setPlayerTurn(1);
                        }

                        notifyItemChanged(i);
                    }
                }
                /* --------------------------------------------------------------------- */

                //holder.cellDataView.setImageResource(R.drawable.filled_box);
            }
        });
    }

    public static void setClickable(View view, boolean clickable){
        if (view != null){
            if (view instanceof ViewGroup){
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++){
                    setClickable(viewGroup.getChildAt(i), false);
                }
            }
            view.setClickable(clickable);
        }
    }



    public void resetCellImage(ArrayList<CellData> newData) {
        cellDataArrayList = newData;


    }

    @Override
    public int getItemCount() {
        return cellDataArrayList.size();
    }
}