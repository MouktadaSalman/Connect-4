package com.example.connect4;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameData extends ViewModel {

    public MutableLiveData<Integer> displayedFragment;
    public MutableLiveData<Integer> selectedGameMode;

    public GameData(){
        displayedFragment = new MediatorLiveData<>();
        selectedGameMode = new MediatorLiveData<>();
    }

    public int getDisplayedFragment() {return displayedFragment.getValue();}

    public void setDisplayedFragment(int value) {displayedFragment.setValue(value);}

    public int getSelectedGameMode() {return selectedGameMode.getValue();}

    public void setSelectedGameMode(int value) {selectedGameMode.setValue(value);}
}
