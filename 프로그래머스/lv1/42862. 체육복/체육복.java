import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        
        int[] arr = new int[n+1+1];//학생 번호가 1부터니까 0 버림 그리고 마지막 인덱스도 따로 처리하기 귀찮으니까 칸을 한칸 늘려줌
        for(int i=0;i<lost.length;i++)
            arr[lost[i]]--; // 잃어버린 친구들은 -1을 해줌
        
        for(int i=0;i<reserve.length;i++)
            arr[reserve[i]]++; //하나 더 가져온 친구들인 +1을 해줌
        
        for(int i=1;i<=n;i++){
            if(arr[i]==1){
                if(arr[i-1]==-1){
                    arr[i-1]++;
                    arr[i]--;
                }
                else if(arr[i+1]==-1){
                    arr[i+1]++;
                    arr[i]--;
                }
            }//if
        }
        
        int answer = 0;
        for(int i=1;i<=n;i++){
            if(arr[i]==0 || arr[i]==1) answer++;
        }
        
        
        return answer;
    }
}