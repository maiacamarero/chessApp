package edu.austral.dissis.chess;

import edu.austral.dissis.chess.adapter.GameIntegration;
import edu.austral.dissis.chess.gui.*;
import edu.austral.dissis.chess.piece.Piece;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Scanner;

public class MyGameEngine implements GameEngine {

    Game game;
    GameIntegration gameIntegration = new GameIntegration();
    Scanner sc = new Scanner(System.in);


    public MyGameEngine() {
        game = new Game();
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Position fromPosition = new Position(move.getFrom().getColumn(), move.getFrom().getRow());
        Piece piece = game.getBoard().getPiece(fromPosition);
        Position toPosition = game.getBoard().getPosition(move.getTo().getColumn(), move.getTo().getRow());
        if (piece == null){
            return new InvalidMove("No hay nada en esa posición.");
        } else if (piece.getTeam() != game.getCurrentTeam()){
            return new InvalidMove("Es el turno del equipo " + game.getCurrentTeam().toString());
        } /*else if (game.getBoard().isGameOver()) { // game over cuando esta en jaque :)
            return new GameOver(gameIntegration.translateTeam(game.getCurrentTeam().getEnemyTeam()));
        }*/else {
            //no anda
            /*if (piece.getPieceType() == PieceType.PAWN && (toPosition.getY() == 1 || toPosition.getY() == 8)){
                piece = game.getBoard().requestQueen(piece, toPosition, piece.getTeam());
            }*/

            boolean moveTo = piece.moveGeneric(toPosition);
            List<ChessPiece> pieces = gameIntegration.translatePieces(game.getBoard(), game.getBoard().getPieces());
            PlayerColor playerColor = getCurrentPlayer(moveTo, gameIntegration.translateTeam(game.getCurrentTeam()));
            assert playerColor != null;
            return new NewGameState(pieces, playerColor);
        }
    }

    private PlayerColor getCurrentPlayer(boolean moveTo, PlayerColor playerColor) {
        if (moveTo && game.getCurrentTeam() == Team.WHITE){
            playerColor = gameIntegration.translateTeam(Team.BLACK);
            game.setCurrentTeam(Team.BLACK);
        }else if (moveTo){
            playerColor = gameIntegration.translateTeam(Team.WHITE);
            game.setCurrentTeam(Team.WHITE);
        }
        return playerColor;
    }

    @NotNull
    @Override
    public InitialState init() {
        game.setupClassic();
        //game.setupCapablanca();
        //chooseConfiguration();
        BoardSize boardSize = gameIntegration.translateSize(game.getSixeX(), game.getSizeY());
        List<ChessPiece> pieces = gameIntegration.translatePieces(game.getBoard(), game.getBoard().getPieces());
        PlayerColor playerColor = gameIntegration.translateTeam(game.getCurrentTeam());
        return new InitialState(boardSize, pieces, playerColor);
    }

    private void chooseConfiguration(){
        boolean continuar = true;
        while (continuar){
            System.out.println("Ingrese la configuración deseada: \n 1. Classic \n 2. Capablanca");
            int option = sc.nextInt();
            if (option == 1){
                game.setupClassic();
                continuar = false;
            }else {
                game.setupCapablanca();
                continuar = false;
            }
        }

    }
}
