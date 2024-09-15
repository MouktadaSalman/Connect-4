package com.example.connect4.Fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.connect4.GameData;
import com.example.connect4.R;

public class PauseMenuFragment extends Fragment {

    private GameData gameDataViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pause_menu, container, false);
        gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        ImageButton exitButton = view.findViewById(R.id.PMExitButton);
        ImageButton restartButton = view.findViewById(R.id.PMRestartButton);
        ImageButton resumeButton = view.findViewById(R.id.PMResumeButton);

        // When exit button is clicked we display fragment 0. (Main Menu)
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Removing the GameBoardFragment
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                Fragment gameBoardFragment = fragmentManager.findFragmentById(R.id.fragment_game_board_container);
                Fragment toolBoardFragment = fragmentManager.findFragmentById(R.id.fragment_tool_bar_container);

                if (gameBoardFragment != null && toolBoardFragment != null) {
                    fragmentManager.beginTransaction()
                            .remove(gameBoardFragment)
                            .remove(toolBoardFragment)
                            .commit();
                }
                gameDataViewModel.setDisplayedFragment(0);
            }
        });

        // When restart button is clicked we display fragment 1. (Game Mode)
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Removing the GameBoardFragment
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                Fragment gameBoardFragment = fragmentManager.findFragmentById(R.id.fragment_game_board_container);
                Fragment toolBoardFragment = fragmentManager.findFragmentById(R.id.fragment_tool_bar_container);

                if (gameBoardFragment != null && toolBoardFragment != null) {
                    fragmentManager.beginTransaction()
                            .remove(gameBoardFragment)
                            .remove(toolBoardFragment)
                            .commit();
                }
                gameDataViewModel.setDisplayedFragment(3);
            }
        });

        // When resume button is clicked we remove the pause button overlay.
        resumeButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setDisplayedFragment(5);
            }
        });

        return view;
    }
}