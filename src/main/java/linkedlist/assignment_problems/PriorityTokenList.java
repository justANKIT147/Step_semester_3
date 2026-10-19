package main.java.linkedlist.assignment_problems;

/**
 * Week 10 - Problem 1: Priority Token Insertion (Singly Linked List)
 *
 * Operations & Time Complexities:
 * - addFirst(int token): O(1) time - directly inserts before head.
 * - addLast(int token): O(N) time (or O(1) with tail) - traverses to end to link node.
 * - insertAt(int index, int token): O(N) time - traverses to index - 1 to splice in node.
 * - Auxiliary Space Complexity: O(1) extra space per operation.
 */
public class PriorityTokenList {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public PriorityTokenList() {
        this.head = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    /**
     * Inserts a token at the front of the list (Priority insertion).
     */
    public void addFirst(int token) {
        Node newNode = new Node(token);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Appends a token at the end of the list (Normal insertion).
     */
    public void addLast(int token) {
        Node newNode = new Node(token);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
    }

    /**
     * Inserts a token at a 0-based index.
     * Constraint: 0 <= index <= current size.
     */
    public void insertAt(int index, int token) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }

        if (index == 0) {
            addFirst(token);
            return;
        }
        if (index == size) {
            addLast(token);
            return;
        }

        Node newNode = new Node(token);
        // Reach the node immediately before the insertion index
        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }

        // Set the new node's next reference first, then connect the previous node
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    public void printList() {
        Node curr = head;
        StringBuilder sb = new StringBuilder();
        while (curr != null) {
            sb.append(curr.data).append(" -> ");
            curr = curr.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        PriorityTokenList list = new PriorityTokenList();

        // Starting list: 101 -> 102 -> 103
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);
        System.out.print("Starting: ");
        list.printList();

        // Operations: addFirst(100), addLast(104), insertAt(2, 150)
        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        System.out.print("Expected: 100 -> 101 -> 150 -> 102 -> 103 -> 104 -> null\nActual:   ");
        list.printList();
    }
}
