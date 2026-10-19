package main.java.abstraction.assignment_problems;

/**
 * Week 7 - Problem 2: Painting subclass extending ArtPiece directly.
 */
public class Painting extends ArtPiece {
    private final String title;
    private final String medium;

    public Painting(String title, String medium) {
        super();
        this.title = title;
        this.medium = medium;
    }

    @Override
    public String describe() {
        return "Painting: " + title + ", " + medium;
    }
}
