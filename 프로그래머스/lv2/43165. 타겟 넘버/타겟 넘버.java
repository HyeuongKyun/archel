import java.util.*;

class Solution {
    static boolean[] boolArr;//dfs에서 부호를 담당해줄 수
    static int N,cnt;
    static int target;
    static int[] numbers;
    
    public int solution(int[] numbers, int target) {
        
        N = numbers.length;
        boolArr = new boolean[N];
        this.target = target;
        this.numbers =numbers;
        cnt=0;
        
        dfs(0);
        // System.out.println(target);
        // System.out.println(Arrays.toString(numbers));
        
        return cnt;
    }
    static void dfs(int idx){
        if(idx==N){
            //System.out.println(Arrays.toString(boolArr));
            int sum=0;
            for(int i=0;i<N;i++){
                
                if(boolArr[i]){
                    sum -= numbers[i];
                }
                else{
                    sum += numbers[i];
                }
            }
            // System.out.println(sum);
            if(target==sum) cnt++;
            return;
        }//기저파트
        
        
        boolArr[idx] = true;
        dfs(idx+1);
        boolArr[idx] = false;
        dfs(idx+1);
        
    }
}