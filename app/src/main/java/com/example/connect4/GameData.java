package com.example.connect4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.connect4.DataStructures.CellData;
import com.example.connect4.PlayerOperations.Player;

import java.sql.Array;
import java.util.ArrayList;

public class GameData extends ViewModel {
    /* Class Fields:
     * recyclerDataArrayListVM -> Used to store recyclerDataArrayList
     * displayedFragment -> Integer used to signify which fragments to load.
     * selectedGameMode -> Game mode selected.
     * selectedBoard -> Size of the board selected.
     * player1 -> Used to store player 1 data.
     * player2 -> Used to store player 2 data.
     * aiPlayer -> Used to store the ai player.
     * selectedPlayer -> Used to check which player is selected.
     * gridColumns -> Store the number of columns in a grid.
     * playerTurn -> Variable used to store which player's turn it is.
     */
    private final MutableLiveData<ArrayList<CellData>> recyclerDataArrayListVM;
    private final MutableLiveData<Integer> displayedFragment;
    private final MutableLiveData<Integer> selectedGameMode;
    private final MutableLiveData<Integer> selectedBoard;
    private final MutableLiveData<Player> player1;
    private final MutableLiveData<Player> player2;
    private final MutableLiveData<Player> aiPlayer;
    private final MutableLiveData<Integer> selectedPlayer;
    private final MutableLiveData<Integer> gridColumns;
    private final MutableLiveData<Integer> playerTurn;
    private final MutableLiveData<String> winner;

    /* Default constructor */
    /* ------------------------------------------------------------------------- */
    public GameData(){
        recyclerDataArrayListVM = new MutableLiveData<>();
        displayedFragment = new MediatorLiveData<>();
        selectedGameMode = new MediatorLiveData<>();
        selectedBoard = new MutableLiveData<>();
        player1 = new MediatorLiveData<>();
        player2 = new MediatorLiveData<>();
        aiPlayer = new MediatorLiveData<>();
        selectedPlayer = new MutableLiveData<>();

        //Initially game begins with game mode selected as PvAI
        setSelectedGameMode(2);

        //Initially no player selected
        setSelectedPlayer(0);

        // Initialise basic player profiles + Ai
        setPlayer1(new Player("Player#1", R.drawable.profile_button, R.drawable.mouktada_great_circle));
        setPlayer2(new Player("Player#2", R.drawable.profile_button, R.drawable.reddisk));
        aiPlayer.setValue(new Player("AI", R.drawable.aiavatar, R.drawable.reddisk));

        playerTurn = new MutableLiveData<>();
        playerTurn.setValue(1);

        gridColumns = new MutableLiveData<>();
        winner = new MutableLiveData<>();
    }
    /* ------------------------------------------------------------------------- */

    /* Accessors and Mutators are stored here. */
    /* --------------------------------------------------------------------------------------------------------------- */
    public LiveData<ArrayList<CellData>> getRecyclerDataArrayListVM() {return recyclerDataArrayListVM;}

    public void setRecyclerDataArrayListVM(ArrayList<CellData> value) {recyclerDataArrayListVM.setValue(value);}

    public LiveData<Integer> getDisplayedFragment() {return displayedFragment;}

    public void setDisplayedFragment(int value) {displayedFragment.setValue(value);}

    public LiveData<Integer> getSelectedGameMode() {return selectedGameMode;}

    public void setSelectedGameMode(int value) {selectedGameMode.setValue(value);}

    public LiveData<Integer> getSelectedBoard() {return selectedBoard;}

    public void setSelectedBoard(int value) {selectedBoard.setValue(value);}

    public LiveData<Player> getPlayer1() {return player1;}

    public void setPlayer1(Player player) {player1.setValue(player);}

    public LiveData<Player> getPlayer2() {return player2;}

    public void setPlayer2(Player player) {player2.setValue(player);}

    public LiveData<Player> getAiPlayer() {return aiPlayer;}

    public LiveData<Integer> getSelectedPlayer() {return selectedPlayer;}

    public void setSelectedPlayer(int value) {selectedPlayer.setValue(value);}

    public LiveData<Integer> getPlayerTurn() {return playerTurn;}

    public void setPlayerTurn(int turn) {playerTurn.setValue(turn);}

    public MutableLiveData<Integer> getGridColumns() {return gridColumns;}

    public void setGridColumns(int gridColumns) {this.gridColumns.setValue(gridColumns);}

    public MutableLiveData<String> getWinner() {return winner;}

    public void setWinner(String winner) {this.winner.setValue(winner);}
    /* --------------------------------------------------------------------------------------------------------------- */

}

