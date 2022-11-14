import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int N = prices.length;
        boolean[] v = new boolean[N];
        int[] answer = new int[N];
        
        for(int i=0;i<N;i++){
            int temp = prices[i];
            if(stack.isEmpty() || stack.peek()<=temp){
                stack.add(temp);
            }
            else{
                int days = 1;
                int idx = i;
                while(stack.peek()>temp){
                    --idx;
                    if(idx==-1) break;
                    if(v[idx]) {
                        days++;
                        continue;
                    }
                    // System.out.println(stack.toString());
                    // System.out.println(temp);
                    // System.out.println(Arrays.toString(answer));
                    int stackTemp = stack.pop();
                    v[idx]=true;
                    answer[idx] = days++;
                    if(stack.isEmpty()) break;
                }
                stack.add(temp);
            }//if,else
        }//for
        
        //for문 돌고 stack에 남아 있는 수만큼 끝날때 까지 안떨어진 숫자
        int days = 0;
        for(int i=N-1;i>=0;i--){
            if(!v[i])
                answer[i] = days;
            days++;
        }
        
        
        
        return answer;
    }
}