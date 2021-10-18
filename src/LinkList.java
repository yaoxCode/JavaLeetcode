import java.util.HashMap;
import java.util.Map;

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
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode resultHead = new ListNode();
        resultHead.next = null;
        //ListNode nextHead = head.next;
        while (head != null) {

            ListNode tmpNode = head;
            ListNode nextHead = head.next;
            tmpNode.next = resultHead.next;
            resultHead.next = tmpNode;
            head = nextHead;

        }
        return resultHead.next;
    }

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

    //141 判断链表是否有环
    //hash存储
    public boolean hasCycle(ListNode head) {
        Map<ListNode,Integer> currtMap = new HashMap<ListNode, Integer>();
        //istNode pointNode = head;
        while ( head != null ) {

            if (!currtMap.containsKey(head)) {
                currtMap.put(head,1);
            } else return true;
            head = head.next;
        }
        return false;
    }

    //160求两个交叉链表的交叉部分
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tmpAHead = headA;
        ListNode tmpBHead = headB;
        int lengA = 0,lengB = 0;
        while( tmpAHead != null) {
            lengA ++;
            tmpAHead =tmpAHead.next;
        }
        while( tmpBHead != null) {
            lengB ++;
            tmpBHead =tmpBHead.next;
        }
        int gap = Math.abs(lengA - lengB);
        if ( lengA >= lengB) {
            while(gap >0) {
                headA = headA.next;
                gap --;
            }
        } else {
            while(gap >0) {
                headB = headB.next;
                gap --;
            }
        }
        if (headA == headB) return headA;

        while (headA.next != null && headB.next !=null) {
            if (headA.next == headB.next) return headA.next;
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
    //21 合并链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode newHead = new ListNode(-200);
        ListNode resNode = newHead;
        while(l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                newHead.next = l1;
                newHead = newHead.next;
                l1 = l1.next;
            } else {
                newHead.next = l2;
                newHead = newHead.next;
                l2 = l2.next;
            }
        }
        if (l1 == null && l2 !=null) newHead.next = l2;
        if (l2 == null && l1 != null) newHead.next = l1;
        return  resNode.next;
    }

    //234 判断是否为回文链表
    public boolean isPalindrome(ListNode head) {
        if (head.next == null || (head == null)) return true;

        //一次遍历求长度
        int listLen = 0;
        ListNode copyHead = head;
        while (copyHead != null) {
            listLen += 1;
            copyHead = copyHead.next;
        }
        int middle = (listLen % 2 == 0) ? listLen/2-1 : listLen/2;

        //先反转前部分
        ListNode rightHead = new ListNode();
        rightHead.next = null;
        int index = 0;
        while(head != null){
            if (index <= middle) {
                ListNode tmpNode = head;
                ListNode nextNode = head.next;
                tmpNode.next = rightHead.next;
                rightHead.next = tmpNode;
                head = nextNode;
                index ++;
            } else break;

        }
        ListNode rightStart = null;
        if(listLen %2 ==0){
            rightStart = rightHead.next;
        } else {
            rightStart = rightHead.next.next;
        }        ListNode leftStart = head;
        //index = 0;
        while (rightStart!= null && leftStart!=null) {
            if (rightStart.val != leftStart.val) return false;
            rightStart = rightStart.next;
            leftStart = leftStart.next;
            // index ++;
        }
        //if (middle == 0)return (rightStart.val == leftStart.val);
        return true;
    }
    //203 删除链表中指定元素
    public ListNode removeElements(ListNode head, int val) {
        ListNode curNode = head;
        if (head == null) return null;
        if (head.next == null && head.val == val) {
            return null;
        }
        if (head.next == null) return head;

        while(curNode!=null && curNode.next!=null) {
            if (curNode.next.val == val) {
                curNode.next = curNode.next.next;
            } else {
                curNode = curNode.next;
            }
        }
        if (head.val != val) return head;
        else return head.next;
    }

    //142 环形链表2  找到环的入口
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slowPoint = head;
        ListNode fastPoint = head;
        ListNode startNode = head;
        while (fastPoint!=null && fastPoint.next!=null) {
            slowPoint = slowPoint.next;
            fastPoint = fastPoint.next.next;
            if (slowPoint == fastPoint ) break;

        }
        if (slowPoint != fastPoint) return null;

        while (startNode != fastPoint) {
            startNode = startNode.next;
            fastPoint = fastPoint.next;
        }
        return startNode;
    }

    //92 指定初末位置，反转链表
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode newHead = new ListNode(0);
        newHead.next = null;
        ListNode resNode = newHead;
        int cnt =1;
        while(head != null && cnt <= right)  {
            if (cnt < left) {
                newHead.next = head;
                newHead = newHead.next;
                head = head.next;
                newHead.next=null;
                cnt += 1;
            }
            if (left <= cnt && cnt <= right ) {
                //尾插法
                ListNode tmpNode = head;
                ListNode nextHead = head.next;
                tmpNode.next = newHead.next;
                newHead.next = tmpNode;
                head = nextHead;
                cnt +=1;
            }

        }
        while ( newHead.next != null) {
            newHead = newHead.next;
        }
        newHead.next = head;
        return resNode.next;
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
