/* P0018
 *
 * Reorder List
 * Given a singly linked list L:
 * L0 -> L1 -> ... -> Ln-1 -> Ln
 * reorder it to:
 * L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
 *
 * Note:
 * This problem is a variant of the card shuffling problem, but this one is 
 * more complicated since we need to reverse the second half in place before
 * merging it into the first one.
 */

public class P0018 {
    public static void reorder(ListNode list) {
        if (list == null || list.next == null || list.next.next == null)
            return;

        ListNode slow = list.next;
        ListNode fast = list.next.next;

        // find the middle of the list
        // when n = 2 * i, numOfFist == numOfSecond == i
        // when n = 2 * i + 1, numOfFist == i + 1
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reserve the second half
        slow.next = reverse(slow.next);

        ListNode second = slow.next;
        // break the first and the second halves
        slow.next = null;
        
        // merge
        ListNode curr = list;
        ListNode tmp1, tmp2;
        while (second != null) {
            tmp1 = curr.next;
            tmp2 = second.next;
            
            second.next = curr.next;
            curr.next = second;

            curr = tmp1;
            second = tmp2;
        }
    }

    /* reserve a singly linked list in place.
     * Different from reorder method, the input arg, list, is not a head
     * */
    private static ListNode reverse(ListNode list) {
        if (list == null)
            return list;

        ListNode prev = list;
        ListNode curr = list.next;
        ListNode tmp;
        while (curr != null) {
            tmp = curr.next;

            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        list.next = null;

        return prev;
    }

    public static void print(ListNode list) {
        //ListNode curr = list.next;
        ListNode curr = list;

        while (curr != null) {
            System.out.print(curr.value);
            curr = curr.next;
        }

        System.out.println();
    }

    public static void main(String args[]) {
        /*
        ListNode list = new ListNode(0);
        list.next = new ListNode(1);
        list.next.next = new ListNode(2);
        list.next.next.next = new ListNode(3);
        list.next.next.next.next = new ListNode(4);
        list.next.next.next.next.next = new ListNode(5);
        */
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        list.next.next = new ListNode(3);
        list.next.next.next = new ListNode(4);

        print(list);

        reorder(list);

        print(list);
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
        this.next  = next;
    }
}
