package edu.austral.dissis.chess;

import edu.austral.dissis.chess.piece.*;
import edu.austral.dissis.chess.rules.*;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class PieceRules {//AHORA ESTE VA A SER tipo un validator de las rules por pieza teniendo un map de pieceType y chess.rules.Rule
    Map<Piece, Rule> pieceRuleMap;
    EnumMap<PieceType, Rule> pieceTypeRuleEnumMap;

    public PieceRules() {
        pieceRuleMap = new HashMap<>();
    }

    public Map<Piece, Rule> getPieceRuleMap() {
        return pieceRuleMap;
    }

    public void fillMap(Piece piece){
        if (piece.getPieceType().equals(PieceType.PAWN)){
            pieceTypeRuleEnumMap.put(piece.getPieceType(), new PawnRule());
        }else if (piece.getPieceType().equals(PieceType.QUEEN)){
            pieceTypeRuleEnumMap.put(piece.getPieceType(), new QueenRule());
        }else if (piece.getPieceType().equals(PieceType.KING)){
            pieceTypeRuleEnumMap.put(piece.getPieceType(), new KingRule());
        }else if (piece.getPieceType().equals(PieceType.BISHOP)){
            pieceTypeRuleEnumMap.put(piece.getPieceType(), new BishopRule());
        }else if (piece.getPieceType().equals(PieceType.ROOK)){
            pieceTypeRuleEnumMap.put(piece.getPieceType(), new RookRule());
        }else{
            pieceTypeRuleEnumMap.put(piece.getPieceType(), new KnightRule());
        }
    }
}
