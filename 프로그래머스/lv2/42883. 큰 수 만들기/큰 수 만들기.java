import java.util.*;

class Solution {
    public String solution(String number, int k) {
        List<Character> list = new ArrayList<>();
        int len = number.length();
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<len;i++)
            list.add(number.charAt(i));
        
        int idx = -1;
        t : while(k>0 && idx < list.size()-1){
            idx++;
            char candidate = list.get(idx);
            for(int i=idx+1;i<=idx+k;i++){
                if(i==list.size()) break t;//앞에서 부터 없앨 수 있는건 끝난거기 때문에 여기서 끝내고 넘어가서 뒤에서 부터 없애는걸 해줘야한다.
                if(candidate<list.get(i)) {
                    k--;
                    // System.out.println(k);
                    continue t;
                }//if
            }//for
            //여기까지 잘 빠져나왔다는건 candidate가 가장 크다는 뜻
            System.out.println(idx);
            sb.append(candidate);
            
        }//while
        // System.out.println(k);
        idx++;
        while(idx<len){
            sb.append(list.get(idx++));
        }
        
        
        
        String answer = sb.toString();
        return answer;
    }
}