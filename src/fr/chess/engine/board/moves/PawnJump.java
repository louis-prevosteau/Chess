package fr.chess.engine.board.moves;

import fr.chess.engine.board.Board;
import fr.chess.engine.board.BoardUtils;
import fr.chess.engine.board.Builder;
import fr.chess.engine.pieces.Pawn;

public class PawnJump extends Move {

    public PawnJump(final Board board,
                    final Pawn pieceMoved,
                    final int destinationCoordinate) {
        super(board, pieceMoved, destinationCoordinate);
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || other instanceof PawnJump && super.equals(other);
    }

    @Override
    public Board execute() {
        final Builder builder = new Builder();
        this.board.currentPlayer().getActivePieces().stream().filter(piece -> !this.movedPiece.equals(piece)).forEach(builder::setPiece);
        this.board.currentPlayer().getOpponent().getActivePieces().forEach(builder::setPiece);
        final Pawn movedPawn = (Pawn)this.movedPiece.movePiece(this);
        builder.setPiece(movedPawn);
        builder.setEnPassantPawn(movedPawn);
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
        builder.setMoveTransition(this);
        return builder.build();
    }

    @Override
    public String toString() {
        return BoardUtils.INSTANCE.getPositionAtCoordinate(this.destinationCoordinate);
    }
}
