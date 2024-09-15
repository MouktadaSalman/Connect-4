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
    private GameData gameDataViewModel;
    private EditText playerName;
    private ImageButton a1, a2, a3, a4, a5, a6;
    private Player checkedP;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customize_profile, container, false);
        gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        playerName = view.findViewById(R.id.CPFNameText);
        Button saveButton = view.findViewById(R.id.CustomizeSaveButton);

        a1 = view.findViewById(R.id.CPFAvatar1);
        a2 = view.findViewById(R.id.CPFAvatar2);
        a3 = view.findViewById(R.id.CPFAvatar3);
        a4 = view.findViewById(R.id.CPFAvatar4);
        a5 = view.findViewById(R.id.CPFAvatar5);
        a6 = view.findViewById(R.id.CPFAvatar6);

        gameDataViewModel.getSelectedPlayer().observe(getViewLifecycleOwner(),
        new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    updatePlayer1();
                    gameDataViewModel.setPlayer1(checkedP);
                }
                if (integer == 2) {
                    updatePlayer2();
                    gameDataViewModel.setPlayer2(checkedP);
                }
            }
        });

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set the selected avatar
                checkedP.setAvatarID(R.drawable.avatar1);
                checkedP.setPlayerAvatar(1);

                //Disable the button
                disableAvatarButton(a1);
                enableAvatarButton(a2);
                enableAvatarButton(a3);
                enableAvatarButton(a4);
                enableAvatarButton(a5);
                enableAvatarButton(a6);
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set the selected avatar
                checkedP.setAvatarID(R.drawable.avatar2);
                checkedP.setPlayerAvatar(2);

                //Disable the button
                disableAvatarButton(a2);
                enableAvatarButton(a1);
                enableAvatarButton(a3);
                enableAvatarButton(a4);
                enableAvatarButton(a5);
                enableAvatarButton(a6);
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set the selected avatar
                checkedP.setAvatarID(R.drawable.avatar3);
                checkedP.setPlayerAvatar(3);

                //Disable the button
                disableAvatarButton(a3);
                enableAvatarButton(a2);
                enableAvatarButton(a1);
                enableAvatarButton(a4);
                enableAvatarButton(a5);
                enableAvatarButton(a6);
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set the selected avatar
                checkedP.setAvatarID(R.drawable.avatar4);
                checkedP.setPlayerAvatar(4);

                //Disable the button
                disableAvatarButton(a4);
                enableAvatarButton(a2);
                enableAvatarButton(a3);
                enableAvatarButton(a1);
                enableAvatarButton(a5);
                enableAvatarButton(a6);
            }
        });

        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set the selected avatar
                checkedP.setAvatarID(R.drawable.avatar5);
                checkedP.setPlayerAvatar(5);

                //Disable the button
                disableAvatarButton(a5);
                enableAvatarButton(a2);
                enableAvatarButton(a3);
                enableAvatarButton(a4);
                enableAvatarButton(a1);
                enableAvatarButton(a6);
            }
        });

        a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set the selected avatar
                checkedP.setAvatarID(R.drawable.avatar6);
                checkedP.setPlayerAvatar(6);

                //Disable the button
                disableAvatarButton(a6);
                enableAvatarButton(a2);
                enableAvatarButton(a3);
                enableAvatarButton(a4);
                enableAvatarButton(a5);
                enableAvatarButton(a1);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set the player name
                checkedP.setPlayerName(playerName.getText().toString());

                //Reset selected player
                gameDataViewModel.setSelectedPlayer(0);

                //Remove this fragment
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(CustomizeProfileFragment.this)
                        .commit();
            }
        });

        return view;
    }

    private void updatePlayer1(){
        LiveData<Player> p = gameDataViewModel.getPlayer1();

        //Null check
        if(p != null){
            checkedP = p.getValue();

            playerName.setText(checkedP.getPlayerName());

            int avatarID = checkedP.getPlayerAvatar();

            setupInitialAvState(avatarID);
        }
    }

    private void updatePlayer2(){
        LiveData<Player> p = gameDataViewModel.getPlayer2();

        //Null check
        if(p != null){
            checkedP = p.getValue();

            playerName.setText(checkedP.getPlayerName());

            int avatarID = checkedP.getPlayerAvatar();

            setupInitialAvState(avatarID);
        }
    }

    private void setupInitialAvState(int value){
        switch (value) {
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
                enableAvatarButton(a1);
                enableAvatarButton(a2);
                enableAvatarButton(a3);
                enableAvatarButton(a4);
                enableAvatarButton(a5);
                enableAvatarButton(a6);
        }
    }

    private void disableAvatarButton(ImageButton avatar){
        avatar.setEnabled(false);
        avatar.setColorFilter(0x77000000);
    }

    private void enableAvatarButton(ImageButton avatar){
        avatar.setEnabled(true);
        avatar.clearColorFilter();
    }
}