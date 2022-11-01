package edu.austral.dissis.chess.piece;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.Position;
import edu.austral.dissis.chess.Team;
import edu.austral.dissis.chess.rules.KnightRule;

import java.util.List;

public class Knight extends Piece{
    public Knight(Board board, Position position, Team team) {
        super(board, position, team, List.of(new KnightRule()), PieceType.KNIGHT);
    }
}
