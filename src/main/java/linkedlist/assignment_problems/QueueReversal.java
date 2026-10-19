package main.java.linkedlist.assignment_problems;

/**
 * Week 10 - Problem 3: Reverse the Queue (In-Place Singly Linked List Reversal)
 *
 * Algorithm Complexity:
 * - Time Complexity: O(N), where N is the number of nodes (0 <= N <= 1000). Single pass.
 * - Auxiliary Space Complexity: O(1). Reverses pointers in place without allocating new nodes.
 */
public class QueueReversal {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Reverses a singly linked list in place using three iterative pointer references.
     *
     * @param head head of the queue
     * @return new head after reversal
     */
    public static Node reverse(Node head) {
        Node previous = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;     // Save next node
            current.next = previous; // Reverse pointer
            previous = current;      // Advance previous
            current = next;          // Advance current
        }

        return previous; // New head
    }

    public static void printList(Node head) {
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        while (curr != null) {
            sb.append(curr.data).append(" -> ");
            curr = curr.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    public static Node buildList(int[] values) {
        if (values == null || values.length == 0) return null;
        Node head = new Node(values[0]);
        Node curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new Node(values[i]);
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        // Example 1: 1 -> 2 -> 3 -> 4
        Node q1 = buildList(new int[]{1, 2, 3, 4});
        System.out.print("Before: ");
        printList(q1);
        q1 = reverse(q1);
        System.out.print("Reversed (Expected: 4 -> 3 -> 2 -> 1 -> null): ");
        printList(q1);

        // Example 2: single node 9
        Node q2 = buildList(new int[]{9});
        System.out.print("\nSingle node before: ");
        printList(q2);
        q2 = reverse(q2);
        System.out.print("Reversed (Expected: 9 -> null): ");
        printList(q2);

        // Edge case: Empty list
        Node q3 = reverse(null);
        System.out.print("\nEmpty list reversed: ");
        printList(q3);
    }
}
