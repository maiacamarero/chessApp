package edu.austral.dissis.chess.piece;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.Position;
import edu.austral.dissis.chess.Team;
import edu.austral.dissis.chess.rules.RookRule;

import java.util.List;

public class Rook extends Piece{
    public Rook(Board board, Position position, Team team) {
        super(board, position, team, List.of(new RookRule()), PieceType.ROOK);
    }
}
