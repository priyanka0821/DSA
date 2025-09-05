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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1=l1, temp2=l2;
        ListNode head=new ListNode(0);
        ListNode temp=head;
        int carry=0;
        while(temp1!=null && temp2!=null){
            int sum=temp1.val+temp2.val+carry;
            ListNode newNode=new ListNode();
            if(sum>9){
                newNode.val=sum%10;
                carry=sum/10;
            }else{
                newNode.val=sum;
                carry=0;
            }
            temp.next=newNode;
             temp = temp.next;
            temp1=temp1.next;
            temp2=temp2.next;
        }
        while(temp1!=null){
            int sum=temp1.val+carry;
            ListNode newNode=new ListNode();
            if(sum>9){
                newNode.val=sum%10;
                carry=1;
            }else{
                newNode.val=sum;
                carry=0;
            }
            temp.next=newNode;
             temp = temp.next;
            temp1=temp1.next;
        }
        while(temp2!=null){
            int sum=temp2.val+carry;
            ListNode newNode=new ListNode();
            if(sum>9){
                newNode.val=sum%10;
                carry=1;
            }else{
                newNode.val=sum;
                carry=0;
            }
             temp.next=newNode;
              temp = temp.next;
            temp2=temp2.next;
        }
        if(carry!=0){
            ListNode newNode=new ListNode(carry);
            temp.next=newNode;
        }
        return head.next;
    }
}