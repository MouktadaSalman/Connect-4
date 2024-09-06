package com.example.connect4.Exceptions;

/*---------------------------------------------*
 *  Module: PlayerDoesNotExistFault            *
 *  Description: Fault when attempting to      *
 *  Author: Jauhar                             *
 *  ID: 21494299                               *
 *  Version: 1.0.0                             *
 *---------------------------------------------*/
public class PlayerDoesNotExistFault extends Exception{
    /*----------------------------------------------*
     * Constructor: PlayerDoesNotExistFault         *
     * Description: Constructor to set fault message*
     * Parameters: message (String)                 *
     *----------------------------------------------*/
    public PlayerDoesNotExistFault(String message){
        super(message);
    }
}
