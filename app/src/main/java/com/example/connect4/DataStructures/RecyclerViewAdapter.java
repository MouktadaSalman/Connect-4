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


                    if (checkForWin(i, currentTurn)) {
                        Toast.makeText(view.getContext(), "Player " + currentTurn + " wins!", Toast.LENGTH_SHORT).show();
                        gameDataViewModel.setDisplayedFragment(6);
                    }

                    gameDataViewModel.setPlayerTurn(currentTurn == 1 ? 2 : 1);
                    notifyItemChanged(i);
                }
                /* --------------------------------------------------------------------- */
            }
        });
    }


    // Method to check if a player has won
    private boolean checkForWin(int position, int player) {

        return checkDirection(position, player, 1, 0)  // Horizontal (left to right)
                || checkDirection(position, player, 0, 1)  // Vertical (top to bottom)
                || checkDirection(position, player, 1, 1)  // Diagonal (top-left to bottom-right)
                || checkDirection(position, player, 1, -1); // Diagonal (bottom-left to top-right)
    }


    private boolean checkDirection(int position, int player, int deltaX, int deltaY) {
        int count = 1;  // Including the current piece

        count += countConsecutive(position, player, deltaX, deltaY);
        count += countConsecutive(position, player, -deltaX, -deltaY);

        return count >= 4;  // Return true if 4 consecutive pieces found
    }

    // for scenarios to count consecutive pieces in one direction
    private int countConsecutive(int position, int player, int deltaX, int deltaY) {
        int count = 0;

        int row = position / numOfColumns;
        int col = position % numOfColumns;

        while (true) {
            row += deltaY;
            col += deltaX;

            // Check bounds
            if (row < 0 || row >= cellDataArrayList.size() / numOfColumns || col < 0 || col >= numOfColumns) {
                break;
            }

            int nextPosition = row * numOfColumns + col;

            if (cellDataArrayList.get(nextPosition).getImageId() == (player == 1 ? R.drawable.filled_box : R.drawable.mouktada_great_circle)) {
                count++;
            } else {
                // If a different player's piece is found, stop counting in any of the directions
                break;
            }
        }
        return count;
    }

    @Override
    public int getItemCount() {
        return cellDataArrayList.size();
    }
}