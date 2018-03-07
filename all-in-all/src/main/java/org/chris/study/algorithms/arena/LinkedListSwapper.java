package org.chris.study.algorithms.arena;

/**
 * Challenge: given a linked list, swap every two adjacent nodes and return its head.
 * 
 * @author Chris
 */
public class LinkedListSwapper {

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

        // adding a new node
        // newNode = new ListNode();
        // newNode.val = 3;
        // temp.nextNode = newNode;
        // temp = newNode;

        return head;
    }

    public static void main(String[] args) {
        LinkedListSwapper swapper = new LinkedListSwapper();
        ListNode originalList = constructList();
        printList(originalList);
        ListNode swappedList = swapper.swapPairs(originalList);
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

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.nextNode == null) {
            return head;
        }
        ListNode newHead = head.nextNode;
        ListNode left = null;
        ListNode right = null;
        while (head != null && head.nextNode != null) {
            if (left != null) {
                left.nextNode = head.nextNode;
            }
            left = head;
            right = head.nextNode;
            left.nextNode = right.nextNode;
            right.nextNode = left;
            head = left.nextNode;
        }
        return newHead;
    }
}
