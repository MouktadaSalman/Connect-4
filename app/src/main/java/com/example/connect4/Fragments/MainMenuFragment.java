package com.example.connect4.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.connect4.GameData;
import com.example.connect4.R;
/*--------------------------------------------------------*
 * Module: MainMenuFragment                               *
 * Description: The module responsible for handling the   *
 *              functions of the main menu fragment       *
 * Author: Jauhar                                         *
 * ID: 21494299                                           *
 *--------------------------------------------------------*/
public class MainMenuFragment extends Fragment {

    private GameData gameDataViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        // Get the game mode selected
        LiveData<Integer> data = gameDataViewModel.getSelectedBoard();
        LiveData<Integer> dataB = gameDataViewModel.getSelectedBoard();

        // Initialise
        int gameMode = 0;
        int board = 0;

        //Null check
        if(data.getValue() != null){
            gameMode = data.getValue();
        }
        if(dataB.getValue() != null){
            board = dataB.getValue();
        }

        Button playButton = view.findViewById(R.id.MainMenuPlayButton);
        ImageButton settingsButton = view.findViewById(R.id.MainMenuSettingsButton);
        ImageButton profilesButton = view.findViewById(R.id.MainMenuProfilesButton);

        //Disable the play button if either game mode/board size not selected
        if(gameMode == 0 || board == 0) {
            playButton.setEnabled(false);
            playButton.getBackground().setAlpha(64);
        }

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gameDataViewModel.setDisplayedFragment(3); }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setDisplayedFragment(1);
            }
        });

        profilesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setDisplayedFragment(2);
            }
        });
        return view;
    }
}