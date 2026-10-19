package main.java.linkedlist.assignment_problems;

/**
 * Week 10 - Problem 2: Cancelled Orders Cleanup
 *
 * Algorithm Complexity:
 * - Time Complexity: O(N), where N is the number of nodes (0 <= N <= 1000). Single pass.
 * - Auxiliary Space Complexity: O(1). In-place deletion by updating pointer links.
 */
public class CancelledOrdersList {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Removes every occurrence of the specified code from the singly linked list.
     *
     * @param head head of the list
     * @param code order code to purge
     * @return new head of the cleaned list
     */
    public static Node removeAll(Node head, int code) {
        // Step 1: Remove matching nodes from the front by updating head
        while (head != null && head.data == code) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        // Step 2: Traverse remaining nodes and skip any matching nodes
        Node curr = head;
        while (curr != null && curr.next != null) {
            if (curr.next.data == code) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }

        return head;
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
        // Example 1: 5 -> 3 -> 5 -> 8 -> 5, remove 5
        Node ex1 = buildList(new int[]{5, 3, 5, 8, 5});
        System.out.print("Before: ");
        printList(ex1);
        ex1 = removeAll(ex1, 5);
        System.out.print("After removing 5 (Expected: 3 -> 8 -> null): ");
        printList(ex1);

        // Example 2: 5 -> 5 -> 5, remove 5
        Node ex2 = buildList(new int[]{5, 5, 5});
        System.out.print("\nBefore: ");
        printList(ex2);
        ex2 = removeAll(ex2, 5);
        System.out.print("After removing 5 (Expected: null): ");
        printList(ex2);

        // Edge case: Empty list
        Node ex3 = removeAll(null, 5);
        System.out.print("\nEmpty list test: ");
        printList(ex3);
    }
}
