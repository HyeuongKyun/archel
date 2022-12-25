//풀이 방법
//String 하나하나 map에 번호가 있는지 확인해본다.
//번호가 1,000,000개 있고 최대 길이가 20이므로 최대 20,000,000만큼을 확인하면 된다.

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Map<String,Boolean> map = new HashMap<>();
        int N = phone_book.length;
        Arrays.sort(phone_book);
        
        for(int i=0;i<N;i++){
            String str = phone_book[i];
            int strN = str.length();
            map.put(str,true);
            for(int j=1;j<strN;j++){
                boolean check = false;
                String subStr = str.substring(0,j);
                check = map.getOrDefault(subStr,false);
                if(check) return false;
            }//for j
        }//for i
        
        
        
        return true;
    }
}