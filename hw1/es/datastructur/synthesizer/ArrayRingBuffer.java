package es.datastructur.synthesizer;
import java.awt.event.ItemEvent;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;


    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
    }

    @Override
    public int capacity(){
        return rb.length;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }


    @Override
    public void enqueue(T x) {
        if(!isEmpty() && isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        if(last >= capacity()){
            last -= capacity();
        }
        rb[last] = x;
        last ++;
        fillCount ++;

        return;
    }


    public T dequeue() {
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        if (first >=  capacity()){
            first -= capacity();
        }
        T returnfirst = rb[first];
        first ++;
        fillCount --;
        return returnfirst;
    }


    @Override
    public T peek() {
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        if (first >= capacity()){
            first -= capacity();
        }
        T returnfirst = rb[first];
        return returnfirst;
    }


    private class ARBIterator implements Iterator<T>{
        int wizPos;
        public ARBIterator() { wizPos = 0; }


        public boolean hasNext(){
            return wizPos < rb.length;
        }

        public T next(){
            T ans = rb[wizPos];
            wizPos ++;
            return ans;
        }

    }

    @Override
    public Iterator<T> iterator(){
        return new ARBIterator();
    }

    @Override
    public boolean equals(Object o){
        if (o == null)  return false;
        if (o == this)  return true;
        if (o.getClass() != this.getClass())  return false;
        ArrayRingBuffer<T> others = (ArrayRingBuffer<T>) o;
        if (others.fillCount != this.fillCount) {
            return false;
        }

        int oposition = others.first;

        for(T i : this){
            if (!i.equals(others.rb[oposition++]))
            {
                return false;
            }
        }

        return true;

    }
}

