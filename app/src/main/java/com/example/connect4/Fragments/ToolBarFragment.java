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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.connect4.GameData;
import com.example.connect4.PlayerOperations.Player;
import com.example.connect4.R;

public class ToolBarFragment extends Fragment {

    private GameData gameDataViewModel;
    private ImageView P1Avatar, P2Avatar;
    private TextView PlayerOneInfo, PlayerTwoInfo;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tool_bar, container, false);

        gameDataViewModel = new ViewModelProvider(requireActivity())
                .get(GameData.class);

        ImageButton pauseButton = view.findViewById(R.id.InGamePauseButton);
        P1Avatar = view.findViewById(R.id.P1Avatar);
        P2Avatar = view.findViewById(R.id.P2Avatar);
        PlayerOneInfo = view.findViewById(R.id.PlayerOneInfo);
        PlayerTwoInfo = view.findViewById(R.id.PlayerTwoInfo);

        // Handle the game mode change so if the game mode is against AI, player 2 name will be set to "AI"
        gameDataViewModel.getSelectedGameMode().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Integer mode) {
                if (mode == 1) {
                    setPlayerDetails(gameDataViewModel.getPlayer1(),
                                     gameDataViewModel.getPlayer2());
                }
                if (mode == 2) {
                    setPlayerDetails(gameDataViewModel.getPlayer1(),
                                     gameDataViewModel.getAiPlayer());
                }
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

    private void setPlayerDetails(LiveData<Player> p1, LiveData<Player> p2){
        if(p1 != null && p2 != null){
            Player p1Data = p1.getValue();
            Player p2Data = p2.getValue();

            //Setup player 1
            assert p1Data != null; //Asserted since already null check from above
            P1Avatar.setImageResource(p1Data.getAvatarID());
            PlayerOneInfo.setText(p1Data.getPlayerName());

            //Setup player 2
            assert p2Data != null; //Asserted since already null check from above
            P2Avatar.setImageResource(p2Data.getAvatarID());
            PlayerTwoInfo.setText(p2Data.getPlayerName());
        }
    }
}
