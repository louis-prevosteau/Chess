package fr.chess.engine.board.moves;

import fr.chess.engine.board.Board;
import fr.chess.engine.board.Builder;
import fr.chess.engine.pieces.Piece;
import fr.chess.engine.pieces.Rook;

public abstract class CastleMove extends Move {

    final Rook castleRook;
    final int castleRookStart;
    final int castleRookDestination;

    CastleMove(final Board board,
               final Piece pieceMoved,
               final int destinationCoordinate,
               final Rook castleRook,
               final int castleRookStart,
               final int castleRookDestination) {
        super(board, pieceMoved, destinationCoordinate);
        this.castleRook = castleRook;
        this.castleRookStart = castleRookStart;
        this.castleRookDestination = castleRookDestination;
    }

    Rook getCastleRook() {
        return this.castleRook;
    }

    @Override
    public boolean isCastlingMove() {
        return true;
    }

    @Override
    public Board execute() {
        final Builder builder = new Builder();
        for (final Piece piece : this.board.getAllPieces()) {
            if (!this.movedPiece.equals(piece) && !this.castleRook.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        builder.setPiece(this.movedPiece.movePiece(this));
        builder.setPiece(new Rook(this.castleRook.getPieceAllegiance(), this.castleRookDestination, false));
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
        builder.setMoveTransition(this);
        return builder.build();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + this.castleRook.hashCode();
        result = prime * result + this.castleRookDestination;
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CastleMove)) {
            return false;
        }
        final CastleMove otherCastleMove = (CastleMove) other;
        return super.equals(otherCastleMove) && this.castleRook.equals(otherCastleMove.getCastleRook());
    }
}
