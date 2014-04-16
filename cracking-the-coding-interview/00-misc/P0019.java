/* P0019
 *
 * Linked List Cycle
 * Given a linked list, determine if it has a cycle in it.
 */

public class P0019 {
    public static boolean isCircular(ListNode list) {
        if (list == null || list.next == null)
            return false;

        ListNode slow = list;
        ListNode fast = list;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }

        return false;
    }

    public static void main(String args[]) {
        ListNode head = new ListNode(1);
        ListNode n1 = new ListNode(2);
        head.next = n1;
        ListNode n2 = new ListNode(3);
        n1.next = n2;
        ListNode n3 = new ListNode(4);
        n2.next = n3;

        //n3.next = n1;

        System.out.println(isCircular(head));
    }
}

class ListNode {
    int value;
    ListNode next;

    public ListNode() {}

    public ListNode(int value) {
        this.value = value;
        this.next  = null;
    }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next  = null;
    }
}
