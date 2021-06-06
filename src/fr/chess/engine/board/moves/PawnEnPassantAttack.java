package fr.chess.engine.board.moves;

import fr.chess.engine.board.Board;
import fr.chess.engine.board.BoardUtils;
import fr.chess.engine.board.Builder;
import fr.chess.engine.pieces.Pawn;
import fr.chess.engine.pieces.Piece;

public class PawnEnPassantAttack extends AttackMove {

    public PawnEnPassantAttack(final Board board,
                               final Piece pieceMoved,
                               final int destinationCoordinate,
                               final Piece pieceAttacked) {
        super(board, pieceMoved, destinationCoordinate, pieceAttacked);
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || other instanceof PawnEnPassantAttack && super.equals(other);
    }

    @Override
    public Board execute() {
        final Builder builder = new Builder();
        this.board.currentPlayer().getActivePieces().stream().filter(piece -> !this.movedPiece.equals(piece)).forEach(builder::setPiece);
        this.board.currentPlayer().getOpponent().getActivePieces().stream().filter(piece -> !piece.equals(this.getAttackedPiece())).forEach(builder::setPiece);
        builder.setPiece(this.movedPiece.movePiece(this));
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
        builder.setMoveTransition(this);
        return builder.build();
    }

    @Override
    public Board undo() {
        final Builder builder = new Builder();
        this.board.getAllPieces().forEach(builder::setPiece);
        builder.setEnPassantPawn((Pawn)this.getAttackedPiece());
        builder.setMoveMaker(this.board.currentPlayer().getAlliance());
        return builder.build();
    }

    @Override
    public String toString() {
        return BoardUtils.INSTANCE.getPositionAtCoordinate(this.movedPiece.getPiecePosition()).substring(0, 1) + "x" +
                BoardUtils.INSTANCE.getPositionAtCoordinate(this.destinationCoordinate);
    }
}
