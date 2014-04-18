/* P0022
 *
 * Remove duplicates from sorted list
 * Given a sorted linked list, delete all duplicates such that each element
 * appear only once.
 */

public class P0022 {
    public static void deleteDups(ListNode list) {
        if (list == null)
            return;

        ListNode prev = list;
        ListNode curr = list.next;

        while (curr != null) {
            if (prev.value != curr.value) {
                prev.next = curr;
                prev = prev.next;
            }

            curr = curr.next;
        }

        return;
    }

    public static void print(ListNode list) {
        for (ListNode p = list; p != null; p = p.next)
            System.out.print(p.value + " ");

        System.out.println();
    }

    public static void main(String args[]) {
        ListNode list = new ListNode(1);
        list.next = new ListNode(1);
        list.next.next = new ListNode(2);
        list.next.next.next = new ListNode(2);
        list.next.next.next.next = new ListNode(2);
        list.next.next.next.next.next = new ListNode(3);

        deleteDups(list);

        print(list);
    }
}

class ListNode {
    int value;
    ListNode next;

    public ListNode() {}

    public ListNode(int value) {
        this.value = value;
    }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next  = next;
    }
}
