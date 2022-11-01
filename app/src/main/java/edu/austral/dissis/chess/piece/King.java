package edu.austral.dissis.chess.piece;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.Position;
import edu.austral.dissis.chess.Team;
import edu.austral.dissis.chess.rules.KingRule;

import java.util.List;

public class King extends Piece{
    public King(Board board, Position position, Team team) {
        super(board, position, team, List.of(new KingRule()), PieceType.KING);
    }
}
