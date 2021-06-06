package fr.chess.engine.board.moves;

import fr.chess.engine.board.Board;
import fr.chess.engine.pieces.Piece;
import fr.chess.engine.pieces.Rook;

public class KingSideCastleMove extends CastleMove {

    public KingSideCastleMove(final Board board,
                              final Piece pieceMoved,
                              final int destinationCoordinate,
                              final Rook castleRook,
                              final int castleRookStart,
                              final int castleRookDestination) {
        super(board, pieceMoved, destinationCoordinate, castleRook, castleRookStart,
                castleRookDestination);
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KingSideCastleMove)) {
            return false;
        }
        final KingSideCastleMove otherKingSideCastleMove = (KingSideCastleMove) other;
        return super.equals(otherKingSideCastleMove) && this.castleRook.equals(otherKingSideCastleMove.getCastleRook());
    }

    @Override
    public String toString() {
        return "O-O";
    }
}
