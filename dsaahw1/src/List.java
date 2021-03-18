
public class List {
    public ListNode headListNode;
    private int size;
    private int sorted;
    public List() {
        headListNode = null;
        sorted = 0;
        size = 0;
    }

    public List(ListNode node) {
        headListNode = node;
        size = 1;
        sorted = 0;
    }

    public int size() {
        return size;
    }
    public int sorted() {
        return sorted;//0-unsorted, 1-ascending, -1-descending
    }

    @Override
    public String toString() {
        String str = "[";
        //str += val;
        ListNode pListNode = headListNode;
        while (pListNode.next != null) {
            str += pListNode.val + ", ";
            pListNode = pListNode.next;
        }
        str += pListNode.val + "]";
        return str;
    }

    public void sort(){
        if(sorted==1){
            headListNode = sortList(headListNode);
            sorted = 1;
        }else if(sorted==-1){
            headListNode = sortList(headListNode);
            this.reverse();
            sorted = -1;
        }else{
            headListNode = sortList(headListNode);
            sorted = 1;
        }
    }
    //sort the list ascending. Any sorting algorithm is OK.
    //attribute sorted should be changed to 1
    public void reverse(){
        ListNode cur = headListNode;
        ListNode pre = null;
        ListNode tmp;
        while (cur != null){
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        headListNode = pre;
        sorted = -sorted;
    }
    //reverse the order of nodes of list
    //attribute sorted should be changed if the list is sorted before
    public void addNode(ListNode node){ //add node to tail of list
        ListNode last = new ListNode(0);
        if(size>0){
            last = headListNode;
            while(last.next != null){
                last = last.next;
            }
            last.next = node;
            size += 1;
        }else{
            headListNode = node;
            size = 1;
        }
    }

    public void addHeadNode(ListNode node) {
        ListNode newNode = node;
        if (headListNode == null) {
            headListNode = newNode;
            size += 1;
            return;
        }
        newNode.next = headListNode;
        headListNode = newNode;
        size += 1;
    }
    public void addNodeSorted(ListNode node){
        ListNode tmp = headListNode;
        while (tmp!=null){
            if(tmp.val==node.val||(tmp.val<node.val && node.val<tmp.next.val)||(tmp.val>node.val && node.val>tmp.next.val)){
                node.next = tmp.next;
                tmp.next = node;
                break;
            }
            tmp = tmp.next;
        }
        /*addNode(node);
        this.sort();*/
    }
    //add node to sorted list and keep list still sorted
    //node should add to the position according to the value

    public void addNode(int index, ListNode node){
        if(index<0||index>size){return;}
        if(index == 0){
            addHeadNode(node);
            size += 1;
        }else if(index == size){
            addNode(node);
            size += 1;
        }else{
            ListNode newNode = node;
            int p = 0;
            ListNode cur = headListNode;
            ListNode pre = null;
            while (cur!=null){
                if(p == index){
                    newNode.next = cur;
                    pre.next = newNode;
                    size += 1;
                    return;
                }
                pre = cur;
                cur = cur.next;
                p++;
            }
            if(p == index){
                newNode.next = cur;
                pre.next = newNode;
                size += 1;
                return;
            }
        }
    }
    //add node to position of index, which is from 0
    public boolean deleteNode(ListNode node){
        ListNode cur = headListNode;
        ListNode pre = null;
        boolean t = false;
        if(node==null){return true;}
        while (cur!=null){
            if(cur.equals(node)){
                if(size==1){headListNode = null;size-=0;return true;}
                   pre.next=cur.next;
                   size -= 1;
                   t = true;
            }
            pre = cur;
            cur = cur.next;
        }
        return t;
    }
    //delete node, return true if success, false if fail
    public boolean deleteNode(int index){
        if(index<0|index>=size){ return false; }
        if(index==0){
            headListNode = headListNode.next;
            size -= 1;
            return true;
        }else {
            int p = 0;
            ListNode cur = headListNode;
            ListNode pre = new ListNode(0);
            pre.next = headListNode;
            while (cur!=null){
                if(p == index){
                    pre.next = cur.next;
                    //cur.next = null;
                    size -= 1;
                    return true;
                }
                pre = cur;
                cur = cur.next;
                p++;
            }
        }
        return false;
    }
    //delete node at position index(from 0), return true if success, false if fail
    public void deleteDuplicates(){
        ListNode tmp = headListNode;
        ListNode tmp2;
        ListNode pre;
        while (tmp != null){
            tmp2 = tmp.next;
            pre = tmp;
            while (tmp2 != null){
                if(tmp.val == tmp2.val){
                    pre.next = tmp2.next;
                    pre = pre.next;
                    tmp2 = tmp2.next;
                }else{
                    pre = pre.next;
                    tmp2 = tmp2.next;
                }
            }
            tmp = tmp.next;
        }
    }
    //delete duplicated node from unsorted list
    public void deleteSortedDuplicates(){
        this.deleteDuplicates();
    }
    // delete duplicated node from sorted list
   public void mergeList(List listToMerge){//merge head of listToMerge to tail of original list
        ListNode tmp = headListNode.next;
        while (tmp.next!=null){
            tmp = tmp.next;
        }
        tmp.next = listToMerge.headListNode;
    }

    public void mergeSortedList(List listToMerge){ //merge to sorted lists and keep new list still sorted
        this.mergeList(listToMerge);
        this.sort();
    }

    private ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = getM(head);
        ListNode right = mid.next;
        mid.next = null;
        return mergeSort(sortList(head), sortList(right));
    }
    private ListNode getM(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode b = head, a = head;
        while (a.next != null && a.next.next != null) {
            b = b.next;
            a = a.next.next;
        }
        return b;
    }
    private ListNode mergeSort(ListNode a, ListNode b) {
        ListNode head, cur;
        head = new ListNode(0);
        cur = head;

        while (a != null && b != null) {
            if (a.val <= b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }
        cur.next = (a == null) ? b : a;
        return head.next;
    }

}
