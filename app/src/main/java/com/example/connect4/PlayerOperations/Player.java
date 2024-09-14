package com.example.connect4.PlayerOperations;

/*---------------------------------------------*
 *  Module: PlayerData                         *
 *  Description: Holds the players info/stats  *
 *  Author: Jauhar                             *
 *  ID: 21494299                               *
 *  Version: 1.0.0                             *
 *---------------------------------------------*/

public class Player {
    /* Class Fields:
     * playerName -> the players name
     * pAvatar -> the players avatar
     * wins -> the player wins (both against ai + other player)
     * loss -> the player loss (both against ai + other player)
     * totalGames -> the player games played (both against ai + other player)
     * winPercent -> the player winPercent (both against ai + other player)
     */
    private String playerName;
    private int avatarID;
    private int playerAvatar;
    private int wins;
    private int loss;
    private int totalGames;
    private double winPercent;

    /*----------------------------------------------*
     * Constructor: PlayerData                      *
     * Description: The constructor of the class    *
     * Parameters: pName (string), pAvatar (string) *
     *----------------------------------------------*/
    public Player(String pName, int pAvatar){
        playerName = pName;
        avatarID = pAvatar;
        playerAvatar = 0;
        wins = 0;
        loss = 0;
        totalGames = 0;
        winPercent = 0.0;
    }

    /*----------------------------------------------*
     * Method: getPlayerName                        *
     * Description: Get player's username           *
     * Parameters: none                             *
     * Result: playerName (String)                  *
     *----------------------------------------------*/
    public String getPlayerName(){
        return playerName;
    }

    /*----------------------------------------------*
     * Method: setPlayerName                        *
     * Description: Set player's username           *
     * Parameters: none                             *
     * Result: playerName (String)                  *
     *----------------------------------------------*/
    public void setPlayerName(String name) { playerName = name; }

    /*----------------------------------------------*
     * Method: getAvatarID                          *
     * Description: Get player's avatar             *
     * Parameters: none                             *
     * Result: avatarID (int)                       *
     *----------------------------------------------*/
    public int getAvatarID(){
        return avatarID;
    }

    /*----------------------------------------------*
     * Method: setAvatarID                          *
     * Description: Set player's avatar             *
     * Parameters: none                             *
     * Result: avatarID (int)                       *
     *----------------------------------------------*/
    public void setAvatarID(int id) { avatarID = id; }

    /*----------------------------------------------*
     * Method: getPlayerAvatar                      *
     * Description: Get player's avatar             *
     * Parameters: none                             *
     * Result: playerAvatar (int)                *
     *----------------------------------------------*/
    public int getPlayerAvatar(){
        return playerAvatar;
    }

    /*----------------------------------------------*
     * Method: setPlayerAvatar                      *
     * Description: Set player's avatar             *
     * Parameters: none                             *
     * Result: playerAvatar (int)                *
     *----------------------------------------------*/
    public void setPlayerAvatar(int id) { playerAvatar = id; }

    /*----------------------------------------------*
     * Method: addWin                               *
     * Description: Add a win for player            *
     * Parameters: none                             *
     * Result: none                                 *
     *----------------------------------------------*/
    public void addWin(){
        wins++;
        totalGames++;

        //Adjust win ratio
        winPercent = (double)wins/(double)totalGames;
    }

    /*----------------------------------------------*
     * Method: addLoss                              *
     * Description: Add a loss for player           *
     * Parameters: none                             *
     * Result: none                                 *
     *----------------------------------------------*/
    public void addLoss(){
        loss++;
        totalGames++;

        //Adjust win ratio
        winPercent = (double)wins/(double)totalGames;
    }

    /*----------------------------------------------*
     * Method: getWins                              *
     * Description: Get player's wins               *
     * Parameters: none                             *
     * Result: wins (int)                           *
     *----------------------------------------------*/
    public int getWins(){
        return wins;
    }

    /*----------------------------------------------*
     * Method: getLoss                              *
     * Description: Get player's loss               *
     * Parameters: none                             *
     * Result: loss (int)                           *
     *----------------------------------------------*/
    public int getLoss(){
        return loss;
    }

    /*----------------------------------------------*
     * Method: winPercentage                        *
     * Description: Get player's win ratio          *
     * Parameters: none                             *
     * Result: winPercent (double)                  *
     *----------------------------------------------*/
    public double winPercentage(){
        return winPercent;
    }
}
