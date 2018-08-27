package org.chris.study.algorithms.arena;

/**
 * Challenge: given a linked list, swap every two adjacent nodes and return its head.
 * 
 * @author Chris
 */
public class LinkedListReverser {

    private static ListNode constructList() {
        ListNode head = new ListNode();
        ListNode temp = head;
        temp.val = 1;
        ListNode newNode = null;

        newNode = new ListNode();
        newNode.val = 2;
        temp.nextNode = newNode;
        temp = newNode;
        newNode = new ListNode();
        newNode.val = 3;
        temp.nextNode = newNode;
        temp = newNode;
        newNode = new ListNode();
        newNode.val = 4;
        temp.nextNode = newNode;
        temp = newNode;
        newNode = new ListNode();
        newNode.val = 5;
        temp.nextNode = newNode;
        temp = newNode;
        newNode = new ListNode();
        newNode.val = 6;
        temp.nextNode = newNode;
        temp = newNode;

        // adding a new node
        // newNode = new ListNode();
        // newNode.val = 3;
        // temp.nextNode = newNode;
        // temp = newNode;

        return head;
    }

    public static void main(String[] args) {
        LinkedListReverser swapper = new LinkedListReverser();
        ListNode originalList = constructList();
        printList(originalList);
        ListNode swappedList = swapper.reverseBetween(originalList, 1, 6);
        printList(swappedList);
    }

    private static void printList(ListNode head) {
        ListNode temp = head;
        if (temp == null) {
            return;
        } else {
            System.out.print(head.val);
        }
        temp = temp.nextNode;
        while (temp != null) {
            System.out.print("->" + temp.val);
            temp = temp.nextNode;
        }
        System.out.println();
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        assert m < n;
        if (head == null || head.nextNode == null) {
            return head;
        }
        boolean fromStart = false;
        if (m == 1) {
            fromStart = true;
            ListNode extraHead = new ListNode();
            extraHead.nextNode = head;
            head = extraHead;
            m++;
            n++;
        }
        ListNode mNode = head;
        ListNode preNode = head;
        int i = 1;
        for (; i < m; i++) {
            preNode = mNode;
            mNode = mNode.nextNode;
        }
        ListNode swapNode = mNode.nextNode;
        while (i < n) {
            ListNode nextNode = swapNode.nextNode;
            swapNode.nextNode = preNode.nextNode;
            preNode.nextNode = swapNode;
            mNode.nextNode = nextNode;
            swapNode = nextNode;
            i++;
        }
        if (fromStart) {
            head = head.nextNode;
        }
        return head;
    }
}
