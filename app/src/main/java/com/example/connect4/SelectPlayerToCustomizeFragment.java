package com.example.connect4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class SelectPlayerToCustomizeFragment extends Fragment {

    private GameData gameDataViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_player_to_customize, container, false);
        gameDataViewModel = new ViewModelProvider(getActivity())
                .get(GameData.class);

        Button customizePlayer1Button = view.findViewById(R.id.customizePlayer1Button);
        Button customizePlayer2Button = view.findViewById(R.id.customizePlayer2Button);
        Button largeGameBoardButton = view.findViewById(R.id.largeGrid);
        Button mediumGameBoardButton = view.findViewById(R.id.mediumGrid);
        Button smallGameBoardButton = view.findViewById(R.id.smallGrid);
        Button backButton = view.findViewById(R.id.back);

        if(gameDataViewModel.getSelectedGameMode() == 2){
            customizePlayer2Button.setEnabled(false);
            customizePlayer2Button.getBackground().setAlpha(64);
        }

        customizePlayer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //need to make xml for customization fragment
            }
        });

        customizePlayer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //need to make xml for customization fragment
            }
        });

        largeGameBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setDisplayedFragment(3);
            }
        });

        mediumGameBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setDisplayedFragment(4);
            }
        });

        smallGameBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setDisplayedFragment(5);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameDataViewModel.getDisplayedFragment() == 2){
                    gameDataViewModel.setDisplayedFragment(1);
                }
                else{
                    gameDataViewModel.setDisplayedFragment(2);
                }


            }
        });
        return view;
    }
}


