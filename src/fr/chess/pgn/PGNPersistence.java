package fr.chess.pgn;

import fr.chess.engine.board.Board;
import fr.chess.engine.board.moves.Move;
import fr.chess.engine.player.Player;

public interface PGNPersistence {

    void persistGame(Game game);

    Move getNextBestMove(Board board, Player player, String gameText);
}
