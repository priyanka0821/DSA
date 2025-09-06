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
      ListNode temp1=l1;
   ListNode temp2=l2;
      
      //create dummy node 
      ListNode dummyNode=new ListNode(-1);
     ListNode curr=dummyNode;
      int carry=0;
      
      
      while(temp1!=null || temp2!=null || carry!=0){
          int sum=carry;
          
          //check if ll is not null
          if(temp1!=null) sum+=temp1.val;
          if(temp2!=null) sum+=temp2.val;
          
          //create new node
         ListNode newNode=new ListNode(sum%10);
          carry=sum/10;
          
          curr.next=newNode;
          curr=curr.next;
          
          //check if ll is not null and increment them
          if(temp1!=null) temp1=temp1.next;
          if(temp2!=null) temp2=temp2.next;
      }
      return dummyNode.next;
    }
}