package edu.austral.dissis.chess.adapter;

import edu.austral.dissis.chess.Board;
import edu.austral.dissis.chess.Team;
import edu.austral.dissis.chess.gui.*;
import edu.austral.dissis.chess.gui.Position;
import edu.austral.dissis.chess.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class GameIntegration {

    public Position translatePosition(edu.austral.dissis.chess.Position position){
        return new Position(position.getY(), position.getX());
    }

    public ChessPiece translatePiece(Piece piece){
        Position position = translatePosition(piece.getPosition());
        PlayerColor playerColor = translateTeam(piece.getTeam());
        return new ChessPiece(piece.getInitialPosition().toString(), playerColor, position, piece.getPieceType().toString().toLowerCase());
    }

    public PlayerColor translateTeam(Team team){
        if (team == Team.BLACK){
            return PlayerColor.BLACK;
        }else return PlayerColor.WHITE;
    }

    public List<ChessPiece> translatePieces(Board board, List<Piece> pieces){
        List<ChessPiece> chessPieces = new ArrayList<>();
        for (int i = 0; i < board.getPieces().size(); i++) {
            chessPieces.add(translatePiece(pieces.get(i)));
        }
        return chessPieces;
    }

    public BoardSize translateSize(int sizeX, int sizeY){
        return new BoardSize(sizeX, sizeY);
    }
}
