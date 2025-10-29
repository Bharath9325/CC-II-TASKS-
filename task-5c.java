import java.util.HashSet;
import java.util.Set;

class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        Set<Integer> seen = new HashSet<>();
        ListNode current = head;
        ListNode prev = null;

        while (current != null) {
            if (seen.contains(current.val)) {
                prev.next = current.next;
            } else {
                seen.add(current.val);
                prev = current;
            }
            current = current.next;
        }
        return head;
    }
    
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);

        System.out.println("Original list:");
        printList(head);

        ListNode uniqueHead = sol.deleteDuplicates(head);

        System.out.println("List after removing duplicates:");
        printList(uniqueHead);
    }
}
