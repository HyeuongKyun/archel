// 인용된 횟수기 인덱스인 배열 생성
// 0인덱스 부터 더해가면서 index가 합을 넘기는 직전이 h-index.

class Solution {
    public int solution(int[] citations) {
        
        int[] cntArr = new int[10001];

        for(int i=0;i<citations.length;i++){
            // totalCnt += citations[i];
            cntArr[citations[i]]++;
        }
        
        int totalCnt = citations.length; //총 논문 갯수
        int hIndex = 0;
        
        for(int i=0;i<cntArr.length;i++){
            // i번 인용된 논문의 총 갯수 totalCnt
            if(totalCnt>=i) hIndex = i;
            // 인용된 논문 갯수가 논문 갯수보다 작으면 h인덱스가 될 수 없다
            else break;
            // i번째 미만 인용된 논문 제외
            totalCnt -= cntArr[i];
        }
        
        int answer = hIndex;
        return answer;
    }
}