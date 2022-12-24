import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Map<Integer,Boolean> map = new HashMap<>();
        int N = nums.length;
        for(int i=0;i<N;i++){
            int kind = nums[i];
            boolean bool=map.getOrDefault(kind,false);
            if(!bool) map.put(kind,true);
        }// for i
        
        int answer = 0;
        
        int size = map.size();
        if(N/2>=size) answer = size;
        else answer = N/2;
        
        return answer;
    }
}