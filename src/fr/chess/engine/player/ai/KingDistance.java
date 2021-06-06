package fr.chess.engine.player.ai;

import fr.chess.engine.pieces.Piece;

public class KingDistance {

    final Piece enemyPiece;
    final int distance;

    KingDistance(final Piece enemyDistance,
                 final int distance) {
        this.enemyPiece = enemyDistance;
        this.distance = distance;
    }

    public Piece getEnemyPiece() {
        return enemyPiece;
    }

    public int getDistance() {
        return distance;
    }

    public int tropismScore() {
        return (enemyPiece.getPieceValue()/10) * distance;
    }

}
