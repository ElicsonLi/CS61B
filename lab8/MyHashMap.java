import java.util.*;

public class MyHashMap<K,V> implements Map61B<K,V> {

    private double loadFactor;
    private ArrayList<MyMap>[]  bucket;
    int size;
    private final int MaxBucketSize = 2;
    public MyHashMap(){
        bucket = new ArrayList[16];
        loadFactor = 0.75;
    }
    public MyHashMap(int initialSize){
        bucket = new ArrayList[initialSize];
        loadFactor = 0.75;
    }
    public MyHashMap(int initialSize, double loadFactor){
        bucket = new  ArrayList[initialSize];
        this.loadFactor = loadFactor;
    }

    private class MyMap{
        public K key;
        public V value;

        MyMap(K k, V v){
            key = k;
            value = v;
        }

        @Override
        public int hashCode() {
            return Math.floorMod(key.hashCode(),bucket.length);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)  return false;
            if (obj == this)  return true;
            if (obj.getClass() != this.getClass())  return false;
            MyMap other = (MyMap) obj;
            return (key.equals((other.key))) && (value.equals(other.value));
        }
    }


    private void doubleSize(){
        ArrayList<MyMap>[]  newBucket = new ArrayList[bucket.length * 2];
        for(int i = 0; i < bucket.length; i ++ ){
            if (bucket[i] != null) {
                for (int j = 0; j < bucket[i].size(); j++) {
                    int bucketnum = Math.floorMod(bucket[i].get(j).key.hashCode(), newBucket.length);
                    if (newBucket[bucketnum] == null) {
                        newBucket[bucketnum] = new ArrayList<>();
                    }
                    newBucket[bucketnum].add(bucket[i].get(j));
                }
            }
        }
        bucket = newBucket;
    }

    /** Removes all of the mappings from this map. */
    public void clear(){
        for(int i = 0; i < bucket.length; i++){
            if(bucket[i] != null){
                bucket[i].clear();
            }
        }
        size = 0;
    }


    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        return (get(key) != null);
    }

    public MyMap getMyMap(K key){
        if (key == null) return null;
        int bucketNum = Math.floorMod(key.hashCode(),bucket.length);
        if(bucket[bucketNum] == null) return null;
        for (MyMap i : bucket[bucketNum]){
            if(i.key.equals(key)) {
                return i;
            }
        }
        return null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        MyMap mp = getMyMap(key);
        if (mp == null) return null;
        return mp.value;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size(){
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value){
        if(containsKey(key)){
            MyMap mp = getMyMap(key);
            mp.value = value;
        }else {
            int bucketnum = Math.floorMod(key.hashCode(),bucket.length);
            MyMap map = new MyMap(key,value);
            if(bucket[bucketnum] == null){
                bucket[bucketnum] = new ArrayList<>();
            }
            bucket[bucketnum].add(map);
            size ++;
            if(size/bucket.length >= MaxBucketSize){
                doubleSize();
            }
        }

    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        Set<K> set = new HashSet<>();
        for (int i = 0; i < bucket.length; i++){
            if (bucket[i] != null){
                for (int j = 0; j < bucket[i].size();j++){
                    set.add(bucket[i].get(j).key);
                }
            }
        }
        return set;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key){
        MyMap mp = getMyMap(key);
        if(mp == null) return null;
        int bucketnum = Math.floorMod(key.hashCode(),bucket.length);
        bucket[bucketnum].remove(mp);
        return mp.value;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value){
        MyMap mp = getMyMap(key);
        if(mp == null || !(mp.value.equals(value))) return null;
        int bucketnum = Math.floorMod(key.hashCode(),bucket.length);
        bucket[bucketnum].remove(mp);
        return mp.value;
    }

    public MHMIterator<K> iterator(){
        return new MHMIterator<K>();
    }

    public class MHMIterator<K> implements Iterator<K> {
        K[] array = (K[]) keySet().toArray();
        int wisPos;
        public MHMIterator() { wisPos = 0; }
        public boolean hasNext(){
            return wisPos < array.length;
        }

        @Override
        public K next() {
            K s = array[wisPos];
            wisPos ++;
            return s;

        }
    }
}
