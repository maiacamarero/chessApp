package edu.austral.dissis.chess.rules;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.movements.Movement;

public class QueenRule implements Rule {
    @Override
    public boolean validateRule(Board board, Movement movement) {
        Rule rookRule = new RookRule();
        Rule bishopRule = new BishopRule();
        if (rookRule.validateRule(board, movement) || bishopRule.validateRule(board, movement)){
            return true;
        }
        return false;
    }
}
