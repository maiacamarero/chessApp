package edu.austral.dissis.chess.piece;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.Position;
import edu.austral.dissis.chess.Team;
import edu.austral.dissis.chess.rules.ArchbishopRule;

import java.util.List;

public class Archbishop extends Piece{
    public Archbishop(Board board, Position position, Team team) {
        super(board, position, team, List.of(new ArchbishopRule()), PieceType.ARCHBISHOP);
    }
}
