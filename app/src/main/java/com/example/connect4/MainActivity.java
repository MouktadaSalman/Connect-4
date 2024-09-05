package com.example.connect4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    GameModeSelectFragment gameModeFragment = new GameModeSelectFragment();
    SelectPlayerToCustomizeFragment playerToCustomizeFragment = new SelectPlayerToCustomizeFragment();
    LargeGameBoardFragment largeGameBoardFragment = new LargeGameBoardFragment();
    MediumGameBoardFragment mediumGameBoardFragment = new MediumGameBoardFragment();
    SmallGameBoardFragment smallGameBoardFragment = new SmallGameBoardFragment();
    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        loadGameModeFragment();

        GameData gameDataViewModel = new ViewModelProvider(this)
                .get(GameData.class);

        gameDataViewModel.displayedFragment.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (gameDataViewModel.getDisplayedFragment() == 1){
                    loadGameModeFragment();
                }
                if (gameDataViewModel.getDisplayedFragment() == 2){
                    loadPlayerToCustomizeFragment();
                }
                if (gameDataViewModel.getDisplayedFragment() == 3){
                    loadLargeGameBoardFragment();
                }

                if (gameDataViewModel.getDisplayedFragment() == 4){
                    loadMediumGameBoardFragment();
                }

                if (gameDataViewModel.getDisplayedFragment() == 5){
                    loadSmallGameBoardFragment();
                }
            }
        });
    }

    private void loadGameModeFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_fill_screen_container);
        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_fill_screen_container, gameModeFragment).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragment_fill_screen_container, gameModeFragment).commit();
        }
    }

    private void loadPlayerToCustomizeFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_fill_screen_container);
        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_fill_screen_container, playerToCustomizeFragment).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragment_fill_screen_container, playerToCustomizeFragment).commit();
        }
    }

    private void loadLargeGameBoardFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_game_board_container);
        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_game_board_container, largeGameBoardFragment).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragment_game_board_container, largeGameBoardFragment).commit();
        }
    }

    private void loadMediumGameBoardFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_game_board_container);
        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_game_board_container, mediumGameBoardFragment).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragment_game_board_container, mediumGameBoardFragment).commit();
        }
    }

    private void loadSmallGameBoardFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_game_board_container);
        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_game_board_container, smallGameBoardFragment).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragment_game_board_container, smallGameBoardFragment).commit();
        }
    }
}