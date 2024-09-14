package com.example.connect4.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.connect4.GameData;
import com.example.connect4.R;

public class SmallGameBoardFragment extends Fragment {

    private GameData gameDataViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_small_game_board, container, false);
        gameDataViewModel = new ViewModelProvider(getActivity())
                .get(GameData.class);



        return view;
    }
}
