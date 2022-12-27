import java.util.*;

class Solution {
    static int[] student1={1,2,3,4,5};
    static int[] student2={2,1,2,3,2,4,2,5};
    static int[] student3={3,3,1,1,2,2,4,4,5,5};
    static int[] score;
    public int[] solution(int[] answers) {
        score = new int[4];//0인덱스 버림
        
        for(int i=0;i<answers.length;i++){
            int nowAns = answers[i];
            if(nowAns==student1[i%5]) score[1]++;
            if(nowAns==student2[i%8]) score[2]++;
            if(nowAns==student3[i%10]) score[3]++;
        }//for i
        
        int maxStudent = Math.max(Math.max(score[1],score[2]),score[3]);
        List<Integer> bestScore = new ArrayList<>();
        for(int i=1;i<=3;i++)
            if(maxStudent==score[i]) bestScore.add(i);
        
        int[] answer = new int[bestScore.size()];
        
        for(int i=0;i<answer.length;i++)
            answer[i] = bestScore.get(i);
        Arrays.sort(answer);
        return answer;
    }
}