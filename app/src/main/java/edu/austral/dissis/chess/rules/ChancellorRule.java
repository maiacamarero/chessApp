package edu.austral.dissis.chess.rules;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.movements.Movement;

public class ChancellorRule implements Rule{
    @Override
    public boolean validateRule(Board board, Movement movement) {
        Rule rookRule = new RookRule();
        Rule knightRule = new KnightRule();
        if (rookRule.validateRule(board, movement) || knightRule.validateRule(board, movement)){
            return true;
        }
        return false;
    }
}
