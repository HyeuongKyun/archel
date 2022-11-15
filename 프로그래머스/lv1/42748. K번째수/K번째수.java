import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int N = commands.length;
        int[] answer = new int[N];
        for(int i=0;i<N;i++){
            int from = commands[i][0]-1;
            int to = commands[i][1];
            int idx = commands[i][2];
            int[] tempArr = new int[to-from];
            for(int j=from;j<to;j++){
                int tempIdx = j-from;
                tempArr[tempIdx] = array[j];
            }
            Arrays.sort(tempArr);
            int result = tempArr[idx-1];
            answer[i] = result;
        }
        return answer;
    }
}