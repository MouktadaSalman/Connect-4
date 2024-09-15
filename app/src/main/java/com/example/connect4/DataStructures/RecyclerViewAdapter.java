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
import java.util.Random;

import com.example.connect4.GameData;
import com.example.connect4.PlayerOperations.Player;
import com.example.connect4.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<CellDataViewHolder> {
    private ArrayList<CellData> cellDataArrayList;
    private double height;
    private GameData gameDataViewModel;
    private int numOfColumns;
    Player player1;
    Player player2;

    /* Constructor for the RecyclerViewAdapter */
    /* -------------------------------------------------------------------------------------------------------------- */
    public RecyclerViewAdapter(ArrayList<CellData> cellDataArrayList, double inHeight, GameData gameDataViewModel) {
        this.cellDataArrayList = cellDataArrayList;
        this.height = inHeight;
        this.gameDataViewModel = gameDataViewModel;
        this.numOfColumns = gameDataViewModel.getGridColumns().getValue();
        player1 = gameDataViewModel.getPlayer1().getValue();
        player2 = gameDataViewModel.getPlayer2().getValue();

        /* Each cellData is intialised to a valid value. */
        /* --------------------------------------- */
        CellData cellData;
        int arraySize = cellDataArrayList.size();
        for(int i = 0; i < arraySize; i++){
            cellData = cellDataArrayList.get(i);
            if (i >= arraySize-numOfColumns){
                cellData.setIsValid(true);
            }
        }
        /* --------------------------------------- */
    }
    /* -------------------------------------------------------------------------------------------------------------- */

    @NonNull
    @Override
    public CellDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_item_layout, parent, false);
        return new CellDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CellDataViewHolder holder, int position) {
        // Retrieve the cell data for the current position in the list
        CellData cellData = cellDataArrayList.get(position);

        // Set the image resource of the cell using the image ID from the cell data
        holder.cellDataView.setImageResource(cellData.getImageId());

        // Dynamically set the row height for each cell
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = (int) height;  // Set the row height based on the 'height' variable
        holder.itemView.setLayoutParams(layoutParams);

        // Set an OnClickListener for the cell view
        holder.cellDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the current player's turn from the game data model
                int currentTurn = gameDataViewModel.getPlayerTurn().getValue();

                Player currentPlayer = (currentTurn == 1) ? player1 : player2;

                // Added: Check if the player is valid (non-null)
                if (currentPlayer == null) {
                    return;
                }

                // Added: Retrieve player's selected disk color
                int playerColour = currentPlayer.getColourID();

                /* Game logic is housed here. */
                /* --------------------------------------------------------------------- */
                // Retrieve the clicked cell's position in the list
                int cellPosition = holder.getBindingAdapterPosition();
                int finalPosition = -1; // Variable to store the final position of the disk.

                // Loop through cells in the column of the selected cell, moving down by 'numOfColumns'
                for (int i = cellPosition; i < cellDataArrayList.size(); i += numOfColumns) {
                    // Get the next cell in the column
                    CellData nextCell = cellDataArrayList.get(i);

                    // Check if the cell is valid (can be modified)
                    if (nextCell.getIsValid()) {
                        finalPosition = i;
                        nextCell.setIsValid(false);

                        // If the cell above exists, mark it as valid (can now be selected)
                        if (i - numOfColumns >= 0) {
                            cellDataArrayList.get(i - numOfColumns).setIsValid(true);
                        }

                        nextCell.setImageId(playerColour);
                        notifyItemChanged(i);
                        break;  // Exit the loop after placing the piece
                    }
                }

                if (finalPosition != -1) {
                    // Notify the adapter that the item has changed so the view can be updated
                    if (checkForWin(finalPosition, currentTurn)) {
                        Toast.makeText(view.getContext(), "Player " + currentTurn + " wins!", Toast.LENGTH_SHORT).show();

                        if (currentTurn == 1) {
                            player1.addWin();
                            player2.addLoss();
                            gameDataViewModel.setWinner(player1.getPlayerName());
                        } else {
                            player2.addWin();
                            player1.addLoss();
                            gameDataViewModel.setWinner(player2.getPlayerName());
                        }
                        gameDataViewModel.setDisplayedFragment(6);
                        return;
                    }

                    int nextTurn = (currentTurn == 1) ? 2 : 1;
                    gameDataViewModel.setPlayerTurn(nextTurn);

                    int gameMode = gameDataViewModel.getSelectedGameMode().getValue();
                    /* This is single player game mode. */
                    /* ------------------------------------------------------------------------------------ */
                    // When player vs AI, there is no such thing as turns.
                    // AI immediately places circle after player does.
                    if (gameMode == 2 && nextTurn == 2) {
                        makeAIMove();
                    }
                    /* ------------------------------------------------------------------------------------ */
                }
            }
            /* --------------------------------------------------------------------- */
        });
    }

    private void makeAIMove() {
        // AI Move - Random column selection
        Random random = new Random();
        int randomColumn;
        int finalPosition = -1;

        do {
            randomColumn = random.nextInt(numOfColumns);
            // Start from the bottom row in the selected column and move up
            for (int row = (cellDataArrayList.size() / numOfColumns) - 1; row >= 0; row--) {
                int position = row * numOfColumns + randomColumn;  // Get the position in the array

                // Check if the cell is empty
                if (cellDataArrayList.get(position).getImageId() == R.drawable.empty_cell) {
                    finalPosition = position;
                    break;
                }
            }
        } while (finalPosition == -1);

        CellData aiCell = cellDataArrayList.get(finalPosition);
        aiCell.setImageId(player2.getColourID());
        aiCell.setIsValid(false);
        if (finalPosition - numOfColumns >= 0) {
            cellDataArrayList.get(finalPosition - numOfColumns).setIsValid(true);
        }

        // Log the position for testing purposes
        Log.d("Testing", "AI placed in position: " + finalPosition);

        // Notify the adapter to refresh the UI for this position
        notifyItemChanged(finalPosition);

        if (checkForWin(finalPosition, 2)) {
            Toast.makeText(itemView.getContext(), "AI wins!", Toast.LENGTH_SHORT).show();
            player2.addWin();
            player1.addLoss();
            gameDataViewModel.setWinner("AI");
            gameDataViewModel.setDisplayedFragment(6);
        } else {
            gameDataViewModel.setPlayerTurn(1);
        }
    }

    // Method to check if a player has won
    private boolean checkForWin(int position, int player) {
        int playerColor = (player == 1) ? player1.getColourID() : player2.getColourID();
        return checkDirection(position, playerColor, 1, 0)  // Horizontal
                || checkDirection(position, playerColor, 0, 1)  // Vertical
                || checkDirection(position, playerColor, 1, 1)  // Diagonal (top-left to bottom-right)
                || checkDirection(position, playerColor, 1, -1); // Diagonal (bottom-left to top-right)
    }

    private boolean checkDirection(int position, int playerColor, int deltaX, int deltaY) {
        int count = 1;  // Start with 1 to include the current piece
        int row = position / numOfColumns;
        int col = position % numOfColumns;

        // Check in positive direction
        count += countConsecutive(row, col, playerColor, deltaX, deltaY);

        // If we haven't found 4 in a row yet, check in negative direction
        if (count < 4) {
            count += countConsecutive(row, col, playerColor, -deltaX, -deltaY);
        }

        return count >= 4;
    }

    private int countConsecutive(int startRow, int startCol, int playerColor, int deltaX, int deltaY) {
        int count = 0;
        int row = startRow + deltaY;
        int col = startCol + deltaX;

        while (row >= 0 && row < cellDataArrayList.size() / numOfColumns &&
                col >= 0 && col < numOfColumns) {
            int position = row * numOfColumns + col;

            if (cellDataArrayList.get(position).getImageId() == playerColor) {
                count++;
                if (count == 3) return count; // We've found 3 more in this direction, which is enough for a win
            } else {
                break; // Stop if we find a different color
            }

            row += deltaY;
            col += deltaX;
        }

        return count;
    }

    @Override
    public int getItemCount() {
        return cellDataArrayList.size();
    }
}