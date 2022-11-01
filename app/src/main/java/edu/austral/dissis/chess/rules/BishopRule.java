package edu.austral.dissis.chess.rules;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.Position;
import edu.austral.dissis.chess.movements.Diagonal;
import edu.austral.dissis.chess.movements.Movement;
import edu.austral.dissis.chess.piece.Piece;

public class BishopRule implements Rule {
    @Override
    public boolean validateRule(Board board, Movement movement) {
        Diagonal diagonalMove = new Diagonal();
        Piece target = board.getPiece(movement.getFinalPosition());
        if (diagonalMove.validateMove(board, movement)){
            if (target != null){
                if (target.getTeam().equals(movement.getPiece().getTeam())){
                    return false;
                }
            }
            //  el alfil no puede saltar piezas
            int difRow = Math.abs(movement.getPiece().getPosition().getY() - movement.getFinalPosition().getY());
            Position path;
            for (int i = 1; i < difRow; i++) {
                path = new Position(movement.getPiece().getPosition().getX() + i * ((movement.getFinalPosition().getX() - movement.getPiece().getPosition().getX())/difRow), movement.getPiece().getPosition().getY() + i * ((movement.getFinalPosition().getY() - movement.getPiece().getPosition().getY())/difRow));
                Piece pieceInPath = board.getPiece(path);
                if (pieceInPath != null){
                    return false;
                }
            }
            return true;
        }else return false;
    }
}
