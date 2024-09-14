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

public class GameSettingsFragment extends Fragment {

    private GameData gameDataViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game_settings, container, false);
        gameDataViewModel = new ViewModelProvider(requireActivity())
                .get(GameData.class);

        // Get the game mode selected + board selected (if ever)
        LiveData<Integer> dataG = gameDataViewModel.getSelectedGameMode();
        LiveData<Integer> dataB = gameDataViewModel.getSelectedBoard();

        Button playerVsPlayerButton = view.findViewById(R.id.pVsPButton);
        Button playerVsAiButton = view.findViewById(R.id.pVsAiButton);
        Button largeGameBoardButton = view.findViewById(R.id.largeGrid);
        Button mediumGameBoardButton = view.findViewById(R.id.mediumGrid);
        Button smallGameBoardButton = view.findViewById(R.id.smallGrid);

        Button backButton = view.findViewById(R.id.SettingsBackButton);

        gameDataViewModel.getSelectedBoard().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {
                    case 1:
                        //Disable button
                        largeGameBoardButton.setEnabled(false);
                        largeGameBoardButton.getBackground().setAlpha(64);

                        //Enable other button
                        mediumGameBoardButton.setEnabled(true);
                        mediumGameBoardButton.getBackground().setAlpha(255);

                        smallGameBoardButton.setEnabled(true);
                        smallGameBoardButton.getBackground().setAlpha(255);
                        break;

                    case 2:

                        //Disable button
                        mediumGameBoardButton.setEnabled(false);
                        mediumGameBoardButton.getBackground().setAlpha(64);

                        //Enable other button
                        largeGameBoardButton.setEnabled(true);
                        largeGameBoardButton.getBackground().setAlpha(255);

                        smallGameBoardButton.setEnabled(true);
                        smallGameBoardButton.getBackground().setAlpha(255);
                        break;

                    case 3:
                        //Disable button
                        smallGameBoardButton.setEnabled(false);
                        smallGameBoardButton.getBackground().setAlpha(64);

                        //Enable other button
                        mediumGameBoardButton.setEnabled(true);
                        mediumGameBoardButton.getBackground().setAlpha(255);

                        largeGameBoardButton.setEnabled(true);
                        largeGameBoardButton.getBackground().setAlpha(255);
                        break;
                }
            }
        });

        gameDataViewModel.getSelectedGameMode().observe(getViewLifecycleOwner(), new Observer<Integer>() {
        @Override
        public void onChanged(Integer integer) {
            switch (integer) {
                case 1:
                    //Disable button
                    playerVsPlayerButton.setEnabled(false);
                    playerVsPlayerButton.getBackground().setAlpha(64);

                    //Enable other button
                    playerVsAiButton.setEnabled(true);
                    playerVsAiButton.getBackground().setAlpha(255);
                    break;

                case 2:
                    //Disable button
                    playerVsAiButton.setEnabled(false);
                    playerVsAiButton.getBackground().setAlpha(64);

                    //Enable other button
                    playerVsPlayerButton.setEnabled(true);
                    playerVsPlayerButton.getBackground().setAlpha(255);
                    break;
            }
        }
    });

        playerVsPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Update data
                gameDataViewModel.setSelectedGameMode(1);
            }
        });

        playerVsAiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Update data
                gameDataViewModel.setSelectedGameMode(2);
            }
        });

        largeGameBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setSelectedBoard(1);
            }
        });

        mediumGameBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setSelectedBoard(2);
            }
        });

        smallGameBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setSelectedBoard(3);
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
