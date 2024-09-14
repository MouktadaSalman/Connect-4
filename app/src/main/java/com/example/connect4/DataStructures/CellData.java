package com.example.connect4.DataStructures;

public class CellData {
    // Fields or attributes.
    private String colour;
    private int rowPosition;
    private int colPosition;

    // Constructor
    public CellData(String colour, int rowPosition, int colPosition) {
        this.colour = colour;
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
    }

    // Accessor
    public String getColour() {
        return colour;
    }
    // Mutator
    public void setColour(String colour) {
        this.colour = colour;
    }

    // Accessor
    public int getRowPosition() {
        return rowPosition;
    }
    // Mutator
    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    // Accessor
    public int getColPosition() {
        return colPosition;
    }
    // Mutator
    public void setColPosition(int colPosition) {
        this.colPosition = colPosition;
    }
}
