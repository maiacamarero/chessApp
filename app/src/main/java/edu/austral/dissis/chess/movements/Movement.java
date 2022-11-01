package edu.austral.dissis.chess.movements;

import edu.austral.dissis.chess.Position;
import edu.austral.dissis.chess.piece.Piece;

public class Movement {

    private final Piece piece;
    private Position finalPosition;

    public Movement(Piece piece, Position finalPosition) {
        this.piece = piece;
        this.finalPosition = finalPosition;
    }

    public Piece getPiece() {
        return piece;
    }

    public Position getFinalPosition() {
        return finalPosition;
    }

}
