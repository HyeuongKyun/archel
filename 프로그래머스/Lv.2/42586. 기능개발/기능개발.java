import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        List<Integer> resultList = new ArrayList<>();
        int idx =0;
        int day =0;
        while(idx<progresses.length){
            day++;
            int cnt =0;
            while(idx<progresses.length){
                if(progresses[idx] + speeds[idx]*day >=100){
                    idx++;
                    cnt++;
                }
                else break;
            }
            if(cnt!=0) resultList.add(cnt);
        }
        int[] answer = new int[resultList.size()];
        for(int i=0;i<resultList.size();i++)
            answer[i] = resultList.get(i);
        return answer;
    }
}