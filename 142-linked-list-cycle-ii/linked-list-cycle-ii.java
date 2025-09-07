/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
     HashSet<ListNode> set=new HashSet<>();
    ListNode temp=head;
    while(temp!=null){

        //if node exist in loop means it has ccycle
        if(set.contains(temp)) return temp;

        //otherwise add the node
        set.add(temp);
        temp=temp.next;
    }    

    //no loop detected
    return null;
    }
}