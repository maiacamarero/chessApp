package edu.austral.dissis.chess;

public enum Team {

    WHITE, BLACK;

    @Override
    public String toString() {
        if(this == WHITE) {
            return "White";
        }
        return "Black";
    }


}
