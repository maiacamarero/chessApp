package edu.austral.dissis.chess.piece;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.Position;
import edu.austral.dissis.chess.Team;
import edu.austral.dissis.chess.rules.QueenRule;

import java.util.List;

public class Queen extends Piece{
    public Queen(Board board, Position position, Team team) {
        super(board, position, team, List.of(new QueenRule()), PieceType.QUEEN);
    }
}
