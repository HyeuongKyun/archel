import java.util.*;

class Solution {
    boolean solution(String s) {
        
        int N = s.length();
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<N;i++){
            char temp = s.charAt(i);
            if(temp=='('){
                stack.add(temp);
            }
            else{
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }//for
        
        if(stack.isEmpty()) return true;
        else return false;

    }
}