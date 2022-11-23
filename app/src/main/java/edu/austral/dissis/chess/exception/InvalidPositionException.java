package edu.austral.dissis.chess.exception;

public class InvalidPositionException extends Exception{

    public InvalidPositionException(){
        super("La posición es invalida");
    }

}
