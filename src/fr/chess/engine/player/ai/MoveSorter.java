package fr.chess.engine.player.ai;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import fr.chess.engine.board.BoardUtils;
import fr.chess.engine.board.moves.Move;

import java.util.Collection;
import java.util.Comparator;

import static fr.chess.engine.board.BoardUtils.mvvlva;

public enum MoveSorter {

    STANDARD {
        @Override
        Collection<Move> sort(final Collection<Move> moves) {
            return Ordering.from((Comparator<Move>) (move1, move2) -> ComparisonChain.start()
                    .compareTrueFirst(move1.isCastlingMove(), move2.isCastlingMove())
                    .compare(mvvlva(move2), mvvlva(move1))
                    .result()).immutableSortedCopy(moves);
        }
    },
    EXPENSIVE {
        @Override
        Collection<Move> sort(final Collection<Move> moves) {
            return Ordering.from((Comparator<Move>) (move1, move2) -> ComparisonChain.start()
                    .compareTrueFirst(BoardUtils.kingThreat(move1), BoardUtils.kingThreat(move2))
                    .compareTrueFirst(move1.isCastlingMove(), move2.isCastlingMove())
                    .compare(mvvlva(move2), mvvlva(move1))
                    .result()).immutableSortedCopy(moves);
        }

    },
    SORT {
        @Override
        Collection<Move> sort(final Collection<Move> moves) {
            return Ordering.from(SMART_SORT).immutableSortedCopy(moves);
        }
    };

    public static Comparator<Move> SMART_SORT = new Comparator<Move>() {
        @Override
        public int compare(final Move move1, final Move move2) {
            return ComparisonChain.start()
                    .compareTrueFirst(BoardUtils.isThreatenedBoardImmediate(move1.getBoard()), BoardUtils.isThreatenedBoardImmediate(move2.getBoard()))
                    .compareTrueFirst(move1.isAttack(), move2.isAttack())
                    .compareTrueFirst(move1.isCastlingMove(), move2.isCastlingMove())
                    .compare(move2.getMovedPiece().getPieceValue(), move1.getMovedPiece().getPieceValue())
                    .result();
        }
    };
    abstract  Collection<Move> sort(Collection<Move> moves);
}
