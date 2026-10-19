package main.java.linkedlist.assignment_problems;

/**
 * Week 10 - Problem 4: Train Coaches Both Ways (Doubly Linked List)
 *
 * Algorithm Complexity:
 * - Finding a coach by name takes O(N) linear scan.
 * - Unlinking a known node once found takes O(1) constant time:
 *     node.prev.next = node.next;
 *     node.next.prev = node.prev;
 * - Traversal (printForward, printBackward) takes O(N) time.
 * - Auxiliary Space: O(1) per operation.
 */
public class TrainCoachList {

    public static class Node {
        public String data;
        public Node prev;
        public Node next;

        public Node(String data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;

    public TrainCoachList() {
        this.head = null;
        this.tail = null;
    }

    public Node getHead() { return head; }
    public Node getTail() { return tail; }

    /**
     * Appends a coach to the end of the train (O(1) using tail reference).
     */
    public void addLast(String name) {
        Node newNode = new Node(name);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    /**
     * Removes the first coach matching the specified name.
     * Correctly handles head, tail, only node, internal node, and nonexistent name.
     *
     * @param name coach name to remove
     * @return true if found and removed, false otherwise
     */
    public boolean remove(String name) {
        if (head == null || name == null) {
            return false;
        }

        Node curr = head;
        // O(N) search
        while (curr != null && !curr.data.equals(name)) {
            curr = curr.next;
        }

        if (curr == null) {
            return false; // Not found
        }

        // O(1) unlinking
        if (curr == head && curr == tail) {
            // Only node in the list
            head = null;
            tail = null;
        } else if (curr == head) {
            // Removing head
            head = head.next;
            head.prev = null;
        } else if (curr == tail) {
            // Removing tail
            tail = tail.prev;
            tail.next = null;
        } else {
            // Internal node
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }

        return true;
    }

    public void printForward() {
        StringBuilder sb = new StringBuilder("Forward: ");
        Node curr = head;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) {
                sb.append(" <-> ");
            }
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }

    public void printBackward() {
        StringBuilder sb = new StringBuilder("Backward: ");
        Node curr = tail;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.prev != null) {
                sb.append(" <-> ");
            }
            curr = curr.prev;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        TrainCoachList train = new TrainCoachList();
        String[] coaches = {"Engine", "A1", "B1", "B2", "Guard"};
        for (String c : coaches) {
            train.addLast(c);
        }

        System.out.println("--- Initial Train ---");
        train.printForward();
        train.printBackward();

        // Remove B1
        System.out.println("\n--- Removing B1 ---");
        train.remove("B1");
        train.printForward();
        train.printBackward();

        // Edge case: Remove head and tail
        System.out.println("\n--- Edge Cases: Remove Engine and Guard ---");
        train.remove("Engine");
        train.remove("Guard");
        train.printForward();
        train.printBackward();
    }
}
