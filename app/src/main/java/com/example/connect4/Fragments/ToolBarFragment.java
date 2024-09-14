package com.example.connect4.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.connect4.GameData;
import com.example.connect4.R;

public class ToolBarFragment extends Fragment {

    private GameData gameDataViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tool_bar, container, false);

        gameDataViewModel = new ViewModelProvider(getActivity())
                .get(GameData.class);

        ImageButton pauseButton = view.findViewById(R.id.InGamePauseButton);
        ImageView P1Avatar = view.findViewById(R.id.P1Avatar);
        ImageView P2Avatar = view.findViewById(R.id.P2Avatar);
        TextView PlayerOneInfo = view.findViewById(R.id.PlayerOneInfo);
        TextView PlayerTwoInfo = view.findViewById(R.id.PlayerTwoInfo);

        // Observe player 1 avatar and update ImageView when changed
        gameDataViewModel.getPlayer1Avatar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer avatarRes) {
                P1Avatar.setImageResource(avatarRes);
            }
        });

        // Observe player 2 avatar and update ImageView when changed
        gameDataViewModel.getPlayer2Avatar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer avatarRes) {
                P2Avatar.setImageResource(avatarRes);
            }
        });

        // Observe player 1 name and update TextView when changed
        gameDataViewModel.getPlayer1Name().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String name) {
                PlayerOneInfo.setText(name);
            }
        });


        // Handle the game mode change so if the game mode is against AI, player 2 name will be set to "AI"
        gameDataViewModel.getSelectedGameMode().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Integer mode) {
                if (mode == 2) {
                    gameDataViewModel.setPlayer2Name("AI");
                }
            }
        });

        // Observe player 2 name and update TextView when changed
        gameDataViewModel.getPlayer2Name().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String name) {


                PlayerTwoInfo.setText(name);
            }
        });

        // Handle pause button click to switch to another fragment
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gameDataViewModel.setDisplayedFragment(4);
            }
        });

        return view;
    }
}
