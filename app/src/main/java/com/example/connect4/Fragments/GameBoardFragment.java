package com.example.connect4.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connect4.DataStructures.CellData;
import com.example.connect4.DataStructures.RecyclerViewAdapter;
import com.example.connect4.GameData;
import com.example.connect4.R;

public class GameBoardFragment extends Fragment {

    private GameData gameDataViewModel;
    private RecyclerView recyclerView;
    private ArrayList<CellData> recyclerDataArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game_board, container, false);
        gameDataViewModel = new ViewModelProvider(getActivity())
                .get(GameData.class);

        double rowHeight = 0;
        int numberOfCells = 0;
        int numberOfColumns = 0;
        int screenHeight;

        recyclerView = view.findViewById(R.id.idCellRV);

        recyclerDataArrayList = new ArrayList<>();
        screenHeight = getResources().getDisplayMetrics().heightPixels / 2; // get the screen height of the device divided by 2

        LiveData<Integer> dataB = gameDataViewModel.getSelectedBoard();

        // Initialise
        int board = 0;

        // get the board selected
        if(dataB.getValue() != null){
            board = dataB.getValue();
        }

        // set the number of cells and the row height based on the board selected
        switch (board) {
            // Large grid 8X7
            case 1:
                numberOfCells = 56;
                numberOfColumns = 7;
                rowHeight = screenHeight / 8.3;
                gameDataViewModel.setGridRows(8);
                gameDataViewModel.setGridColumns(numberOfColumns);
                break;

            // Medium grid 7X6
            case 2:
                numberOfCells = 42;
                numberOfColumns = 6;
                rowHeight = screenHeight / 7.0;
                gameDataViewModel.setGridRows(7);
                gameDataViewModel.setGridColumns(numberOfColumns);
                break;

            // Small grid 6X5
            case 3:
                numberOfCells = 30;
                numberOfColumns = 5;
                rowHeight = screenHeight / 5.8;
                gameDataViewModel.setGridRows(5);
                gameDataViewModel.setGridColumns(numberOfColumns);
                break;
        }

        // create items views for the recycler view based on the number of cells for the grid size selected
        for (int r = 0; r < (numberOfCells / numberOfColumns); r++) {
            for (int c = 0; c < numberOfColumns; c++) {
                recyclerDataArrayList.add(new CellData(R.drawable.empty_cell, r, c));
            }
        }

        // create the adapter for the recycler view
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(recyclerDataArrayList, rowHeight, gameDataViewModel);

        // make the layout of the recycler view a grid with the number of columns selected
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), numberOfColumns);

        // set the layout and adapter of the recycler view
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
