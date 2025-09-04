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
    public static ListNode reverse(ListNode head){
        ListNode curr=head, prev=null, next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }

    public static boolean compareLL(ListNode head1, ListNode head2){
        ListNode temp1=head1, temp2=head2;
        while(temp2!=null){
            if(temp1.val==temp2.val){
                temp1=temp1.next;
                  temp2=temp2.next;
            }else return false;
        }
        return true;
    }
    public boolean isPalindrome(ListNode head) {
       //find middle of ll
       ListNode slow=head, fast=head;
       while(fast!=null && fast.next!=null){
        slow=slow.next;
        fast=fast.next.next;
       }

       //reverse second half
       ListNode head2=reverse(slow);

       //compare both half
     boolean isPal =compareLL(head,head2);
     //again reverse second half;
     reverse(slow);
     return isPal;


    }
}