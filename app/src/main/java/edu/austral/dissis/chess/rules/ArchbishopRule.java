package edu.austral.dissis.chess.rules;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.movements.Movement;

public class ArchbishopRule implements Rule{
    @Override
    public boolean validateRule(Board board, Movement movement) {
        Rule bishopRule = new BishopRule();
        Rule knightRule = new KnightRule();
        if (bishopRule.validateRule(board, movement) || knightRule.validateRule(board, movement)){
            return true;
        }return false;
    }
}
