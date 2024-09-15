package com.example.connect4.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.connect4.GameData;
import com.example.connect4.PlayerOperations.Player;
import com.example.connect4.R;

public class CustomizeProfileFragment extends Fragment {
    // ViewModel that stores the game data
    private GameData gameDataViewModel;
    // EditText for inputting the player's name
    private EditText playerName;
    // ImageButtons representing the avatar choices
    private ImageButton a1, a2, a3, a4, a5, a6;
    // The currently selected player object
    private Player checkedP;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customize_profile, container, false);
        gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        // Initialize UI components
        playerName = view.findViewById(R.id.CPFNameText);
        Button saveButton = view.findViewById(R.id.CustomizeSaveButton);

        // Avatar selection buttons
        a1 = view.findViewById(R.id.CPFAvatar1);
        a2 = view.findViewById(R.id.CPFAvatar2);
        a3 = view.findViewById(R.id.CPFAvatar3);
        a4 = view.findViewById(R.id.CPFAvatar4);
        a5 = view.findViewById(R.id.CPFAvatar5);
        a6 = view.findViewById(R.id.CPFAvatar6);

        // Observe changes in the selected player (1 or 2)
        gameDataViewModel.getSelectedPlayer().observe(getViewLifecycleOwner(),
                new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        // When player 1 is selected, load player 1's data
                        if (integer == 1) {
                            updatePlayer1();
                            gameDataViewModel.setPlayer1(checkedP);
                        }
                        // When player 2 is selected, load player 2's data
                        if (integer == 2) {
                            updatePlayer2();
                            gameDataViewModel.setPlayer2(checkedP);
                        }
                    }
                });

        /* Set listeners for avatar buttons to update the selected avatar */
        /* --------------------------------------------------------------------- */
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the selected avatar to avatar 1 and disable this button
                checkedP.setAvatarID(R.drawable.avatar1);
                checkedP.setPlayerAvatar(1);
                disableAvatarButton(a1);
                enableAvatarButtonsExcept(a1);
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the selected avatar to avatar 2 and disable this button
                checkedP.setAvatarID(R.drawable.avatar2);
                checkedP.setPlayerAvatar(2);
                disableAvatarButton(a2);
                enableAvatarButtonsExcept(a2);
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the selected avatar to avatar 3 and disable this button
                checkedP.setAvatarID(R.drawable.avatar3);
                checkedP.setPlayerAvatar(3);
                disableAvatarButton(a3);
                enableAvatarButtonsExcept(a3);
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the selected avatar to avatar 4 and disable this button
                checkedP.setAvatarID(R.drawable.avatar4);
                checkedP.setPlayerAvatar(4);
                disableAvatarButton(a4);
                enableAvatarButtonsExcept(a4);
            }
        });

        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the selected avatar to avatar 5 and disable this button
                checkedP.setAvatarID(R.drawable.avatar5);
                checkedP.setPlayerAvatar(5);
                disableAvatarButton(a5);
                enableAvatarButtonsExcept(a5);
            }
        });

        a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the selected avatar to avatar 6 and disable this button
                checkedP.setAvatarID(R.drawable.avatar6);
                checkedP.setPlayerAvatar(6);
                disableAvatarButton(a6);
                enableAvatarButtonsExcept(a6);
            }
        });
        /* --------------------------------------------------------------------- */

        // Save button to update the player's name
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the player's name to the text entered in the EditText
                checkedP.setPlayerName(playerName.getText().toString());

                // Reset the selected player
                gameDataViewModel.setSelectedPlayer(0);

                // Remove this fragment after saving the data
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(CustomizeProfileFragment.this)
                        .commit();
            }
        });

        return view;
    }

    // Update UI with player 1's data
    private void updatePlayer1() {
        LiveData<Player> p = gameDataViewModel.getPlayer1();

        if (p != null) {
            checkedP = p.getValue();
            playerName.setText(checkedP.getPlayerName());
            setupInitialAvState(checkedP.getPlayerAvatar());
        }
    }

    // Update UI with player 2's data
    private void updatePlayer2() {
        LiveData<Player> p = gameDataViewModel.getPlayer2();

        if (p != null) {
            checkedP = p.getValue();
            playerName.setText(checkedP.getPlayerName());
            setupInitialAvState(checkedP.getPlayerAvatar());
        }
    }

    /* Set up the initial state of avatar buttons based on the selected avatar */
    /* -------------------------------------------------------------- */
    private void setupInitialAvState(int avatarID) {
        switch (avatarID) {
            case 1:
                disableAvatarButton(a1);
                break;
            case 2:
                disableAvatarButton(a2);
                break;
            case 3:
                disableAvatarButton(a3);
                break;
            case 4:
                disableAvatarButton(a4);
                break;
            case 5:
                disableAvatarButton(a5);
                break;
            case 6:
                disableAvatarButton(a6);
                break;
            default:
                enableAllAvatarButtons();
        }
    }
    /* -------------------------------------------------------------- */

    // Disable a specific avatar button and visually indicate it's been selected
    private void disableAvatarButton(ImageButton avatar) {
        avatar.setEnabled(false);
        avatar.setColorFilter(0x77000000); // Apply a dark filter to show it's disabled
    }

    // Enable a specific avatar button and remove any visual filter
    private void enableAvatarButton(ImageButton avatar) {
        avatar.setEnabled(true);
        avatar.clearColorFilter();
    }

    // Enable all avatar buttons
    private void enableAllAvatarButtons() {
        enableAvatarButton(a1);
        enableAvatarButton(a2);
        enableAvatarButton(a3);
        enableAvatarButton(a4);
        enableAvatarButton(a5);
        enableAvatarButton(a6);
    }

    // Enable all buttons except the one that was disabled (already selected)
    private void enableAvatarButtonsExcept(ImageButton disabledAvatar) {
        if (disabledAvatar != a1) enableAvatarButton(a1);
        if (disabledAvatar != a2) enableAvatarButton(a2);
        if (disabledAvatar != a3) enableAvatarButton(a3);
        if (disabledAvatar != a4) enableAvatarButton(a4);
        if (disabledAvatar != a5) enableAvatarButton(a5);
        if (disabledAvatar != a6) enableAvatarButton(a6);
    }
}
