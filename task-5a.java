class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        ListNode current = head;

        while (current != null) {
            ListNode prev = dummy;
            while (prev.next != null && prev.next.val < current.val) {
                prev = prev.next;
            }
            
            ListNode nextNode = current.next;
            current.next = prev.next;
            prev.next = current;
            current = nextNode;
        }

        return dummy.next;
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
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        System.out.println("Original list:");
        printList(head);

        ListNode sortedHead = sol.insertionSortList(head);

        System.out.println("Sorted list:");
        printList(sortedHead);
    }
}
