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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.connect4.GameData;
import com.example.connect4.R;

public class SelectPlayerToCustomizeFragment extends Fragment {

    private GameData gameDataViewModel;
    private ImageView avatarP1, avatarP2;
    private TextView p1NameEditText, p1Ratio, p1TotGames, p1Wins, p1Loss;
    private TextView p2NameEditText, p2Ratio, p2TotGames, p2Wins, p2Loss;
    private Button p1CustomButton, p2CustomButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_player_to_customize, container, false);
        gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        return view;
    }
}
