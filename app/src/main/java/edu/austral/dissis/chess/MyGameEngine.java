package edu.austral.dissis.chess;

import edu.austral.dissis.chess.adapter.GameIntegration;
import edu.austral.dissis.chess.gui.*;
import edu.austral.dissis.chess.movements.Check;
import edu.austral.dissis.chess.movements.Movement;
import edu.austral.dissis.chess.movements.MovementValidator;
import edu.austral.dissis.chess.piece.Piece;
import edu.austral.dissis.chess.piece.PieceType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Scanner;

public class MyGameEngine implements GameEngine {

    Game game;
    GameIntegration gameIntegration = new GameIntegration();
    MovementValidator movementValidator;
    Scanner sc = new Scanner(System.in);


    public MyGameEngine() {
        game = new Game();
        movementValidator = new Check();
    }

    private void chooseConfiguration(){
        System.out.println("Ingrese la configuración deseada: \n 1. Classic \n 2. Tweedle");
        int config = sc.nextInt();
        switch (config){
            case 1 -> game.setup();
            case 2 -> game.setupTweedle();
            default -> game.setup();
        };
    }

    //podemos tener una clase que sea configuration o algo asi y que ahi se hagan los distintos tipos de boards
    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Position fromPosition = new Position(move.getFrom().getColumn(), move.getFrom().getRow());
        Piece piece = game.getBoard().getPiece(fromPosition);
        Position toPosition = game.getBoard().getPosition(move.getTo().getColumn(), move.getTo().getRow());
        Movement movement = new Movement(piece, toPosition);
        if (piece == null){
            return new InvalidMove("No hay nada en esa posición.");
        } else if (piece.getTeam() != game.getCurrentTeam()){
            return new InvalidMove("Es el turno del equipo " + game.getCurrentTeam().toString());
        }/*else if (movementValidator.validateMove(game.getBoard(), movement)){
            return new GameOver(gameIntegration.translateTeam(game.getCurrentTeam()));
        }else if (game.getBoard().isValidCastling(Position position1, Position position2)){
            game.getBoard().castle(Position position1, Position position2);
            boolean moveTo = true;
            List<ChessPiece> pieces = gameIntegration.translatePieces(game.getBoard(), game.getBoard().getPieces());
            PlayerColor playerColor = getCurrentPlayer(moveTo, gameIntegration.translateTeam(game.getCurrentTeam()));
            assert playerColor != null;
            return new NewGameState(pieces, playerColor);
        }*/else {
            //no anda ya estoy de mal humor
            if (piece.getPieceType() == PieceType.PAWN && (toPosition.getY() == 1 || toPosition.getY() == 8)){
                piece = game.getBoard().requestQueen(piece, toPosition, piece.getTeam());
            }
            boolean moveTo = piece.moveTo(toPosition);
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
        game.setup();
        //game.setupTweedle();
        //game.setupCapablanca();
        BoardSize boardSize = gameIntegration.translateSize(game.getSixeX(), game.getSizeY());
        List<ChessPiece> pieces = gameIntegration.translatePieces(game.getBoard(), game.getBoard().getPieces());
        PlayerColor playerColor = gameIntegration.translateTeam(game.getCurrentTeam());
        return new InitialState(boardSize, pieces, playerColor);
    }
}
