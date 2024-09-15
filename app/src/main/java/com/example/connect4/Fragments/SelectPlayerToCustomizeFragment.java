package com.example.connect4.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.connect4.GameData;
import com.example.connect4.PlayerOperations.Player;
import com.example.connect4.R;

public class SelectPlayerToCustomizeFragment extends Fragment {

    private GameData gameDataViewModel;
    private ImageView avatarP1, avatarP2;
    private TextView p1NameEditText, p1Ratio, p1TotGames, p1Wins, p1Loss;
    private TextView p2NameEditText, p2Ratio, p2TotGames, p2Wins, p2Loss;
    private Button p1CustomButton, p2CustomButton;
    private Player updatingPlayer1, updatingPlayer2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_player_to_customize, container, false);
        gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        p1CustomButton = view.findViewById(R.id.player1button);
        p2CustomButton = view.findViewById(R.id.player2button);

        //Get access to modify custom button details
        avatarP1 = view.findViewById(R.id.CPBAvatar);
        p1NameEditText = view.findViewById(R.id.CPBPlayerName);
        p1Ratio = view.findViewById(R.id.CPBWinRatioRes);
        p1TotGames = view.findViewById(R.id.CPBTotGamesRes);
        p1Wins = view.findViewById(R.id.CPBWinRes);
        p1Loss = view.findViewById(R.id.CPBLossRes);

        avatarP2 = view.findViewById(R.id.CPBAvatar2);
        p2NameEditText = view.findViewById(R.id.CPBPlayerName2);
        p2Ratio = view.findViewById(R.id.CPBWinRatioRes2);
        p2TotGames = view.findViewById(R.id.CPBTotGamesRes2);
        p2Wins = view.findViewById(R.id.CPBWinRes2);
        p2Loss = view.findViewById(R.id.CPBLossRes2);

        getInitialPlayerDetails();

        p1CustomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setSelectedPlayer(1);
            }
        });

        p2CustomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setSelectedPlayer(2);
            }
        });

        return view;
    }

    private void getInitialPlayerDetails(){
        LiveData<Player> p1 = gameDataViewModel.getPlayer1();
        LiveData<Player> p2 = gameDataViewModel.getPlayer2();

        //Null check
        if(p1 != null && p2 != null){
            updatingPlayer1 = p1.getValue();
            updatingPlayer2 = p2.getValue();

            avatarP1.setImageResource(updatingPlayer1.getAvatarID());
            p1NameEditText.setText(updatingPlayer1.getPlayerName());
            p1Ratio.setText(String.format("%.1f", updatingPlayer1.winPercentage()));
            p1TotGames.setText(updatingPlayer1.getWins()+updatingPlayer1.getLoss());
            p1Wins.setText(updatingPlayer1.getWins());
            p1Loss.setText(updatingPlayer1.getLoss());

            avatarP2.setImageResource(updatingPlayer2.getAvatarID());
            p2NameEditText.setText(updatingPlayer2.getPlayerName());
            p2Ratio.setText(String.format("%.1f", updatingPlayer2.winPercentage()));
            p2TotGames.setText(updatingPlayer1.getWins()+updatingPlayer2.getLoss());
            p2Wins.setText(updatingPlayer2.getWins());
            p2Loss.setText(updatingPlayer2.getLoss());
        }
    }
}
