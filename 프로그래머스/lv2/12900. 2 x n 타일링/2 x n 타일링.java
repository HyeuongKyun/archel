//풀이 방법: An = A(n-1) + A(n-2) 
//위에 것을 좀 더 자세히 말하면 An이 될 수 있는 경우는 A(n-1)까지 오고 나서 세로 하나 아니면 A(n-2)에 가로 2개 놓는 방법 뿐
import java.util.*;

class Solution {
    public int solution(int n) {
        
        //시간 초과 때문에 메모라이징
        int[] aArr = new int[n+1];
        aArr[1] = 1; aArr[2]=2;
        int answer = an(n,aArr);
        return answer;
    }
    
    public int an(int n, int[] aArr){
        if(aArr[n]==0) aArr[n]= (an(n-2, aArr)+an(n-1, aArr))%1000000007;//이게 가능한 이유 modulo 개념
        return aArr[n];
    }//an
}