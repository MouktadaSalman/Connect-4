package com.example.connect4.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.connect4.GameData;
import com.example.connect4.R;

public class GameModeSelectFragment extends Fragment {

    private GameData gameDataViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_game_mode, container, false);
        gameDataViewModel = new ViewModelProvider(getActivity())
                .get(GameData.class);

        Button playerVsPlayerButton = view.findViewById(R.id.pVsPButton);
        Button playerVsAiButton = view.findViewById(R.id.pVsAiButton);

        playerVsPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setDisplayedFragment(2);
                gameDataViewModel.setSelectedGameMode(1);
            }
        });

        playerVsAiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setDisplayedFragment(2);
                gameDataViewModel.setSelectedGameMode(2);
            }
        });
        return view;
    }
}
