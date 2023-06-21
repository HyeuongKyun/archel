//풀이 방법: stack을 이용하여 균형잡힌 괄호 문자열인지, 올바른 괄호 문자열인지 판단하고 
import java.util.*;

class Solution {
    static Stack<Character> stackRight, stackBalance;
    static String result;
    public String solution(String p) {
        stackRight = new Stack<>();
        stackBalance = new Stack<>();
        result = "";
        
        String[] uv = uvCreate(p);
        
        String answer = dfs(uv[0], uv[1]);
        return answer;
    }//solution
    
    public static String[] uvCreate(String p){
        int[] count = new int[2];
        
        int i = 0;
        String u = "";
        for(i=0; i<p.length(); i++){
            char tempChar = p.charAt(i);
            if(tempChar=='(') count[0]++;
            else count[1]++;
            u += tempChar;
            if(count[0]!=0 &&(count[0]==count[1])) break;
        }//for
        
        String v = "";
        if(p.length()!=0) v = p.substring(i+1, p.length());
        
        // System.out.println(v.equals(""));//true 빈문자열
        return new String[]{u, v};
    }//uvCreate
    
    public static String dfs(String u, String v){
        if(checkRight(u, v)){
            return u+v;
        }//재귀조건
        
        String nextU = "";
        String[] nextUV = uvCreate(v);
        String vRetValue = dfs(nextUV[0], nextUV[1]);
        if(checkRight(u, "")){
            return u + vRetValue;
        } else {
            nextU += '(';
            nextU += vRetValue;
            nextU += ')';
            for(int i=1; i<u.length()-1; i++){
                if(u.charAt(i)=='(') nextU += ')';
                else nextU += '(';
            }
            return nextU;
        }
        
        
        
    }//dfs
    
    public static boolean checkRight(String u, String v){
        String temp = u + v;
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<temp.length(); i++){
            char tempChar = temp.charAt(i);
            if(tempChar=='(') stack.push('(');
            else {
                if(stack.size()!=0) stack.pop();
                else return false;
            }
        }//for i
        
        //여기까지 왔으면 올바른 괄호 문자열이라는뜻
        return true;
        
    }
    
}