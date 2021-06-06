package fr.chess.engine.board.moves;

import fr.chess.engine.board.Board;
import fr.chess.engine.board.BoardUtils;
import fr.chess.engine.board.Builder;
import fr.chess.engine.pieces.Pawn;
import fr.chess.engine.pieces.Piece;

public class PawnPromotion extends PawnMove {

    final Move decoratedMove;
    final Pawn promotedPawn;
    final Piece promotionPiece;

    public PawnPromotion(final Move decoratedMove,
                         final Piece promotionPiece) {
        super(decoratedMove.getBoard(), decoratedMove.getMovedPiece(), decoratedMove.getDestinationCoordinate());
        this.decoratedMove = decoratedMove;
        this.promotedPawn = (Pawn) decoratedMove.getMovedPiece();
        this.promotionPiece = promotionPiece;
    }

    @Override
    public int hashCode() {
        return decoratedMove.hashCode() + (31 * promotedPawn.hashCode());
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || other instanceof PawnPromotion && (super.equals(other));
    }

    @Override
    public Board execute() {
        final Board pawnMovedBoard = this.decoratedMove.execute();
        final Builder builder = new Builder();
        pawnMovedBoard.currentPlayer().getActivePieces().stream().filter(piece -> !this.promotedPawn.equals(piece)).forEach(builder::setPiece);
        pawnMovedBoard.currentPlayer().getOpponent().getActivePieces().forEach(builder::setPiece);
        builder.setPiece(this.promotionPiece.movePiece(this));
        builder.setMoveMaker(pawnMovedBoard.currentPlayer().getAlliance());
        builder.setMoveTransition(this);
        return builder.build();
    }

    @Override
    public boolean isAttack() {
        return this.decoratedMove.isAttack();
    }

    @Override
    public Piece getAttackedPiece() {
        return this.decoratedMove.getAttackedPiece();
    }

    @Override
    public String toString() {
        return BoardUtils.INSTANCE.getPositionAtCoordinate(this.movedPiece.getPiecePosition()) + "-" +
                BoardUtils.INSTANCE.getPositionAtCoordinate(this.destinationCoordinate) + "=" + this.promotionPiece.getPieceType();
    }

}
