package edu.austral.dissis.chess;

import edu.austral.dissis.chess.movements.Movement;
import edu.austral.dissis.chess.piece.Piece;
import edu.austral.dissis.chess.piece.PieceType;
import edu.austral.dissis.chess.piece.Queen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private Map<Position, Piece> piecesPositions;
    private List<Piece> pieces;
    private List<Position> positions;
    private int preferredSize;
    private Map<Position, Piece> historicalPositions;
    private List<Piece> whites;
    private List<Piece> blacks;

    public Board(int size) {
        this.preferredSize = size;
        positions = new ArrayList<>();
        pieces = new ArrayList<>();
        whites = new ArrayList<>();
        blacks = new ArrayList<>();

        fillBoard();
        piecesPositions = new HashMap<>();
        historicalPositions = new HashMap<>();
    }

    public void fillBoard(){
        for (int i = 1; i <= preferredSize; i++) {
            for (int j = 1; j <= preferredSize; j++) {
                positions.add(new Position(i, j));
            }
        }
    }

    public boolean isInBounds(Position position) {
        if ((position.getX() <= preferredSize && position.getX() > 0) && (position.getY() <= preferredSize && position.getY() > 0)){
            return true;
        }else{
            return false;
        }
    }

    public Map<Position, Piece> getHistoricalPositions() {
        return historicalPositions;
    }

    public Piece getHistoricalPiece(Position position){
        return historicalPositions.get(position);
    }

    public void removeFromBoard(Piece piece) {
        piecesPositions.remove(piece.getPosition());
        pieces.remove(piece);
    }

    public Position getPosition(int x, int y){
        for (Position position : positions) {
            if (position.getX() == x && position.getY() == y) {
                return position;
            }
        }throw new RuntimeException("Invalid position.");
    }

    public Piece getPiece(Position position){
        if (isInBounds(position)){
            return piecesPositions.get(position);
        }throw new RuntimeException("Position is out of bounds.");
    }

    public void placePiece(Piece piece, Position position) {
        if (isInBounds(position)){
            piecesPositions.put(position, piece);
            historicalPositions.put(position, piece);
            pieces.add(piece);

            if (piece.getTeam() == Team.BLACK){
                blacks.add(piece);
            }else whites.add(piece);
        }
    }

    public Piece requestQueen(Piece piece, Position position, Team team){
        removeFromBoard(piece);
        Piece queen = new Queen(this, position, team);
        placePiece(queen, position);
        return queen;
    }


    public boolean isGameOver(){
        return isCheckmate(Team.WHITE) || isCheckmate(Team.BLACK);
    }

    private boolean isCheckmate(Team team) {
        List<Piece> attackers = getAttackingPieces(team);
        if (attackers.size() == 0) return false;
        //boolean checkmate = true;
        Position attackerPosition = null;
        Position kingPosition = positionOfKing(team);
        Piece king = getPiece(kingPosition);
        // verifico que no haya piezas en el camino
        for (Piece attacker : attackers) {
            attackerPosition = attacker.getPosition();
        }
        Piece attackingPiece = getPiece(attackerPosition);
        Movement movement = new Movement(attackingPiece, kingPosition);
        int increment = (movement.getPiece().getTeam().equals(Team.WHITE)) ? -1 : 1;
        Position attackPath = new Position(movement.getPiece().getPosition().getY() + increment, movement.getPiece().getPosition().getX());
        for (Position position : positions) {
            // si el king se puede mover a una posición donde no se vea atacado, retorna falso.
            if (king.moveTo(position) ) {
                if (positionOfKing(team) != position){
                    return false;
                }
            }
            Piece tmpPiece = getPiece(position);
            if (tmpPiece != null) {
                if (tmpPiece.getTeam() == king.getTeam() && tmpPiece.moveTo(attackPath)) {
                    return false;
                }
            }
        }
        return true;
    }

    private Position positionOfKing(Team team){
        for (Position position : positions) {
            Piece piece = getPiece(position);
            if (piece != null) {
                if (piece.getPieceType() == PieceType.KING && piece.getTeam() == team) {
                    return position;
                }
            }
        }throw new RuntimeException("El king esta fuera del tablero.");
    }

    private List<Piece> getAttackingPieces(Team team) {
        List<Piece> attackersPositions = new ArrayList<>();
        Position kingPosition = positionOfKing(team);
        Piece king = getPiece(kingPosition);
        for (Piece piece : pieces) {
            if (piece.moveTo(kingPosition) && king.getTeam() != piece.getTeam()) {
                attackersPositions.add(piece);
                break;
            }
        }
        return attackersPositions;
    }

    public boolean isCheck(Team team){
        if (getAttackingPieces(team).size() > 0){
            return true;
        }else return false;
    }

    /*public boolean moveMakesCheck(Piece piece, Movement movement){
        Piece tmpPiece = getPiece(movement.getFinalPosition());
    }*/


    public List<Piece> getPieces() {
        return pieces;
    }

    public List<Position> getPositions() {
        return positions;
    }
}
