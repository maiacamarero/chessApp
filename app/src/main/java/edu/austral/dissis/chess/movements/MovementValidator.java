package edu.austral.dissis.chess.movements;

import edu.austral.dissis.chess.Board;

public interface MovementValidator {
    boolean validateMove(Board board, Movement movement);
    //boolean isCheck(); //jaque
}
