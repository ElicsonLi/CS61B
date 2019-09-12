public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> ans = new ArrayDeque<>();

        for (int i = 0; i < word.length(); i++){
            ans.addLast(word.charAt(i));
        }

        return ans;
    }

    public boolean isPalindrome(String word){
        Deque<Character> temp = wordToDeque(word);
        while ((temp.size()!= 0)&&(temp.size() != 1)){
            if(temp.removeFirst() != temp.removeLast()){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        int len = word.length();
        for (int i = 0; i < word.length()/2; i++){
            if(!cc.equalChars(word.charAt(i),word.charAt(len-1-i))){
                return false;
            }
        }
        return true;
    }
}
