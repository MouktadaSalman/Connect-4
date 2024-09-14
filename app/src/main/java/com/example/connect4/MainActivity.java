package com.example.connect4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.connect4.Fragments.GameModeSelectFragment;
import com.example.connect4.Fragments.LargeGameBoardFragment;
import com.example.connect4.Fragments.MediumGameBoardFragment;
import com.example.connect4.Fragments.SelectPlayerToCustomizeFragment;
import com.example.connect4.Fragments.SmallGameBoardFragment;

public class MainActivity extends AppCompatActivity {

    /* This block of code is for initialising the fragments. */
    /* ------------------------------------------------------------------------------------------------------- */
    GameModeSelectFragment gameModeSelectFragment = new GameModeSelectFragment();
    SelectPlayerToCustomizeFragment selectPlayerToCustomizeFragment = new SelectPlayerToCustomizeFragment();
    LargeGameBoardFragment largeGameBoardFragment = new LargeGameBoardFragment();
    MediumGameBoardFragment mediumGameBoardFragment = new MediumGameBoardFragment();
    SmallGameBoardFragment smallGameBoardFragment = new SmallGameBoardFragment();
    /* ------------------------------------------------------------------------------------------------------- */

    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initially we load the option to choose either single-player or two-player mode.
        loadGameModeFragment();

        // gameDataViewModel (of type GameData) is responsible for sharing data across fragments.
        GameData gameDataViewModel = new ViewModelProvider(this)
                .get(GameData.class);

        /* gameDataViewModel checks for any integer changes set by the different fragments when clicked to determine
         * the correct fragment to load.  */
        /* ------------------------------------------------------------------------------------------------------- */
        gameDataViewModel.getDisplayedFragment().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    loadGameModeFragment();
                }
                else if (integer == 2) {
                    loadPlayerToCustomizeFragment();
                }
                else if (integer == 3) {
                    loadLargeGameBoardFragment();
                }
                else if (integer == 4) {
                    loadMediumGameBoardFragment();
                }
                else if (integer == 5) {
                    loadSmallGameBoardFragment();
                }
            }
        });
        /* ------------------------------------------------------------------------------------------------------- */

    }

    /* Anything in this block of code are simply functions used to display fragments. */
    /* ----------------------------------------------------------------------------------------------------------- */
    private void loadGameModeFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_fill_screen_container);
        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_fill_screen_container, gameModeSelectFragment).commit();
        }
        else{
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStackImmediate();
            }
            fm.beginTransaction().replace(R.id.fragment_fill_screen_container, gameModeSelectFragment).commit();
        }
    }

    private void loadPlayerToCustomizeFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_fill_screen_container);
        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_fill_screen_container, selectPlayerToCustomizeFragment).addToBackStack(null).commit();
        }
        else{
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStackImmediate();
            }
            fm.beginTransaction().replace(R.id.fragment_fill_screen_container, selectPlayerToCustomizeFragment).commit();
        }
    }

    private void loadLargeGameBoardFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_game_board_container);
        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_game_board_container, largeGameBoardFragment).addToBackStack(null).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragment_game_board_container, largeGameBoardFragment).commit();
        }
    }

    private void loadMediumGameBoardFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_game_board_container);
        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_game_board_container, mediumGameBoardFragment).addToBackStack(null).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragment_game_board_container, mediumGameBoardFragment).commit();
        }
    }

    private void loadSmallGameBoardFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_game_board_container);
        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_game_board_container, smallGameBoardFragment).addToBackStack(null).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragment_game_board_container, smallGameBoardFragment).commit();
        }
    }
    /* ----------------------------------------------------------------------------------------------------------- */
}