public class ArrayDeque<T> implements Deque<T>{
    private T[] array;
    private int head;
    private int tail;
    private int size;

    public ArrayDeque(){
        array =(T[]) new Object[8];
        size = 0;
    }

    public ArrayDeque(LinkedListDeque other){
        for(int i = 0; i < other.size();i++){
            addLast((T)(other.get(head+i)));
        }
    }
    public  void doublesize(){
        Object[]  temp = new Object[array.length*2];
        for (int i = 0 ; i<size; i++)
        {
            temp[i] =(T) (array[(i+head)%array.length]);
        }
        array = (T[]) (temp);
        head = 0;
        tail = size;
    }

    public  void halfsize(){
        Object[]  temp = new Object[array.length/2];
        for (int i = 0 ; i<size; i++)
        {
            temp[i] =(T) (array[(i+head)%array.length]);
        }
        array = (T[]) (temp);
        head = 0;
        tail = size;
    }

    @Override
    public void addFirst(T item){
        --head;
        if(head<0)  head += array.length;
        array[head] = item;
        size ++;
        if(size == array.length)  doublesize();
    }

    @Override
    public void addLast(T item){
        array[tail++] = item;
        if(tail>=array.length)   tail -= array.length;
        size ++;
        if(size == array.length)   doublesize();
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        if(isEmpty()) {
            System.out.println("");
        }else{
            for(int i=0; i<size; i++) {
                System.out.print("" + array[(i+head)%array.length]);
                if (i == size - 1) System.out.println();
                else System.out.print(" ");
            }
        }
    }

    @Override
    public T removeFirst(){
        double ratio;
        if(isEmpty())  return null;
        T ans = array[head];
        if(head++ >= array.length)  head = head - array.length;
        size --;
        while ((ratio = (double) size/array.length) < 0.25){
            halfsize();
        }
        return ans;
    }

    @Override
    public T removeLast(){
        double ratio;
        if(isEmpty())  return null;
        if(tail-- < 0)  tail = tail + array.length;
        T ans = array[tail];
        size --;
        while ((ratio = (double) size/array.length) < 0.25){
            halfsize();
        }
        return ans;
    }

    @Override
    public T get(int index){
        int key = head + index;
        if(key >= array.length)  key -= array.length;
        return array[key];
    }
}