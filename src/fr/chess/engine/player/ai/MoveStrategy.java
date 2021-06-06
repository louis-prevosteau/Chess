package fr.chess.engine.player.ai;

import fr.chess.engine.board.Board;
import fr.chess.engine.board.moves.Move;

public interface MoveStrategy {

    long getNumBoardsEvaluated();

    Move execute(Board board);
}
