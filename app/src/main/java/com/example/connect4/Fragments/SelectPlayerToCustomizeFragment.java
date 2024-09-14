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

public class SelectPlayerToCustomizeFragment extends Fragment {

    private GameData gameDataViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_player_to_customize, container, false);
        gameDataViewModel = new ViewModelProvider(requireActivity())
                .get(GameData.class);

        Button customizePlayer1Button = view.findViewById(R.id.customizePlayer1Button);
        Button customizePlayer2Button = view.findViewById(R.id.customizePlayer2Button);
        Button backButton = view.findViewById(R.id.ProfileBackButton);

        /* If the user chose the single-player game mode, we blur the option to customise player 2. */
        /* ------------------------------------------------------------------------------------------------------- */
        gameDataViewModel.getSelectedGameMode().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 2){
                    customizePlayer2Button.setEnabled(false);
                    customizePlayer2Button.getBackground().setAlpha(64);
                }
            }
        });
        /* ------------------------------------------------------------------------------------------------------- */

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


