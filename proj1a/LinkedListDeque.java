public class LinkedListDeque<T>{
    private class ItemNode {
        public T item;
        public ItemNode next;
        public ItemNode prev;
        public ItemNode(T i, ItemNode n, ItemNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private ItemNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new ItemNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other){
        sentinel = new ItemNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

        for(int i = 0; i < other.size();i++){
            addLast((T)(other.get(i)));
        }
    }

    public void addFirst(T item){
        sentinel.next = new ItemNode(item,sentinel.next,sentinel);
        size ++;
    }

    public void addLast(T item){
        sentinel.prev = new ItemNode(item,sentinel,sentinel.prev);
        size ++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        if(isEmpty()) {
            System.out.println("");
        }else{
            ItemNode p = sentinel;
            for(int i=0; i<size; i++) {
                System.out.print("" + p.item);
                if (i == size - 1) System.out.println();
                else System.out.print(" ");
            }
        }
    }

    public T removeFirst(){
        if(isEmpty())  return null;
        ItemNode p = sentinel.next;
        sentinel.next = sentinel.next.next;
        size --;
        return p.item;
    }

    public T removeLast(){
        if(isEmpty())  return null;
        ItemNode p =sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        size --;
        return p.item;
    }

    public T get(int index){
        ItemNode p = sentinel.next;
        for (int i = 0; i<size; i++){
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index){
        LinkedListDeque p =new LinkedListDeque(this);
        if (index == 0)  return sentinel.next.item;
        p.removeFirst();
        return (T)(p.getRecursive(index-1));
    }

}