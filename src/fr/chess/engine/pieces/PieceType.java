package fr.chess.engine.pieces;

public enum PieceType {

    PAWN(100, "P"),
    KNIGHT(300, "N"),
    BISHOP(330, "B"),
    ROOK(500, "R"),
    QUEEN(900, "Q"),
    KING(10000, "K");

    private final int value;
    private final String pieceName;

    public int getPieceValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.pieceName;
    }

    PieceType(final int val,
              final String pieceName) {
        this.value = val;
        this.pieceName = pieceName;
    }
}
