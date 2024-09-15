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
    private int leftTurns;
    private Toast curToast = null;

    /* Constructor for the RecyclerViewAdapter */
    /* -------------------------------------------------------------------------------------------------------------- */
    public RecyclerViewAdapter(ArrayList<CellData> cellDataArrayList, double inHeight, GameData gameDataViewModel) {
        this.cellDataArrayList = cellDataArrayList;
        this.height = inHeight;
        this.gameDataViewModel = gameDataViewModel;
        this.numOfColumns = gameDataViewModel.getGridColumns().getValue();
        player1 = gameDataViewModel.getPlayer1().getValue();
        player2 = gameDataViewModel.getPlayer2().getValue();
        leftTurns = cellDataArrayList.size();

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


                Player currentPlayer = (currentTurn == 1) ? gameDataViewModel.getPlayer1().getValue() : gameDataViewModel.getPlayer2().getValue();

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
                CellData nextCell;
                int j = 1; // Variable to store the next player's turn (default is 1)
                int finalPosition = -1; // Variable to store the final position of the disk.

                // Loop through cells in the column of the selected cell, moving down by 'numOfColumns'
                for (int i = cellPosition; i < cellDataArrayList.size(); i += numOfColumns) {

                    // Get the next cell in the column
                    nextCell = cellDataArrayList.get(i);

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

                        int gameMode = gameDataViewModel.getSelectedGameMode().getValue();

                        /* This is for two-player game mode. */
                        /* ------------------------------------------------------------------------------------ */
                        if (gameMode == 1) {
                            // Check the current player's turn and update the cell's image accordingly
                            if (currentTurn == 1) {
                                leftTurns--;
                                // Player 1's turn: set the image to 'filled_box'
                                nextCell.setImageId(playerColour);
                                j = 2; // Set the next turn to player 2
                            } else if (currentTurn == 2) {
                                leftTurns--;
                                // Player 2's turn: set the image to 'mouktada_great_circle'
                                nextCell.setImageId(playerColour);
                                j = 1; // Set the next turn to player 1
                            }

                            notifyItemChanged(i);
                            // Update the game data model with the new player turn
                            gameDataViewModel.setPlayerTurn(j);
                        }
                        /* ------------------------------------------------------------------------------------ */


                        /* This is single player game mode. */
                        /* ------------------------------------------------------------------------------------ */
                        // When player vs AI, there is no such thing as turns.
                        // AI immediately places circle after player does.
                        if (gameMode == 2) {
                            leftTurns-=2;
                            // Player makes their move (this logic should be placed before the AI move)
                            nextCell.setImageId(R.drawable.filled_box);

                            // AI Move - Random column selection
                            Random random = new Random();
                            int randomColumn = random.nextInt(numOfColumns);

                            // Start from the bottom row in the selected column and move up
                            for (int row = (cellDataArrayList.size() / numOfColumns) - 1; row >= 0; row--) {
                                int position = row * numOfColumns + randomColumn;  // Get the position in the array

                                // Check if the cell is empty
                                if (cellDataArrayList.get(position).getImageId() == R.drawable.empty_cell) {
                                    // Place the AI's piece
                                    cellDataArrayList.get(position).setImageId(R.drawable.mouktada_great_circle);

                                    // Mark the cell as no longer valid (cannot place more pieces here)
                                    cellDataArrayList.get(position).setIsValid(false);

                                    // Mark the cell above as valid if it exists
                                    if (row > 0) {
                                        cellDataArrayList.get((row - 1) * numOfColumns + randomColumn).setIsValid(true);
                                    }

                                    // Log the position for testing purposes
                                    Log.d("Testing", "AI placed in position: " + position);

                                    // Notify the adapter to refresh the UI for this position
                                    notifyItemChanged(position);

                                    break;  // AI has made its move, exit the loop
                                }
                            }
                        }
                        /* ------------------------------------------------------------------------------------ */
                    }

                    if (finalPosition != -1) {
                        // Notify the adapter that the item has changed so the view can be updated
                        if (checkForWin(i, currentTurn)) {

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
                        gameDataViewModel.setPlayerTurn(currentTurn == 1 ? 2 : 1);
                        notifyItemChanged(i);
                    }
                }

                if (curToast != null) {
                    curToast.cancel();
                }

                curToast = Toast.makeText(view.getContext(), leftTurns + " Turns Left!", Toast.LENGTH_SHORT);
                curToast.show();
            }

            /* --------------------------------------------------------------------- */
        });
    }

            // Method to check if a player has won
            private boolean checkForWin(int position, int player) {
                int playerColor = (player == 1) ? R.drawable.filled_box : R.drawable.mouktada_great_circle;
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
