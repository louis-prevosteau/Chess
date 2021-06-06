package fr.chess.engine.board.moves;

import fr.chess.engine.board.Board;
import fr.chess.engine.pieces.Piece;

public abstract class AttackMove extends Move {

    private final Piece attackedPiece;

    AttackMove(final Board board,
               final Piece pieceMoved,
               final int destinationCoordinate,
               final Piece pieceAttacked) {
        super(board, pieceMoved, destinationCoordinate);
        this.attackedPiece = pieceAttacked;
    }

    @Override
    public int hashCode() {
        return this.attackedPiece.hashCode() + super.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AttackMove)) {
            return false;
        }
        final AttackMove otherAttackMove = (AttackMove) other;
        return super.equals(otherAttackMove) && getAttackedPiece().equals(otherAttackMove.getAttackedPiece());
    }

    @Override
    public Piece getAttackedPiece() {
        return this.attackedPiece;
    }

    @Override
    public boolean isAttack() {
        return true;
    }
}
