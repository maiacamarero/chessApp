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
        if (piece instanceof Pawn){
            pieceRuleMap.put(piece, new PawnRule());
        }else if (piece instanceof Queen){
            pieceRuleMap.put(piece, new QueenRule());
        }else if (piece instanceof King){
            pieceRuleMap.put(piece, new KingRule());
        }else if (piece instanceof Bishop){
            pieceRuleMap.put(piece, new BishopRule());
        }else if (piece instanceof Rook){
            pieceRuleMap.put(piece, new RookRule());
        }else{
            pieceRuleMap.put(piece, new KnightRule());
        }
    }
}
