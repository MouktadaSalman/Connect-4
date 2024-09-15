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
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
        this.imageId = inImageid;
        this.isValid = false;
    }

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

    public void setIsValid(boolean validity) { isValid = validity; }

    public boolean getIsValid() { return isValid; }
}
