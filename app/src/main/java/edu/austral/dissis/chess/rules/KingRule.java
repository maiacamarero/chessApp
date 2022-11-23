package edu.austral.dissis.chess.rules;

import edu.austral.dissis.chess.*;
import edu.austral.dissis.chess.movements.*;
import edu.austral.dissis.chess.piece.Piece;

public class KingRule implements Rule {
    @Override
    public boolean validateRule(Board board, Movement movement) {
        Castle castle = new Castle(movement.getPiece().getPosition().getY());
        Check check = new Check();
        Diagonal diagonalMove = new Diagonal();
        Vertical verticalMove = new Vertical();
        Horizontal horizontalMove = new Horizontal();
        Piece target = board.getPiece(movement.getFinalPosition());
        int difRow = movement.getPiece().getPosition().getY() - movement.getFinalPosition().getY();
        int difCol = movement.getPiece().getPosition().getX() - movement.getFinalPosition().getX();
        if (diagonalMove.validateMove(board, movement) || verticalMove.validateMove(board, movement) || horizontalMove.validateMove(board, movement)){
            //se mueve en el cuadrado que lo rodea
            if (Math.abs(difRow) <= 1 && Math.abs(difCol) <= 1){
                // come en las mismas direcciones en las que se mueve.
                if (target != null){
                    if (target.getTeam().equals(movement.getPiece().getTeam())){
                        return false;
                    }
                    return true;
                }
                return true;
            }
            int row = Math.abs(difRow);
            int col = Math.abs(difCol);
            // chequea si esta amenazado o si ya se movio, en cualquiera de esos casos no lo deja hacer castle
            if (row + col == 1 || movement.getPiece().hasMoved()){
                return false;
            }
            if (col > 1) {
                 castle.validateMove(board, movement);
            }
        }
        return false;
    }
}
