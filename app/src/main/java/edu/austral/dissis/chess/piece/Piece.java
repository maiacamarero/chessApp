package edu.austral.dissis.chess.piece;

import edu.austral.dissis.chess.*;
import edu.austral.dissis.chess.movements.Check;
import edu.austral.dissis.chess.movements.Movement;
import edu.austral.dissis.chess.movements.MovementValidator;
import edu.austral.dissis.chess.rules.Rule;

import java.util.List;

public class Piece {

    private boolean eaten;
    private Position position;
    private final Position initialPosition;
    private final Team team;
    private boolean hasMoved;
    private Board board;
    private List<Rule> rules;
    private PieceType pieceType;
    private boolean isCheck;

    public Piece(Board board, Position position, Team team, List<Rule> rules, PieceType pieceType) {
        this.position = position;
        this.team = team;
        hasMoved = false;
        this.board = board;
        this.rules = rules;
        eaten = false;
        this.initialPosition = position;
        this.pieceType = pieceType;
        this.isCheck = false;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public boolean isEaten() {
        return eaten;
    }

    public Position getPosition() {
        return position;
    }

    public Team getTeam() {
        return team;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void makeMove(Position otherPosition) {
        if (board != null){
            for (Rule rule : rules) {
                if (rule.validateRule(board, new Movement(this, otherPosition))) {
                    if (board.getPiece(getPosition()) == this){
                        board.removeFromBoard(this);
                    }
                    Piece target = board.getPiece(otherPosition);
                    this.position = otherPosition;
                    if (target != null) {
                        this.eatPiece(target);
                    }
                    this.hasMoved = true;
                    board.placePiece(this, otherPosition);
                    System.out.println("se movió con éxito");
                }else System.out.println("Invalid movement");
            }
        }
    }

    public List<Rule> getRules() {
        return rules;
    }

    private MovementValidator checkValidator = new Check();

    public boolean moveTo(Position otherPosition) {
        if (board != null){
            for (Rule rule : rules) {
                if (rule.validateRule(board, new Movement(this, otherPosition))) {
                    if (board.getPiece(getPosition()) == this){
                        board.removeFromBoard(this);
                    }
                    Piece target = board.getPiece(otherPosition);
                    if (target != null) {
                        this.eatPiece(target);
                    }
                    this.position = otherPosition;
                    this.hasMoved = true;
                    board.placePiece(this, otherPosition);
                    //checksIfCheck(otherPosition);
                    return true;
                }else return false;
            }
        }return false;
    }

    public boolean canMove(Position otherPosition) { // para chequear el jaque
        if (board != null){
            for (Rule rule : rules) {
                if (rule.validateRule(board, new Movement(this, otherPosition))) {
                    //isCheck = true;
                    return true;
                }else {
                    //isCheck = false;
                    return false;
                }
            }
        }return false;
    }

    public boolean getOutOfCheck(Position otherPosition) {
        if (board != null){
            for (Rule rule : rules) {
                Position originalPosition = getPosition();
                if (rule.validateRule(board, new Movement(this, otherPosition))) {
                    checksIfCheck(otherPosition);
                    if (!isCheck){
                        if (board.getPiece(getPosition()) == this){
                            board.removeFromBoard(this);
                        }
                        Piece target = board.getPiece(otherPosition);

                        if (target != null) {
                            this.eatPiece(target);
                        }
                        this.position = otherPosition;
                        this.hasMoved = true;
                        board.placePiece(this, otherPosition);
                        return true;
                    } else{
                        this.position = originalPosition;
                        return false;
                    }
                }
            }
        }return false;
    }

    public boolean moveGeneric(Position otherPosition){
        checksIfCheck(otherPosition);
        if (isCheck){
            return getOutOfCheck(otherPosition);
        }else return moveTo(otherPosition);
    }

    public void checksIfCheck(Position otherPosition){
        if (checkValidator.validateMove(board, new Movement(this, otherPosition))){
            isCheck = true;
        }else isCheck = false;
    }

    private void eatPiece(Piece piece) {
        piece.setEaten(true);
        board.getPieces().remove(piece);
        board.removeFromBoard(piece);
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public Board getBoard() {
        return board;
    }

}
