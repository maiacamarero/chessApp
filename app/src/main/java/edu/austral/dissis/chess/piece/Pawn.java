package edu.austral.dissis.chess.piece;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.Position;
import edu.austral.dissis.chess.Team;
import edu.austral.dissis.chess.rules.PawnRule;

import java.util.List;

public class Pawn extends Piece{
    public Pawn(Board board, Position position, Team team) {
        super(board, position, team, List.of(new PawnRule()), PieceType.PAWN);
    }
}
