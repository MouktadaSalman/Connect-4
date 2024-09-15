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

    private final MutableLiveData<Integer> player1Avatar;
    private final MutableLiveData<Integer> player2Avatar;

    private final MutableLiveData<String> player1Name;
    private final MutableLiveData<String> player2Name;

    public GameData(){
        displayedFragment = new MediatorLiveData<>();
        selectedGameMode = new MediatorLiveData<>();
        selectedBoard = new MutableLiveData<>();
        player1 = new MediatorLiveData<>();
        player2 = new MediatorLiveData<>();
        selectedPlayer = new MutableLiveData<>();

        //Initially no player selected
        setSelectedPlayer(0);

        // Initialise basic player profiles
        setPlayer1(new Player("Player#1", R.drawable.profile_button));
        setPlayer2(new Player("Player#2", R.drawable.profile_button));

        player1Avatar = new MutableLiveData<>();
        player2Avatar = new MutableLiveData<>();


        player1Name = new MutableLiveData<>();
        player2Name = new MutableLiveData<>();

        // Default avatar and name settings
        player1Avatar.setValue(R.drawable.avatar1);
        player2Avatar.setValue(R.drawable.avatar1);
        player1Name.setValue("Player 1");
        player2Name.setValue("Player 2");

    }

    public LiveData<Integer> getDisplayedFragment() {return displayedFragment;}

    public void setDisplayedFragment(int value) {displayedFragment.setValue(value);}

    public LiveData<Integer> getSelectedGameMode() {return selectedGameMode;}

    public void setSelectedGameMode(int value) {selectedGameMode.setValue(value);}

    public LiveData<Integer> getSelectedBoard() {return selectedBoard;}

    public void setSelectedBoard(int value) {selectedBoard.setValue(value);}

    public LiveData<Integer> getPlayer1Avatar() {return player1Avatar;}

    public void setPlayer1Avatar(int avatar) {player1Avatar.setValue(avatar);}

    public LiveData<Integer> getPlayer2Avatar() {return player2Avatar;}

    public void setPlayer2Avatar(int avatar) {player2Avatar.setValue(avatar);}

    public LiveData<String> getPlayer1Name() {return player1Name;}

    public void setPlayer1Name(String name) {player1Name.setValue(name);}

    public LiveData<String> getPlayer2Name() {return player2Name;}

    public void setPlayer2Name(String name) {player2Name.setValue(name);}

    public LiveData<Player> getPlayer1() {return player1;}

    public void setPlayer1(Player player) {player1.setValue(player);}

    public LiveData<Player> getPlayer2() {return player2;}

    public void setPlayer2(Player player) {player2.setValue(player);}

    public LiveData<Integer> getSelectedPlayer() {return selectedPlayer;}

    public void setSelectedPlayer(int value) {selectedPlayer.setValue(value);}
}

