package edu.austral.dissis.chess.piece;

import edu.austral.dissis.chess.*;
import edu.austral.dissis.chess.rules.BishopRule;

import java.util.List;

public class Bishop extends Piece {
    public Bishop(Board board, Position position, Team team) {
        super(board, position, team, List.of(new BishopRule()), PieceType.BISHOP);
    }
}
