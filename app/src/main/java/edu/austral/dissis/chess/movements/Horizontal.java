package edu.austral.dissis.chess.movements;

import edu.austral.dissis.chess.Board;

public class Horizontal implements MovementValidator {
    @Override
    public boolean validateMove(Board board, Movement movement) {
        if (!board.isInBounds(movement.getFinalPosition())){
            return false;
        }
        return movement.getPiece().getPosition().getY() == movement.getFinalPosition().getY();
    }
}
