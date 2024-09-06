package com.example.connect4;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.connect4.PlayerOperations.Player;

public class GameData extends ViewModel {

    public MutableLiveData<Integer> displayedFragment;
    public MutableLiveData<Integer> selectedGameMode;
    public MutableLiveData<Player> player1;
    public MutableLiveData<Player> player2;

    public GameData(){
        displayedFragment = new MediatorLiveData<>();
        selectedGameMode = new MediatorLiveData<>();
        player1 = new MediatorLiveData<>();
        player2 = new MediatorLiveData<>();
    }

    public int getDisplayedFragment() {return displayedFragment.getValue();}

    public void setDisplayedFragment(int value) {displayedFragment.setValue(value);}

    public int getSelectedGameMode() {return selectedGameMode.getValue();}

    public void setSelectedGameMode(int value) {selectedGameMode.setValue(value);}
}
