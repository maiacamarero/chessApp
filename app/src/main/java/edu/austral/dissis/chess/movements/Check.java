package edu.austral.dissis.chess.movements;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.Position;
import edu.austral.dissis.chess.Team;
import edu.austral.dissis.chess.piece.Piece;
import edu.austral.dissis.chess.piece.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Check implements MovementValidator{
    @Override
    public boolean validateMove(Board board, Movement movement) {
        Position position = board.getPosition(movement.getPiece().getPosition().getX(), movement.getPiece().getPosition().getY());
        if (position == null) return false;
        Piece piece = board.getPiece(position);
        if (piece == null) return false;
        Team playerTeam = movement.getPiece().getTeam();
        Team opponentTeam = playerTeam == Team.WHITE ? Team.BLACK : Team.WHITE;
        List<Piece> piecesOnBoard = board.getPieces();
        for (Piece pieceOnBoard: piecesOnBoard){ //chequea todas las piezas enemigas
            if (pieceOnBoard != null && pieceOnBoard.getTeam() == playerTeam){
                for (Piece attacker : getAttackingPieces(board, movement)) {
                    if (attacker != null){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private List<Piece> getAttackingPieces(Board board, Movement movement) {
        List<Piece> attackersPositions = new ArrayList<>();
        Team playerTeam = movement.getPiece().getTeam();
        //List<Piece> piecesOnBoard = board.getPieces();
        Position kingPosition = new Position(0, 0);
        if (playerTeam == Team.BLACK){
            for (int i = 0; i < board.getBlacks().size(); i++) {
                if (board.getBlacks().get(i) != null && board.getBlacks().get(i).getPieceType() == PieceType.KING){
                    kingPosition = board.getBlacks().get(i).getPosition();
                    break;
                }
            }
        }else {
            for (int i = 0; i < board.getWhites().size(); i++) {
                if (board.getWhites().get(i) != null && board.getWhites().get(i).getPieceType() == PieceType.KING){
                    kingPosition = board.getWhites().get(i).getPosition();
                    break;
                }
            }
        }
//        for (Piece pieceOnBoard: piecesOnBoard) { // busco el rey
//            if (pieceOnBoard != null && pieceOnBoard.getTeam() == playerTeam.getEnemyTeam() && pieceOnBoard.getPieceType() == PieceType.KING){
//                kingPosition = pieceOnBoard.getPosition();
//                break;
//            }
//        }
        Piece king = board.getPiece(kingPosition);
        for (Piece piece : board.getPieces()) {
            if (piece.moveTo(kingPosition) && king.getTeam() != piece.getTeam()) {
                attackersPositions.add(piece);
                break;
            }
        }
        return attackersPositions;
    }
}
