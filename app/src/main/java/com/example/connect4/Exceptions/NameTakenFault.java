package com.example.connect4.Exceptions;

/*---------------------------------------------*
 *  Module: NameTakenFault                     *
 *  Description: Fault when name already taken *
 *  Author: Jauhar                             *
 *  ID: 21494299                               *
 *  Version: 1.0.0                             *
 *---------------------------------------------*/
public class NameTakenFault extends Exception{
    /*----------------------------------------------*
     * Constructor: NameTakenFault                  *
     * Description: Constructor to set fault message*
     * Parameters: message (String)                 *
     *----------------------------------------------*/
    public NameTakenFault(String message){
        super(message);
    }
}
