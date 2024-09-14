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
import android.widget.ImageView;

import com.example.connect4.GameData;
import com.example.connect4.PlayerOperations.Player;
import com.example.connect4.R;


public class CustomizeProfileFragment extends Fragment {
    private GameData gameDataViewModel;
    private ImageView avatarChosen;
    private EditText playerName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customize_profile, container, false);
        gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        playerName = view.findViewById(R.id.CPFNameText);



        gameDataViewModel.getSelectedPlayer().observe(getViewLifecycleOwner(),
        new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {

                } else if (integer == 2) {

                }else {

                }
            }
        });


        return view;
    }

    private void updatePlayer1(){
        LiveData<Player> p1 = gameDataViewModel.getPlayer1();
        Player checkedP1 = null;

        //Null check
        if(p1 != null){
            checkedP1 = p1.getValue();

            assert checkedP1 != null;
            playerName.setText(checkedP1.getPlayerName());
        }
    }
}