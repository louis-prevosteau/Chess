package fr.chess.engine.player.ai;

import fr.chess.engine.board.Board;

public interface BoardEvaluator {

    int evaluate(Board board, int depth);
}
