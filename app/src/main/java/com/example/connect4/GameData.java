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

    public GameData(){
        displayedFragment = new MediatorLiveData<>();
        selectedGameMode = new MediatorLiveData<>();
        selectedBoard = new MutableLiveData<>();
        player1 = new MediatorLiveData<>();
        player2 = new MediatorLiveData<>();

    }

    public LiveData<Integer> getDisplayedFragment() {return displayedFragment;}

    public void setDisplayedFragment(int value) {displayedFragment.setValue(value);}

    public LiveData<Integer> getSelectedGameMode() {return selectedGameMode;}

    public void setSelectedGameMode(int value) {selectedGameMode.setValue(value);}

    public LiveData<Integer> getSelectedBoard() {return selectedBoard;}

    public void setSelectedBoard(int value) {selectedBoard.setValue(value);}
}
