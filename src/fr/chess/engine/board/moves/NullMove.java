package fr.chess.engine.board.moves;

import fr.chess.engine.board.Board;

public class NullMove extends Move {

    public NullMove() {
        super(null, -1);
    }

    @Override
    public int getCurrentCoordinate() {
        return -1;
    }

    @Override
    public int getDestinationCoordinate() {
        return -1;
    }

    @Override
    public Board execute() {
        throw new RuntimeException("cannot execute null move!");
    }

    @Override
    public String toString() {
        return "Null Move";
    }
}
