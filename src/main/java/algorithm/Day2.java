package algorithm;

public class Day2 {
    //203.移除链表元素
    public ListNode removeElements(ListNode head, int val) {
        ListNode virtual_head = new ListNode(0, head);

        ListNode prev = virtual_head, current = head;

        while (current != null) {
            if (current.val == val) {
                prev.next = current.next;
            } else {
                prev = current;
            }
            current = current.next;
        }

        return virtual_head.next;
    }

    //707. 设计链表  fail
    class MyLinkedList {
        private int size;
        private ListNode head;
        private ListNode tail;

        public MyLinkedList() {

        }

        public int get(int index) {
            if (index >= size) return -1;
            ListNode p = head;
            while (index-- > 0) p = p.next;
            return p.val;
        }

        public void addAtHead(int val) {
            head = new ListNode(val, head);
            if (size == 0) tail = head;
            size++;
        }

        public void addAtTail(int val) {
            ListNode node = new ListNode(val);
            if (size == 0) head = tail = node;
            else {
                tail.next = node;
                tail = node;
            }
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (index == size) {
                addAtTail(val);
            } else if (index == 0) {
                addAtHead(val);
            } else if (index > 0 && index < size) {
                ListNode p = head;
                while (index-- > 0) {
                    p = p.next;
                }
                p.next = new ListNode(val, p.next);
                size++;
            }
        }

        public void deleteAtIndex(int index) {
            if (index == 0 && size > 0) {
                head = head.next;
            } else if (index > 0 && index < size) {
                ListNode p = head;
                while (index-- > 0) {
                    p = p.next;
                }
                if (p.next != null)
                    p.next = p.next.next;

            }
            size--;
        }
    }

    //206. 反转链表
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(0);
        while (head != null) {
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        return dummy.next;
    }

    //24. 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode current = head;
        ListNode next = null;
        if (current != null) next = head.next;
        while (next != null) {
            current.next = next.next;
            prev.next = next;
            next.next = current;

            prev = prev.next.next;
            current = prev.next;
            if (current == null)
                next = null;
            else next = current.next;
        }

        return dummy.next;
    }

    //19. 删除链表的倒数第 N 个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode p = dummy;
        while (n-- > 0) p = p.next;
        ListNode q = dummy;
        while (p != null) {
            p = p.next;
            q = q.next;
        }
        q.next = q.next.next;
        return dummy.next;
    }

    //160. 相交链表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA, q = headB;
        while (p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }

    //142. 环形链表 II
    public ListNode detectCycle(ListNode head) {
        ListNode p = head, q = head;
        while (q != null && q.next != null) {
            p = p.next;
            q = q.next.next;

            if (p == q) {
                p = head;
                while (p != q) {
                    p = p.next;
                    q = q.next;
                }
                return p;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Day2 day2 = new Day2();
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
