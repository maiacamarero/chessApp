package edu.austral.dissis.chess.rules;

import edu.austral.dissis.chess.*;
import edu.austral.dissis.chess.movements.Castle;
import edu.austral.dissis.chess.movements.Horizontal;
import edu.austral.dissis.chess.movements.Movement;
import edu.austral.dissis.chess.movements.Vertical;
import edu.austral.dissis.chess.piece.Piece;

public class RookRule implements Rule {
    @Override
    public boolean validateRule(Board board, Movement movement) {
        Castle castleMove = new Castle(movement.getPiece().getPosition().getY());
        Vertical verticalMove = new Vertical();
        Horizontal horizontalMove = new Horizontal();
        Piece target = board.getPiece(movement.getFinalPosition());
        int difRow = movement.getFinalPosition().getY() - movement.getPiece().getPosition().getY();
        int difCol = movement.getFinalPosition().getX() - movement.getPiece().getPosition().getX();
        if (verticalMove.validateMove(board, movement) || horizontalMove.validateMove(board, movement)){
            // solo come pieza enemiga
            if (target != null){
                if (target.getTeam() == movement.getPiece().getTeam()){
                    return false;
                }
                return true;
            }
            // no salta piezas
            int diferencia = Math.abs(difRow) + Math.abs(difCol);
            for (int i = 1; i <= diferencia; i++) {
                Position path1 = new Position(movement.getPiece().getPosition().getX() + i * (difCol / diferencia), movement.getPiece().getPosition().getY() + i * (difRow / diferencia));
                Piece pieceInPath = board.getPiece(path1);
                if (pieceInPath != null){
                    return false;
                }else if (castleMove.isCastle()){
                    return true;
                }
                return true;
            }
        }
        return false;
    }
}
