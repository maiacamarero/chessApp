package edu.austral.dissis.chess.rules;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.movements.Movement;

public interface Rule {
    boolean validateRule(Board board, Movement movement);
}
