package edu.austral.dissis.chess;

import edu.austral.dissis.chess.piece.Piece;
import edu.austral.dissis.chess.piece.PieceType;
import edu.austral.dissis.chess.rules.QueenRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private Map<Position, Piece> piecesPositions;
    private List<Piece> pieces;
    private List<Position> positions;
    private Map<Position, Piece> historicalPositions;
    private List<Piece> whites;
    private List<Piece> blacks;
    private int sizeX;
    private int sizeY;

    public Board(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        positions = new ArrayList<>();
        pieces = new ArrayList<>();
        whites = new ArrayList<>();
        blacks = new ArrayList<>();
        fillBoard();
        piecesPositions = new HashMap<>();
        historicalPositions = new HashMap<>();
    }

    public void fillBoard(){
        for (int i = 1; i <= sizeX; i++) {
            for (int j = 1; j <= sizeY; j++) {
                positions.add(new Position(i, j));
            }
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public boolean isInBounds(Position position) {
        if ((position.getX() <= sizeX && position.getX() > 0) && (position.getY() <= sizeY && position.getY() > 0)){
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
        }throw new RuntimeException("Invalid position."); // esto esta mal obvio
    }

    public List<Piece> getWhites() {
        return whites;
    }

    public List<Piece> getBlacks() {
        return blacks;
    }

    public Piece getPiece(Position position) {
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
        Piece queen = new Piece(piece.getBoard(), position, team, List.of(new QueenRule()), PieceType.QUEEN);
        placePiece(queen, position);
        return queen;
    }

//    public boolean isGameOver(){
//        return isCheckmate(Team.WHITE) || isCheckmate(Team.BLACK);
//    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public List<Position> getPositions() {
        return positions;
    }

    private Piece getKingByColor(Team team){
        Piece king = null;
        for (Piece pieceOnBoard: pieces) { // busco el rey
            if (pieceOnBoard != null && pieceOnBoard.getTeam() == team && pieceOnBoard.getPieceType() == PieceType.KING){
                king = pieceOnBoard;
                break;
            }
        }
        return king;
    }
}
