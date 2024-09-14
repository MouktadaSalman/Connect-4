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
import android.widget.ImageView;

import com.example.connect4.GameData;
import com.example.connect4.PlayerOperations.Player;
import com.example.connect4.R;


public class CustomizeProfileFragment extends Fragment {
    private GameData gameDataViewModel;
    private ImageView avatarChosenP1, avatarChosenP2;
    private EditText player1NameEditText, player2NameEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customize_profile, container, false);
        gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        LiveData<Player> p1 = gameDataViewModel.getPlayer1();
        LiveData<Player> p2 = gameDataViewModel.getPlayer2();

        Player checkedP1 = null;
        Player checkedP2 = null;

        //Null check
        if(p1 != null && p2 != null){
            checkedP1 = p1.getValue();
            checkedP2 = p2.getValue();

            checkedP1.setPlayerName();
        }



        return view;
    }
}