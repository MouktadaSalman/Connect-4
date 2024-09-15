package com.example.connect4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.connect4.Fragments.CustomizeProfileFragment;
import com.example.connect4.Fragments.GameSettingsFragment;
import com.example.connect4.Fragments.MainMenuFragment;
import com.example.connect4.Fragments.SelectPlayerToCustomizeFragment;
import com.example.connect4.Fragments.GameBoardFragment;

public class MainActivity extends AppCompatActivity {

    /* This block of code is for initialising the fragments. */
    /* ------------------------------------------------------------------------------------------------------- */
    MainMenuFragment mainMenuFragment = new MainMenuFragment();
    GameSettingsFragment gameSettingsFragment = new GameSettingsFragment();
    SelectPlayerToCustomizeFragment selectPlayerToCustomizeFragment = new SelectPlayerToCustomizeFragment();
    CustomizeProfileFragment customizeProfileFragment = new CustomizeProfileFragment();
    GameBoardFragment gameBoardFragment = new GameBoardFragment();
    /* ------------------------------------------------------------------------------------------------------- */

    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Initially we load the option to choose either single-player or two-player mode.
        loadMainMenuFragment();

        // gameDataViewModel (of type GameData) is responsible for sharing data across fragments.
        GameData gameDataViewModel = new ViewModelProvider(this)
                .get(GameData.class);

        /* gameDataViewModel checks for any integer changes set by the different fragments when clicked to determine
         * the correct fragment to load.  */
        /* ------------------------------------------------------------------------------------------------------- */
        gameDataViewModel.getDisplayedFragment().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 0) {
                    loadMainMenuFragment();
                }
                else if (integer == 1) {
                    loadGameModeFragment();
                }
                else if (integer == 2) {
                    loadPlayerToCustomizeFragment();
                    gameDataViewModel.getSelectedPlayer().observe(MainActivity.this,
                    new Observer<Integer>() {
                        @Override
                        public void onChanged(Integer value) {
                            if(value == 1 || value == 2){
                                loadCustomizeFragment();
                            }
                        }
                    });
                }
                else if (integer == 3) {
                    // Get the board selected
                    loadGameBoardFragment();
                }
            }
        });
        /* ------------------------------------------------------------------------------------------------------- */

    }

    /* Anything in this block of code are simply functions used to display fragments. */
    /* ----------------------------------------------------------------------------------------------------------- */
    private void loadMainMenuFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_fill_screen_container);
        if (frag == null){
            fm.beginTransaction()
                    .add(R.id.fragment_fill_screen_container, mainMenuFragment)
                    .commit();
        }
        else{
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStackImmediate();
            }
            fm.beginTransaction().replace(R.id.fragment_fill_screen_container, mainMenuFragment).commit();
        }
    }

    private void loadGameModeFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_fill_screen_container);
        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_fill_screen_container, gameSettingsFragment).commit();
        }
        else{
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStackImmediate();
            }
            fm.beginTransaction().replace(R.id.fragment_fill_screen_container, gameSettingsFragment).commit();
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

    private void loadCustomizeFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_endgame_container);
        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_endgame_container, customizeProfileFragment).addToBackStack(null).commit();
        }
        else{
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStackImmediate();
            }
            fm.beginTransaction().replace(R.id.fragment_endgame_container, customizeProfileFragment).commit();
        }
    }

    private void loadGameBoardFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_game_board_container);
        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_game_board_container, gameBoardFragment).addToBackStack(null).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragment_game_board_container, gameBoardFragment).commit();
        }
    }
    /* ----------------------------------------------------------------------------------------------------------- */
}