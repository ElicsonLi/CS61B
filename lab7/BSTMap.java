import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {

    private int size;

    private Node map;

    private class Node {
        K key;
        V val;
        Node left;
        Node right;

        /**
         * Stores KEY as the key in this key-value pair, VAL as the value, and
         * NEXT as the next node in the linked list.
         */
        Node(K k, V v, Node l, Node r) {
            key = k;
            val = v;
            left = l;
            right = r;
        }

        /**
         * Returns the Entry in this linked list of key-value pairs whose key
         * is equal to KEY, or null if no such Entry exists.
         */
        Node get(Node nd,K k) {
            if (nd == null) {
                return null;
            }
            if (k.compareTo(nd.key) > 0) {
                return get(nd.right,k);
            } else if (k.compareTo(nd.key) < 0) {
                return get(nd.left,k);
            } else {
                return nd;
            }
        }

        void print(Node nd){
            if(nd == null){
                return;
            }
            print(nd.left);
            System.out.println(""+nd.key+": "+nd.val+" ");
            print(nd.right);
            return;
        }
    }


    @Override
    public void clear() {
        size = 0;
        map = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        V ans = get(key);
        if (ans == null) {
            return false;
        }
        return true;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (map == null){
            return null;
        }
        Node nd = map.get(map,key);
        if (nd == null) return null;
        return nd.val;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            Node temp = map.get(map,key);
            temp.val = value;
        } else if (map == null) {
            map = new Node(key, value, null, null);
            size ++;
        } else{
            Node nd = map;
            while (nd != null){
                if (key.compareTo(nd.key) > 0) {
                    if(nd.right != null){
                        nd = nd.right;
                    }else {
                        nd.right = new Node(key,value,null,null);
                        size ++;
                        break;
                    }
                }else{
                    if(nd.left != null){
                        nd = nd.left;
                    }else {
                        nd.left = new Node(key,value,null,null);
                        size ++;
                        break;
                    }
                }
            }
        }
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder(){
        map.print(map);
    }
}
