package com.example.connect4.DataStructures;

import com.example.connect4.R;

public class CellData {
    // Fields or attributes.
    private String colour;
    private int rowPosition;
    private int colPosition;
    private int imageId;
    private boolean isValid;
    private int positionInArray;

    // Constructor
    public CellData(int inImageid, int rowPosition, int colPosition, String colour) {
//        this.colour = colour;
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
        this.imageId = inImageid;
        this.isValid = false;
        this.colour = colour;
        this.positionInArray = 0;
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
        isValid = false;
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

    public boolean isEmpty() { return isValid; }

    public void setIsValid(boolean empty) { isValid = empty; }

    public boolean getIsValid() { return isValid; }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getPositionInArray(){return positionInArray;}

    public void setPositionInArray(int positionInArray){this.positionInArray = positionInArray;}

}
