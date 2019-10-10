package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<Node> arrayTree;
    private int size;

    public ArrayHeapMinPQ(){
        arrayTree = new ArrayList<>();
        arrayTree.add(new Node(null,Double.NEGATIVE_INFINITY,0));
        size = 0;
    }

    private class Node{
        private T data;
        private double priority;
        private int pos;
        public Node(T x, double p,int ps){
            data = x;
            priority = p;
            pos = ps;
        }

        public Node(Node s){
            this.pos = s.pos;
            this.priority = s.priority;
            this.data = s.data;
        }

        public void changePriority(double p){
            priority = p;
        }

        public void changeData(T d){
            data = d;
        }

        public void changePos(Node x){
            int temp = this.pos;
            this.pos = x.pos;
            x.pos = temp;
        }

        private void copy(Node s){
            this.pos = s.pos;
            this.priority = s.priority;
            this.data = s.data;
        }
    }
    private Node parent(Node x){
        return arrayTree.get(x.pos/2);
    }

    private Node minchild(Node x){
        Node leftchild = arrayTree.get(2*x.pos);
        if(2 * x.pos + 1 > size){
            return leftchild;
        }
        Node rightchild = arrayTree.get(2*x.pos + 1);
        return (leftchild.priority > rightchild.priority) ? rightchild : leftchild;
    }



    private void updatepos(Node x){
        while ((x.pos != 1)&&(x.priority < parent(x).priority)){
            Node p = parent(x);
            int parentIndex = p.pos;
            int xIndex = x.pos;
            x.changePos(p);
            Collections.swap(arrayTree,xIndex,parentIndex);
        }
        while ((2 * x.pos + 1 < size)&&(x.priority > minchild(x).priority)){
            Node c = minchild(x);
            int childIndex = c.pos;
            int xIndex = x.pos;
            x.changePos(c);
            Collections.swap(arrayTree,xIndex,childIndex);
        }
    }

    private Node find(T item){
        for(Node nd : arrayTree){
            if(nd.data == null) continue;
            if(nd.data.equals(item)){
                return nd;
            }
        }
        return null;
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority){
        if(contains(item)) {
            throw new IllegalArgumentException();
        }
        Node x = new Node(item,priority,size + 1);
        arrayTree.add(x);
        updatepos(x);
        size ++;
    }

    /* Returns true if the PQ contains the given item. */
    //@Override
    public boolean contains(T item){
        return (find(item) != null);
    }
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest(){
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return arrayTree.get(1).data;
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        Node minPQ = arrayTree.get(1);
        Node last = arrayTree.get(size);
        Collections.swap(arrayTree,minPQ.pos,last.pos);
        minPQ.changePos(last);
        arrayTree.remove(size);
        size --;
        if(size != 0){
            updatepos(arrayTree.get(1));
        }


        return minPQ.data;
    }
    /* Returns the number of items in the PQ. */

    @Override
    public int size(){
        return size;
    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority){
        Node nd = find(item);
        if (nd == null){
            throw new NoSuchElementException();
        }
        nd.changePriority(priority);
        updatepos(nd);
    }
}
