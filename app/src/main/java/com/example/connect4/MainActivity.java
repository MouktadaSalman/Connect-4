package com.example.connect4;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.connect4.Fragments.GameSettingsFragment;
import com.example.connect4.Fragments.MainMenuFragment;
import com.example.connect4.Fragments.PauseMenuFragment;
import com.example.connect4.Fragments.SelectPlayerToCustomizeFragment;
import com.example.connect4.Fragments.GameBoardFragment;
import com.example.connect4.Fragments.ToolBarFragment;

public class MainActivity extends AppCompatActivity {

    /* This block of code is for initialising the fragments. */
    /* ------------------------------------------------------------------------------------------------------- */
    MainMenuFragment mainMenuFragment = new MainMenuFragment();
    GameSettingsFragment gameSettingsFragment = new GameSettingsFragment();
    SelectPlayerToCustomizeFragment selectPlayerToCustomizeFragment = new SelectPlayerToCustomizeFragment();
    GameBoardFragment gameBoardFragment = new GameBoardFragment();
    ToolBarFragment toolBarFragment = new ToolBarFragment();
    PauseMenuFragment pauseMenuFragment = new PauseMenuFragment();
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
                }
                else if (integer == 3) {
                    // Get the board selected
                    loadGameBoardFragment();
                    loadToolBarFragment();
                }
                else if (integer == 4) {
                    loadPauseMenuFragment();
                }
                else if (integer == 5){
                    findViewById(R.id.fragment_pause_overlay).setVisibility(View.INVISIBLE);
                }
            }
        });
        /* ------------------------------------------------------------------------------------------------------- */
    }

    /* Anything in this block of code are simply functions used to display fragments. */
    /* ----------------------------------------------------------------------------------------------------------- */
    private void loadMainMenuFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_fill_screen_container);

        findViewById(R.id.fragment_game_board_container).setVisibility(View.INVISIBLE);
        findViewById(R.id.fragment_tool_bar_container).setVisibility(View.INVISIBLE);

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

        findViewById(R.id.fragment_game_board_container).setVisibility(View.INVISIBLE);
        findViewById(R.id.fragment_tool_bar_container).setVisibility(View.INVISIBLE);

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

        findViewById(R.id.fragment_game_board_container).setVisibility(View.INVISIBLE);
        findViewById(R.id.fragment_tool_bar_container).setVisibility(View.INVISIBLE);

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

    private void loadGameBoardFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_game_board_container);

        findViewById(R.id.fragment_game_board_container).setVisibility(View.VISIBLE);

        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_game_board_container, gameBoardFragment).addToBackStack(null).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragment_game_board_container, gameBoardFragment).commit();
        }
    }

    private void loadToolBarFragment() {
        Fragment frag = fm.findFragmentById(R.id.fragment_tool_bar_container);

        findViewById(R.id.fragment_tool_bar_container).setVisibility(View.VISIBLE);

        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_tool_bar_container, toolBarFragment).addToBackStack(null).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragment_tool_bar_container, toolBarFragment).commit();
        }
    }

    private void loadPauseMenuFragment(){
        Fragment frag = fm.findFragmentById(R.id.fragment_pause_overlay);

        findViewById(R.id.fragment_pause_overlay).setVisibility(View.VISIBLE);

        if (frag == null){
            fm.beginTransaction().add(R.id.fragment_pause_overlay, pauseMenuFragment).addToBackStack(null).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragment_pause_overlay, pauseMenuFragment).commit();
        }
        findViewById(R.id.fragment_pause_overlay).setVisibility(View.VISIBLE);
    }
    /* ----------------------------------------------------------------------------------------------------------- */
}