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

public class PauseMenuFragment extends Fragment {

    private GameData gameDataViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pause_menu, container, false);
        gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        ImageButton exitButton = view.findViewById(R.id.PMExitButton);
        ImageButton restartButton = view.findViewById(R.id.PMRestartButton);
        ImageButton resumeButton = view.findViewById(R.id.PMResumeButton);


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setDisplayedFragment(0);
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setDisplayedFragment(1);
            }
        });

        resumeButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}