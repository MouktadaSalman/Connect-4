package com.example.connect4.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.connect4.GameData;
import com.example.connect4.R;

public class ToolBarFragment extends Fragment {

    private GameData gameDataViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_tool_bar, container, false);

        gameDataViewModel = new ViewModelProvider(getActivity())
                .get(GameData.class);

        ImageButton pauseButton = view.findViewById(R.id.InGamePauseButton);

        pauseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                gameDataViewModel.setDisplayedFragment(4);
            }
        });

        return view;
    }
}
