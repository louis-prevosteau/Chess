package fr.chess.engine.board;

import fr.chess.engine.Alliance;
import fr.chess.engine.board.moves.Move;
import fr.chess.engine.pieces.Pawn;
import fr.chess.engine.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public class Builder {

    Map<Integer, Piece> boardConfig;
    Alliance nextMoveMaker;
    Pawn enPassantPawn;
    Move transitionMove;

    public Builder() {
        this.boardConfig = new HashMap<>(32, 1.0f);
    }

    public Builder setPiece(final Piece piece) {
        this.boardConfig.put(piece.getPiecePosition(), piece);
        return this;
    }

    public Builder setMoveMaker(final Alliance nextMoveMaker) {
        this.nextMoveMaker = nextMoveMaker;
        return this;
    }

    public Builder setEnPassantPawn(final Pawn enPassantPawn) {
        this.enPassantPawn = enPassantPawn;
        return this;
    }

    public Builder setMoveTransition(final Move transitionMove) {
        this.transitionMove = transitionMove;
        return this;
    }

    public Board build() {
        return new Board(this);
    }

}

