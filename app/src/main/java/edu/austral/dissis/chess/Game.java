package edu.austral.dissis.chess;

import edu.austral.dissis.chess.piece.*;

import java.util.Scanner;

public class Game {

    private Board board;
    private int preferredSize = 8;
    private Team currentTeam;
    Scanner userInput = new Scanner(System.in);

    public Game() {
        board = new Board(preferredSize);
        currentTeam = Team.WHITE;
    }

    public void setup(){
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
        this.addQueen(board.getPosition(5, 1), Team.BLACK);
        this.addKing(board.getPosition(4, 1), Team.BLACK);
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
        this.addQueen(board.getPosition(5, 8), Team.WHITE);
        this.addKing(board.getPosition(4, 8), Team.WHITE);
        this.addBishop(board.getPosition(6, 8), Team.WHITE);
        this.addKnight(board.getPosition(7, 8), Team.WHITE);
        this.addRook(board.getPosition(8, 8), Team.WHITE);
        currentTeam = Team.WHITE;
    }

    public void setCurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
    }

    public void gameLoop(){
        boolean continueGame = true;
        setup();
        while(continueGame){

            System.out.println("que pieza quiere mover? X-loc: ");
            int nextX = userInput.nextInt();
            System.out.println("Y-loc: ");
            int nextY = userInput.nextInt();
            Position position = board.getPosition(nextX, nextY);
            Piece target = board.getPiece(position);

            if (target == null){
                System.out.println("no hay ninguna pieza en esa posicion.");
                continueGame = true;
            }else if (target.getTeam() != currentTeam){
                System.out.println("no es tuya");
                continueGame = false;
            }else {
                System.out.println("A donde se quiere mover? X-loc: ");
                nextX = userInput.nextInt();
                System.out.println("Y-loc: ");
                nextY = userInput.nextInt();
                Position finalPos = board.getPosition(nextX, nextY);

                target.makeMove(finalPos);

            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public int getPreferredSize() {
        return preferredSize;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public void addPawn(Position initialPosition, Team team){
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

}
