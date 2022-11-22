package edu.austral.dissis.chess.piece;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.Position;
import edu.austral.dissis.chess.Team;
import edu.austral.dissis.chess.rules.ChancellorRule;

import java.util.List;

public class Chancellor extends Piece{
    public Chancellor(Board board, Position position, Team team) {
        super(board, position, team, List.of(new ChancellorRule()), PieceType.CHANCELLOR);
    }
}
