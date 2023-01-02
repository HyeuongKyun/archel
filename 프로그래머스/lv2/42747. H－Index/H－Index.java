//풀이 방법
//인용 횟수를 나타내는 배열를 생성 후 인용 횟수에 맞게 카운트
//논문 갯수보다 큰 인용 횟수는 최대 갯수의 자리에 카운트
//그 후 누적합을 구한 배열로 변경
//for문을 2번 도니까 최대 O(N) 약 2000
import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int nCit = citations.length;
        int[] cntArr = new int[nCit+1];
        //횟수 카운팅
        for(int i=0;i<nCit;i++){
            if(citations[i]>nCit) cntArr[nCit]++;
            else cntArr[citations[i]]++;
        }
        for(int i=nCit;i>0;i--){
            cntArr[i-1] += cntArr[i];
            if(cntArr[i]>=i) return i;
        }
        
        //0번 인용을 위해서 필요함
        int answer = 0;
        return answer;
    }
}