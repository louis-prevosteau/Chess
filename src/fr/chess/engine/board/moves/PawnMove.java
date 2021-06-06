package fr.chess.engine.board.moves;

import fr.chess.engine.board.Board;
import fr.chess.engine.board.BoardUtils;
import fr.chess.engine.pieces.Piece;

public class PawnMove extends Move {

    public PawnMove(final Board board,
                    final Piece pieceMoved,
                    final int destinationCoordinate) {
        super(board, pieceMoved, destinationCoordinate);
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || other instanceof PawnMove && super.equals(other);
    }

    @Override
    public String toString() {
        return BoardUtils.INSTANCE.getPositionAtCoordinate(this.destinationCoordinate);
    }
}
