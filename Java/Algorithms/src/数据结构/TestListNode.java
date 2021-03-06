package 数据结构;

/**
 * @author ZhangCheng
 * @version 1.0
 * @date 2022/1/9 10:52
 * 链表
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {this.val = val;}
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
public class TestListNode {

    //    合并两个有序链表
    public ListNode mergeLists(ListNode list1, ListNode list2) {
        ListNode root=new ListNode();
        ListNode p1=list1,p2=list2,p=root;
        while(p1!=null&&p2!=null){
            if(p1.val<p2.val) {
                p.next = p1;
                p1=p1.next;
            }
            else{
                p.next=p2;
                p2=p2.next;
            }
            p=p.next;
        }
        if(p1==null)
            p.next=p2;
        if(p2==null)
            p.next=p1;
        return root.next;
    }

    //    移除链表元素
    public ListNode removeElements(ListNode head, int val) {
        ListNode root=new ListNode(0,head);
        ListNode p=root;
        while(p.next!=null){
            if(p.next.val==val)
                p.next=p.next.next;
            else
                p=p.next;
        }
        return root.next;
    }

//    给你一个链表的头节点 head ，判断链表中是否有环。
    public boolean hasCycle(ListNode head) {
//        双指针
        ListNode fast=head,slow=head;
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if (slow==fast)
                return true;
        }
        return false;
    }

//    反转链表 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
    public ListNode reverseList(ListNode head) {
        ListNode prev=null,cur=head;
        while (cur!=null){
            ListNode next=cur.next;
            cur.next=prev;
            prev=cur;
            cur=next;
        }
        return prev;
    }

//    给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null)
            return null;
        ListNode p=head;
        while (p.next!=null){
            if (p.next.val==p.val)
                p.next=p.next.next;
            else
                p=p.next;
        }
        return head;
    }

    //    在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，判断数组中是否含有该整数。
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int rows = matrix.length, columns = matrix[0].length;
        int row = 0, column = columns - 1;
        while (row < rows && column >= 0) {
            int num = matrix[row][column];
            if (num == target)
                return true;
            else if (num > target)
                column--;
            else
                row++;
        }
        return false;
    }
}

