package com.example.connect4.DataStructures;

public class CellData {
    // Fields or attributes.
//    private String colour;
    private int rowPosition;
    private int colPosition;
    private int imageId;

    // Constructor
    public CellData(int inImageid, int rowPosition, int colPosition) {
//        this.colour = colour;
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
        this.imageId = inImageid;
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

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
