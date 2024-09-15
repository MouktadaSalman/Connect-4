package com.example.connect4.DataStructures;

import com.example.connect4.R;

public class CellData {
    // Fields or attributes.
    private String colour;
    private int rowPosition;
    private int colPosition;
    private int imageId;
    private boolean isEmpty;

    // Constructor
    public CellData(int inImageid, int rowPosition, int colPosition, String colour) {
//        this.colour = colour;
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
        this.imageId = inImageid;
        this.isEmpty = true;
        this.colour = colour;
    }

    // Accessor
//    public String getColour() {
//        return colour;
//    }
//    // Mutator
//    public void setColour(String colour) {
//        this.colour = colour;
//    }
//
    public void resetImage(){
        imageId = R.drawable.empty_cell;
        isEmpty = true;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) { this.imageId = imageId; }

    public boolean isEmpty() { return isEmpty; }

    public void setEmpty(boolean empty) { isEmpty = empty; }

    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }

}
