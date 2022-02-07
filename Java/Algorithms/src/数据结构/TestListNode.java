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

}
