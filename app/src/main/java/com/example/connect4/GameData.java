package com.example.connect4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.connect4.DataStructures.CellData;
import com.example.connect4.PlayerOperations.Player;

public class GameData extends ViewModel {

    private final MutableLiveData<Integer> displayedFragment;
    private final MutableLiveData<Integer> selectedGameMode;
    private final MutableLiveData<Integer> selectedBoard;
    private final MutableLiveData<Player> player1;
    private final MutableLiveData<Player> player2;
    private final MutableLiveData<Integer> selectedPlayer;

    public GameData(){
        displayedFragment = new MediatorLiveData<>();
        selectedGameMode = new MediatorLiveData<>();
        selectedBoard = new MutableLiveData<>();
        player1 = new MediatorLiveData<>();
        player2 = new MediatorLiveData<>();
        selectedPlayer = new MutableLiveData<>();

        //Initially game begins with game mode selected as PvAI
        setSelectedGameMode(2);

        //Initially no player selected
        setSelectedPlayer(0);

        // Initialise basic player profiles
        setPlayer1(new Player("Player#1", R.drawable.profile_button));
        setPlayer2(new Player("Player#2", R.drawable.profile_button));
    }

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

    public LiveData<Integer> getSelectedPlayer() {return selectedPlayer;}

    public void setSelectedPlayer(int value) {selectedPlayer.setValue(value);}
}

