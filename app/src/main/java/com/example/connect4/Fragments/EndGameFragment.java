package com.example.connect4.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.connect4.GameData;
import com.example.connect4.R;


public class EndGameFragment extends Fragment {

    private GameData gameDataViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_end_game, container, false);
        gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        // Initializing the fragment buttons
        ImageButton exitButton = view.findViewById(R.id.EGExitButton);
        ImageButton restartButton = view.findViewById(R.id.EGRestartButton);


        // Setting the exit button to go back to the main menu
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setDisplayedFragment(0);
            }
        });

        // Setting the restart button to go back to the game
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setDisplayedFragment(1);
            }
        });

        return view;

    }
}