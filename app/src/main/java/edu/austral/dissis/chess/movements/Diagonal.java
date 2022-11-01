package edu.austral.dissis.chess.movements;

import edu.austral.dissis.chess.Board;

public class Diagonal implements MovementValidator {
    @Override
    public boolean validateMove(Board board, Movement movement) {
        if (!board.isInBounds(movement.getFinalPosition())){
            return false;
        }
        // Diferencia entre filas y columnas.
        // si las dif son iguales es movimiento en diagonal.
        int difRow, difCol;
        difRow = Math.abs(movement.getPiece().getPosition().getY() - movement.getFinalPosition().getY());
        difCol = Math.abs(movement.getPiece().getPosition().getX() - movement.getFinalPosition().getX());
        if (difRow == difCol){
            return true;
        }else return false;
    }

}
