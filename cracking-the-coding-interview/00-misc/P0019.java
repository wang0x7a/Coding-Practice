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
        ListNode fast = list.next.next;

        while (fast != null) {
            if (slow == fast)
                return true;

            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }

    public static void main(String args[]) {}
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
