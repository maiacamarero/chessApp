package edu.austral.dissis.chess;

import edu.austral.dissis.chess.gui.*;
import edu.austral.dissis.chess.piece.Piece;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyGameEngine implements GameEngine {

    Game game;
    GameIntegration gameIntegration = new GameIntegration();

    public MyGameEngine() {
        game = new Game();
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Position fromPosition = new Position(move.getFrom().getColumn(), move.getFrom().getRow());
        Piece piece = game.getBoard().getPiece(fromPosition);
        if (piece == null){
            return new InvalidMove("No hay nada es esa posición.");
        } else if (piece.getTeam() != game.getCurrentTeam()){
            return new InvalidMove("Es el turno del equipo " + game.getCurrentTeam().toString());
        }/*else if (game.getBoard().isGameOver()){
            return new InvalidMove("Jaque Mate, Game Over!");
        }else if (game.getBoard().isValidCastling(Position position1, Position position2)){
            game.getBoard().castle(Position position1, Position position2);
            boolean moveTo = true;
            List<ChessPiece> pieces = gameIntegration.translatePieces(game.getBoard(), game.getBoard().getPieces());
            PlayerColor playerColor = getCurrentPlayer(moveTo, gameIntegration.translateTeam(game.getCurrentTeam()));
            assert playerColor != null;
            return new NewGameState(pieces, playerColor);
        }*/else {
            Position toPosition = game.getBoard().getPosition(move.getTo().getColumn(), move.getTo().getRow());
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
        BoardSize boardSize = gameIntegration.translateSize(game.getPreferredSize());
        List<ChessPiece> pieces = gameIntegration.translatePieces(game.getBoard(), game.getBoard().getPieces());
        PlayerColor playerColor = gameIntegration.translateTeam(game.getCurrentTeam());
        return new InitialState(boardSize, pieces, playerColor);
    }
}
