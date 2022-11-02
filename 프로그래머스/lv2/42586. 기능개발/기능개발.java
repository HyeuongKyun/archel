import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int N = speeds.length;
        int[] days = new int[N];
        
        for(int i=0;i<N;i++){
            int pro = progresses[i];
            int sp = speeds[i];
            int day =0;
            if((100-pro)%sp==0)
                day = (100-pro)/sp;
            else
                day = (100-pro)/sp + 1;
            days[i] = day;
        }
        System.out.println(Arrays.toString(days));
        int cnt=0;
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<N;i++){
            int temp = days[i];
            if(stack.isEmpty()) {
                stack.add(temp);
            }//if
            else{
                int peek = stack.peek();
                if(peek>=temp){
                    cnt++;
                }//if
                else{
                    stack.pop();
                    stack.add(temp);
                    list.add(++cnt);
                    cnt=0;
                }//else
            }//else
        }//for
        
        if(!stack.isEmpty()){
            cnt += stack.size();
            list.add(cnt);
        }
        System.out.println(list);
        int len = list.size();
        int[] answer = new int[len];
        for(int i=0;i<len;i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}