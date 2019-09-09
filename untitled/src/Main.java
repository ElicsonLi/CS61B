public class Main {
    public static void main(String[] args)
    {
        Solution s = new Solution();
        System.out.println(s.isPalindrome(123));
    }
}

class Solution {
    public boolean isPalindrome(int x) {
        StringBuilder sb1 = new StringBuilder(""+x);
        String str1 = sb1.reverse().toString();
        int ans = Integer.parseInt(str1);

        if (ans == x)  return true;
        return false;


    }
}