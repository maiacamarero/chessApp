package edu.austral.dissis.chess;

import edu.austral.dissis.chess.piece.*;

public class Game {

    private Board board;
    private Team currentTeam;
    private int sixeX;
    private int sizeY;

    public Game() {
        currentTeam = Team.WHITE;
    }

    public void setupClassic(){
        this.sixeX = 8;
        this.sizeY = 8;
        board = new Board(sixeX, sizeY);

        this.addPawn(board.getPosition(1, 2), Team.BLACK);
        this.addPawn(board.getPosition(2, 2), Team.BLACK);
        this.addPawn(board.getPosition(3, 2), Team.BLACK);
        this.addPawn(board.getPosition(4, 2), Team.BLACK);
        this.addPawn(board.getPosition(5, 2), Team.BLACK);
        this.addPawn(board.getPosition(6, 2), Team.BLACK);
        this.addPawn(board.getPosition(7, 2), Team.BLACK);
        this.addPawn(board.getPosition(8, 2), Team.BLACK);

        this.addRook(board.getPosition(1, 1), Team.BLACK);
        this.addKnight(board.getPosition(2, 1), Team.BLACK);
        this.addBishop(board.getPosition(3, 1), Team.BLACK);
        this.addQueen(board.getPosition(4, 1), Team.BLACK);
        this.addKing(board.getPosition(5, 1), Team.BLACK);
        this.addBishop(board.getPosition(6, 1), Team.BLACK);
        this.addKnight(board.getPosition(7, 1), Team.BLACK);
        this.addRook(board.getPosition(8, 1), Team.BLACK);

        this.addPawn(board.getPosition(1, 7), Team.WHITE);
        this.addPawn(board.getPosition(2, 7), Team.WHITE);
        this.addPawn(board.getPosition(3, 7), Team.WHITE);
        this.addPawn(board.getPosition(4, 7), Team.WHITE);
        this.addPawn(board.getPosition(5, 7), Team.WHITE);
        this.addPawn(board.getPosition(6, 7), Team.WHITE);
        this.addPawn(board.getPosition(7, 7), Team.WHITE);
        this.addPawn(board.getPosition(8, 7), Team.WHITE);

        this.addRook(board.getPosition(1, 8), Team.WHITE);
        this.addKnight(board.getPosition(2, 8), Team.WHITE);
        this.addBishop(board.getPosition(3, 8), Team.WHITE);
        this.addQueen(board.getPosition(4, 8), Team.WHITE);
        this.addKing(board.getPosition(5, 8), Team.WHITE);
        this.addBishop(board.getPosition(6, 8), Team.WHITE);
        this.addKnight(board.getPosition(7, 8), Team.WHITE);
        this.addRook(board.getPosition(8, 8), Team.WHITE);
    }

    public void setupCapablanca(){
        this.sixeX = 10;
        this.sizeY = 8;

        board = new Board(sixeX, sizeY);

        addPawn(board.getPosition(1, 2), Team.BLACK);
        addPawn(board.getPosition(2, 2), Team.BLACK);
        addPawn(board.getPosition(3, 2), Team.BLACK);
        addPawn(board.getPosition(4, 2), Team.BLACK);
        addPawn(board.getPosition(5, 2), Team.BLACK);
        addPawn(board.getPosition(6, 2), Team.BLACK);
        addPawn(board.getPosition(7, 2), Team.BLACK);
        addPawn(board.getPosition(8, 2), Team.BLACK);
        addPawn(board.getPosition(9, 2), Team.BLACK);
        addPawn(board.getPosition(10, 2), Team.BLACK);

        addRook(board.getPosition(1, 1), Team.BLACK);
        addKnight(board.getPosition(2, 1), Team.BLACK);
        addArchbishop(board.getPosition(3, 1), Team.BLACK);
        addBishop(board.getPosition(4, 1), Team.BLACK);
        addQueen(board.getPosition(5, 1), Team.BLACK);
        addKing(board.getPosition(6, 1), Team.BLACK);
        addBishop(board.getPosition(7, 1), Team.BLACK);
        addChancellor(board.getPosition(8, 1), Team.BLACK);
        addKnight(board.getPosition(9, 1), Team.BLACK);
        addRook(board.getPosition(10, 1), Team.BLACK);

        addPawn(board.getPosition(1, 7), Team.WHITE);
        addPawn(board.getPosition(2, 7), Team.WHITE);
        addPawn(board.getPosition(3, 7), Team.WHITE);
        addPawn(board.getPosition(4, 7), Team.WHITE);
        addPawn(board.getPosition(5, 7), Team.WHITE);
        addPawn(board.getPosition(6, 7), Team.WHITE);
        addPawn(board.getPosition(7, 7), Team.WHITE);
        addPawn(board.getPosition(8, 7), Team.WHITE);
        addPawn(board.getPosition(9, 7), Team.WHITE);
        addPawn(board.getPosition(10, 7), Team.WHITE);

        addRook(board.getPosition(1, 8), Team.WHITE);
        addKnight(board.getPosition(2, 8), Team.WHITE);
        addArchbishop(board.getPosition(3, 8), Team.WHITE);
        addBishop(board.getPosition(4, 8), Team.WHITE);
        addQueen(board.getPosition(5, 8), Team.WHITE);
        addKing(board.getPosition(6, 8), Team.WHITE);
        addBishop(board.getPosition(7, 8), Team.WHITE);
        addChancellor(board.getPosition(8, 8), Team.WHITE);
        addKnight(board.getPosition(9, 8), Team.WHITE);
        addRook(board.getPosition(10, 8), Team.WHITE);
    }

    public void setCurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
    }

    public Board getBoard() {
        return board;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public void addPawn(Position initialPosition, Team team) {
        Pawn pawn = new Pawn(board, initialPosition, team);
        board.placePiece(pawn, initialPosition);
    }

    public void addQueen(Position initialPosition, Team team){
        Queen queen = new Queen(board, initialPosition, team);
        board.placePiece(queen, initialPosition);
    }

    public void addKing(Position initialPosition, Team team){
        King king = new King(board, initialPosition, team);
        board.placePiece(king, initialPosition);
    }

    public void addBishop(Position initialPosition, Team team){
        Bishop bishop = new Bishop(board, initialPosition, team);
        board.placePiece(bishop, initialPosition);
    }

    public void addRook(Position initialPosition, Team team){
        Rook rook = new Rook(board, initialPosition, team);
        board.placePiece(rook, initialPosition);
    }

    public void addKnight(Position initialPosition, Team team){
        Knight knight = new Knight(board, initialPosition, team);
        board.placePiece(knight, initialPosition);
    }

    public void addArchbishop(Position initialPosition, Team team){
        Archbishop archbishop = new Archbishop(board, initialPosition, team);
        board.placePiece(archbishop, initialPosition);
    }

    public void addChancellor(Position initialPosition, Team team){
        Chancellor chancellor = new Chancellor(board, initialPosition, team);
        board.placePiece(chancellor, initialPosition);
    }

    public int getSixeX() {
        return sixeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
