package com.example.connect4.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class ProfileFragment extends Fragment {

    private GameData gameDataViewModel;
    private ImageView avatarP1, avatarP2;
    private TextView p1NameEditText, p1Ratio, p1TotGames, p1Wins, p1Loss;
    private TextView p2NameEditText, p2Ratio, p2TotGames, p2Wins, p2Loss;
    private Player updatingPlayer1, updatingPlayer2;
    private View p2CustomButton, overlayView2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        View p1CustomButton = view.findViewById(R.id.player1button);
        p2CustomButton = view.findViewById(R.id.player2button);
        Button backButton = view.findViewById(R.id.ProfileBackButton);

        overlayView2 = p2CustomButton.findViewById(R.id.overlay_view);

        //Get access to modify custom button details
        avatarP1 = p1CustomButton.findViewById(R.id.CPBAvatar);
        p1NameEditText = p1CustomButton.findViewById(R.id.CPBPlayerName);
        p1Ratio = p1CustomButton.findViewById(R.id.CPBWinRatioRes);
        p1TotGames = p1CustomButton.findViewById(R.id.CPBTotGamesRes);
        p1Wins = p1CustomButton.findViewById(R.id.CPBWinRes);
        p1Loss = p1CustomButton.findViewById(R.id.CPBLossRes);

        avatarP2 = p2CustomButton.findViewById(R.id.CPBAvatar2);
        p2NameEditText = p2CustomButton.findViewById(R.id.CPBPlayerName2);
        p2Ratio = p2CustomButton.findViewById(R.id.CPBWinRatioRes2);
        p2TotGames = p2CustomButton.findViewById(R.id.CPBTotGamesRes2);
        p2Wins = p2CustomButton.findViewById(R.id.CPBWinRes2);
        p2Loss = p2CustomButton.findViewById(R.id.CPBLossRes2);

        gameDataViewModel.getSelectedPlayer().observe(getViewLifecycleOwner(),
        new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                //If user finish customise
                if (integer == 0){
                    getInitialPlayerDetails();
                }
            }
        });

        gameDataViewModel.getSelectedGameMode().observe(getViewLifecycleOwner(),
        new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                //Check if the game mode is PvP or PvAi
                if(integer == 2){
                    //Disable 2nd player customization
                    p2CustomButton.setEnabled(false);
                    overlayView2.setVisibility(View.VISIBLE);
                    avatarP2.setColorFilter(0x77000000);
                    avatarP2.setBackgroundResource(R.drawable.overlay);
                }
                else {
                    p2CustomButton.setEnabled(true);
                    overlayView2.setVisibility(View.GONE);
                    avatarP2.clearColorFilter();
                    avatarP2.setBackgroundResource(R.drawable.avatar_border_white);
                }
            }
        });

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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { gameDataViewModel.setDisplayedFragment(0);}
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

            int total1 = updatingPlayer1.getWins()+updatingPlayer1.getLoss();
            int total2 = updatingPlayer2.getWins()+updatingPlayer2.getLoss();

            String totalString1 = "" + total1;
            String totalString2 = "" + total2;

            avatarP1.setImageResource(updatingPlayer1.getAvatarID());
            p1NameEditText.setText(updatingPlayer1.getPlayerName());
            p1Ratio.setText(String.format("%.1f", updatingPlayer1.winPercentage()));
            p1TotGames.setText(totalString1);
            p1Wins.setText(String.valueOf(updatingPlayer1.getWins()));
            p1Loss.setText(String.valueOf(updatingPlayer1.getLoss()));

            avatarP2.setImageResource(updatingPlayer2.getAvatarID());
            p2NameEditText.setText(updatingPlayer2.getPlayerName());
            p2Ratio.setText(String.format("%.1f", updatingPlayer2.winPercentage()));
            p2TotGames.setText(totalString2);
            p2Wins.setText(String.valueOf(updatingPlayer2.getWins()));
            p2Loss.setText(String.valueOf(updatingPlayer2.getLoss()));
        }
    }
}
