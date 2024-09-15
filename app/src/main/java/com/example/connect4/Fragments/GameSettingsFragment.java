package com.example.connect4.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.connect4.GameData;
import com.example.connect4.R;

public class GameSettingsFragment extends Fragment {

    // ViewModel that stores and manages the game data
    private GameData gameDataViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_settings, container, false);

        // Initialize ViewModel for game data, shared across activities/fragments
        gameDataViewModel = new ViewModelProvider(requireActivity())
                .get(GameData.class);

        // Initialize the buttons for game settings (game modes, board sizes, back button)
        Button playerVsPlayerButton = view.findViewById(R.id.pVsPButton); // Player vs Player mode
        Button playerVsAiButton = view.findViewById(R.id.pVsAiButton);     // Player vs AI mode
        Button largeGameBoardButton = view.findViewById(R.id.largeGrid);   // Large game board selection
        Button mediumGameBoardButton = view.findViewById(R.id.mediumGrid); // Medium game board selection
        Button smallGameBoardButton = view.findViewById(R.id.smallGrid);   // Small game board selection
        Button backButton = view.findViewById(R.id.SettingsBackButton);    // Back button to return to the previous screen

        // Observe changes in the selected board size (1: large, 2: medium, 3: small)
        gameDataViewModel.getSelectedBoard().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer boardSize) {
                switch (boardSize) {
                    case 1:
                        // Large board selected, disable the large board button and enable others
                        largeGameBoardButton.setEnabled(false);
                        largeGameBoardButton.getBackground().setAlpha(64); // Semi-transparent to indicate disabled
                        mediumGameBoardButton.setEnabled(true);
                        mediumGameBoardButton.getBackground().setAlpha(255); // Fully opaque
                        smallGameBoardButton.setEnabled(true);
                        smallGameBoardButton.getBackground().setAlpha(255);
                        break;

                    case 2:
                        // Medium board selected, disable the medium board button and enable others
                        mediumGameBoardButton.setEnabled(false);
                        mediumGameBoardButton.getBackground().setAlpha(64);
                        largeGameBoardButton.setEnabled(true);
                        largeGameBoardButton.getBackground().setAlpha(255);
                        smallGameBoardButton.setEnabled(true);
                        smallGameBoardButton.getBackground().setAlpha(255);
                        break;

                    case 3:
                        // Small board selected, disable the small board button and enable others
                        smallGameBoardButton.setEnabled(false);
                        smallGameBoardButton.getBackground().setAlpha(64);
                        mediumGameBoardButton.setEnabled(true);
                        mediumGameBoardButton.getBackground().setAlpha(255);
                        largeGameBoardButton.setEnabled(true);
                        largeGameBoardButton.getBackground().setAlpha(255);
                        break;
                }
            }
        });

        // Observe changes in the selected game mode (1: Player vs Player, 2: Player vs AI)
        gameDataViewModel.getSelectedGameMode().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer gameMode) {
                switch (gameMode) {
                    case 1:
                        // Player vs Player selected, disable this button and enable the Player vs AI button
                        playerVsPlayerButton.setEnabled(false);
                        playerVsPlayerButton.getBackground().setAlpha(64);
                        playerVsAiButton.setEnabled(true);
                        playerVsAiButton.getBackground().setAlpha(255);
                        break;

                    case 2:
                        // Player vs AI selected, disable this button and enable the Player vs Player button
                        playerVsAiButton.setEnabled(false);
                        playerVsAiButton.getBackground().setAlpha(64);
                        playerVsPlayerButton.setEnabled(true);
                        playerVsPlayerButton.getBackground().setAlpha(255);
                        break;
                }
            }
        });

        // Set click listeners for the game mode buttons
        playerVsPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the selected game mode to Player vs Player (1)
                gameDataViewModel.setSelectedGameMode(1);
            }
        });

        playerVsAiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the selected game mode to Player vs AI (2)
                gameDataViewModel.setSelectedGameMode(2);
            }
        });

        // Set click listeners for the board size buttons
        largeGameBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the selected board size to Large (1)
                gameDataViewModel.setSelectedBoard(1);
            }
        });

        mediumGameBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the selected board size to Medium (2)
                gameDataViewModel.setSelectedBoard(2);
            }
        });

        smallGameBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the selected board size to Small (3)
                gameDataViewModel.setSelectedBoard(3);
            }
        });

        // Set click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Return back to the main menu by setting the displayed fragment to 0 (main menu fragment)
                gameDataViewModel.setDisplayedFragment(0);
            }
        });

        return view;
    }
}
