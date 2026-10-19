package main.java.linkedlist.assignment_problems;

/**
 * Week 10 - Problem 5: Round-Robin Game Turns (Circular Singly Linked List)
 *
 * Algorithm Complexity:
 * - Adding a player: O(1) time using the tail pointer.
 *   For the first node, newNode.next = newNode, tail = newNode.
 *   For subsequent nodes, newNode.next = tail.next (head), tail.next = newNode, tail = newNode.
 * - Printing T turns: O(T) time, advancing along the circular links.
 * - Auxiliary Space Complexity: O(1) auxiliary space.
 */
public class RoundRobinGame {

    public static class Node {
        public String data;
        public Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node tail;
    private int playerCount;

    public RoundRobinGame() {
        this.tail = null;
        this.playerCount = 0;
    }

    public int getPlayerCount() { return playerCount; }

    /**
     * Appends a player in O(1) time while maintaining circular invariants.
     */
    public void addLast(String name) {
        Node newNode = new Node(name);
        if (tail == null) {
            tail = newNode;
            tail.next = tail; // points to itself
        } else {
            newNode.next = tail.next; // points to head
            tail.next = newNode;      // previous tail points to new node
            tail = newNode;           // tail becomes new node
        }
        playerCount++;
    }

    /**
     * Prints player names for the requested number of turns, cycling circularly.
     *
     * @param turns number of turns to simulate (1 <= turns <= 100)
     */
    public void printTurns(int turns) {
        if (tail == null || turns <= 0) {
            return;
        }

        Node current = tail.next; // Head of circular list
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < turns; i++) {
            sb.append(current.data);
            if (i < turns - 1) {
                sb.append(" ");
            }
            current = current.next;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        RoundRobinGame game = new RoundRobinGame();
        game.addLast("Asha");
        game.addLast("Ravi");
        game.addLast("Neha");

        System.out.print("Expected: Asha Ravi Neha Asha Ravi Neha Asha\nActual:   ");
        game.printTurns(7);

        // Edge case: 1 player, 4 turns
        RoundRobinGame single = new RoundRobinGame();
        single.addLast("SoloPlayer");
        System.out.print("Single player 4 turns: ");
        single.printTurns(4);
    }
}
