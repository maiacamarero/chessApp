package edu.austral.dissis.chess.rules;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.movements.Movement;
import edu.austral.dissis.chess.piece.Piece;

public class KnightRule implements Rule {
    @Override
    public boolean validateRule(Board board, Movement movement) {
        if (board.isInBounds(movement.getFinalPosition())){
            //no como bien, cuando come no desaparece la ficha comida
            int difRow, difCol;
            difRow = movement.getPiece().getPosition().getY() - movement.getFinalPosition().getY();
            difCol = movement.getPiece().getPosition().getX() - movement.getFinalPosition().getX();
            Piece target = board.getPiece(movement.getFinalPosition());
            //el caballo se mueve 1 en filas y 2 en columnas o viceversa.
            if (((Math.abs(difRow) == 1) && (Math.abs(difCol) == 2)) || ((Math.abs(difRow) == 2) && (Math.abs(difCol) == 1))){
                // come siempre
                if (target != null){
                    if (target.getTeam().equals(movement.getPiece().getTeam())){
                        return false;
                    }
                    return true;
                }
                return true;
            }else{
                return false;
            }
        }return false;

    }
}
