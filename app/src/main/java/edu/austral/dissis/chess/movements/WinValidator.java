package edu.austral.dissis.chess.movements;

import edu.austral.dissis.chess.Board;

public class WinValidator implements MovementValidator{
    @Override
    public boolean validateMove(Board board, Movement movement) {
        return false;
    }
}
