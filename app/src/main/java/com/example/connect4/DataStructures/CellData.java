package com.example.connect4.DataStructures;

public class CellData {
    /* Class Fields:
     * imageId -> Stores the image (Example: R.drawable.image).
     * isValid -> Used to check if a grid is valid to determine if circle can be placed.
     */
    private int imageId;
    private boolean isValid;

    /*----------------------------------------------*
     * Constructor: CellData                        *
     * Description: The constructor of the class    *
     * Parameters: inImageId (int)                  *
     *----------------------------------------------*/
    public CellData(int inImageId) {
        this.imageId = inImageId;
        this.isValid = false;
    }

    /*----------------------------------------------*
     * Method: getImageId                           *
     * Description: Gets image ID                   *
     * Parameters: none                             *
     * Result: imageId (int)                        *
     *----------------------------------------------*/
    public int getImageId() {
        return imageId;
    }

    /*----------------------------------------------*
     * Method: setImageId                           *
     * Description: Sets image ID                   *
     * Parameters: imageId (int)                    *
     * Result: none                                 *
     *----------------------------------------------*/
    public void setImageId(int imageId) { this.imageId = imageId; }

    /*----------------------------------------------*
     * Method: setIsValid                           *
     * Description: Sets validity of a grid.        *
     * Parameters: validity (boolean)               *
     * Result: none                                 *
     *----------------------------------------------*/
    public void setIsValid(boolean validity) { isValid = validity; }

    /*----------------------------------------------*
     * Method: getIsValid                           *
     * Description: Gets validity of a grid.        *
     * Parameters: none                             *
     * Result: isValid (boolean)                    *
     *----------------------------------------------*/
    public boolean getIsValid() { return isValid; }
}
