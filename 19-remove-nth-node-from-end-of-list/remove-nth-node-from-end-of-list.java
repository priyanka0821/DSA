/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public static int getSize(ListNode head){
        if(head==null) return 0;
        if(head.next==null) return 1;
        return 1+getSize(head.next);
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null) return head;
         int size=getSize(head);
        if(head.next==null || n==size) return head.next;
       
        ListNode temp=head;

        for(int i=1;i<size-n;i++){
            temp=temp.next;
        }

       
        temp.next=temp.next.next;
        return head;    
    }
}