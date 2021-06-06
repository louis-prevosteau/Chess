package fr.chess.engine.board.moves;

import fr.chess.engine.board.Board;
import fr.chess.engine.board.BoardUtils;
import fr.chess.engine.pieces.Piece;

public class PawnAttackMove extends AttackMove {

    public PawnAttackMove(final Board board,
                          final Piece pieceMoved,
                          final int destinationCoordinate,
                          final Piece pieceAttacked) {
        super(board, pieceMoved, destinationCoordinate, pieceAttacked);
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || other instanceof PawnAttackMove && super.equals(other);
    }

    @Override
    public String toString() {
        return BoardUtils.INSTANCE.getPositionAtCoordinate(this.movedPiece.getPiecePosition()).substring(0, 1) + "x" +
                BoardUtils.INSTANCE.getPositionAtCoordinate(this.destinationCoordinate);
    }
}
