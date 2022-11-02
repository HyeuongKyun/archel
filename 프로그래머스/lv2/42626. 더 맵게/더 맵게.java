import java.util.*;

class Solution {
    public int solution(int[] sc, int K) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<sc.length;i++) pq.add(sc[i]);
        
        int cnt=0;
        while(true){

           
            int temp1 = pq.poll();
            if(temp1>=K) {
                return cnt;
            }
             if(pq.isEmpty()){
               return -1;
            }
            int temp2 = pq.poll();
            int temp3 = temp1 + (temp2*2);
            pq.add(temp3);
            cnt++;      
       }//while
        
        
        
    }
}