package edu.austral.dissis.chess.exception;

public class PositionOutOfBoundsException extends Exception{

    public PositionOutOfBoundsException(){
        super("La posición solicitada se encuentra fuera del tablero");
    }
}
