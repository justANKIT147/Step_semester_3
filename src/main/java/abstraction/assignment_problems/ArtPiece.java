package main.java.abstraction.assignment_problems;

/**
 * Week 7 - Problem 2: Abstract ArtPiece
 * Base abstract class defining pieceId tracking and contract for description.
 */
public abstract class ArtPiece {
    private static int idCounter = 0;
    private final int pieceId;

    public ArtPiece() {
        this.pieceId = ++idCounter;
    }

    public int getPieceId() {
        return pieceId;
    }

    public abstract String describe();
}
