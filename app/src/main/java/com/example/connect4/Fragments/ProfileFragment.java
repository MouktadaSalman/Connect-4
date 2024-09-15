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

    // ViewModel to manage shared game data
    private GameData gameDataViewModel;

    // UI components for Player 1 and Player 2
    private ImageView avatarP1, avatarP2;
    private TextView p1NameEditText, p1Ratio, p1TotGames, p1Wins, p1Loss;
    private TextView p2NameEditText, p2Ratio, p2TotGames, p2Wins, p2Loss;

    // Objects representing the data of the two players
    private Player updatingPlayer1, updatingPlayer2;

    // UI components for Player 2's customization button and overlay
    private View p2CustomButton, overlayView2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize ViewModel to access game data
        gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        // Retrieve references to Player 1 and Player 2 customization buttons and the back button
        View p1CustomButton = view.findViewById(R.id.player1button);
        p2CustomButton = view.findViewById(R.id.player2button);
        Button backButton = view.findViewById(R.id.ProfileBackButton);

        // Retrieve Player 2's overlay view (used to disable it in PvAI mode)
        overlayView2 = p2CustomButton.findViewById(R.id.overlay_view);

        // Player 1 UI elements (avatar, name, stats)
        avatarP1 = p1CustomButton.findViewById(R.id.CPBAvatar);
        p1NameEditText = p1CustomButton.findViewById(R.id.CPBPlayerName);
        p1Ratio = p1CustomButton.findViewById(R.id.CPBWinRatioRes);
        p1TotGames = p1CustomButton.findViewById(R.id.CPBTotGamesRes);
        p1Wins = p1CustomButton.findViewById(R.id.CPBWinRes);
        p1Loss = p1CustomButton.findViewById(R.id.CPBLossRes);

        // Player 2 UI elements (avatar, name, stats)
        avatarP2 = p2CustomButton.findViewById(R.id.CPBAvatar2);
        p2NameEditText = p2CustomButton.findViewById(R.id.CPBPlayerName2);
        p2Ratio = p2CustomButton.findViewById(R.id.CPBWinRatioRes2);
        p2TotGames = p2CustomButton.findViewById(R.id.CPBTotGamesRes2);
        p2Wins = p2CustomButton.findViewById(R.id.CPBWinRes2);
        p2Loss = p2CustomButton.findViewById(R.id.CPBLossRes2);

        // Observe changes in the selected player for customization
        gameDataViewModel.getSelectedPlayer().observe(getViewLifecycleOwner(),
                new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        // When player customization is completed, fetch the initial player details
                        if (integer == 0) {
                            getInitialPlayerDetails();
                        }
                    }
                }
        );

        // Observe changes in the game mode (1: PvP, 2: PvAI)
        gameDataViewModel.getSelectedGameMode().observe(getViewLifecycleOwner(),
                new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        // If the game mode is Player vs AI, disable Player 2 customization
                        if (integer == 2) {
                            p2CustomButton.setEnabled(false);
                            overlayView2.setVisibility(View.VISIBLE);  // Show overlay to indicate disabled state
                            avatarP2.setColorFilter(0x77000000);       // Apply a dim effect on Player 2 avatar
                            avatarP2.setBackgroundResource(R.drawable.overlay);  // Set overlay background
                        } else {
                            // If PvP mode, enable Player 2 customization
                            p2CustomButton.setEnabled(true);
                            overlayView2.setVisibility(View.GONE);      // Hide overlay
                            avatarP2.clearColorFilter();                // Remove dim effect
                            avatarP2.setBackgroundResource(R.drawable.avatar_border_white);  // Restore avatar border
                        }
                    }
                }
        );

        // Set click listener for Player 1 customization button
        p1CustomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setSelectedPlayer(1);  // Set Player 1 as the selected player
            }
        });

        // Set click listener for Player 2 customization button
        p2CustomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setSelectedPlayer(2);  // Set Player 2 as the selected player
            }
        });

        // Set click listener for the back button to return to the main menu
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDataViewModel.setDisplayedFragment(0);  // Display the main menu fragment
            }
        });

        return view;
    }

    // Fetch and display the initial details for Player 1 and Player 2
    private void getInitialPlayerDetails() {
        // Fetch LiveData for Player 1 and Player 2
        LiveData<Player> p1 = gameDataViewModel.getPlayer1();
        LiveData<Player> p2 = gameDataViewModel.getPlayer2();

        // Null check to ensure both players are available
        if (p1 != null && p2 != null) {
            updatingPlayer1 = p1.getValue();
            updatingPlayer2 = p2.getValue();

            // Calculate total games for each player
            int total1 = updatingPlayer1.getWins() + updatingPlayer1.getLoss();
            int total2 = updatingPlayer2.getWins() + updatingPlayer2.getLoss();

            // Convert total games to strings for display
            String totalString1 = "" + total1;
            String totalString2 = "" + total2;

            // Update Player 1 details in the UI
            avatarP1.setImageResource(updatingPlayer1.getAvatarID());
            p1NameEditText.setText(updatingPlayer1.getPlayerName());
            p1Ratio.setText(String.format("%.1f", updatingPlayer1.winPercentage()));
            p1TotGames.setText(totalString1);
            p1Wins.setText(String.valueOf(updatingPlayer1.getWins()));
            p1Loss.setText(String.valueOf(updatingPlayer1.getLoss()));

            // Update Player 2 details in the UI
            avatarP2.setImageResource(updatingPlayer2.getAvatarID());
            p2NameEditText.setText(updatingPlayer2.getPlayerName());
            p2Ratio.setText(String.format("%.1f", updatingPlayer2.winPercentage()));
            p2TotGames.setText(totalString2);
            p2Wins.setText(String.valueOf(updatingPlayer2.getWins()));
            p2Loss.setText(String.valueOf(updatingPlayer2.getLoss()));
        }
    }
}
