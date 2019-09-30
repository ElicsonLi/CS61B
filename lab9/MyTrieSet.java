import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    Node root;

    private class Node{
        private char c;
        private HashSet<Node>  next;
        private boolean isEnd;

        Node(char ch, boolean end ){
            c = ch;
            isEnd = end;
            next = new HashSet<>();
        }

        public Node add(Node nd,char ch, boolean end){
            Node x = new Node(ch,end);
            nd.next.add(x);
            return x;
        }

        public char getValue(){
            return c;
        }

        public void setEnd(){
            isEnd = true;
        }

        public boolean isEnd(){
            return isEnd;
        }

        public Node lastNode(String key){
            Node pointer = root;
            char[] keys = key.toCharArray();
            for (int i = 0; i < keys.length; i ++){
                if (pointer.next == null) break;
                for (Node j : pointer.next){
                    if (j.getValue() == keys[i]){
                        pointer = j;
                        break;
                    }
                }
            }
            return pointer;
        }

        public boolean contains(String key){
            Node pointer = lastNode(key);
            return (pointer.getValue() == key.charAt(key.length()-1)) && (pointer.isEnd);
        }

        public void prefix(Node nd,String key,List<String> ls){
            if(nd == null){
                return;
            }

            if(nd.isEnd() == true){
                ls.add(key);
            }
            for (Node i : nd.next){
                String x = key + i.getValue();
                prefix(i,x,ls);
            }
        }

    }
    public MyTrieSet(){
        root = new Node('n',false);
    }

    @Override
    /** Clears all items out of Trie */
    public void clear(){
        root.next = null;
    }

    @Override
    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key){
        return root.contains(key);
    }

    @Override
    /** Inserts string KEY into Trie */
    public void add(String key){
        Node lastNode = root;
        int lastValue = 0;
        for(int i = 0; i < key.length(); i++){
            String x = key.substring(0,key.length()-i);
            lastNode = root.lastNode(x);
            if(lastNode.getValue() == x.charAt(x.length()-1)){
                lastValue = key.length() - i;
                lastNode = root.lastNode(x);
                break;
            }
        }

        if (lastValue == key.length())  {
            lastNode.setEnd();
            return;
        }else {
            for (int i = lastValue; i < key.length() - 1; i++) {
                Node t = lastNode.add(lastNode, key.charAt(i), false);
                lastNode = t;
            }
            lastNode.add(lastNode, key.charAt(key.length() - 1), true);
        }
    }

    @Override
    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix){
        List<String> ans = new ArrayList<>();
        Node lastNode = root.lastNode(prefix);
        root.prefix(lastNode,prefix,ans);

        return ans;
    }

    @Override
    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key){
        throw new UnsupportedOperationException();
    }
}
