class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class LinkList {
    //NO.206 反转列表
    // 头插法和递归，两种解题思路


    //NO.19
    //删除链表倒数第N个节点
    //快慢指针
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode resNode = head;
        ListNode postNode = head;
        if (n==1) return head.next;

        int i = 1;
        while(i<=n) {
            head = head.next;
            i += 1;
        }
        ListNode preNode = head;
        if (head == null) return resNode.next;

        while(preNode.next != null) {
            postNode = postNode.next;
            preNode = preNode.next;
        }
        postNode.next = preNode;
        return resNode;
    }

    //NO.82
    //删除链表中的重复元素
    //双指针
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode resNode = head;
        ListNode postNode = head;
        ListNode preNode = head.next;
        while (preNode != null) {
            if (preNode.val == postNode.val) {
                preNode = preNode.next;
            } else {
                postNode.next = preNode;
                preNode = preNode.next;
            }
        }
        return resNode;
    }

    public static void main(String[] args) {
        ListNode ten = new ListNode(10,null);
        ListNode nine = new ListNode(9,ten);
        ListNode eight = new ListNode(8,nine);
        ListNode seven = new ListNode(5,eight);
        ListNode six = new ListNode(4,seven);
        ListNode five = new ListNode(4,six);
        ListNode four = new ListNode(3,five);
        ListNode three = new ListNode(3,four);
        ListNode two = new ListNode(2,three);
        ListNode one = new ListNode(1,two);
        LinkList tmp = new LinkList();
        tmp.deleteDuplicates(one);

    }
}
