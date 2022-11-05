package edu.austral.dissis.chess.rules;

import edu.austral.dissis.chess.*;
import edu.austral.dissis.chess.movements.Diagonal;
import edu.austral.dissis.chess.movements.Movement;
import edu.austral.dissis.chess.movements.Vertical;
import edu.austral.dissis.chess.piece.Piece;
import edu.austral.dissis.chess.piece.Queen;

public class PawnRule implements Rule {
    @Override
    public boolean validateRule(Board board, Movement movement) {
        Vertical verticalMove = new Vertical(); // se mueve
        Diagonal diagonalMove = new Diagonal(); // come
        Piece target = board.getPiece(movement.getFinalPosition());
        int increment = (movement.getPiece().getTeam().equals(Team.WHITE)) ? -1 : 1;
        int exitRow = (movement.getPiece().getTeam().equals(Team.WHITE)) ? 7 : 2;
        //movimiento normal de los peones
        if (verticalMove.validateMove(board, movement) && (movement.getPiece().getPosition().getY() + increment == movement.getFinalPosition().getY())){
            //no come asi
            if (target != null){
                return false;
            }else{
                return true;
            }
        }
        //movimiento inicial
        else if ((verticalMove.validateMove(board, movement)) && (movement.getPiece().getPosition().getY() == exitRow) && (movement.getFinalPosition().getY() == exitRow + 2 * increment)){
            // no come asi
            if (target != null){
                return false;
            }
            // verifico que no haya piezas en el camino
            Position path = new Position(movement.getPiece().getPosition().getX(), movement.getPiece().getPosition().getY() + increment);
            target = board.getPiece(path);
            if (target != null){
                return false;
            }
            return true;
        }
        // movimiento para comer
        else if (diagonalMove.validateMove(board, movement) && (movement.getFinalPosition().getY() == movement.getPiece().getPosition().getY() + increment)){
            //no hay nada para comer
            if (target == null){
                return false;
            }
            //no es pieza rival
            if (target.getTeam().equals(movement.getPiece().getTeam())){
                return false;
            }
            return true;
        }
        return false;
    }

    public Piece requestQueen(Board board, Movement movement) {
        Piece target = board.getPiece(movement.getFinalPosition());
        if (validateRule(board, movement)) {
            if (movement.getFinalPosition().getY() == 1 || movement.getFinalPosition().getY() == 8) {
                target = new Queen(board, movement.getFinalPosition(), target.getTeam());
            }
        }
        return target;
    }
}
