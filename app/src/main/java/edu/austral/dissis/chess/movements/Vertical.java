package edu.austral.dissis.chess.movements;

import edu.austral.dissis.chess.Board;

public class Vertical implements MovementValidator {
    @Override
    public boolean validateMove(Board board, Movement movement) {
        if (board.isInBounds(movement.getFinalPosition()) && (movement.getPiece().getPosition().getX() == movement.getFinalPosition().getX())){
            return true;
        }return false;
    }

}
