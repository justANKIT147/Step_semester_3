package main.java.abstraction.assignment_problems;

/**
 * Week 7 - Problem 2: Sculpture subclass extending ArtPiece directly.
 */
public class Sculpture extends ArtPiece {
    private final String title;
    private final String material;

    public Sculpture(String title, String material) {
        super();
        this.title = title;
        this.material = material;
    }

    @Override
    public String describe() {
        return "Sculpture: " + title + ", " + material;
    }
}
