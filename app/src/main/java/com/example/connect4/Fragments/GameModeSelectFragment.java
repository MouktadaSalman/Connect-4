package com.example.connect4.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.connect4.GameData;
import com.example.connect4.R;

public class GameModeSelectFragment extends Fragment {

    private GameData gameDataViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_game_mode, container, false);
        gameDataViewModel = new ViewModelProvider(requireActivity())
                .get(GameData.class);

        // Get the game mode selected + board selected (if ever)
        LiveData<Integer> dataG = gameDataViewModel.getSelectedGameMode();
        LiveData<Integer> dataB = gameDataViewModel.getSelectedBoard();

        // Initialise
        int gameMode = 0;
        int board = 0;

        //Null checks
        if(dataG.getValue() != null){
            gameMode = dataG.getValue();
        }

        if(dataB.getValue() != null){
            board = dataB.getValue();
        }

        Button playerVsPlayerButton = view.findViewById(R.id.pVsPButton);
        Button playerVsAiButton = view.findViewById(R.id.pVsAiButton);
        Button largeGameBoardButton = view.findViewById(R.id.largeGrid);
        Button mediumGameBoardButton = view.findViewById(R.id.mediumGrid);
        Button smallGameBoardButton = view.findViewById(R.id.smallGrid);

        Button backButton = view.findViewById(R.id.SettingsBackButton);

        //Disable the selected game mode of the correct button
        if(gameMode == 1) {
            playerVsPlayerButton.setEnabled(false);
            playerVsPlayerButton.getBackground().setAlpha(64);
        }
        if (gameMode == 2) {
            playerVsAiButton.setEnabled(false);
            playerVsAiButton.getBackground().setAlpha(64);
        }

        //Disable the appropriate game mode buttons
        if (board == 1) {
            largeGameBoardButton.setEnabled(false);
            largeGameBoardButton.getBackground().setAlpha(64);
        }
        if (board == 2) {
            mediumGameBoardButton.setEnabled(false);
            mediumGameBoardButton.getBackground().setAlpha(64);
        }
        if (board == 3) {
            smallGameBoardButton.setEnabled(false);
            smallGameBoardButton.getBackground().setAlpha(64);
        }

        playerVsPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Disable button
                playerVsPlayerButton.setEnabled(false);
                playerVsPlayerButton.getBackground().setAlpha(64);

                //Enable other button
                playerVsAiButton.setEnabled(true);
                playerVsAiButton.getBackground().setAlpha(255);

                //Update data
                gameDataViewModel.setSelectedGameMode(1);
            }
        });

        playerVsAiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Disable button
                playerVsAiButton.setEnabled(false);
                playerVsAiButton.getBackground().setAlpha(64);

                //Enable other button
                playerVsPlayerButton.setEnabled(true);
                playerVsPlayerButton.getBackground().setAlpha(255);

                //Update data
                gameDataViewModel.setSelectedGameMode(2);
            }
        });

        largeGameBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Disable button
                largeGameBoardButton.setEnabled(false);
                largeGameBoardButton.getBackground().setAlpha(64);

                //Enable other buttons
                mediumGameBoardButton.setEnabled(true);
                mediumGameBoardButton.getBackground().setAlpha(255);

                smallGameBoardButton.setEnabled(true);
                smallGameBoardButton.getBackground().setAlpha(255);

                gameDataViewModel.setSelectedBoardSize(1);
            }
        });

        mediumGameBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Disable button
                mediumGameBoardButton.setEnabled(false);
                mediumGameBoardButton.getBackground().setAlpha(64);

                //Enable other buttons
                largeGameBoardButton.setEnabled(true);
                largeGameBoardButton.getBackground().setAlpha(255);

                smallGameBoardButton.setEnabled(true);
                smallGameBoardButton.getBackground().setAlpha(255);

                gameDataViewModel.setSelectedBoardSize(2);
            }
        });

        smallGameBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Disable button
                smallGameBoardButton.setEnabled(false);
                smallGameBoardButton.getBackground().setAlpha(64);

                //Enable other buttons
                mediumGameBoardButton.setEnabled(true);
                mediumGameBoardButton.getBackground().setAlpha(255);

                largeGameBoardButton.setEnabled(true);
                largeGameBoardButton.getBackground().setAlpha(255);

                gameDataViewModel.setSelectedBoardSize(3);
            }
        });

        // Return to main menu
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Return back to the main menu
                gameDataViewModel.setDisplayedFragment(0);
            }
        });
        return view;
    }
}
