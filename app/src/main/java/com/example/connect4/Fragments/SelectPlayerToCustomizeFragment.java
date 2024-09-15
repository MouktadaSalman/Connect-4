package com.example.connect4.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.connect4.GameData;
import com.example.connect4.R;

public class SelectPlayerToCustomizeFragment extends Fragment {

    private GameData gameDataViewModel;
    private ImageView avatarChoosenP1, avatarChoosenP2;
    private EditText player1NameEditText, player2NameEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_player_to_customize, container, false);
        gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        avatarChoosenP1 = view.findViewById(R.id.avatarChoosen);
        avatarChoosenP2 = view.findViewById(R.id.avatarChoosenP2);

        player1NameEditText = view.findViewById(R.id.player1Name);
        player2NameEditText = view.findViewById(R.id.player2Name);

        // Initialize Player 1 avatar ImageViews
        ImageView avatar1P1 = view.findViewById(R.id.avatar1P1);
        ImageView avatar2P1 = view.findViewById(R.id.avatar2P1);
        ImageView avatar3P1 = view.findViewById(R.id.avatar3P1);
        ImageView avatar4P1 = view.findViewById(R.id.avatar4P1);
        ImageView avatar5P1 = view.findViewById(R.id.avatar5P1);
        ImageView avatar6P1 = view.findViewById(R.id.avatar6P1);

        // Initialize Player 2 avatar ImageViews
        ImageView avatar1P2 = view.findViewById(R.id.avatar1P2);
        ImageView avatar2P2 = view.findViewById(R.id.avatar2P2);
        ImageView avatar3P2 = view.findViewById(R.id.avatar3P2);
        ImageView avatar4P2 = view.findViewById(R.id.avatar4P2);
        ImageView avatar5P2 = view.findViewById(R.id.avatar5P2);
        ImageView avatar6P2 = view.findViewById(R.id.avatar6P2);

        // Saving avatar selections and names from GameData
        gameDataViewModel.getPlayer1Avatar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer avatar) {
                avatarChoosenP1.setImageResource(avatar);
            }
        });

        gameDataViewModel.getPlayer2Avatar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer avatar) {
                avatarChoosenP2.setImageResource(avatar);
            }
        });

        gameDataViewModel.getPlayer1Name().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String name) {
                player1NameEditText.setText(name);
            }
        });

        gameDataViewModel.getPlayer2Name().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String name) {
                player2NameEditText.setText(name);
            }
        });

        // Set click listeners for Player 1 avatars
        setAvatarClickListener(avatar1P1, R.drawable.avatar1, true);
        setAvatarClickListener(avatar2P1, R.drawable.avatar2, true);
        setAvatarClickListener(avatar3P1, R.drawable.avatar3, true);
        setAvatarClickListener(avatar4P1, R.drawable.avatar4, true);
        setAvatarClickListener(avatar5P1, R.drawable.avatar5, true);
        setAvatarClickListener(avatar6P1, R.drawable.avatar6, true);

        // Set click listeners for Player 2 avatars
        setAvatarClickListener(avatar1P2, R.drawable.avatar1, false);
        setAvatarClickListener(avatar2P2, R.drawable.avatar2, false);
        setAvatarClickListener(avatar3P2, R.drawable.avatar3, false);
        setAvatarClickListener(avatar4P2, R.drawable.avatar4, false);
        setAvatarClickListener(avatar5P2, R.drawable.avatar5, false);
        setAvatarClickListener(avatar6P2, R.drawable.avatar6, false);

        // Handle the game mode change
        gameDataViewModel.getSelectedGameMode().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer mode) {
                if (mode == 2) { // also for the last method down, to see if it is single player mode
                    disablePlayer2Customization(avatar1P2, avatar2P2, avatar3P2, avatar4P2, avatar5P2, avatar6P2, avatarChoosenP2);
                }
            }
        });

        // Set click listener for back button
        Button backButton = view.findViewById(R.id.ProfileBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the names before navigating back
                String player1Name = player1NameEditText.getText().toString();
                String player2Name = player2NameEditText.getText().toString();

                // Update GameData with the names
                gameDataViewModel.setPlayer1Name(player1Name);
                gameDataViewModel.setPlayer2Name(player2Name);

                // Return to the main menu
                gameDataViewModel.setDisplayedFragment(0);
            }
        });

        return view;
    }

    /**
     * Method to set the click listener for avatars
     *
     * @param avatarView The avatar ImageView that can be clicked
     * @param avatarDrawable The drawable resource of the avatar image
     * @param isPlayer1 True if setting the avatar for Player 1, False for Player 2
     */
    private void setAvatarClickListener(ImageView avatarView, final int avatarDrawable, final boolean isPlayer1) {
        avatarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isPlayer1) {
                    gameDataViewModel.setPlayer1Avatar(avatarDrawable);

                } else {
                    gameDataViewModel.setPlayer2Avatar(avatarDrawable);

                }
            }
        });
    }

    /** We could use this when the game mode is 1 V AI mode
     * Disable Player 2's avatar customization in single-player mode
     */
    private void disablePlayer2Customization(ImageView... avatars) {

        for (ImageView avatar : avatars) {
            avatar.setEnabled(false);
            avatar.setColorFilter(0x77000000);

            player2NameEditText.setEnabled(false);
        }
    }
}
