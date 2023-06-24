//풀이 3으로 나눈 몫과 나머지를 2진수를 통해서 표현
import java.util.*;

class Solution {
    public String solution(int n) {

        String answer = "";
        int r = -1;

        while(n>0){
            r = n%3;//나머지가 0이면 4 ,1->1, 2->2
            n = n/3;//몫
            if(r==0) {
                answer = "4" + answer;
                n--;
            }
            else if(r==1) answer = "1" + answer;
            else if(r==2) answer = "2" + answer;
        }
        
        
        return answer;
    }
}