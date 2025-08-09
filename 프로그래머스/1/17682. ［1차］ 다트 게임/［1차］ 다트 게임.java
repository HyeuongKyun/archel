import java.util.*;

class Solution {
    List<Integer> valueList;
    public int solution(String dartResult) {
        valueList = new ArrayList<>();
        int idx=0;
        int listIdx =0;
        while(idx<dartResult.length()-1){
            char firChar = dartResult.charAt(idx);
            if(!('0' <= firChar && firChar <= '9')){
                idx++;
                continue;
            }
            char secChar = dartResult.charAt(idx+1);
            char thrChar='0';
            char fouChar='0';
            if(idx+2 < dartResult.length()) thrChar = dartResult.charAt(idx+2);
            if(idx+3 < dartResult.length()) fouChar = dartResult.charAt(idx+3);
            
            if (secChar=='0') {
                fillList(listIdx++, 10, thrChar, fouChar);
                idx = idx + 3;
            } else {
                fillList(listIdx++, firChar-'0', secChar, thrChar);
                idx = idx + 2;
            }
            
        }
        
        
        int answer = 0;
        for(Integer num : valueList) answer += num;
        return answer;
    }
    
    public void fillList(int idx, int num, char SDT, char SA){
        if(SDT =='D'){
            num = num*num;
        } else if(SDT =='T'){
            num = num*num*num;
        }
        
        if(SA == '*'){
            valueList.add(num*2);
            if(idx==0) return;
            int idxV = valueList.remove(idx-1);
            valueList.add(idx-1, idxV*2);
        } else if(SA == '#'){
            valueList.add(num*(-1));
        } else {
            valueList.add(num);
        }
    }
}