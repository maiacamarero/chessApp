package edu.austral.dissis.chess.movements;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.Position;
import edu.austral.dissis.chess.Team;
import edu.austral.dissis.chess.piece.Piece;
import edu.austral.dissis.chess.piece.PieceType;
import edu.austral.dissis.chess.piece.Rook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Castle implements MovementValidator{

    private boolean isCastle = false;

    @Override
    public boolean validateMove(Board board, Movement movement) {
//        Horizontal horizontalMove = new Horizontal();
//        Piece target = board.getPiece(movement.getFinalPosition());
        int owner = (movement.getPiece().getTeam().equals(Team.WHITE)) ? 0 : 1;
        int exitRow = (movement.getPiece().getTeam().equals(Team.WHITE)) ? 8 : 1;
//        if (horizontalMove.validateMove(board, movement) && (movement.getPiece().getPosition().getX() == exitRow) && (movement.getFinalPosition().getY() == exitRow + 2 * increment)){
//            // no come asi
//            if (target != null){
//                return false;
//            }
//            // verifico que no haya piezas en el camino
//            Position path = new Position(movement.getPiece().getPosition().getX() + increment, movement.getPiece().getPosition().getY());
//            target = board.getPiece(path);
//            if (target != null){
//                return false;
//            }
//            isCastle = true;
//            return true;
//        }
        Position targetPosition = movement.getFinalPosition();
        Piece targetPiece = board.getPiece(movement.getFinalPosition());

        List<Position> castlingMove = new ArrayList<>(Arrays.asList(new Position(3, 1), new Position(7, 1), new Position(3, 8), new Position(7, 8)));
        List<Position> rooksPosition = new ArrayList<>(Arrays.asList(new Position(1, 1), new Position(8, 1), new Position(1, 8), new Position(8, 8)));
        if (castlingMove.contains(targetPosition) && !targetPiece.hasMoved()){
            boolean success = false;
            if (targetPosition.equals(castlingMove.get(owner*2+1))){
                success = checkRook(board, rooksPosition, castlingMove, owner*2, 1);
            }else if (targetPosition.equals(castlingMove.get(owner*2))){
                success = checkRook(board, rooksPosition, castlingMove, owner*2, 0);
            }
            return success;
        }else {
            return targetPiece.moveTo(targetPosition);
        }
    }

    private boolean checkRook(Board board, List<Position> rooksPosition, List<Position> castlingMove, int owner, int moveType) {
//        Piece piece = board.getPiece(rooksPosition.get(owner + moveType));
//        if (piece.getPieceType() == PieceType.ROOK){
//            Rook rook = (Rook) piece;
//            if (!rook.hasMoved()){
//                boolean noCheck = true;
//                boolean freeBoard = true;
//                for (int i = 0; i < board.getPieces().size(); i++) {
//                    //if (board.getPieces().get(i).getTeam() == this.getTeam()) continue;
//                    Position position =
//                }
//            }
//        }
        return false;
    }

    public boolean isCastle() {
        return isCastle;
    }
}
